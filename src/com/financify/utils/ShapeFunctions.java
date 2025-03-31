package com.financify.utils;

// this function is for transforming the "t" on the animate methods
public class ShapeFunctions {

	public static float elasticOut(float t){
		return  (float) (Math.sin(-13.0f * (t + 1.0f) * (Math.PI/2)) * Math.pow(2.0, -10.0 * t) + 1.0);
	}

}
