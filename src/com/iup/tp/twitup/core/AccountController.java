package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.account.TwitupAccountCreationView;
import com.iup.tp.twitup.observer.MainViewObserver;
import com.iup.tp.twitup.observer.account.AccountObserver;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.session.SessionObserver;

public class AccountController implements AccountObserver{
	TwitupAccountCreationView view;
	private IDatabaseObservable database;
	private EntityManager eM;
	protected Session session; 
	
	public AccountController(IDatabaseObservable database, EntityManager Em, Session session) {
		this.database= database;
		this.eM=Em;
		this.session=session;
	}

	@Override
	public void notifyCreateAccount(String tag, String mdp, String confirmation, String name, String avatar) {
		/*System.out.println("CONTROLLER -> CREATION ACCOUNT :" + login + "--" + mdp + " ---" + name);
		if(login!="" && mdp!="" && name!="") {
			System.out.println("pas vide");
			User user = new User(UUID.randomUUID(), login, mdp, name, new HashSet<String>(), null);
			this.eM.sendUser(user);
		}*/
		
		String erreur = "";
		if (tag == null) {
			erreur += "Tag null\n";
		} 
		else if (tag.isEmpty()) {
			erreur += "Tag vide\n";
		}
		else if (this.database.getUserBytag(tag) != null) {
			erreur += "Tag déjà utilisé\n";
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
			erreur += "Confirmation différente du mot de passe\n";
		}
		
		if (name == null) {
			erreur += "Nom null\n";
		} 
		else if (name.isEmpty()) {
			erreur += "Nom vide\n";
		}
		if (erreur.contentEquals("")) {
			User newUser = new User(UUID.randomUUID(), tag, mdp, name, new HashSet<String>(), avatar);
			// Ajout de l'utilisateur à la base
			this.eM.sendUser(newUser);
		}else {
			System.out.println("L'user n'a pas pu être ajouté en base :");
			System.out.print(erreur);
		}
	}

	@Override
	public void notifyAccountConnection(String tag, String mdp) {
		/*System.out.println("CONTROLLER -> CONNECTION ACCOUNT");
		System.out.println("Tentative de connection");
		User user = this.database.getUser(login,mdp);
		if(user!=null) {
			System.out.println("creation de la session");
			for(SessionObserver s : this.session.sObservers) {
				s.notifyModificationSession(user);
			}
		}*/
		User user = this.database.getUserBytag(tag);
		if (user != null) {
			if (user.getUserPassword().equals(mdp)) {
				System.out.println(tag + " trouvé");
				System.out.println("creation de la session");
				if(this.session!=null) {
					for(SessionObserver so :  this.session.sObservers) {
						so.notifyModificationSession(user);
					}
				}
			}
			else {
				System.out.println("Mot de passe incorrect");
			}
		}
		else {
			System.out.println("Utilisateur non trouvé");
		}
	}
}
