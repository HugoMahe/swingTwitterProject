package com.iup.tp.twitup.datamodel;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.observer.session.SessionObservable;
import com.iup.tp.twitup.observer.session.SessionObserver;

public class Session implements SessionObservable {

	protected User user; 
	public Set<SessionObserver> sObservers = new HashSet<SessionObserver>();
	
	@Override
	public void addObserver(SessionObserver observer) {
		// TODO Auto-generated method stub
		this.sObservers.add(observer);
	}

	@Override
	public void removeObserver(SessionObserver observer) {
		// TODO Auto-generated method stub
		this.sObservers.remove(observer);
	}

	public User getUser() {
		return this.user;
	}
	
	public void setUser(User userParam) {
		this.user=userParam;
	}
}
