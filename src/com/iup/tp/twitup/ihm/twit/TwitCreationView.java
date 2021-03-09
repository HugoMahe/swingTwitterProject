package com.iup.tp.twitup.ihm.twit;

import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.iup.tp.twitup.observer.twit.TwitObservable;
import com.iup.tp.twitup.observer.twit.TwitObserver;

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
	public void removeObserver(TwitObserver observer) {
		this.tO.remove(observer);
	}

}
