package com.iup.tp.twitup.core;

import java.io.File;

import javax.swing.JPanel;

import com.iup.tp.twitup.common.Session;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitCreationView;
import com.iup.tp.twitup.ihm.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.TwitupAccountCreationView;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.observer.MainViewObserver;
import com.iup.tp.twitup.observer.SessionObserver;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup implements MainViewObserver, SessionObserver {
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitupMainView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = false;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;
	
	protected AccountCreationController accountController;
	protected AccountLoginController accountLoginController;
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

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		}
		// Initialisation de l'IHM
		this.initGui();
		
		// Initialisation du répertoire d'échange
		//this.initDirectory();
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
		//this.mMainView.session=this.session;
		dossier = this.mMainView.askDirectory();
		if(dossier!=null) {
			this.initDirectory(dossier.getAbsolutePath());
		}else {
			System.out.println("ERREUR: Fermeture..." );
			System.exit(-1);
		}
		this.mMainView.showGUI();
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	protected void initDirectory() {
	}

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 * 
	 * @param directory
	 *            , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mEntityManager = new EntityManager(mDatabase);
	}

	/**
	 * Initialisation du répertoire d'échange.
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
//		this.emptyController();
		System.out.println("ouverture du menu de creation de compte");
		// INSTANCIATION DU CONTROLLER
	 	this.accountController = new AccountCreationController(mDatabase, mEntityManager);
	 	// INSTANCIATION DE LA VUE
		TwitupAccountCreationView toShow = new TwitupAccountCreationView();
		// AJOUT DES OBSERVERS
		toShow.addObserver(this.accountController);
		
		// DEMANDE A LA VUE D'AFFICHER LE CONTENU DE LA VIEW
		this.mMainView.showView(toShow);
	}

	@Override
	public void notifyConnectionPage() {
//		this.emptyController();
		System.out.println("ouverture du menu de connection de compte");
	 	this.accountLoginController = new AccountLoginController(mDatabase, mEntityManager,this.session);
		TwitUpAccountLoginView toShow = new TwitUpAccountLoginView();
		toShow.addObserver(accountLoginController);
		
		this.mMainView.showView(toShow);
	}
	
	

	@Override
	public void notifyCreationTwitPage() {
		System.out.println("lancement de la creation de la page de twit");
//		this.emptyController();
		this.twitController = new TwitController();
		TwitCreationView toShow = new TwitCreationView();
		
		this.mMainView.showView(toShow);
	}
	
	
	
	/*public void emptyController() {
		if(this.accountController!=null) {
			this.accountController.view.removeObservers(this.accountController);
			this.accountController=null;
		}
		if(this.accountLoginController!=null) {
			this.accountLoginController.view.removeObservers(this.accountLoginController);
			this.accountLoginController=null;
		}
		if(this.twitController!=null) {
			this.twitController.view.removeObserver(this.twitController);
			this.twitController=null;
		}
	}*/

	@Override
	public void notifyModificationSession(User user) {
		if(user!=null) {
			this.session.setUser(user);
			//this.mMainView.printAccountButton();
		}
		this.mMainView.repaint();
	}

	@Override
	public void notifyDeconnection() {
		this.session=new Session();
		this.mMainView.session=this.session;
		this.mMainView.repaint();
	}

	@Override
	public void notifyPrintAllAccountPage() {
		System.out.println("creation de la page d'affiche des comptes");
		
	}
	
}
