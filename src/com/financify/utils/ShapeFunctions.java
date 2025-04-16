package com.financify.utils;

// this function is for transforming the "t" on the animate methods
public class ShapeFunctions {

	public static float elasticOut(float t){
		return  (float) (Math.sin(-13.0f * (t + 1.0f) * (Math.PI/2)) * Math.pow(2.0, -10.0 * t) + 1.0);
	}

	public static float easeOutExpo(float t){
		return (float) (1 - Math.pow(2, -10 *t));
	}

	public static float easeOutBounce(float t){
		final float n1 = 7.5625f;
		final float d1 = 2.75f;
		if (t < 1 / d1) {
			return n1 * t * t;
		} else if (t < 2 / d1) {
			return (float)(n1 * (t -= 1.5 / d1) * t + 0.75);
		} else if (t < 2.5 / d1) {
			return (float)(n1 * (t -= 2.25 / d1) * t + 0.9375);
		} else {
			return (float)(n1 * (t -= 2.625 / d1) * t + 0.984375);
		}
	}

}
