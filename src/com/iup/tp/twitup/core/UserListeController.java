package com.iup.tp.twitup.core;


import com.iup.tp.twitup.datamodel.ListeUser;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.user.UserObserver;

public class UserListeController implements UserObserver {
	private IDatabaseObservable database;
	private ListeUser listeUser;

	
	public UserListeController(IDatabaseObservable databaseParam) {
		this.database=databaseParam;
	}
	
	
	@Override
	public void notifyFiltreFil(String tag) {
		listeUser.setUsers(database.getUsersByTag(tag));
	}

	public void setUsers(ListeUser users) {
		this.listeUser = users;
	}
}
