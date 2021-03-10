package com.iup.tp.twitup.observer.session;

import com.iup.tp.twitup.datamodel.User;

public interface SessionObserver {

	void notifyModificationSession(User user);
	
}
