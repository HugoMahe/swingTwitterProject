package com.iup.tp.twitup.core;

import com.iup.tp.twitup.common.Session;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.TwitupAccountCreationView;
import com.iup.tp.twitup.observer.AccountObserver;
import com.iup.tp.twitup.observer.MainViewObserver;

public class AccountLoginController implements AccountObserver {
	TwitUpAccountLoginView view;
	private IDatabase database;
	private EntityManager eM;

	public AccountLoginController(TwitUpAccountLoginView view, IDatabase database, EntityManager Em) {
		this.view=view;
		this.view.addObserver(this);
		this.database= database;
		this.eM=Em;
	}
	
	@Override
	public void notifyCreateAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyAccountConnection() {
		// TODO Auto-generated method stub
		System.out.println("CONTROLLER -> CONNECTION ACCOUNT");
		System.out.println("Tentative de connection");
		User user = this.database.getUser(this.view.getLoginTxt(),this.view.getMdpTxt());
		if(user!=null) {
			System.out.println("creation de la session");
			Session session = new Session();
		}
	}

}
