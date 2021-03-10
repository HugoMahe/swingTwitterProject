package com.iup.tp.twitup.ihm.user;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.iup.tp.twitup.core.UserItemController;
import com.iup.tp.twitup.datamodel.ListeUser;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.observer.user.UserListeViewObservable;
import com.iup.tp.twitup.observer.user.UserListeViewObserver;

public class UserListeView extends JPanel implements UserListeViewObservable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6389747582046876345L;
	
	protected Set<UserListeViewObserver> observers;
	
	protected UserListeItem filUser;
	
	protected JTextField rechercheField;
	
	
	public UserListeView (ListeUser listeUser, UserItemController userItemController, Session session) {
		this.observers = new HashSet<>();
		
        this.setLayout(new GridBagLayout());
        
		JLabel titreLabel = new JLabel("Liste des utilisateurs");
		titreLabel.setFont(new Font("Arial",Font.BOLD,12));
		
		this.filUser = new UserListeItem(listeUser.getUsers(), userItemController, session);
		listeUser.addObserver(filUser);
		
		this.rechercheField = new JTextField();
		this.rechercheField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				notification();
			}
			public void removeUpdate(DocumentEvent e) {
				notification();
			}
			public void insertUpdate(DocumentEvent e) {
				notification();
			}

			public void notification() {
				UserListeView.this.filtrer();
			}
		});

		JButton filtre = new JButton(new ImageIcon(getClass().getResource("/loupe.png")));
		filtre.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		UserListeView.this.filtrer();
	    	}
	    });
		
		this.add(titreLabel, new GridBagConstraints(0, 0, 3, 1, 1, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(filUser, new GridBagConstraints(2, 1, 4, 3, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		this.add(rechercheField, new GridBagConstraints(4, 0, 1, 1, 0.5, 0, GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		this.add(filtre, new GridBagConstraints(5, 0, 1, 1, 0.5, 0, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	}


	protected void filtrer() {
		for (UserListeViewObserver observer : this.observers) {
			observer.notifyFiltreFil(this.rechercheField.getText());
		}
	}

	@Override
	public void addObserver(UserListeViewObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(UserListeViewObserver observer) {
		this.observers.remove(observer);
	}
}
