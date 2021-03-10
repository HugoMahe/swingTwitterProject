package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.common.INotifier;
import com.iup.tp.twitup.observer.notification.NotificationSendMessageObservable;
import com.iup.tp.twitup.observer.notification.NotificationSendMessageObserver;

public class NotificationController implements INotifier, NotificationSendMessageObservable {

	protected  Set<NotificationSendMessageObserver> notifObservers = new HashSet<NotificationSendMessageObserver>();

	
	@Override
	public void envoyerNotification(String message) {
		// TODO Auto-generated method stub
		for(NotificationSendMessageObserver notificationObserver : this.notifObservers) {
			notificationObserver.notifyNotificationReceived(message);
		}
	}

	@Override
	public void addObserver(NotificationSendMessageObserver observer) {
		this.notifObservers.add(observer);
	}

	@Override
	public void removeObserver(NotificationSendMessageObserver observer) {
		this.notifObservers.remove(observer);
	}

}
