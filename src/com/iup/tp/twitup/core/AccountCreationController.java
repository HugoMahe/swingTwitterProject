package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.TwitupAccountCreationView;
import com.iup.tp.twitup.ihm.viewObserver;

public class AccountCreationController implements viewObserver{
	TwitupAccountCreationView view;
	private IDatabase database;
	
	public AccountCreationController(TwitupAccountCreationView view, IDatabase database) {
		this.view=view;
		this.view.addObserver(this);
		this.database= database;
	}

	@Override
	public void notifyCreateAccount() {
		System.out.println("CONTROLLER -> CREATION ACCOUNT :" + this.view.getLoginText() + "--" + this.view.getMdpText() + " ---" + this.view.getNameText());
		User user = new User(null, this.view.getLoginText(), this.view.getMdpText(), this.view.getNameText(), null, null);
		//this.database.addUser(userToAdd);
	}
}
