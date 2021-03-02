package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.core.AccountCreationController;

public class TwitupAccountCreationView extends JPanel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton bouton = new JButton("Creer son compte");
	private JFormattedTextField login = new JFormattedTextField();
	private JFormattedTextField mdp = new JFormattedTextField();
	private JLabel loginLabel = new JLabel("Login");
	private JLabel mdpLabel = new JLabel("Password");
	
	public TwitupAccountCreationView() {
		System.out.println("lancement de la vue compte");
		this.setLayout(new GridBagLayout());
		this.add(new JLabel("Creation de compte"), new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(1,1,1,1), 0, 0));
		this.add(bouton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(login,new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(mdp, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(loginLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0));
		this.add(mdpLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.configureTextField(150, 30);
	}
	
	public void init() {
		bouton.addActionListener(new BoutonListener());
	}

	
	public void configureTextField(int width, int height) {
		this.login.setPreferredSize(new Dimension(width, height));
		this.mdp.setPreferredSize(new Dimension(width, height));
	}
	
	class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	      System.out.println("TEXT : login " + login.getText());
	    }
	  }

}
