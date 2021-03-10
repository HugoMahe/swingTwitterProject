package com.iup.tp.twitup.ihm.util;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.iup.tp.twitup.observer.MainViewObserver;

public class ButtonPanelView extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1802679045628616353L;
	protected JButton boutonMireConnection;
	protected ActionListener connectionAction;
	private Set<MainViewObserver> observers;

	public ButtonPanelView(Set<MainViewObserver> vObservers) {
		this.removeAll();
		this.setLayout(new GridBagLayout());
		this.observers = vObservers;
		JButton boutonMireCreationCompte = new JButton("Creer son compte");
		boutonMireCreationCompte.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for( MainViewObserver ob: observers) {
					ob.notifyCreateAccountPage();
				}
			}
		});
		this.add(boutonMireCreationCompte, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		
		
		this.boutonMireConnection = new JButton("Se connecter");		
		this.boutonMireConnection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for( MainViewObserver ob: observers) {
					ob.notifyConnectionPage();
				}
			}
		});
		this.add(boutonMireConnection, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		
		this.setBorder(new LineBorder(Color.blue));
	}

	
	public void showHideButtons() {
		System.out.println("Showing hide buttons");
		
		// AFFICHAGE DU FIL DE TWIT
		JButton boutonFilTwit = new JButton("Fil des twits");
		boutonFilTwit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (MainViewObserver ob : observers) {
					ob.notifyFilTwitPage();
				}
			}
		});
		this.add(boutonFilTwit, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));		
		
		// AFFICHAGE DE LA LISTE DES UTILISATEURS
		JButton boutonListeUtilisateur = new JButton("Liste des utilisateurs");
		boutonListeUtilisateur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (MainViewObserver ob : observers) {
					ob.notifyPrintAllAccountPage();
				}
			}
		});
		this.add(boutonListeUtilisateur, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));		
	
		////// BOUTON DE CREATION D'UN TWIT
		JButton boutonCreationTwit = new JButton("Création d'un twit");
		boutonCreationTwit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (MainViewObserver ob : observers) {
					ob.notifyCreationTwitPage();
				}
			}
		});
		this.add(boutonCreationTwit, new GridBagConstraints(4, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));		
		
		//////////// BOUTON DE CONSULTATION DE SON PROFIL
		JButton boutonAfficheProfil = new JButton("Consulter son profil");
		boutonAfficheProfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (MainViewObserver ob : observers) {
					ob.notifyProfilPage();
				}
			}
		});
		this.add(boutonAfficheProfil, new GridBagConstraints(5, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));	
		
		
		
		JButton boutonDeconnection = new JButton("Se déconnecter");
		boutonDeconnection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (MainViewObserver ob : observers) {
					ob.notifyDeconnection();
				}
			}
		});
		this.add(boutonDeconnection, new GridBagConstraints(6, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));	

	}
}
