package com.iup.tp.twitup.observer.notification;

public interface NotificationMessageSendObservable {
	
	void addObserver(NotificationMessageSendObserver observer);
	
	void removeObserver(NotificationMessageSendObserver observer);

}
