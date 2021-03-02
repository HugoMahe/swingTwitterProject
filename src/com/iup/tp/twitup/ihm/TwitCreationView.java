package com.iup.tp.twitup.ihm;

import java.util.Set;

import javax.swing.JButton;

public class TwitCreationView implements TwitObservable {
	protected Set<TwitObserver> tO;
	
	JButton valider = new JButton("Valider");
	
	public TwitCreationView() {
		
	}
	
	@Override
	public void addObserver(TwitObserver observer) {
		
	}

	@Override
	public void removeObservers(TwitObserver observer) {
		
	}

}
