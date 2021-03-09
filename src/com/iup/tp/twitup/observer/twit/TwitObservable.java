package com.iup.tp.twitup.observer.twit;

public interface TwitObservable {

	void addObserver(TwitObserver observer);
	
	void removeObserver(TwitObserver observer);
	
	
}
