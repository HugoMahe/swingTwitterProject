package com.iup.tp.twitup.ihm.user;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.user.UserItemObservable;
import com.iup.tp.twitup.observer.user.UserItemObserver;
import com.iup.tp.twitup.observer.user.UserObserver;

public class UserItem extends JPanel implements UserItemObservable, UserObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 318503268440621458L;
	
	protected Set<UserItemObserver> observers;

	protected User user;

	protected Session session;
	
	protected JButton gestionAbonnement;

	public UserItem(User user, Session session) {
		this.observers = new HashSet<>();
		this.user = user;
		this.session = session;
		
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.setBackground(Color.white);
		
		JLabel nomUser = new JLabel(this.user.getName());
		JLabel tagUser = new JLabel("@" + this.user.getUserTag());

		
		if (!this.session.getUser().equals(this.user) ) {
			if (this.session.getUser().isFollowing(this.user)) {
				this.gestionAbonnement = new JButton("Se desabonner");
				
			}
			else {
				this.gestionAbonnement = new JButton("S'abonner");
			}
			
			this.gestionAbonnement.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		if (UserItem.this.session.getUser().isFollowing(user)) {
			    		UserItem.this.desabonnement();
		    		}
		    		else {
		    			UserItem.this.abonnement();
		    		}
		    	}
		    });
			this.add(this.gestionAbonnement, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.EAST, 
					GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
		}
		
		tagUser.setForeground(Color.LIGHT_GRAY);
		
		this.add(nomUser, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		this.add(tagUser, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
	}

	protected void desabonnement() {
		for (UserItemObserver observer: this.observers) {
			observer.notifierDesabonnement(this.user.getUserTag());
		}
	}

	protected void abonnement() {
		for (UserItemObserver observer: this.observers) {
			observer.notifierAbonnement(this.user.getUserTag());
		}
	}

	@Override
	public void addObserver(UserItemObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(UserItemObserver observer) {
		this.observers.remove(observer);
	}

	@Override
	public void notifyChangementFollow() {
		if (!this.session.getUser().equals(this.user) ) {
			if (this.session.getUser().isFollowing(this.user)) {
				this.gestionAbonnement.setText("Se desabonner");
			}
			else {
				this.gestionAbonnement.setText("S'abonner");
			}
		}
		this.revalidate();
		this.repaint();
	}
}
