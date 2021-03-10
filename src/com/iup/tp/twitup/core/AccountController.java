package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.UUID;

import com.iup.tp.twitup.common.INotifier;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.account.TwitupAccountCreationView;
import com.iup.tp.twitup.observer.account.AccountObserver;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.session.SessionObserver;

public class AccountController implements AccountObserver{
	TwitupAccountCreationView view;
	private IDatabaseObservable database;
	private EntityManager eM;
	protected Session session;
	protected INotifier controllerNotification;
	
	public AccountController(IDatabaseObservable database, EntityManager Em, Session session, INotifier notificationParam) {
		this.database= database;
		this.eM=Em;
		this.session=session;
		this.controllerNotification=notificationParam;
	}

	/**
	 * Vérification des informations de création de compte
	 */
	@Override
	public void notifyCreateAccount(String tag, String mdp, String confirmation, String name, String avatar) {
		String erreur = "";
		if (tag == null) {
			erreur += "Tag null \n";
		} 
		else if (tag.isEmpty()) {
			erreur += "Tag vide \n";
		}
		else if (this.database.getUserBytag(tag) != null) {
			erreur += "Tag déjà  utilisé\n";
		}

		if (mdp == null) {
			erreur += "Mot de passe null\n";
		} 
		else if (mdp.isEmpty()) {
			erreur += "Mot de passe vide\n";
		}
		
		if (confirmation == null) {
			erreur += "Confirmation null\n";
		} 
		else if (confirmation.isEmpty()) {
			erreur += "Confirmation vide\n";
		}
		else if (!mdp.equals(confirmation)) {
			erreur += "Confirmation diffÃ©rente du mot de passe\n";
		}
		
		if (name == null) {
			erreur += "Nom null\n";
		} 
		else if (name.isEmpty()) {
			erreur += "Nom vide\n";
		}
		if (erreur.contentEquals("")) {
			User newUser = new User(UUID.randomUUID(), tag, mdp, name, new HashSet<String>(), avatar);
			// Ajout de l'utilisateur Ã  la base
			this.eM.sendUser(newUser);
			this.controllerNotification.envoyerNotification("Compte ajouté", false);
		}else {
			System.out.println("L'user n'a pas pu être ajouté en base :");
			this.controllerNotification.envoyerNotification(erreur,true);
			System.out.print(erreur);
		}
	}

	/**
	 * Verification des identifiants de connection -> instanciation de la session
	 */
	@Override
	public void notifyAccountConnection(String tag, String mdp) {
		User user = this.database.getUserBytag(tag);
		if (user != null) {
			if (user.getUserPassword().equals(mdp)) {
				System.out.println(tag + " trouvé");
				System.out.println("création de la session");
				this.controllerNotification.envoyerNotification("Connecté", false);
				if(this.session!=null) {
					System.out.println("ici debug");
					for(SessionObserver so :  this.session.sObservers) {
						so.notifyModificationSession(user);
					}
				}
			}
			else {
				System.out.println("Mot de passe incorrect");
				this.controllerNotification.envoyerNotification("Mot de passe incorrect",true);
			}
		}
		else {
			System.out.println("Utilisateur non trouvé");
			this.controllerNotification.envoyerNotification("Utilisation non trouvé",true);
		}
	}
}
