package com.financify.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import com.financify.components.Animation;

public class Utils {

	
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
				while(frameCount < (GlobalConstants.FPS * time)){
					try{
						animation.createAnimation(t);
						frameCount++;
                        t += 1 / (GlobalConstants.FPS * time);
						Thread.sleep(1000/GlobalConstants.FPS);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

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
