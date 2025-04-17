/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.financify.app;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

import com.financify.components.Animation;
import com.financify.components.BarChart;
import com.financify.components.ExtraJPanel;
import com.financify.components.RoundBtn;
import com.financify.components.RoundPanel;
import com.financify.utils.*;
import com.financify.utils.GlobalConstants;

public class Statistics extends ExtraJPanel {

    private Utils utils = new Utils();
    private JLabel lblSpent;
    private JLabel lblSaved;
    private JLabel lblGoals;
    private JLabel lblTimeSpent;
    private BarChart moneyChart;

    private JSONObject globalStatsJSON;
    private JSONObject monthlySavedJSON;

    public Statistics(JSONObject globalStatsJSON, JSONObject monthlySavedJSON) {
        this.globalStatsJSON = globalStatsJSON;
        this.monthlySavedJSON = monthlySavedJSON;
        initComponents();
    }

    private void initComponents(){
        SpringLayout springLayout = new SpringLayout();
        
        setPreferredSize(new Dimension(770, 1000)); // Set preferred size larger than the scroll pane
        setBackground(Color.decode("#121212"));
        setLayout(springLayout);

        JLabel lblAppName = new JLabel("FINANCIFY");
        lblAppName.setForeground(Color.decode("#d2a2fc"));
        lblAppName.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 18));        
        springLayout.putConstraint(SpringLayout.WEST, lblAppName,  18, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblAppName, 24, SpringLayout.NORTH, this); 

        JLabel lblFinanceWrapped = new JLabel("Finance Wrapped");
        lblFinanceWrapped.setForeground(Color.WHITE);
        lblFinanceWrapped.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 32));        
        springLayout.putConstraint(SpringLayout.WEST, lblFinanceWrapped,  17, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblFinanceWrapped, 0, SpringLayout.SOUTH, lblAppName); 

        //share btn
        RoundBtn btnShare = new RoundBtn("Share");
        btnShare.setBorderRadius(20);
        btnShare.setBackground(Color.white);
        btnShare.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16));
        btnShare.setPreferredSize(new Dimension(90, 40));
        //TODO: find a way to not make this so annoying to line up
        springLayout.putConstraint(SpringLayout.WEST, btnShare, 643, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, btnShare, 36, SpringLayout.NORTH, this);
        btnShare.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                utils.playAnimation(new Animation() {
                    @Override
                    public void createAnimation(float t){
                        btnShare.setBackground(utils.interpolateColor(btnShare.getBackground(), Color.decode("#232323"), t));
                    }
                }, 0.2f);
                super.mouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                utils.playAnimation(new Animation() {
                    @Override
                    public void createAnimation(float t){
                        btnShare.setBackground(utils.interpolateColor(btnShare.getBackground(), Color.WHITE, t));
                    }
                }, 0.2f);
                super.mouseExited(e);
            }
        });        
        btnShare.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                moneyChart.playAnimation();
            }
            
        });


        Font smallFont = utils.createFont("com/financify/resources/Poppins/Poppins-Light.ttf", Font.PLAIN, 14);
        Font boldFont = utils.createFont("com/financify/resources/Poppins/Poppins-Bold.ttf", Font.PLAIN, 24);
        // small stats
        // this is gonna be ALLOT

        // MONEY SAVED
        RoundPanel miniPanel1 = new RoundPanel();
        miniPanel1.setLayout(springLayout);
        miniPanel1.setBorderRadius(20); 
        miniPanel1.setBackground(Color.decode("#282828"));
        miniPanel1.setPreferredSize(new Dimension(167,118));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel1,  17, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel1, 20, SpringLayout.SOUTH, lblFinanceWrapped);

        JLabel lblMini1 = new JLabel("Money Saved");
        lblMini1.setForeground(Color.white);
        lblMini1.setFont(smallFont);
        springLayout.putConstraint(SpringLayout.WEST, lblMini1, 16, SpringLayout.WEST, miniPanel1);
        springLayout.putConstraint(SpringLayout.SOUTH, lblMini1, 0, SpringLayout.VERTICAL_CENTER, miniPanel1);
        
        lblSaved = new JLabel(utils.formatBigNumbers((double) globalStatsJSON.get("totalSaved")));
        lblSaved.setForeground(Color.WHITE);
        lblSaved.setFont(boldFont);
        springLayout.putConstraint(SpringLayout.WEST, lblSaved, 16, SpringLayout.WEST, miniPanel1);
        springLayout.putConstraint(SpringLayout.NORTH, lblSaved, 0, SpringLayout.VERTICAL_CENTER, miniPanel1);

        miniPanel1.add(lblMini1);
        miniPanel1.add(lblSaved);

        // MONEY SPENT
        RoundPanel miniPanel2 = new RoundPanel();
        miniPanel2.setLayout(springLayout);
        miniPanel2.setBorderRadius(20); 
        miniPanel2.setBackground(Color.decode("#282828"));
        miniPanel2.setPreferredSize(new Dimension(167,118));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel2, 17, SpringLayout.EAST, miniPanel1);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel2, 20, SpringLayout.SOUTH, lblFinanceWrapped);
        
        JLabel lblMini2 = new JLabel("Money Spent");
        lblMini2.setForeground(Color.white);
        lblMini2.setFont(smallFont);
        springLayout.putConstraint(SpringLayout.WEST, lblMini2, 16, SpringLayout.WEST, miniPanel2);
        springLayout.putConstraint(SpringLayout.SOUTH, lblMini2, 0, SpringLayout.VERTICAL_CENTER, miniPanel2);
        
        lblSpent = new JLabel(utils.formatBigNumbers((double) globalStatsJSON.get("totalSpent")));
        lblSpent.setForeground(Color.WHITE);
        lblSpent.setFont(boldFont);
        springLayout.putConstraint(SpringLayout.WEST, lblSpent, 16, SpringLayout.WEST, miniPanel2);
        springLayout.putConstraint(SpringLayout.NORTH, lblSpent, 0, SpringLayout.VERTICAL_CENTER, miniPanel2);

        miniPanel2.add(lblMini2);
        miniPanel2.add(lblSpent);

        // GOALS REACHED
        RoundPanel miniPanel3 = new RoundPanel();
        miniPanel3.setLayout(springLayout);
        miniPanel3.setBorderRadius(20); 
        miniPanel3.setBackground(Color.decode("#282828"));
        miniPanel3.setPreferredSize(new Dimension(167,118));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel3, 17, SpringLayout.EAST, miniPanel2);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel3, 20, SpringLayout.SOUTH, lblFinanceWrapped);
        
        JLabel lblMini3 = new JLabel("Goals Reached");
        lblMini3.setForeground(Color.white);
        lblMini3.setFont(smallFont);
        springLayout.putConstraint(SpringLayout.WEST, lblMini3, 16, SpringLayout.WEST, miniPanel3);
        springLayout.putConstraint(SpringLayout.SOUTH, lblMini3, 0, SpringLayout.VERTICAL_CENTER, miniPanel3);
        

        // json simple treats any number as long so you have to keep using long
        lblGoals = new JLabel(utils.formatBigNumbers( (long)globalStatsJSON.get("goalsReached")));
        lblGoals.setForeground(Color.WHITE);
        lblGoals.setFont(boldFont);
        springLayout.putConstraint(SpringLayout.WEST, lblGoals, 16, SpringLayout.WEST, miniPanel3);
        springLayout.putConstraint(SpringLayout.NORTH, lblGoals, 0, SpringLayout.VERTICAL_CENTER, miniPanel3);

        miniPanel3.add(lblMini3);
        miniPanel3.add(lblGoals);

        // TIME SPENT
        RoundPanel miniPanel4 = new RoundPanel();
        miniPanel4.setLayout(springLayout);
        miniPanel4.setBorderRadius(20); 
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

        moneyChart = new BarChart(true, 1.0f);
        moneyChart.setPreferredSize(new Dimension(750, 300));
        moneyChart.setFont(smallFont);
        moneyChart.setMaxBarColor(Color.decode("#993ff4"));
        moneyChart.setBarColor(Color.decode("#282828"));
        moneyChart.setBorderRadius(20);
        moneyChart.setLabelColor(Color.white);
        springLayout.putConstraint(SpringLayout.NORTH, moneyChart, 20, SpringLayout.SOUTH,miniPanel1);
        springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, moneyChart, 0, SpringLayout.HORIZONTAL_CENTER, this);
        initChart();

        Font medFont = utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16);

        add(lblAppName);
        add(lblFinanceWrapped);
        add(btnShare);
        add(miniPanel1);
        add(miniPanel2);
        add(miniPanel3);
        add(miniPanel4);
        add(moneyChart);


    }

    private void initChart(){
        // get current year
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        JSONObject months = null;
        try {
            months = (JSONObject) monthlySavedJSON.get(String.valueOf(formatter.format(date)));
        } catch (Exception e) {
            System.err.println("HAPPY NEW YEAR!!... or maybe you just haven't saved for this year ");
            return;
        }

        for(String month: GlobalConstants.MONTHS){
            moneyChart.addChartData(month, (Double) months.get(month));
        }

    }

    
}
