package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.ihm.util.ButtonPanelView;
import com.iup.tp.twitup.observer.MainViewObservable;
import com.iup.tp.twitup.observer.MainViewObserver;

public class TwitupMainView  extends JFrame implements MainViewObservable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8168615736650006368L;

	protected  Set<MainViewObserver> vObservers;
	
	protected JButton boutonMireConnection;
	public Session session;

	protected JPanel content;
	protected JPanel toolbar = new JPanel();
	protected ButtonPanelView buttonPanel;

	public TwitupMainView()  {
		super("ma nouvelle application ");
		this.vObservers= new HashSet<MainViewObserver>();
		
		WindowListener wL = new WindowAdapter() {
			public void windowClosing(WindowEvent e ) {
				System.exit(0);
			}
		};
		
		addWindowListener(wL);
	}
	
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

	/**
	 * 
	 */
	public void init() {
		

		
		
		
		// HERE MENU BAR
		this.defineMenuItems();
		
		// HERE BUTTON PANEL
		this.buttonPanel = new ButtonPanelView(this.vObservers);
		this.toolbar.add(this.buttonPanel);
		
		this.setPreferredSize(new Dimension(800, 800));
		//setSize(500, 500);
		
		// HERE CONTENT PANEL
		content = new JPanel(new GridBagLayout());
		content.setBorder(new LineBorder(Color.red));
		JPanel cont = new JPanel(new GridBagLayout());
		
		cont.add(toolbar, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));		
		cont.add(content, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));		
		
		this.setContentPane(cont);
		setVisible(true);
	}
	
	
	
	
	/***
	 * 
	 */
	public void defineMenuItems() {
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
	}
	
	
	public void showGUI() {
		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				TwitupMainView.this.setLocation((screenSize.width - TwitupMainView.this.getWidth()) / 6, (screenSize.height - TwitupMainView.this.getHeight()) / 4);
				// Affichage
				TwitupMainView.this.pack();
				TwitupMainView.this.setVisible(true);

			}
		});
	}
	
	
	public void showView(JPanel toShow) {
		content.removeAll();
		content.add(toShow, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		this.revalidate();
		this.repaint();
	}

	@Override
	public void addObserver(MainViewObserver observer) {
		this.vObservers.add(observer);
	}

	@Override
	public void removeObserver(MainViewObserver observer) {
		this.vObservers.remove(observer);
	}

	public void printAccountButton() {
		// TODO Auto-generated method stub
		this.buttonPanel.showHideButtons();
		this.revalidate();
		this.repaint();
		
	}

	public void hideAccountButton() {
		System.out.println("deconnection");
		this.remove(buttonPanel);
		this.toolbar.removeAll();
		this.buttonPanel =new ButtonPanelView(vObservers);
		this.toolbar.add(this.buttonPanel);
		this.revalidate();
		this.repaint();
	}

	
}

