package com.iup.tp.twitup.ihm;

import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.iup.tp.twitup.observer.TwitObservable;
import com.iup.tp.twitup.observer.TwitObserver;

public class TwitCreationView extends JPanel implements TwitObservable  {
	protected Set<TwitObserver> tO;
	
	JButton valider = new JButton("Valider");
	
	public TwitCreationView() {
		
	}
	
	@Override
	public void addObserver(TwitObserver observer) {
		this.tO.add(observer);
	}

	@Override
	public void removeObservers(TwitObserver observer) {
		this.tO.remove(observer);
	}

}
