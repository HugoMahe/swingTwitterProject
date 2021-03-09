package com.iup.tp.twitup.ihm.util;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotificationView extends JPanel{

	/**
	 * 
	 */
	public NotificationView() {
		// TODO Auto-generated constructor stub
		setPreferredSize(new Dimension(100, 100));
		JLabel tagLabel = new JLabel("test notification ");
		this.add(tagLabel);
	}
	
	private static final long serialVersionUID = 1090699756912624757L;

}
