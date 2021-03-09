package com.iup.tp.twitup.observer.twit;

public interface TwitObserver {

	void notifyCreateTwit(String text);
	
	void notifyFiltreFil(String tag);
}
