package com.iup.tp.twitup.core;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.account.TwitUpAccountLoginView;
import com.iup.tp.twitup.ihm.twit.TwitCreationView;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.twit.TwitObserver;

public class TwitController implements TwitObserver {
	private EntityManager eM;
	private Session session; 
	
	public TwitController(Session session, EntityManager eMParam) {
		// TODO Auto-generated constructor stub
		this.session=session;
		this.eM=eMParam;
	}

	@Override
	public void notifyCreateTwit(String text) {
		System.out.println("creation d'un twit reçu sur le controller" + text);
		Twit twit = new Twit(this.session.getUser(), text);
		this.eM.sendTwit(twit);
		System.out.println("CONTROLLER : Twit envoyé" );
	}
	

}
