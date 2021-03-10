package com.iup.tp.twitup.ihm.twit;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.observer.twit.ListeTwitObserver;

public class TwitFilComponent extends JPanel implements ListeTwitObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5787573847540101532L;
	protected Map<Twit, TwitComponent> twits;
	protected JScrollPane scrollPane;

	public TwitFilComponent(List<Twit> twits) {
		this.twits = new HashMap<>();
		this.scrollPane = new JScrollPane();
		
		this.setLayout(new GridBagLayout());
		
		this.mettreAJourFil(twits);
		
		this.add(this.scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
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
		
		this.scrollPane.setViewportView(fil);
	}
}
