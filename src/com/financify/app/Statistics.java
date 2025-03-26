/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.financify.app;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.financify.components.RoundBtn;
import com.financify.components.RoundPanel;
import com.financify.utils.*;

public class Statistics extends JPanel {

    CustomFont customFont = new CustomFont();

    public Statistics() {

        SpringLayout springLayout = new SpringLayout();
        GlobalConstants globalConstants = new GlobalConstants();
        
        setPreferredSize(new Dimension(770, 1000)); // Set preferred size larger than the scroll pane
        setBackground(Color.decode("#121212"));
        setLayout(springLayout);

        JLabel lblAppName = new JLabel("FINANCIFY");
        lblAppName.setForeground(Color.decode("#d2a2fc"));
        lblAppName.setFont(customFont.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 18));        
        springLayout.putConstraint(SpringLayout.WEST, lblAppName,  18, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblAppName, 24, SpringLayout.NORTH, this); 

        JLabel lblFinanceWrapped = new JLabel("Finance Wrapped");
        lblFinanceWrapped.setForeground(Color.WHITE);
        lblFinanceWrapped.setFont(customFont.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 32));        
        springLayout.putConstraint(SpringLayout.WEST, lblFinanceWrapped,  17, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblFinanceWrapped, 0, SpringLayout.SOUTH, lblAppName); 

        //share btn
        RoundBtn btnShare = new RoundBtn("Share");
        btnShare.setBorderRadius(20);
        btnShare.setBackground(Color.white);
        btnShare.setFont(customFont.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16));
        btnShare.setPreferredSize(new Dimension(90, 40));
        //TODO: find a way to not make this so annoying to line up
        springLayout.putConstraint(SpringLayout.WEST, btnShare, 643, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, btnShare, 36, SpringLayout.NORTH, this);
        btnShare.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseEntered(MouseEvent e) {
               super.mouseEntered(e);
               btnShare.setBackground(Color.decode("#8b8b8b"));
           }
           @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                btnShare.setBackground(Color.white);
            }
        });

        Font smallFont = customFont.createFont("com/financify/resources/Poppins/Poppins-Light.ttf", Font.PLAIN, 14);
        Font boldFont = customFont.createFont("com/financify/resources/Poppins/Poppins-Bold.ttf", Font.PLAIN, 24);
        // small stats
        // this is gonna be ALLOT

        // MONEY SAVED
        RoundPanel miniPanel1 = new RoundPanel();
        miniPanel1.setLayout(springLayout);
        miniPanel1.setRoundAll(20); 
        miniPanel1.setBackground(Color.decode("#282828"));
        miniPanel1.setPreferredSize(new Dimension(167,118));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel1, + 17, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel1, 20, SpringLayout.SOUTH, lblFinanceWrapped);

        JLabel lblMini1 = new JLabel("Money Saved");
        lblMini1.setForeground(Color.white);
        lblMini1.setFont(smallFont);
        springLayout.putConstraint(SpringLayout.WEST, lblMini1, 16, SpringLayout.WEST, miniPanel1);
        springLayout.putConstraint(SpringLayout.SOUTH, lblMini1, 0, SpringLayout.VERTICAL_CENTER, miniPanel1);
        
        lblSaved = new JLabel("$12K");
        lblSaved.setForeground(Color.WHITE);
        lblSaved.setFont(boldFont);
        springLayout.putConstraint(SpringLayout.WEST, lblSaved, 16, SpringLayout.WEST, miniPanel1);
        springLayout.putConstraint(SpringLayout.NORTH, lblSaved, 0, SpringLayout.VERTICAL_CENTER, miniPanel1);

        miniPanel1.add(lblMini1);
        miniPanel1.add(lblSaved);

        // MONEY SPENT
        RoundPanel miniPanel2 = new RoundPanel();
        miniPanel2.setLayout(springLayout);
        miniPanel2.setRoundAll(20); 
        miniPanel2.setBackground(Color.decode("#282828"));
        miniPanel2.setPreferredSize(new Dimension(167,118));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel2, 17, SpringLayout.EAST, miniPanel1);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel2, 20, SpringLayout.SOUTH, lblFinanceWrapped);
        
        JLabel lblMini2 = new JLabel("Money Spent");
        lblMini2.setForeground(Color.white);
        lblMini2.setFont(smallFont);
        springLayout.putConstraint(SpringLayout.WEST, lblMini2, 16, SpringLayout.WEST, miniPanel2);
        springLayout.putConstraint(SpringLayout.SOUTH, lblMini2, 0, SpringLayout.VERTICAL_CENTER, miniPanel2);
        
        lblSpent = new JLabel("$875");
        lblSpent.setForeground(Color.WHITE);
        lblSpent.setFont(boldFont);
        springLayout.putConstraint(SpringLayout.WEST, lblSpent, 16, SpringLayout.WEST, miniPanel2);
        springLayout.putConstraint(SpringLayout.NORTH, lblSpent, 0, SpringLayout.VERTICAL_CENTER, miniPanel2);

        miniPanel2.add(lblMini2);
        miniPanel2.add(lblSpent);

        // GOALS REACHED
        RoundPanel miniPanel3 = new RoundPanel();
        miniPanel3.setLayout(springLayout);
        miniPanel3.setRoundAll(20); 
        miniPanel3.setBackground(Color.decode("#282828"));
        miniPanel3.setPreferredSize(new Dimension(167,118));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel3, 17, SpringLayout.EAST, miniPanel2);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel3, 20, SpringLayout.SOUTH, lblFinanceWrapped);
        
        JLabel lblMini3 = new JLabel("Goals Reached");
        lblMini3.setForeground(Color.white);
        lblMini3.setFont(smallFont);
        springLayout.putConstraint(SpringLayout.WEST, lblMini3, 16, SpringLayout.WEST, miniPanel3);
        springLayout.putConstraint(SpringLayout.SOUTH, lblMini3, 0, SpringLayout.VERTICAL_CENTER, miniPanel3);
        
        lblGoals = new JLabel("23");
        lblGoals.setForeground(Color.WHITE);
        lblGoals.setFont(boldFont);
        springLayout.putConstraint(SpringLayout.WEST, lblGoals, 16, SpringLayout.WEST, miniPanel3);
        springLayout.putConstraint(SpringLayout.NORTH, lblGoals, 0, SpringLayout.VERTICAL_CENTER, miniPanel3);

        miniPanel3.add(lblMini3);
        miniPanel3.add(lblGoals);

        // TIME SPENT
        RoundPanel miniPanel4 = new RoundPanel();
        miniPanel4.setLayout(springLayout);
        miniPanel4.setRoundAll(20); 
        miniPanel4.setBackground(Color.decode("#282828"));
        miniPanel4.setPreferredSize(new Dimension(167,118));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel4, 17, SpringLayout.EAST, miniPanel3);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel4, 20, SpringLayout.SOUTH, lblFinanceWrapped);

        JLabel lblMini4 = new JLabel("Days Active");
        lblMini4.setForeground(Color.white);
        lblMini4.setFont(smallFont);
        springLayout.putConstraint(SpringLayout.WEST, lblMini4, 16, SpringLayout.WEST, miniPanel4);
        springLayout.putConstraint(SpringLayout.SOUTH, lblMini4, 0, SpringLayout.VERTICAL_CENTER, miniPanel4);
        
        lblTimeSpent = new JLabel("1y 2m");
        lblTimeSpent.setForeground(Color.WHITE);
        lblTimeSpent.setFont(boldFont);
        springLayout.putConstraint(SpringLayout.WEST, lblTimeSpent, 16, SpringLayout.WEST, miniPanel4);
        springLayout.putConstraint(SpringLayout.NORTH, lblTimeSpent, 0, SpringLayout.VERTICAL_CENTER, miniPanel4);

        miniPanel4.add(lblMini4);
        miniPanel4.add(lblTimeSpent);

        JLabel test = new JLabel("testing");
        test.setFont(boldFont);
        test.setForeground(Color.white);
        springLayout.putConstraint(SpringLayout.WEST, test,  23, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, test,600, SpringLayout.NORTH, this);

        add(lblAppName);
        add(lblFinanceWrapped);
        add(btnShare);
        add(miniPanel1);
        add(miniPanel2);
        add(miniPanel3);
        add(miniPanel4);
        add(test);

    }

    private JLabel lblSpent;
    private JLabel lblSaved;
    private JLabel lblGoals;
    private JLabel lblTimeSpent;
    private com.financify.components.BarChart moneyChart;

    @SuppressWarnings("unused")
    private void testChart(){
        JParse jp = new JParse("com/financify/resources/statistics.json");
        JSONArray month_data = jp.getArrayFromKey("month_data");
        for(int i = 0; i < month_data.size();i++){
            JSONObject data = (JSONObject) month_data.get(i);
            String month = data.get("month").toString();
            Double money = (Double) data.get("money");
            moneyChart.addChartData(month, money);
        }
    }
    
}
