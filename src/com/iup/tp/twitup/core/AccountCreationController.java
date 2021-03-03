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
	
	public AccountCreationController(TwitupAccountCreationView view, IDatabase database, EntityManager Em) {
		this.view=view;
		this.view.addObserver(this);
		this.database= database;
		this.eM=Em;
	}

	@Override
	public void notifyCreateAccount() {
		System.out.println("CONTROLLER -> CREATION ACCOUNT :" + this.view.getLoginText() + "--" + this.view.getMdpText() + " ---" + this.view.getNameText());
		if(this.view.getLoginText()!="" && this.view.getMdpText()!="" && this.view.getNameText()!="") {
			System.out.println("pas vide");
			User user = new User(UUID.randomUUID(), this.view.getLoginText(), this.view.getMdpText(), this.view.getNameText(), new HashSet<String>(), null);
			this.eM.sendUser(user);
		}

	}

	@Override
	public void notifyAccountConnection() {
		// TODO Auto-generated method stub
		
	}
}
