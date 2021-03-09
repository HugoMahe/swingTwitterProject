package com.iup.tp.twitup.observer.session;

import com.iup.tp.twitup.datamodel.Twit;

public interface FollowObserver {

	void notifyCheckFollowerTwit(Twit twit);
}
