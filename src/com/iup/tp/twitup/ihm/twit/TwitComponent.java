package com.iup.tp.twitup.ihm.twit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitComponent extends JPanel {
	public TwitComponent(Twit twit) {
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		this.setBackground(Color.white);
		
		JLabel nomUser = new JLabel(twit.getTwiter().getName());
		JLabel tagUser = new JLabel("@" + twit.getTwiter().getUserTag());
		
		JLabel twitText = new JLabel(twit.getText());
		JLabel twitDate = new JLabel((new Date(twit.getEmissionDate())).toLocaleString());
		
		tagUser.setForeground(Color.LIGHT_GRAY);
		twitDate.setForeground(Color.LIGHT_GRAY);
		
		this.add(nomUser, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		this.add(tagUser, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, 
				GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		this.add(twitText, new GridBagConstraints(0, 1, 6, 3, 1, 1, GridBagConstraints.NORTHWEST, 
				GridBagConstraints.BOTH, new Insets(0, 5, 0, 0), 0, 0));
		this.add(twitDate, new GridBagConstraints(1, 8, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
	}
}
