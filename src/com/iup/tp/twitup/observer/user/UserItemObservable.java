package com.iup.tp.twitup.observer.user;

public interface UserItemObservable {
	
	void addObserver(UserItemObserver observer);
	
	void removeObserver(UserItemObserver observer);
}
