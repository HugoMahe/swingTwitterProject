package com.iup.tp.twitup.ihm.twit;

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

import com.iup.tp.twitup.datamodel.ListeTwit;
import com.iup.tp.twitup.observer.twit.TwitObservable;
import com.iup.tp.twitup.observer.twit.TwitObserver;

public class TwitFilView extends JPanel implements TwitObservable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6389747582046876345L;
	
	protected Set<TwitObserver> observers;
	
	protected TwitFilComponent filTwit;
	
	protected JTextField rechercheField;
	
	
	public TwitFilView (ListeTwit twits) {
		this.observers = new HashSet<>();
		
        this.setLayout(new GridBagLayout());
        
		JLabel titreLabel = new JLabel("Fil twitter");
		titreLabel.setFont(new Font("Arial",Font.BOLD,12));
		
		this.filTwit = new TwitFilComponent(twits.getTwits());
		twits.addObserver(filTwit);
		
		this.rechercheField = new JTextField();

		JButton filtre = new JButton(new ImageIcon(getClass().getResource("/loupe.png")));
		filtre.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		TwitFilView.this.filtrer();
	    	}
	    });
		
		this.add(titreLabel, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(filTwit, new GridBagConstraints(2, 1, 4, 3, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		this.add(rechercheField, new GridBagConstraints(4, 0, 1, 1, 0.5, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
		this.add(filtre, new GridBagConstraints(5, 0, 1, 1, 0.5, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	}


	protected void filtrer() {
		for (TwitObserver observer : this.observers) {
			observer.notifyFiltreFil(this.rechercheField.getText());
		}
	}

	@Override
	public void addObserver(TwitObserver observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(TwitObserver observer) {
		this.observers.remove(observer);
	}
}
