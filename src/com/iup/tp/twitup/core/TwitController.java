package com.iup.tp.twitup.core;


import com.iup.tp.twitup.datamodel.ListeTwit;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.twit.TwitObserver;

public class TwitController implements TwitObserver {
	private EntityManager eM;
	private Session session; 
	private IDatabaseObservable database;
	private ListeTwit twits;

	
	public TwitController(Session session, EntityManager eMParam, IDatabaseObservable databaseParam) {
		// TODO Auto-generated constructor stub
		this.session=session;
		this.eM=eMParam;
		this.database=databaseParam;
	}

	@Override
	public void notifyCreateTwit(String text) {
		System.out.println("creation d'un twit reçu sur le controller" + text);
		Twit twit = new Twit(this.session.getUser(), text);
		this.eM.sendTwit(twit);
		System.out.println("CONTROLLER : Twit envoyé" );
	}
	
	
	@Override
	public void notifyFiltreFil(String tag) {
		if (tag.equals("")) {
			database.getTwits();
		}
		else if (tag.substring(0,0).equals("#")) {
			System.out.println(tag.substring(1));
			twits.setTwits(database.getTwitsWithTag(tag.substring(1)));
		}
		else if (tag.substring(0,0).equals("@")) {
			twits.setTwits(database.getTwitsWithUserTag(tag.substring(1)));
		}
		else {
			System.out.println(tag);
			twits.setTwits(database.getTwitsWithTag(tag));
			twits.addTwits(database.getTwitsWithUserTag(tag));
		}
	}

	public void setTwits(ListeTwit twits) {
		this.twits = twits;
	}

}
