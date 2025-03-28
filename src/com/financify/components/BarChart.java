/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.financify.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import com.financify.utils.*;

public class BarChart extends javax.swing.JPanel {

    /*
     * umm if you want to use this just honestly ask me how - ragenut
     * it's really scuffed i made this when i was sick and rushing for presentation
     * TODO: refactor bar chart code 
     */

    Utils utils = new Utils();
    private Color barColor = Color.RED;
    private Color labelColor = Color.black;
    private Color maxBarColor = Color.BLUE;
    private int thickness = 30;
    private ArrayList<String> chartDataStrings = new ArrayList<>();
    private ArrayList<Double> chartDataValues = new ArrayList<>();
    private double max_value = 0;
    private int borderRadius = 0;
    
    
    public BarChart() {
        setOpaque(false);
        
        initComponents();
    }

    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g.create();
        
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // make straight line on the bottom 
        int bottom_line = (int) (getHeight() * 0.8);
        
        //get margins between bars
        int margin = 0;
        if(!chartDataValues.isEmpty()){
            margin = (getWidth() / (chartDataValues.size() + 1)) - (thickness/2) ;
        }
        
        // get max first
        for(double value: chartDataValues){
            if(max_value < value){
                max_value = value;
            }
        }
        
        for(int i = 0; i < chartDataStrings.size(); i++){
            double value = chartDataValues.get(i);
            String key = chartDataStrings.get(i);
            
            int px_height = 0;
            if(value == max_value){
                px_height = (int)(getHeight()*0.6);
                g2d.setColor(maxBarColor);
            }else{
                px_height = (int) (value/max_value * (getHeight()*0.6));
                g2d.setColor(barColor);
            }
            
            int posx = (i*(thickness/2)) + ( (margin) * (i+1) );
            g2d.fillRoundRect(posx , bottom_line-px_height, thickness, px_height, borderRadius, borderRadius);
            
            int txtWidth = 0;
            int txtHeight = g2d.getFontMetrics().getHeight();
            g2d.setColor(labelColor);

            // draw labelss
            txtWidth = g2d.getFontMetrics().stringWidth(key);
            g2d.drawString(key, posx + ((thickness-txtWidth)/2), (int) bottom_line+ (2 *txtHeight));
            
            // draw numbers
            Double rounded = Double.valueOf(Math.round(value*100)/100);
            txtWidth = g2d.getFontMetrics().stringWidth(rounded.toString());
            g2d.drawString(rounded.toString(), posx + ((thickness-txtWidth)/2), (bottom_line-px_height) - txtWidth);
        }
        
        
        
        g.dispose();
        super.paintComponent(g);
    }
    
    public void addChartData(String s, Double d){
        chartDataStrings.add(s);
        chartDataValues.add(d);
        
    }
    
    public int getThickness(){
        return thickness;
    }
    
    public void setThickness(int thickness){
        this.thickness = thickness; 
    }
    
    public void setBarColor(Color barColor){
        this.barColor = barColor;
    }
    public Color getBarColor(){
        return barColor;
    }
    
    public void setLabelColor(Color labelColor){
        this.labelColor = labelColor;
    }
    public Color getLabelColor(){
        return labelColor;
    }

    public void setMaxBarColor(Color maxBarColor){
        this.maxBarColor = maxBarColor;
    }
    public Color getMaxBarColor(){
        return maxBarColor;
    }

    public void setBorderRadius(int borderRadius){
        this.borderRadius = borderRadius;
    }
    public int getBorderRadius(){
        return borderRadius;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    

}
