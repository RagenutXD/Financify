package com.financify.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;

import com.financify.components.Animation;

public class Utils {

	/**
	* Takes in a path to the file (this assumes that the file is in the Base path)
	* And returns the contents of the file as a String
	* 
	* @param file_dir is located in the C:\Users\<user>\Financify\ directory
	*/
	public String fileToString(String file_dir){
		String s= "";
		try {
			FileReader file = new FileReader(GlobalConstants.BASE_PATH + file_dir);
			BufferedReader bufferedReader = new BufferedReader(file);
			String line;
			while((line = bufferedReader.readLine()) != null) {
				s += line +"\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}
	/**
	* Takes in a path to the file (from inside the src folder)
	* And returns the contents of the file as a String
	*/
	public String srcFileToString(String file_dir){
		String s= "";
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream(file_dir);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			String line;
			while((line = bufferedReader.readLine()) != null) {
				s += line +"\n";
			} } catch (IOException e) { e.printStackTrace(); } return s; }

	public String formatBigNumbers(double number){
		if(number >= 1000000000) return String.valueOf((double)Math.round((number/1000000000)*100)/100) + "B";
		if(number >= 1000000) return String.valueOf((double)Math.round((number/1000000)*100)/100) + "M";
		if(number >= 1000) return String.valueOf((double) Math.round((number/1000)*100)/100) + "K";
		return String.valueOf(number);
	}
	public String formatBigNumbers(long number){
		if(number >= 1000000000) return String.valueOf(Math.round((number/1000000)*100)/100) + "B";
		if(number >= 1000000) return String.valueOf(Math.round((number/1000000)*100)/100) + "M";
		if(number >= 1000) return String.valueOf( Math.round((number/1000)*100)/100) + "K";
		return String.valueOf(number);
	}
	
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

	public void playAnimation(Animation animation, float time){
		new Thread(new Runnable() {
			@Override
			public void run(){
				int frameCount = 0;
				float t = 0.0f;	
				while(frameCount <= (GlobalConstants.FPS * time)){
					try{
						animation.createAnimation(t);
						frameCount++;
                        t += 1 / (GlobalConstants.FPS * time);
						Thread.sleep(1000/GlobalConstants.FPS);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				animation.postAnimation();
			}
		}).start();
	}

	/*
	 * Returns an integer between a starting point to an end point
	 * Based on the progress t
	 */
	public int interpolateInt(int from, int to, float step){
        // to avoid values accidentally going past "to"
        // we have to round "step" to 2 decimal places
        step = (float) Math.round(step * 100)/100;

        return (int) (from + (to - from) * step);
    }
	public double interpolateDouble(double from, double to, float step){
        step = (float) Math.round(step * 100)/100;

        return  (from + (to - from) * step);
    }

    public Color interpolateColor(Color from, Color to, float step){
        step = (float) Math.round(step * 100)/100;

        int r = interpolateInt(from.getRed(), to.getRed(), step);
        int g = interpolateInt(from.getGreen(), to.getGreen(), step);
        int b = interpolateInt(from.getBlue(), to.getBlue(), step);

        return new Color(r, g, b);
    }

}
