package com.iup.tp.twitup.ihm.twit;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.observer.twit.ListeTwitObserver;

public class TwitFilComponent extends JScrollPane implements ListeTwitObserver {
	protected Map<Twit, TwitComponent> twits;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5787573847540101532L;

	public TwitFilComponent(List<Twit> twits) {
		this.twits = new HashMap<>();
		this.setPreferredSize(new Dimension(250, 300));
		
		this.mettreAJourFil(twits);
	}

	@Override
	public void notifyMiseAJour(List<Twit> twits) {
		this.mettreAJourFil(twits);
	}

	private void mettreAJourFil(List<Twit> twits) {
		JPanel fil = new JPanel();
		fil.setLayout(new GridBagLayout());
		
		for (Twit twit: twits) {
			TwitComponent twitComponent = this.twits.get(twit);
			if (twitComponent == null) {
				twitComponent = new TwitComponent(twit);
			}
			this.twits.put(twit, twitComponent);
			fil.add(twitComponent, new GridBagConstraints(0, twits.indexOf(twit), 1, 1, 1, 1, GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		}
		
		this.setViewportView(fil);
	}
}
