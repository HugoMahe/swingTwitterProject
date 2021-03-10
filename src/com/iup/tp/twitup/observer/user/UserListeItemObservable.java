package com.iup.tp.twitup.observer.user;

public interface UserListeItemObservable {

	void addObserver(UserListeItemObserver observer);
	
	void removeObserver(UserListeItemObserver observer);
}
