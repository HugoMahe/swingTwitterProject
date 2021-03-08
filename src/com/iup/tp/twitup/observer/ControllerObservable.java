package com.iup.tp.twitup.observer;

public interface ControllerObservable {

	public void addObserver(ControllerObserver observer);
	
	public void removeObserver(ControllerObserver observer);
}
