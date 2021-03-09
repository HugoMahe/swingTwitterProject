package com.iup.tp.twitup.observer.account;

public interface AccountObserver {

	void notifyCreateAccount(String tag, String mdp, String confirmation, String name, String avatar);

	
	void notifyAccountConnection(String login, String mdp);
}
