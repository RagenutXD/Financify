/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.financify.statistics;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.financify.components.RoundPanel;
import com.financify.utils.*;

public class FinancifyStats extends JFrame {

    /**
     * Creates new form FinancifyStats
     */
    public FinancifyStats() {
        initComponents();
        //jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        
        //testChart();
        
    }

    private void initComponents(){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
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
        springLayout.putConstraint(SpringLayout.EAST, sidePanel, 60, SpringLayout.WEST, statisticsPanel);
        springLayout.putConstraint(SpringLayout.NORTH, sidePanel, 0, SpringLayout.NORTH, statisticsPanel);
        springLayout.putConstraint(SpringLayout.SOUTH, sidePanel, 150, SpringLayout.SOUTH, statisticsPanel);

        // header
        headerPanel = new RoundPanel();
        headerPanel.setRoundAll(0, 0, 100, 100);
        headerPanel.setBackground(new Color(0x282828));
        springLayout.putConstraint(SpringLayout.WEST, headerPanel, 0, SpringLayout.EAST, sidePanel);
        springLayout.putConstraint(SpringLayout.EAST, headerPanel, 0, SpringLayout.EAST, statisticsPanel);
        springLayout.putConstraint(SpringLayout.NORTH, headerPanel, 0, SpringLayout.NORTH, statisticsPanel);
        springLayout.putConstraint(SpringLayout.SOUTH, headerPanel, 150, SpringLayout.NORTH, headerPanel);

        lblTitle = new JLabel("Finance Wrapped");
        lblTitle.setForeground(Color.white);
        lblTitle.setFont(new CustomFont().createFont("com/financify/resources/Poppins/Poppins-Bold.ttf", Font.PLAIN, 32));
        springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 20, SpringLayout.NORTH, headerPanel);
        springLayout.putConstraint(SpringLayout.WEST, lblTitle, 20, SpringLayout.WEST, headerPanel);
        
        headerPanel.add(lblTitle);

        statisticsPanel.add(sidePanel);
        statisticsPanel.add(headerPanel);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel statisticsPanel;
    private JLabel lblTitle;
    private JScrollPane jScrollPane1;
    private JLabel lblMoney;
    private com.financify.components.BarChart moneyChart;
    private com.financify.components.RoundPanel headerPanel;
    private JPanel sidePanel;
    // End of variables declaration//GEN-END:variables

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
