package com.iup.tp.twitup.observer.user;

import java.util.List;

import com.iup.tp.twitup.datamodel.User;

public interface UserListeItemObserver {

	void notifyMiseAJour(List<User> users);

}
