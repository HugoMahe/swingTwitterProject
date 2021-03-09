package com.iup.tp.twitup.observer.account;

public interface AccountObservable {

	void addObserver(AccountObserver observer);
	
	void removeObservers(AccountObserver observer);
}
