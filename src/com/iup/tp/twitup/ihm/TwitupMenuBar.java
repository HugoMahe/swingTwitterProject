package com.iup.tp.twitup.ihm;

import javax.swing.*;

public class TwitupMenuBar extends JFrame {

	public TwitupMenuBar(JFrame parent) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("menu 1");
		menuBar.add(menu1);
		parent.add(menuBar);
	}
	
}
