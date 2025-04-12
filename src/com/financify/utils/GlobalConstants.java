package com.financify.utils;

public class GlobalConstants {

	// where all the user data will be stored
	public static final String BASE_PATH = System.getProperty("user.home") + "\\Financify";

	// screen size
	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 900;	

	// size of size panel
	public static final int SIDE_PANEL_WIDTH = 130;

	// keep the animations 60 fps
	public static final int FPS = 60;

	// the json used in saving old save data
	public static final String DATE_JSON_TEMPLATE = "{\"Jan\": 0.0, \"Feb\": 0.0, \"Mar\": 0.0, \"Apr\": 0.0, \"May\": 0.0, \"Jun\": 0.0, \"Jul\": 0.0, \"Aug\": 0.0, \"Sep\": 0.0, \"Oct\": 0.0, \"Nov\": 0.0, \"Dec\": 0.0}";

	// keep the months in a constant array so it doesn't consume much memory
	public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

}
