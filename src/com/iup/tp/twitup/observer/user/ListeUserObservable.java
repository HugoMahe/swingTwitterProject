package com.iup.tp.twitup.observer.user;

public interface ListeUserObservable {

	void addObserver(ListeUserObserver observer);
	
	void removeObserver(ListeUserObserver observer);
}
