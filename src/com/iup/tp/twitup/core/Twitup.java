package com.iup.tp.twitup.core;

import java.io.File;
import java.util.Properties;

import javax.swing.JPanel;

import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.Database;
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
	
	protected AccountController accountController;
	protected TwitController twitController;
	protected Session session = null;
	protected File dossier = null;

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
		/*dossier = this.mMainView.askDirectory();
		if(dossier!=null) {
			this.initDirectory(dossier.getAbsolutePath());
		}else {
			System.out.println("ERREUR: Fermeture..." );
			System.exit(-1);
		}*/
	}

	/**
	 * Initialisation du rÃ©pertoire d'Ã©change (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir Ã©tÃ© saisi et Ãªtre valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	
	
	protected void initDirectory() {
		//System.out.println(System.getProperty("sun.arch.data.model"));
		System.out.println("ressource properties " + getClass().getResource("/configuration.properties"));
		Properties prop =  this.propManager.loadProperties(getClass().getResource("/configuration.properties").getFile());
		System.out.println(prop);
		System.out.println("la " + prop.getProperty("EXCHANGE_DIRECTORY"));
		if(prop.getProperty("EXCHANGE_DIRECTORY").length()==0) {
			dossier = this.mMainView.askDirectory();
			if(dossier!=null) {
				this.initDirectory(dossier.getAbsolutePath());
				prop.setProperty("EXCHANGE_DIRECTORY", dossier.getAbsolutePath());
				propManager.writeProperties(prop, getClass().getResource("/configuration.properties").getFile());
			}else {
				System.out.println("ERREUR: Fermeture..." );
				System.exit(-1);
			}
		}else {
			this.initDirectory(prop.getProperty("EXCHANGE_DIRECTORY"));
		}
	}

	/**
	 * Indique si le fichier donnÃ© est valide pour servire de rÃ©pertoire
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
	 * Initialisation du mode bouchonÃ© de l'application
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
	 * Initialisation du rÃ©pertoire d'Ã©change.
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
		System.out.println("ouverture du menu de creation de compte");
		// INSTANCIATION DU CONTROLLER
	 	this.accountController = new AccountController(mDatabase, mEntityManager,this.session);
	 	// INSTANCIATION DE LA VUE
		TwitupAccountCreationView toShow = new TwitupAccountCreationView();
		// AJOUT DES OBSERVERS
		toShow.addObserver(this.accountController);
		
		// DEMANDE A LA VUE D'AFFICHER LE CONTENU DE LA VIEW
		this.mMainView.showView(toShow);
	}

	@Override
	public void notifyConnectionPage() {
		System.out.println("ouverture du menu de connection de compte");
		if(this.accountController==null) {
		 	this.accountController = new AccountController(mDatabase, mEntityManager,this.session);
		}
		TwitUpAccountLoginView toShow = new TwitUpAccountLoginView();
		toShow.addObserver(this.accountController);
		
		this.mMainView.showView(toShow);
	}

	@Override
	public void notifyCreationTwitPage() {
		System.out.println("lancement de la creation de la page de twit");
		this.twitController = new TwitController(this.session,this.mEntityManager);
		TwitCreationView toShow = new TwitCreationView();
		toShow.addObserver(twitController);
		
		this.mMainView.showView(toShow);
	}
	
	@Override
	public void notifyFilTwitPage() {
		System.out.println("lancement de l'affichage du fil de twit");
		
		// IL Y A UN CONTROLLER ASOCIE ? (VOIR AVEC LEO) 
		TwitFilView ftv = new TwitFilView(this.mDatabase.getTwits());
		this.mMainView.showView(ftv);
	}

	@Override
	public void notifyModificationSession(User user) {
		if(user!=null) {
			this.session.setUser(user);
			System.out.println("il est connecté");
			this.mMainView.printAccountButton();
		}
		this.mMainView.repaint();
	}

	@Override
	public void notifyDeconnection() {
		this.session=new Session();
		this.mMainView.session=this.session;
		this.mMainView.hideAccountButton();
		this.mMainView.repaint();
	}

	@Override
	public void notifyPrintAllAccountPage() {
		System.out.println("creation de la page d'affiche des comptes");
		
		
	}

	@Override
	public void notifyProfilPage() {
		System.out.println("affichage du profil");
		ProfilPageView ppv = new ProfilPageView(this.session);
		
		this.mMainView.showView(ppv);
	}
	
}
