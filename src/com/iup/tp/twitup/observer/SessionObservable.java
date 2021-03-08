package com.iup.tp.twitup.observer;

public interface SessionObservable {
	
	void addObserver(SessionObserver observer);
	
	void removeObserver(SessionObserver observer);

}
