package com.iup.tp.twitup.core;

import com.iup.tp.twitup.common.Session;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.TwitupAccountCreationView;
import com.iup.tp.twitup.observer.AccountObserver;
import com.iup.tp.twitup.observer.MainViewObserver;
import com.iup.tp.twitup.observer.SessionObserver;

public class AccountLoginController implements AccountObserver {
	private IDatabase database;
	private EntityManager eM;
	protected Session session; 

	public AccountLoginController(IDatabase database, EntityManager Em, Session session ) {
		this.database= database;
		this.eM=Em;
		this.session=session;
	}
	
	@Override
	public void notifyCreateAccount(String login, String mdp, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyAccountConnection(String login, String mdp){
		System.out.println("CONTROLLER -> CONNECTION ACCOUNT");
		System.out.println("Tentative de connection");
		User user = this.database.getUser(login,mdp);
		if(user!=null) {
			System.out.println("creation de la session");
			for(SessionObserver s : this.session.sObservers) {
				s.notifyModificationSession(user);
			}
		}
	}

}
