package com.iup.tp.twitup.observer.session;

public interface FollowObservable {

	void addObserver(SessionObserver observer);
	
	void removeObserver(SessionObserver observer);
}
