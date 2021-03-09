package com.iup.tp.twitup.ihm.account;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.iup.tp.twitup.observer.account.AccountObservable;
import com.iup.tp.twitup.observer.account.AccountObserver;


public class TwitupAccountCreationView extends JPanel implements AccountObservable {
	/**
	 * Liste des observateurs de cr�ation d'user.
	 */
	protected  Set<AccountObserver> vObservers;
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Champ de saisie du tag.
	 */
	protected JTextField tagField;
	
	/**
	 * Champ de saisie du mot de passe.
	 */
	private JPasswordField mdpField;
	
	/**
	 * Champ de saisie de la confirmation du mot de passe.
	 */
	protected JPasswordField confirmationField;
	
	/**
	 * Champ de saisie de l'avatar.
	 */
	protected JTextField avatarField;
	
	/**
	 * Champ de saisie du nom.
	 */
	private JTextField nameField;
	
	public TwitupAccountCreationView() {
		this.vObservers= new HashSet<AccountObserver>();
		
		this.setLayout(new GridBagLayout());
		this.setOpaque(true);
		this.setBackground(new Color(150,10,100,70));
		this.setBorder(new LineBorder(Color.RED,4,true));

		JLabel titreLabel = new JLabel("Création utilisateur");
		titreLabel.setFont(new Font("Arial",Font.BOLD,12));
		
		this.tagField = new JTextField();
		this.mdpField = new JPasswordField();
		this.confirmationField = new JPasswordField();
		this.nameField = new JTextField();
		this.avatarField = new JTextField();

		JButton avatarSearch = new JButton(new ImageIcon("src/resources/images/folder_icon.png"));
		avatarSearch.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		TwitupAccountCreationView.this.choseAvatar();
	    	}
	    });
		
		JButton send = new JButton("Creer");
		send.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		TwitupAccountCreationView.this.tenterCreerUser();
	    	}
	    });

		int y = 0;
		this.add(titreLabel, new GridBagConstraints(1, y, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		y++;
		
		this.add(new JLabel("Tag : "), new GridBagConstraints(0, y, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(this.tagField, new GridBagConstraints(1, y, 1, 1, 1, 1,  GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		y++;

		this.add(new JLabel("Mot de passe : "), new GridBagConstraints(0, y, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(this.mdpField, new GridBagConstraints(1, y, 1, 1, 1, 1,  GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		y++;
		
		this.add(new JLabel("Confirmation du mot de passe : "), new GridBagConstraints(0, y, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(this.confirmationField, new GridBagConstraints(1, y, 1, 1, 1, 1,  GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		y++;
		
		this.add(new JLabel("Nom : "), new GridBagConstraints(0, y, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(this.nameField, new GridBagConstraints(1, y, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		y++;

		this.add(new JLabel("Avatar : "), new GridBagConstraints(0, y, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(this.avatarField, new GridBagConstraints(1, y, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		this.add(avatarSearch, new GridBagConstraints(2, y, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		y++;
		
		this.add(send, new GridBagConstraints(2, y, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(10, 5, 0, 0), 0, 0));
	}

	
	@SuppressWarnings("deprecation")
	protected void tenterCreerUser() {
		// Cr�ation d'un utilisateur
		String tag = this.tagField.getText();
		String mdp = this.mdpField.getText();
		String confirmation = this.confirmationField.getText();
		String name = this.nameField.getText();
		String avatar = this.avatarField.getText();
		
		for (AccountObserver observer: this.vObservers) {
			observer.notifyCreateAccount(tag, mdp, confirmation, name, avatar);
		}
	}


	protected void choseAvatar() {
		String selection = "";
		JFileChooser avatarImage = new JFileChooser(); 
		avatarImage.setCurrentDirectory(new File("./src/resources/images"));
		avatarImage.setDialogTitle("Choisir l'image d'avatar");
		avatarImage.setFileSelectionMode(JFileChooser.FILES_ONLY);
		avatarImage.setFileFilter(new FileNameExtensionFilter(
				"JPG & PNG Images", "jpg", "png"));
		int returnVal  = avatarImage.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		       System.out.println("dossier choisi: " +
		    		   avatarImage.getSelectedFile().getPath());
		       selection = avatarImage.getSelectedFile().getPath();
	    }
		this.avatarField.setText(selection);
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void addObserver(AccountObserver observer) {
		this.vObservers.add(observer);
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void removeObservers(AccountObserver observer) {
		this.vObservers.remove(observer);
	}

}
