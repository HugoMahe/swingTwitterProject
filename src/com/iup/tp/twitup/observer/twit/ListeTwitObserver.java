package com.iup.tp.twitup.observer.twit;

import java.util.List;

import com.iup.tp.twitup.datamodel.Twit;

public interface ListeTwitObserver {

	void notifyMiseAJour(List<Twit> twits);
}
