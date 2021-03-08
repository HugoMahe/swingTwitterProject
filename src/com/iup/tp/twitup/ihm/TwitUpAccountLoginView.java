package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.observer.AccountObservable;
import com.iup.tp.twitup.observer.AccountObserver;

public class TwitUpAccountLoginView extends JPanel implements AccountObservable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8550093391559773959L;
	protected Set<AccountObserver> vObservers;
	
	//ELEMENTS GRAPHIQUES
	JButton bouton = new JButton("Se connecter");
	private JFormattedTextField login = new JFormattedTextField();
	private JFormattedTextField mdp = new JFormattedTextField();
	private JLabel loginLabel = new JLabel("Login");
	private JLabel mdpLabel = new JLabel("Password");

	public TwitUpAccountLoginView() {
		System.out.println("lancement de la vue connection");
		this.vObservers= new HashSet<AccountObserver>();
		this.setLayout(new GridBagLayout());
		this.add(new JLabel("Vous connecter à votre compte"), new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(1,1,1,1), 0, 0));
		this.add(bouton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(login,new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(mdp, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(loginLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		this.add(mdpLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.configureTextField(150, 30);
		
		this.bouton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("TEXT : login " + login.getText());
					      for(AccountObserver ob : vObservers) {
					    	  ob.notifyAccountConnection();
					      }
					}
		});
	}
	
	public void configureTextField(int width, int height) {
		this.login.setPreferredSize(new Dimension(width, height));
		this.mdp.setPreferredSize(new Dimension(width, height));
	}
	
	@Override
	public void addObserver(AccountObserver observer) {
		this.vObservers.add(observer);
		
	}

	@Override
	public void removeObservers(AccountObserver observer) {
		this.vObservers.remove(observer);
	}
	
	public String getLoginTxt() {
		return this.login.getText();
	}
	
	public String getMdpTxt() {
		return this.mdp.getText();
	}

}
