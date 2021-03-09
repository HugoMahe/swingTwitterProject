package com.iup.tp.twitup.ihm.twit;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.iup.tp.twitup.observer.twit.TwitObservable;
import com.iup.tp.twitup.observer.twit.TwitObserver;

public class TwitCreationView extends JPanel  implements TwitObservable {
	/**
	 * Champ de saisie du twit.
	 */
	protected JTextArea twitField;
	protected  Set<TwitObserver> tObservers;

	
	public TwitCreationView() {
        this.setLayout(new GridBagLayout());
        this.tObservers = new HashSet<TwitObserver>();
        
		JLabel titreLabel = new JLabel("Création twit");
		titreLabel.setFont(new Font("Arial",Font.BOLD,12));
		
		JLabel twitText = new JLabel("Twit : ");
		this.twitField = new JTextArea(5, 30);
		twitField.setLineWrap(true);
		
		JButton envoyerButton = new JButton("Envoyer");
		envoyerButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println(twitField.getText());
	    		for(TwitObserver to : tObservers) {
	    			to.notifyCreateTwit(twitField.getText());
	    		}
	    	}
	    });

		this.add(titreLabel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		this.add(twitText, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(twitField, new GridBagConstraints(1, 1, 2, 1, 1, 1,  GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 40), 0, 0));
		
		this.add(envoyerButton, new GridBagConstraints(2, 6, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(10, 5, 0, 0), 0, 0));
	}

	@Override
	public void addObserver(TwitObserver observer) {
		this.tObservers.add(observer);
		
	}

	@Override
	public void removeObserver(TwitObserver observer) {
		// TODO Auto-generated method stub
		this.tObservers.remove(observer);
	}
	
}
