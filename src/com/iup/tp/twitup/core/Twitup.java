package com.iup.tp.twitup.core;

import java.io.File;

import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitCreationView;
import com.iup.tp.twitup.ihm.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.TwitupAccountCreationView;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;
import com.iup.tp.twitup.observer.MainViewObserver;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup implements MainViewObserver {
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
	
	protected File dossier = null;

	/**
	 * Constructeur.
	 */
	public Twitup() {
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
		this.mDatabase.addObserver(mMainView);

		// initialisation du controller enfant
		
		
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
		dossier = this.mMainView.askDirectory();
		if(dossier!=null) {
			this.initDirectory(dossier.getAbsolutePath());
		 	/*TwitupAccountCreationView taccv =  this.mMainView.drawAccountCreationView();
		 	this.accountController = new AccountCreationController( taccv, mDatabase );*/
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
	public void notifyCreateAccount() {
		if(this.accountLoginController!=null) {
			this.accountLoginController=null;
		}
		System.out.println("ouverture du menu de creation de compte");
		TwitupAccountCreationView taccv =  this.mMainView.drawAccountCreationView();
	 	this.accountController = new AccountCreationController( taccv, mDatabase, mEntityManager);
	}

	@Override
	public void notifyConnection() {
		if(this.accountController!=null) {
			this.accountController=null;
		}
		System.out.println("ouverture du menu de connection de compte");
		TwitUpAccountLoginView taclv =  this.mMainView.drawAccountLoginView();
	 	this.accountLoginController = new AccountLoginController( taclv, mDatabase, mEntityManager);
	}

	@Override
	public void notifyCreationTwit() {
		System.out.println("lancement de la creation de twit");
		if(this.accountController!=null) {
			this.accountController=null;
		}
		if(this.accountLoginController!=null) {
			this.accountLoginController=null;
		}
		TwitCreationView tcv = this.mMainView.drawTwitCreationView();
		this.twitController = new TwitController();
		
	}
	
}
