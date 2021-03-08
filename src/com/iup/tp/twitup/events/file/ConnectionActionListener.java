package com.iup.tp.twitup.events.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import com.iup.tp.twitup.observer.MainViewObserver;

public class ConnectionActionListener implements ActionListener {

	
	public Boolean connection = true;
	private Set<MainViewObserver> vObservers;
	
	public ConnectionActionListener(Set<MainViewObserver> vObserversParam) {
		this.vObservers=vObserversParam;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// comportement de connection true=se connecter, false = se deconnecter
		if(connection) {
			for( MainViewObserver ob : vObservers) {
				ob.notifyConnectionPage();
			}
		}else {
			System.out.println("ici on va se deconnecter");
			for(MainViewObserver ob : vObservers) {
				ob.notifyDeconnection();
			}
		}
	}
}
