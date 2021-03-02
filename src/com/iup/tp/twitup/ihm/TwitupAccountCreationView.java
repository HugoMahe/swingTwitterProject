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


public class TwitupAccountCreationView extends JPanel implements viewObservable {
	
	/**
	 * 
	 */
	protected  Set<viewObserver> vObservers;
	
	private static final long serialVersionUID = 1L;
	JButton bouton = new JButton("Creer son compte");
	private JFormattedTextField login = new JFormattedTextField();
	private JFormattedTextField mdp = new JFormattedTextField();
	private JFormattedTextField name = new JFormattedTextField();
	private JLabel loginLabel = new JLabel("Login");
	private JLabel mdpLabel = new JLabel("Password");
	private JLabel nameLabel = new JLabel("Name");
	
	public TwitupAccountCreationView() {
		System.out.println("lancement de la vue compte");
		this.vObservers= new HashSet<viewObserver>();
		this.setLayout(new GridBagLayout());
		this.add(new JLabel("Creation de compte"), new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(1,1,1,1), 0, 0));
		this.add(bouton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(login,new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(mdp, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(name,new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(loginLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		this.add(mdpLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(nameLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

		this.configureTextField(150, 30);
		
		this.bouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("TEXT : login " + login.getText());
			      for(viewObserver ob : vObservers) {
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
	

	@Override
	public void addObserver(viewObserver observer) {
		// TODO Auto-generated method stub
		this.vObservers.add(observer);
	}

	@Override
	public void removeObserver(viewObserver observer) {
		// TODO Auto-generated method stub
		this.vObservers.remove(observer);
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

}
