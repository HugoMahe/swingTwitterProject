package com.iup.tp.twitup.core;

import java.io.File;
import java.util.Properties;
import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.ListeTwit;
import com.iup.tp.twitup.datamodel.ListeUser;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.ihm.account.ProfilPageView;
import com.iup.tp.twitup.ihm.account.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.account.TwitupAccountCreationView;
import com.iup.tp.twitup.ihm.twit.TwitCreationView;
import com.iup.tp.twitup.ihm.twit.TwitFilView;
import com.iup.tp.twitup.ihm.user.UserListeView;
import com.iup.tp.twitup.observer.MainViewObserver;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.session.SessionObserver;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup implements MainViewObserver, SessionObserver {
	/**
	 * Base de donnÃ©es.
	 */
	protected IDatabaseObservable mDatabase;

	/**
	 * Gestionnaire des entitÃ©s contenu de la base de donnÃ©es.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainView mMainView;

	/**
	 * Classe de surveillance de rÃ©pertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * RÃ©pertoire d'Ã©change de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchonÃ© est activÃ©.
	 */
	protected boolean mIsMockEnabled = false;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;
	
	protected PropertiesManager propManager = new PropertiesManager();
	
	
	// CONTROLLER FILS
	protected AccountController accountController;
	protected TwitController twitController;
	private UserListeController userListeController;
	private UserItemController userItemController;
	protected TwitListenerController twitListenerController;
	
	
	// OBJET SESSION 
	protected Session session = null;
	protected File dossier = null;
	
	// CONTROLLER DE NOTIFICATION
	protected NotificationController notificationController= new NotificationController();


	

	/**
	 * Constructeur.
	 */
	public Twitup() {
		this.session = new Session();
		this.session.addObserver(this);
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de donnÃ©es
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}
		// Initialisation de l'IHM
		this.initGui();
		
		// Initialisation du rÃ©pertoire d'Ã©change
		this.initDirectory();
		
		
		this.mMainView.showGUI();
		
		// AJOUT DE L'ECOUTE DE LA MAIN VIEW SUR LE CONTROLLER DE NOTIFICATIONS
		this.notificationController.addObserver(this.mMainView);

		
		// AJOUT DU CONTROLLER QUI ECOUTE LA BASE DE DONNES DE TWIT
		this.twitListenerController = new TwitListenerController(this.session, this.notificationController);
		this.mDatabase.addObserver(twitListenerController);
		
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		this.mMainView = new TwitupMainView();
		this.mMainView.addObserver(this);
		this.mMainView.init();
	}

	/**
	 * Initialisation du rÃ©pertoire d'Ã©change (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir Ã©tÃ© saisi et Ãªtre valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	
	
	protected void initDirectory() {
		String file= getClass().getResource("/configuration.properties").getFile();
		Properties prop =  PropertiesManager.loadProperties(file);
		if(prop.getProperty("EXCHANGE_DIRECTORY").length()==0) {
			dossier = this.mMainView.askDirectory();
			if(dossier!=null) {
				this.initDirectory(dossier.getAbsolutePath());
				prop.setProperty("EXCHANGE_DIRECTORY", dossier.getAbsolutePath());
				PropertiesManager.writeProperties(prop, getClass().getResource("/configuration.properties").getFile());
			}else {
				System.out.println("ERREUR: Fermeture..." );
				System.exit(-1);
			}
		}else {
			this.initDirectory(prop.getProperty("EXCHANGE_DIRECTORY"));
		}
	}

	/**
	 * Indique si le fichier donnée est valide pour servire de répertoire
	 * d'Ã©change
	 * 
	 * @param directory
	 *            , RÃ©pertoire Ã  tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si rÃ©pertoire disponible en lecture et Ã©criture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchonné de l'application
	 */
	protected void initMock() {
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de donnÃ©es
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
	}

	/**
	 * Initialisation du répertoire d'Ã©change.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);
		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		// ... setVisible?
	}

	@Override
	public void notifyCreateAccountPage() {
		// INSTANCIATION DU CONTROLLER
	 	this.accountController = new AccountController(mDatabase, mEntityManager,this.session, notificationController);
	 	// INSTANCIATION DE LA VUE
		TwitupAccountCreationView toShow = new TwitupAccountCreationView();
		// AJOUT DES OBSERVERS
		toShow.addObserver(this.accountController);
		// DEMANDE A LA VUE D'AFFICHER LE CONTENU DE LA VIEW
		this.mMainView.showView(toShow);
	}

	@Override
	public void notifyConnectionPage() {
		if(this.accountController==null) {
		 	this.accountController = new AccountController(mDatabase, mEntityManager,this.session, notificationController);
		}
		TwitUpAccountLoginView toShow = new TwitUpAccountLoginView();
		toShow.addObserver(this.accountController);
		
		this.mMainView.showView(toShow);
		
	}

	@Override
	public void notifyCreationTwitPage() {
		this.twitController = new TwitController(this.session,this.mEntityManager,this.mDatabase,this.notificationController);
		TwitCreationView toShow = new TwitCreationView();
		toShow.addObserver(twitController);
		this.mMainView.showView(toShow);
	}
	
	@Override
	public void notifyFilTwitPage() {		
		ListeTwit twits = new ListeTwit(this.mDatabase.getTwits());
		this.twitController = new TwitController(this.session,this.mEntityManager,this.mDatabase,this.notificationController);
		this.twitController.setTwits(twits);
		
		TwitFilView ftv = new TwitFilView(twits);
		ftv.addObserver(this.twitController);
		
		this.mMainView.showView(ftv);
	}

	@Override
	public void notifyModificationSession(User user) {
		if(user!=null) {
			this.session.setUser(user);
			System.out.println("Il est connecte");
			this.mMainView.printAccountButton();
		}
		this.mMainView.repaint();
	}

	@Override
	public void notifyDeconnection() {
		this.session=new Session();
		this.session.addObserver(this);
		this.mMainView.session=this.session;
		this.mMainView.hideAccountButton();
		this.mMainView.repaint();
		this.notifyConnectionPage();
	}

	@Override
	public void notifyPrintAllAccountPage() {		
		ListeUser listeUsers = new ListeUser(this.mDatabase.getUsers());
		this.userListeController = new UserListeController(this.mDatabase);
		this.userItemController = new UserItemController(this.session, this.mEntityManager);
		this.userListeController.setUsers(listeUsers);
		
		UserListeView ufv = new UserListeView(listeUsers, this.userItemController, this.session);
		ufv.addObserver(this.userListeController);
		
		this.mMainView.showView(ufv);
	}

	@Override
	public void notifyProfilPage() {
		ProfilPageView ppv = new ProfilPageView(this.session);	
		this.mMainView.showView(ppv);
	}

	
}
