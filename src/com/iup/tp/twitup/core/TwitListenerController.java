package com.iup.tp.twitup.core;

import com.iup.tp.twitup.common.INotifier;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.database.IDatabaseObserver;

public class TwitListenerController implements IDatabaseObserver {
	
	protected Session session;
	INotifier controllerNotification;
	
	public TwitListenerController(Session session, INotifier controllerNotification) {
		this.session=session;
		this.controllerNotification=controllerNotification;
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println("le tweet " + addedTwit.getText() );
		System.out.println("[CONTROLLEUR TWIT LISTENER] - AJOUT DU TWEET");
		User user = addedTwit.getTwiter();
		for(String userIt : this.session.getUser().getFollows()) {
			if(user.getUserTag().equals(userIt)) {
				this.controllerNotification.envoyerNotification(user.getUserTag() + " A ajouté un tweet", false);
			}
		}
		
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub

		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}
