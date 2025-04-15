package com.financify.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JButton;

public class RoundBtn extends JButton{
	/*
	 * A simpler version of round panel
	 * 
	 * It is a button with the original design hidden
	 * and replaced with a round rectangle 2d
	 */
	
	private int borderRadius;
	private int borderSize = 0;
	private Color borderColor;

	public Color getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
	public int getBorderSize() {
		return borderSize;
	}
	public void setBorderSize(int borderSize) {
		this.borderSize = borderSize;
	}

	public boolean isHovered;
	
	// constructors
	public RoundBtn(){
		setContentAreaFilled(false);
		hoverMouseListener();
	}
	public RoundBtn(String text){
		super(text);
		setContentAreaFilled(false);
		hoverMouseListener();
	}
	public RoundBtn( Icon icon){
		super(icon);
		setContentAreaFilled(false);
		hoverMouseListener();
	}
	public RoundBtn(String text, Icon icon){
		super(text, icon);
		setContentAreaFilled(false);
		hoverMouseListener();
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

		if(borderSize>0){
			g2.setStroke(new BasicStroke(borderSize));
			g2.setColor(borderColor);
			g2.drawRoundRect(borderSize/2, borderSize/2, getWidth()-borderSize, getHeight()-borderSize, borderRadius-(2*borderSize), borderRadius-(2*borderSize));
		}
		
		super.paintComponent(g);
		g.dispose();
	}
	
	private void hoverMouseListener(){
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				isHovered = true;
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				isHovered = false;
				super.mouseExited(e);
			}
		});
	}

}
