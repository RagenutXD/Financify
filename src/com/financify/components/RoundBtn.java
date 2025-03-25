package com.financify.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;
import javax.swing.JButton;

public class RoundBtn extends JButton{
	
	private int borderRadius;
	
	// constructors
	public RoundBtn(){
		setContentAreaFilled(false);
	}
	public RoundBtn(String text){
		super(text);
		setContentAreaFilled(false);
	}
	public RoundBtn( Icon icon){
		super(icon);
		setContentAreaFilled(false);
	}
	public RoundBtn(String text, Icon icon){
		super(text, icon);
		setContentAreaFilled(false);
	}

	public int getBorderRadius(){
		return borderRadius;
	}

	public void setBorderRadius(int borderRadius){
		this.borderRadius = borderRadius;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//draw rounded border
		g2.setColor(getBackground());
		g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
		
		super.paintComponent(g);
		g.dispose();
	}

}
