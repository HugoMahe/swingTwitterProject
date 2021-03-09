package com.iup.tp.twitup.core;

import javax.swing.JPanel;

import com.iup.tp.twitup.ihm.account.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.twit.TwitCreationView;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.twit.TwitObserver;

public class TwitController implements TwitObserver {
	TwitCreationView view;
	private IDatabaseObservable database;
	private EntityManager eM;
	
	public TwitController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void notifyCreateTwit() {
		// TODO Auto-generated method stub
		System.out.println("creation d'un twit");
		this.view.addObserver(this);
	}
	

}
