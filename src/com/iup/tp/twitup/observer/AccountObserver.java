package com.iup.tp.twitup.observer;

public interface AccountObserver {

	void notifyCreateAccount(String login, String mdp, String name);
	
	void notifyAccountConnection(String login, String mdp);
}
