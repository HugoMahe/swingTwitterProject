package com.iup.tp.twitup.core;

import com.iup.tp.twitup.common.INotifier;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.database.IDatabaseObserver;

public class TwitListenerController implements IDatabaseObserver {
	
	public TwitListenerController(Session session, INotifier controllerNotification) {
		
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println("[CONTROLLEUR TWIT LISTENER] - AJOUT DU TWEET");
		
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		System.out.println("[CONTROLLEUR TWIT LISTENER] - DELETE DU TWEET");
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		System.out.println("[CONTROLLEUR TWIT LISTENER] - MODIFICATION DU TWEET");
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		System.out.println("[CONTROLLEUR LISTENER] - UTILISATEUR AJOUTE");
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		System.out.println("[CONTROLLEUR LISTENER] - UTILISATEUR DELETE");
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}
