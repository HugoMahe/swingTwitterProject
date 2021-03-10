package com.iup.tp.twitup.observer.user;


public interface UserListeViewObservable {

	void addObserver(UserListeViewObserver observer);
	
	void removeObserver(UserListeViewObserver observer);

}
