package com.iup.tp.twitup.datamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.observer.twit.ListeTwitObservable;
import com.iup.tp.twitup.observer.twit.ListeTwitObserver;

public class ListeTwit implements ListeTwitObservable {
	protected List<Twit> twits;
	protected Set<ListeTwitObserver> observers;

	public ListeTwit(Set<Twit> twits) {
		super();
		this.observers = new HashSet<>();
		this.twits = new ArrayList<>();
		this.twits.addAll(twits);
	}

	public List<Twit> getTwits() {
		return twits;
	}

	public void setTwits(Set<Twit> twits) {
		this.twits = new ArrayList<>();
		this.twits.addAll(twits);
		this.trierListe();
		this.notifierMiseAJour();
	}

	public void addTwits(Set<Twit> twits) {
		this.twits.addAll(twits);
		this.trierListe();
		this.notifierMiseAJour();
	}
	
	private void trierListe() {
		java.util.Collections.sort(this.twits, (twit1, twit2) -> {
			return Long.compare(twit1.getEmissionDate(), twit2.getEmissionDate()) * -1;
		});
	}
	
	private void notifierMiseAJour() {
		for (ListeTwitObserver observer : this.observers) {
			observer.notifyMiseAJour(this.twits);
		}
	}

	@Override
	public void addObserver(ListeTwitObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(ListeTwitObserver observer) {
		this.observers.remove(observer);
	}
}
