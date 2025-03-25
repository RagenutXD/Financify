/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.financify.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
/**
 *
 * @author user
 */
public class BarChart extends javax.swing.JPanel {

    /*
     * umm if you want to use this just honestly ask me how - ragenut
     * it's really scuffed i made this when i was sick and rushing for presentation
     * TODO: refactor bar chart code 
     */

    private Color barColor = new Color(255,0,0);
    private Color labelColor = new Color(0, 0,0);
    private int thickness = 30;
    private ArrayList<String> chartDataStrings = new ArrayList<>();
    private ArrayList<Double> chartDataValues = new ArrayList<>();
    private double max_value = 0;
    
    
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
        
        // make straight line on the bottom 
        int bottom_line = (int) (getHeight() * 0.8);
        g2d.drawLine(0, bottom_line, getWidth(), bottom_line);
        
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
            }else{
                px_height = (int) (value/max_value * (getHeight()*0.7));
            }
            
            g2d.setColor(barColor);
            int posx = (i*(thickness/2)) + ( (margin) * (i+1) );
            g2d.fillRect(posx , bottom_line-px_height, thickness, px_height);
            // margin -> bar - > margin -> margin
            
            g2d.setColor(labelColor);
            g2d.drawString(key, posx, (int) (getHeight() * 0.9));
            
            Double rounded = Double.valueOf(Math.round(value*100)/100);
            int font_size =  g2d.getFont().getSize();
            int width = g2d.getFontMetrics().stringWidth(rounded.toString());
//            g2d.drawChars(rounded.toString().toCharArray(), 0, rounded.toString().length(), posx ,font_size);
            g2d.drawString(rounded.toString(), posx + ((thickness-width)/2), (bottom_line-px_height) - font_size);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    

}
