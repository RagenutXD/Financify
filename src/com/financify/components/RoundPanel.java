package com.financify.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class RoundPanel extends JPanel{

	public void setRoundAll(int topLeft, int topRight, int bottomLeft, int bottomRight ){
		this.roundTopLeft = topLeft;
		this.roundTopRight = topRight;
		this.roundBottomLeft = bottomLeft;
		this.roundBottomRight = bottomRight;
		repaint();
	}

	public int getRoundTopLeft() {
		return roundTopLeft;
	}

	public void setRoundTopLeft(int roundTopLeft) {
		this.roundTopLeft = roundTopLeft;
		repaint();
	}

	public int getRoundTopRight() {
		return roundTopRight;
	}

	public void setRoundTopRight(int roundTopRight) {
		this.roundTopRight = roundTopRight;
		repaint();
	}

	public int getRoundBottomLeft() {
		return roundBottomLeft;
	}

	public void setRoundBottomLeft(int roundBottomLeft) {
		this.roundBottomLeft = roundBottomLeft;
		repaint();
	}

	public int getRoundBottomRight() {
		return roundBottomRight;
	}

	public void setRoundBottomRight(int roundBottomRight) {
		this.roundBottomRight = roundBottomRight;
		repaint();
	}

	public void setBorderThickness(int borderThickness){
		this.borderThickness = borderThickness;
		repaint();
	}

	public int getBorderThickness(){
		return borderThickness;
	}

	private int roundTopLeft = 0;
	private int roundTopRight = 0;
	private int roundBottomLeft = 0;
	private int roundBottomRight=0;
	private int borderThickness = 0;
	
	public RoundPanel() {
		setOpaque(false);
	}
	
	@Override
	protected void paintComponent(Graphics grphcs) {
		super.paintComponent(grphcs);

		Graphics2D g2 = (Graphics2D)grphcs.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(getBackground());
		Area area = new Area(createTopRight());
		if(roundTopRight > 0) {
			area.intersect(new Area(createTopLeft()));
		}
		if(roundBottomLeft> 0) {
			area.intersect(new Area(createBottomLeft()));
		}
		if(roundBottomRight> 0) {
			area.intersect(new Area(createBottomRight()));
		}
		
		g2.fill(area);

		// Draw the inside border with rounded corners
		g2.setStroke(new BasicStroke(borderThickness));
		Area borderArea = new Area(createTopRight(borderThickness));
		if(roundTopRight > 0) {
			borderArea.intersect(new Area(createTopLeft(borderThickness)));
		}
		if(roundBottomLeft> 0) {
			borderArea.intersect(new Area(createBottomLeft(borderThickness)));
		}
		if(roundBottomRight> 0) {
			borderArea.intersect(new Area(createBottomRight(borderThickness)));
		}
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(borderThickness));
		borderArea.intersect(area);
		g2.draw(borderArea);

		g2.dispose();
	}
	
	private Shape createTopLeft() {
		int width = getWidth();
		int height = getHeight();
		int roundX = Math.min(width, roundTopLeft);
		int roundY = Math.min(height, roundTopLeft);
		Area area = new Area (new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
		area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height - roundY/2)));
		return area;
	}
	private Shape createTopRight() {
		int width = getWidth();
		int height = getHeight();
		int roundX = Math.min(width, roundTopRight);
		int roundY = Math.min(height, roundTopRight);
		Area area = new Area (new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
		area.add(new Area(new Rectangle2D.Double(0, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, roundY/2, width, height - roundY/2)));
		return area;
	}
	private Shape createBottomLeft() {
		int width = getWidth();
		int height = getHeight();
		int roundX = Math.min(width, roundBottomLeft);
		int roundY = Math.min(height, roundBottomLeft);
		Area area = new Area (new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
		area.add(new Area(new Rectangle2D.Double(roundX/2, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY/2)));
		return area;
	}
	private Shape createBottomRight() {
		int width = getWidth();
		int height = getHeight();
		int roundX = Math.min(width, roundBottomRight);
		int roundY = Math.min(height, roundBottomRight);
		Area area = new Area (new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
		area.add(new Area(new Rectangle2D.Double(0, 0, width-roundX/2, height)));
		area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY/2)));
		return area;
	}

	private Shape createTopLeft(int inset) {
		int width = getWidth() - inset;
		int height = getHeight() - inset;
		int roundX = Math.min(width, roundTopLeft);
		int roundY = Math.min(height, roundTopLeft);
		Area area = new Area(new RoundRectangle2D.Double(inset / 2.0, inset / 2.0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(roundX / 2.0 + inset / 2.0, inset / 2.0, width - roundX / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(inset / 2.0, roundY / 2.0 + inset / 2.0, width, height - roundY / 2.0)));
		return area;
	}

	private Shape createTopRight(int inset) {
		int width = getWidth() - inset;
		int height = getHeight() - inset;
		int roundX = Math.min(width, roundTopRight);
		int roundY = Math.min(height, roundTopRight);
		Area area = new Area(new RoundRectangle2D.Double(inset / 2.0, inset / 2.0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(inset / 2.0, inset / 2.0, width - roundX / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(inset / 2.0, roundY / 2.0 + inset / 2.0, width, height - roundY / 2.0)));
		return area;
	}

	private Shape createBottomLeft(int inset) {
		int width = getWidth() - inset;
		int height = getHeight() - inset;
		int roundX = Math.min(width, roundBottomLeft);
		int roundY = Math.min(height, roundBottomLeft);
		Area area = new Area(new RoundRectangle2D.Double(inset / 2.0, inset / 2.0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(roundX / 2.0 + inset / 2.0, inset / 2.0, width - roundX / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(inset / 2.0, inset / 2.0, width, height - roundY / 2.0)));
		return area;
	}

	private Shape createBottomRight(int inset) {
		int width = getWidth() - inset;
		int height = getHeight() - inset;
		int roundX = Math.min(width, roundBottomRight);
		int roundY = Math.min(height, roundBottomRight);
		Area area = new Area(new RoundRectangle2D.Double(inset / 2.0, inset / 2.0, width, height, roundX, roundY));
		area.add(new Area(new Rectangle2D.Double(inset/2, inset / 2.0, width - roundX / 2.0, height)));
		area.add(new Area(new Rectangle2D.Double(inset / 2.0, inset / 2.0, width, height - roundY / 2.0)));
		return area;
	}

}
