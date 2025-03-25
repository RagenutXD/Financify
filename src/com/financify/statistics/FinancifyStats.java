/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.financify.statistics;

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

public class FinancifyStats extends JFrame {

    private CustomFont customFontController = new CustomFont();

    public FinancifyStats() {
        initComponents();
        //jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        
        //testChart();
        
    }

    private void initComponents(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setTitle("Financify");
        setLocationRelativeTo(null);

        // COMPONENTS

        SpringLayout springLayout = new SpringLayout();
        
        statisticsPanel = new JPanel();
        statisticsPanel.setLayout(springLayout);
        statisticsPanel.setBackground(new Color(0x121212));

        
        //sidebar
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(0x282828));
        springLayout.putConstraint(SpringLayout.WEST, sidePanel, 0, SpringLayout.WEST, statisticsPanel);
        springLayout.putConstraint(SpringLayout.EAST, sidePanel, 130, SpringLayout.WEST, statisticsPanel);
        springLayout.putConstraint(SpringLayout.NORTH, sidePanel, 0, SpringLayout.NORTH, statisticsPanel);
        springLayout.putConstraint(SpringLayout.SOUTH, sidePanel, 0, SpringLayout.SOUTH, statisticsPanel);

        lblAppName = new JLabel("FINANCIFY");
        lblAppName.setForeground(Color.decode("#d2a2fc"));
        lblAppName.setFont(customFontController.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 18));        
        springLayout.putConstraint(SpringLayout.WEST, lblAppName, 18, SpringLayout.EAST, sidePanel); 
        springLayout.putConstraint(SpringLayout.NORTH, lblAppName, 24, SpringLayout.NORTH, statisticsPanel); 

        lblFinanceWrapped = new JLabel("Finance Wrapped");
        lblFinanceWrapped.setForeground(Color.WHITE);
        lblFinanceWrapped.setFont(customFontController.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 32));        
        springLayout.putConstraint(SpringLayout.WEST, lblFinanceWrapped, 17, SpringLayout.EAST, sidePanel); 
        springLayout.putConstraint(SpringLayout.NORTH, lblFinanceWrapped, 0, SpringLayout.SOUTH, lblAppName); 

        //share btn
        btnShare = new RoundBtn("Share");
        btnShare.setBorderRadius(20);
        btnShare.setBackground(Color.white);
        btnShare.setFont(customFontController.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16));
        springLayout.putConstraint(SpringLayout.EAST, btnShare, -17, SpringLayout.EAST, statisticsPanel);
        springLayout.putConstraint(SpringLayout.WEST, btnShare, -80, SpringLayout.EAST, btnShare);
        springLayout.putConstraint(SpringLayout.NORTH, btnShare, 36, SpringLayout.NORTH, statisticsPanel);
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

        // small stats
        // this is gonna be ALLOT
        miniPanel1 = new RoundPanel();
        miniPanel1.setRoundAll(20); 
        miniPanel1.setBackground(Color.decode("#282828"));
        miniPanel1.setPreferredSize(new Dimension(167,112));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel1, 17, SpringLayout.EAST, sidePanel);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel1, 20, SpringLayout.SOUTH, lblFinanceWrapped);

        miniPanel2 = new RoundPanel();
        miniPanel2.setRoundAll(20); 
        miniPanel2.setBackground(Color.decode("#282828"));
        miniPanel2.setPreferredSize(new Dimension(167,112));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel2, 17, SpringLayout.EAST, miniPanel1);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel2, 20, SpringLayout.SOUTH, lblFinanceWrapped);
        
        miniPanel3 = new RoundPanel();
        miniPanel3.setRoundAll(20); 
        miniPanel3.setBackground(Color.decode("#282828"));
        miniPanel3.setPreferredSize(new Dimension(167,112));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel3, 17, SpringLayout.EAST, miniPanel2);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel3, 20, SpringLayout.SOUTH, lblFinanceWrapped);

        miniPanel4 = new RoundPanel();
        miniPanel4.setRoundAll(20); 
        miniPanel4.setBackground(Color.decode("#282828"));
        miniPanel4.setPreferredSize(new Dimension(167,112));
        springLayout.putConstraint(SpringLayout.WEST, miniPanel4, 17, SpringLayout.EAST, miniPanel3);
        springLayout.putConstraint(SpringLayout.NORTH, miniPanel4, 20, SpringLayout.SOUTH, lblFinanceWrapped);
        
        statisticsPanel.add(sidePanel);
        statisticsPanel.add(lblAppName);
        statisticsPanel.add(lblFinanceWrapped);
        statisticsPanel.add(btnShare);
        statisticsPanel.add(miniPanel1);
        statisticsPanel.add(miniPanel2);
        statisticsPanel.add(miniPanel3);
        statisticsPanel.add(miniPanel4);

        this.getContentPane().add(statisticsPanel);

    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FinancifyStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinancifyStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinancifyStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinancifyStats.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinancifyStats().setVisible(true);
            }
        });
    }

    private JPanel statisticsPanel;
    private JLabel lblAppName;
    private JLabel lblFinanceWrapped;
    private RoundBtn btnShare;
    private com.financify.components.BarChart moneyChart;
    private JPanel sidePanel;
    private RoundPanel miniPanel1; 
    private RoundPanel miniPanel2; 
    private RoundPanel miniPanel3; 
    private RoundPanel miniPanel4; 

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
