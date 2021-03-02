package com.iup.tp.twitup.ihm;

public interface TwitObservable {

	void addObserver(TwitObserver observer);
	
	void removeObservers(TwitObserver observer);
	
	
}
