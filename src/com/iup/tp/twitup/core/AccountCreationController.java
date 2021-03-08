package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupAccountCreationView;
import com.iup.tp.twitup.observer.AccountObserver;
import com.iup.tp.twitup.observer.MainViewObserver;

public class AccountCreationController implements AccountObserver{
	TwitupAccountCreationView view;
	private IDatabase database;
	private EntityManager eM;
	
	public AccountCreationController(IDatabase database, EntityManager Em) {
		this.database= database;
		this.eM=Em;
	}

	@Override
	public void notifyCreateAccount(String login, String mdp, String name) {
		System.out.println("CONTROLLER -> CREATION ACCOUNT :" + login + "--" + mdp + " ---" + name);
		if(login!="" && mdp!="" && name!="") {
			System.out.println("pas vide");
			User user = new User(UUID.randomUUID(), login, mdp, name, new HashSet<String>(), null);
			this.eM.sendUser(user);
		}
	}

	@Override
	public void notifyAccountConnection(String login, String mdp) {
		// TODO Auto-generated method stub
		
	}
}
