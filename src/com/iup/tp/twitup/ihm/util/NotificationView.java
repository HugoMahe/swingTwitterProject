package com.iup.tp.twitup.ihm.util;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.observer.MainViewObserver;
import com.iup.tp.twitup.observer.notification.NotificationObservable;
import com.iup.tp.twitup.observer.notification.NotificationObserver;

public class NotificationView extends JPanel implements NotificationObservable{

	/**
	 * 
	 */
	
	protected  Set<NotificationObserver> nObservers = new HashSet<NotificationObserver>();

	
	public NotificationView(String message) {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(400, 400));
		JLabel tagLabel = new JLabel(message);
		this.add(tagLabel);
		
		JButton closeButton = new JButton("fermer");
		closeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(NotificationObserver no : nObservers ) {
					no.notifyCloseNotification();
				}
			}
		});
		this.add(closeButton);
	}
	
	private static final long serialVersionUID = 1090699756912624757L;

	@Override
	public void addObserver(NotificationObserver observer) {
		// TODO Auto-generated method stub
		this.nObservers.add(observer);
	}

	@Override
	public void removeObserver(NotificationObserver observer) {
		// TODO Auto-generated method stub
		this.nObservers.remove(observer);
	}

}
