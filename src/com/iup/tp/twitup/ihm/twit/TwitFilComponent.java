package com.iup.tp.twitup.ihm.twit;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitFilComponent extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2796037766814402917L;

	public TwitFilComponent(Set<Twit> twits) {
		this.setPreferredSize(new Dimension(250, 300));
		
		JPanel fil = new JPanel();
		fil.setLayout(new GridBagLayout());
		
		int i = 0;
		for (Twit twit: twits) {
			fil.add(new TwitComponent(twit), new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
			i++;
		}
		
		this.setViewportView(fil);
	}
}
