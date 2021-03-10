package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.Session;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.observer.user.UserItemObserver;

public class UserItemController implements UserItemObserver {
	protected Session session;
	private EntityManager mEntityManager;

	public UserItemController(Session session, EntityManager emParam) {
		this.session = session;
		this.mEntityManager = emParam;
	}

	@Override
	public void notifierAbonnement(String userTag) {
		User user = this.session.getUser();
		user.addFollowing(userTag);
		this.mEntityManager.sendUser(user);
	}

	@Override
	public void notifierDesabonnement(String userTag) {
		User user = this.session.getUser();
		user.removeFollowing(userTag);
		this.mEntityManager.sendUser(user);
	}

}
