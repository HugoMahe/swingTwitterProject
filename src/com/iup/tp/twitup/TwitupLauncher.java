package com.iup.tp.twitup;

import com.iup.tp.twitup.core.Twitup;

/**
 * Classe de lancement de l'application.
 * 
 * @author S.Lucas
 */
public class TwitupLauncher {

	/**
	 * Launcher.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("lancement du build");
		Twitup twitup = new Twitup();
		twitup.show();
	}

}
