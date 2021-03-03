package com.iup.tp.twitup.observer;

public interface AccountObservable {

	void addObserver(AccountObserver observer);
	
	void removeObservers(AccountObserver observer);
}
