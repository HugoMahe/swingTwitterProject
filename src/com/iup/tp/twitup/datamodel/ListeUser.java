package com.iup.tp.twitup.datamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.observer.user.UserListeItemObservable;
import com.iup.tp.twitup.observer.user.UserListeItemObserver;

public class ListeUser implements UserListeItemObservable {
	protected List<User> users;
	protected Set<UserListeItemObserver> observers;

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
		for (UserListeItemObserver observer : this.observers) {
			observer.notifyMiseAJour(this.users);
		}
	}
	
	@Override
	public void addObserver(UserListeItemObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(UserListeItemObserver observer) {
		this.observers.remove(observer);
	}

}
