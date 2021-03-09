package com.iup.tp.twitup.ihm.account;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iup.tp.twitup.observer.account.AccountObservable;
import com.iup.tp.twitup.observer.account.AccountObserver;

public class TwitUpAccountLoginView extends JPanel implements AccountObservable {
	private static final long serialVersionUID = -8550093391559773959L;
	
	/**
	 * Liste des observateurs de la connexion.
	 */
	protected Set<AccountObserver> vObservers;

	/**
	 * Champ de saisie du tag.
	 */
	protected JTextField tagField;
	
	/**
	 * Champ de saisie du mot de passe.
	 */
	protected JPasswordField passwordField;


	public TwitUpAccountLoginView() {
		System.out.println("lancement de la vue connection");
		this.vObservers= new HashSet<AccountObserver>();
		this.setLayout(new GridBagLayout());
        
		JLabel titreLabel = new JLabel("Connexion utilisateur");
		titreLabel.setFont(new Font("Arial",Font.BOLD,12));
		
		this.tagField = new JTextField();
		this.passwordField = new JPasswordField();

		JButton connexionButton = new JButton("Connexion");
		connexionButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		TwitUpAccountLoginView.this.tentativeConnexion();
	    	}
	    });
		
		
		this.add(titreLabel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		this.add(new JLabel("Tag : "), new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(tagField, new GridBagConstraints(1, 1, 1, 1, 1, 1,  GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

		this.add(new JLabel("Mot de passe : "), new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(passwordField, new GridBagConstraints(1, 2, 1, 1, 1, 1,  GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		
		this.add(connexionButton, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(10, 5, 0, 0), 0, 0));
	}
	
	protected void tentativeConnexion() {
		String tag = this.tagField.getText();
		@SuppressWarnings("deprecation")
		String password = this.passwordField.getText();

		for (AccountObserver observer: this.vObservers) {
			observer.notifyAccountConnection(tag, password);
		}
	}

	@Override
	public void addObserver(AccountObserver observer) {
		this.vObservers.add(observer);
		
	}

	@Override
	public void removeObservers(AccountObserver observer) {
		this.vObservers.remove(observer);
	}

}
