package com.iup.tp.twitup.observer.notification;

public interface NotificationSendMessageObservable {

	void addObserver(NotificationSendMessageObserver observer);
	
	void removeObserver(NotificationSendMessageObserver observer);
}
