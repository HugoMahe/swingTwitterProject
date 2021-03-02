package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.TwitupAccountCreationView;
import com.iup.tp.twitup.ihm.viewObserver;

public class AccountLoginController implements viewObserver {
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
	public void notifyConnection() {
		// TODO Auto-generated method stub
		System.out.println("CONTROLLER -> CONNECTION ACCOUNT");
	}

}
