package com.financify.utils;

public class GlobalConstants {

	// screen size
	public final int WINDOW_HEIGHT = 600;
	public final int WINDOW_WIDTH = 900;	

	// size of size panel
	public final int SIDE_PANEL_WIDTH = 130;
	/*
	 * make sure to use SIDE_PANEL_WIDTH when trying to anchor something to the left
	 * otherwise it will be anchored to the left of the window screen instead
	 * 
	 * this makes 'c1' 10 px away from the side panel 
	 * 	springLayout.put(SpringLayout.WEST, c1, SIDE_PANEL_WIDTH + 10, SpringLayout.WEST, this) 
	 * 
	 * this makes 'c1' 10 px away from the left of the screen 
	 * 	springLayout.put(SpringLayout.WEST, c1, 10, SpringLayout.WEST, this) 
	 */

}
