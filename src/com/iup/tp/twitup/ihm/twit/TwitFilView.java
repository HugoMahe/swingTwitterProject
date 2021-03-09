package com.iup.tp.twitup.ihm.twit;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitFilView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6389747582046876345L;
	
	
	public TwitFilView (Set<Twit> twits) {
		
        this.setLayout(new GridBagLayout());
        
		JLabel titreLabel = new JLabel("Fil twitter");
		titreLabel.setFont(new Font("Arial",Font.BOLD,12));
		
		TwitFilComponent filTwit = new TwitFilComponent(twits);
		
		this.add(titreLabel, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		this.add(filTwit, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	}
}
