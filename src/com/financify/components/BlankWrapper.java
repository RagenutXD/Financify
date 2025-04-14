package com.financify.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JScrollPane;

public class BlankWrapper extends JScrollPane{

	public BlankWrapper(Component comp){
		super(comp);
		setBorder(null);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setOpaque(false);
		getViewport().setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		/*
		 * Just setting it opaque to false will still leave a little border
		 * Doing this will make sure nothing is drawn
		 */
	}	
}
