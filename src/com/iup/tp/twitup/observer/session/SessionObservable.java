package com.iup.tp.twitup.observer.session;

public interface SessionObservable {
	
	void addObserver(SessionObserver observer);
	
	void removeObserver(SessionObserver observer);

}
