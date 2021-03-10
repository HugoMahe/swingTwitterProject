package com.iup.tp.twitup.core;


import com.iup.tp.twitup.common.INotifier;
import com.iup.tp.twitup.datamodel.ListeTwit;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.database.IDatabaseObservable;
import com.iup.tp.twitup.observer.twit.TwitObserver;

public class TwitController implements TwitObserver {
	private EntityManager eM;
	private Session session; 
	private IDatabaseObservable database;
	private ListeTwit twits;
	protected INotifier controllerNotification;

	
	public TwitController(Session session, EntityManager eMParam, IDatabaseObservable databaseParam, INotifier controllerNotification) {
		// TODO Auto-generated constructor stub
		this.session=session;
		this.eM=eMParam;
		this.database=databaseParam;
		this.controllerNotification=controllerNotification;
	}

	@Override
	public void notifyCreateTwit(String text) {
		System.out.println("creation d'un twit reçu sur le controller" + text.length());
		if(text.length()<=250) {
			Twit twit = new Twit(this.session.getUser(), text);
			this.eM.sendTwit(twit);
			System.out.println("CONTROLLER : Twit envoyé" );
			this.controllerNotification.envoyerNotification("Twit envoyé", false);
		}else {
			System.out.println("erreur tweet trop long");
			this.controllerNotification.envoyerNotification("Tweet trop long",true);
		}
	}
	
	
	@Override
	public void notifyFiltreFil(String tag) {
		if (tag.isEmpty()) {
			twits.setTwits(database.getTwits());
		}
		else if (tag.substring(0,1).equals("#")) {
			twits.setTwits(database.getTwitsWithTag(tag.substring(1)));
		}
		else if (tag.substring(0,1).equals("@")) {
			twits.setTwits(database.getTwitsWithUserTag(tag.substring(1)));
			User user = database.getUserBytag(tag.substring(1));
			twits.addTwits(database.getUserTwits(user));
		}
		else {
			twits.setTwits(database.getTwitsWithTag(tag));
			twits.addTwits(database.getTwitsWithUserTag(tag));
			User user = database.getUserBytag(tag);
			twits.addTwits(database.getUserTwits(user));
		}
	}

	public void setTwits(ListeTwit twits) {
		this.twits = twits;
	}

}
