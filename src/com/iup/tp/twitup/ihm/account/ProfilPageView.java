package com.iup.tp.twitup.ihm.account;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.iup.tp.twitup.datamodel.Session;

public class ProfilPageView extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 441175835203834866L;

	public ProfilPageView(Session session) {
		// TODO Auto-generated constructor stub
		this.setLayout(new GridBagLayout());
		this.setOpaque(true);
		this.setBackground(new Color(150,10,100,70));
		this.setBorder(new LineBorder(Color.RED,4,true));
		
		JLabel tagLabel = new JLabel("user tag : ");
		this.add(tagLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		JLabel tag = new JLabel(session.getUser().getUserTag());
		this.add(tag, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		/// NAME
		JLabel nameLabel = new JLabel("Name : ");
		this.add(nameLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		JLabel name = new JLabel(session.getUser().getName());
		this.add(name, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		
		// AVATAR
		JLabel avatar = new JLabel("Avatar : ");
		this.add(avatar, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
			File file = new File(session.getUser().getAvatarPath());
			if(file.exists()) {
				try {
					BufferedImage a = ImageIO.read(file);
					System.out.println("j'ai mon image");
					JLabel picLabel = new JLabel(new ImageIcon(a));
					this.add(picLabel,new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, 
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0) );
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		
	}
	
	

}
