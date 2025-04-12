package com.financify.components;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JLayeredPane;
import javax.swing.JRootPane;

import com.financify.utils.GlobalConstants;

public class CoverPanel extends RoundPanel{

	public CoverPanel(){
		setBackground(new Color(0,0,0, 150));
		setBounds(0,0,GlobalConstants.WINDOW_WIDTH, GlobalConstants.WINDOW_HEIGHT);
		
		// this prevents mouse clicks from groing through
		addMouseListener(new MouseAdapter() {});
	}

	public void cover(JRootPane rootPane){
		/**
		 * Puts the coverpanel into the the main jframe
		 * 
		 * This cant be used in a constructor unless its in an event listener
		 */
		JLayeredPane layeredPane = rootPane.getLayeredPane();
		layeredPane.add(this, JLayeredPane.PALETTE_LAYER);
	}

}
