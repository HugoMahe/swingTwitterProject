package com.iup.tp.twitup.observer.user;


public interface UserObservable {

	void addObserver(UserObserver observer);
	
	void removeObserver(UserObserver observer);

}
