package com.iup.tp.twitup.observer;

public interface MainViewObservable {

	
	void addObserver(MainViewObserver observer);
	
	void removeObserver(MainViewObserver observer);
	
	
}
