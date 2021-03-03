package com.iup.tp.twitup.observer;

public interface TwitObservable {

	void addObserver(TwitObserver observer);
	
	void removeObservers(TwitObserver observer);
	
	
}
