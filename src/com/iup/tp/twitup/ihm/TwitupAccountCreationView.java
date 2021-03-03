package com.iup.tp.twitup.ihm;

import java.awt.Color;
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
import javax.swing.border.LineBorder;

import com.iup.tp.twitup.observer.AccountObservable;
import com.iup.tp.twitup.observer.AccountObserver;


public class TwitupAccountCreationView extends JPanel implements AccountObservable {
	protected  Set<AccountObserver> vObservers;
	
	private static final long serialVersionUID = 1L;
	JButton bouton = new JButton("Creer son compte");
	private JFormattedTextField login = new JFormattedTextField();
	private JFormattedTextField mdp = new JFormattedTextField();
	private JFormattedTextField name = new JFormattedTextField();
	private JLabel loginLabel = new JLabel("Login");
	private JLabel mdpLabel = new JLabel("Password");
	private JLabel nameLabel = new JLabel("Name");
	private JPanel container = new JPanel();
	
	public TwitupAccountCreationView() {
		this.setLayout(new GridBagLayout());
		this.setOpaque(true);
		this.setBackground(new Color(150,10,100,70));
		this.setBorder(new LineBorder(Color.RED,4,true));
		//this.setPreferredSize(new Dimension(800,800));
		container.setOpaque(true);
		container.setBackground(new Color(50,150,200,70));
		container.setBorder(new LineBorder(Color.CYAN,4,true));
		//container.setPreferredSize(new Dimension(800, 800));
		container.setLayout(new GridBagLayout());
		//this.add(container, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0) );
		this.add(new JLabel("Creation de compte"), new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		
		this.add(loginLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		this.add(login,new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		this.add(mdpLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(mdp, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		this.add(nameLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(name,new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		this.add(bouton, new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		
		this.vObservers= new HashSet<AccountObserver>();
		container.setLayout(new GridBagLayout());
		//this.configureTextField(150, 30);
		this.bouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("TEXT : login " + login.getText());
			      for(AccountObserver ob : vObservers) {
			    	  ob.notifyCreateAccount();
			      }
			}
		});
	}

	
	public void configureTextField(int width, int height) {
		this.login.setPreferredSize(new Dimension(width, height));
		this.mdp.setPreferredSize(new Dimension(width, height));
		this.name.setPreferredSize(new Dimension(width, height));
	}
	
	public String getLoginText() {
		return this.login.getText();
	}
	
	public String getMdpText() {
		return this.mdp.getText();
	}
	
	public String getNameText() {
		return this.name.getText();
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
