package com.iup.tp.twitup.observer.notification;

public interface NotificationObservable {

	void addObserver(NotificationObserver observer);
	
	void removeObserver(NotificationObserver observer);
}
