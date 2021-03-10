package com.iup.tp.twitup.ihm.user;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.core.UserItemController;
import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.user.UserListeItemObserver;

public class UserListeItem extends JPanel implements UserListeItemObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5787573847540101532L;
	
	protected Map<User, UserItem> users;
	
	protected UserItemController userItemController;

	protected Session session;
	
	protected JScrollPane scrollPane;

	public UserListeItem(List<User> users, UserItemController userItemController, Session session) {
		this.users = new HashMap<>();
		this.userItemController = userItemController;
		this.session = session;
		
		this.scrollPane = new JScrollPane();
		this.setLayout(new GridBagLayout());
		
		this.mettreAJourListe(users);
		
		this.add(this.scrollPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
	}

	@Override
	public void notifyMiseAJour(List<User> users) {
		this.mettreAJourListe(users);
	}

	private void mettreAJourListe(List<User> users) {
		JPanel liste = new JPanel();
		liste.setLayout(new GridBagLayout());
		for (User user: users) {
			UserItem userComponent = this.users.get(user);
			if (userComponent == null) {
				userComponent = new UserItem(user, this.session);
				userComponent.addObserver(this.userItemController);
				this.session.getUser().addObserver(userComponent);
				this.users.put(user, userComponent);
			}
			liste.add(userComponent, new GridBagConstraints(0, users.indexOf(user), 1, 1, 1, 1, GridBagConstraints.CENTER, 
					GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		}

		this.scrollPane.setViewportView(liste);
	}
}
