package com.iup.tp.twitup.observer.notification;

public interface NotificationSendMessageObserver {

	void notifyNotificationReceived(String message, Boolean error);
}
