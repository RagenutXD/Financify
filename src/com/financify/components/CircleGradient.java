package com.financify.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

public class CircleGradient extends JPanel{

	private int radius;
	private float fadeIntensity = 0.0f;

	public CircleGradient(int radius){
		this.radius = radius;
		setPreferredSize(new Dimension(this.radius*2, this.radius*2));
		setOpaque(false);
	}

	public void setRadius(int radius){
		this.radius = radius;
		setPreferredSize(new Dimension(this.radius*2, this.radius*2));
	}

	/**
	 * The distance between the first point and the final point in the gradient
	 * 
	 * The lower the distance, the stronger the fade
	 * 
	 * @param f a float between 0 to 1 that determines the strength of the fade
	 */
	public void setFadeIntensity(float f){
		this.fadeIntensity = f;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Point2D center = new Point2D.Double(radius, radius);
		float[] dist = {fadeIntensity, 1.0f};
		Color pairColor = new Color(getBackground().getRed(), getBackground().getGreen(), getBackground().getBlue(),0);
		Color colors[] = {getBackground(), pairColor};
		RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);

		g2.setPaint(p);
		g2.fillOval(0, 0, getWidth(), getHeight());


		super.paintComponent(g);
	}
	
}
