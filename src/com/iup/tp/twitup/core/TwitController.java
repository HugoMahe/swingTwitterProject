package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.TwitCreationView;
import com.iup.tp.twitup.ihm.TwitUpAccountLoginView;
import com.iup.tp.twitup.observer.TwitObserver;

public class TwitController implements TwitObserver {
	TwitCreationView view;
	private IDatabase database;
	private EntityManager eM;
	
	@Override
	public void notifyCreateTwit() {
		// TODO Auto-generated method stub
		System.out.println("creation d'un twit");
	}
	

}
