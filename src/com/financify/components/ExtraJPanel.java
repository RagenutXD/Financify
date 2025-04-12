package com.financify.components;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/*
 * This is the panel used in panels such as Statistics, Home, Self Notes, etc.
 */
public class ExtraJPanel extends JPanel{

	/*
	 * This is a method that will overrided by the children if they have to execute something before they exit
	 * 
	 * This is so that any updates to the json will only be written to the file when changing files
	 * and not when anything is changed
	 */
	public void onExit(){
	}

	
}
