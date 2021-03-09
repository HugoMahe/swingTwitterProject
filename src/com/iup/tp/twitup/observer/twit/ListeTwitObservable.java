package com.iup.tp.twitup.observer.twit;

public interface ListeTwitObservable {

	void addObserver(ListeTwitObserver observer);
	
	void removeObserver(ListeTwitObserver observer);
	
	
}
