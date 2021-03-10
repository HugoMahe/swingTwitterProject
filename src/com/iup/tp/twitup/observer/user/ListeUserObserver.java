package com.iup.tp.twitup.observer.user;

import java.util.List;

import com.iup.tp.twitup.datamodel.User;

public interface ListeUserObserver {

	void notifyMiseAJour(List<User> users);

}
