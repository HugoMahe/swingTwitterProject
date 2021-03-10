package com.iup.tp.twitup.observer;

public interface NotificationObservable {

	void addObserver(NotificationObserver observer);
	
	void removeObserver(NotificationObserver observer);
}
