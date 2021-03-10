package com.iup.tp.twitup.datamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.observer.user.ListeUserObservable;
import com.iup.tp.twitup.observer.user.ListeUserObserver;

public class ListeUser implements ListeUserObservable {
	protected List<User> users;
	protected Set<ListeUserObserver> observers;

	public ListeUser(Set<User> users) {
		super();
		this.observers = new HashSet<>();
		this.users = new ArrayList<>();
		this.users.addAll(users);
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = new ArrayList<>();
		this.users.addAll(users);
		this.notifierMiseAJour();
	}

	public void addUsers(Set<User> users) {
		this.users.addAll(users);
		this.notifierMiseAJour();
	}
	
	private void notifierMiseAJour() {
		for (ListeUserObserver observer : this.observers) {
			observer.notifyMiseAJour(this.users);
		}
	}
	
	@Override
	public void addObserver(ListeUserObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(ListeUserObserver observer) {
		this.observers.remove(observer);
	}

}
