package com.financify.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class CustomFont {

	public Font createFont(String file_dir, int style, int size) {
				
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(file_dir);
			Font f = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(style, size);
			return f;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	
}
