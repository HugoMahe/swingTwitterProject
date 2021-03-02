package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class TwitupMainView  extends JFrame implements IDatabaseObserver {

	TwitupAccountCreationView accountView =null;
	
	// INIT DU DOSSIER DE BASE
	public File askDirectory(){
		System.out.println("Selection du fichier");
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
		int retour = chooser.showOpenDialog(null);
		if(retour== JFileChooser.APPROVE_OPTION) {
			System.out.println("Dossier correct");
			chooser.getSelectedFile().getName();
			System.out.println("chemin :" + chooser.getSelectedFile());
			return chooser.getSelectedFile();
		}
		return null;
	}
	
	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println("twit added");
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		System.out.println("twit deleted");

	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		System.out.println("twit modified");

	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		System.out.println("user added");

	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		System.out.println("user deleted");

	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		System.out.println("user modified");

	}

	
	public TwitupMainView() {
		super("ma nouvelle application ");

	}

	public void init() {
		// ajout du window listener
		WindowListener wL = new WindowAdapter() {
			public void windowClosing(WindowEvent e ) {
				System.exit(0);
			}
		};
		
		
		addWindowListener(wL);
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu menu = new JMenu("Application");
		JMenuItem JMenuItemQuitter = new JMenuItem( new AbstractAction("Quitter") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fermeture de l'application");
				System.exit(0);
			}
		});
		menu.add(JMenuItemQuitter);
		Icon IconeQuitter = new ImageIcon(getClass().getResource("/exitIcon_20.png"));
		System.out.println(IconeQuitter);
		JMenuItemQuitter.setIcon(IconeQuitter);
		menuBar.add(menu);
		
		JMenu menu2 = new JMenu("?");
		JMenuItem JMenuItem = new JMenuItem( new AbstractAction("A propos de") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Icon iconAPropos = new ImageIcon(getClass().getResource("/logoIUP_50.jpg"));
				JOptionPane.showMessageDialog(null, "Message de a propos de ", "About", JOptionPane.INFORMATION_MESSAGE, iconAPropos );
			}
		});
		menu2.add(JMenuItem);
		menuBar.add(menu2);
		setPreferredSize(new Dimension(600, 600));
		setVisible(true);
	}
	
	
	public void showGUI() {
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				JFrame frame = TwitupMainView.this;
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((screenSize.width - frame.getWidth()) / 6,
						(screenSize.height - frame.getHeight()) / 4);

				// Affichage
				frame.setVisible(true);
				frame.pack();
			}
		});
	}
	
	
	
	
	public void drawAccountCreationView() {
		accountView = new TwitupAccountCreationView();
		this.accountView.init();
		this.setContentPane(accountView);
	}

	
	
	
	}

