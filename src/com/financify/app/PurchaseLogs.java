package com.financify.app;

import javax.swing.*;

import java.awt.*;

import com.financify.components.ExtraJPanel;
import com.financify.utils.GlobalConstants;
import com.financify.utils.Utils;

public class PurchaseLogs extends ExtraJPanel{
	
	public PurchaseLogs(){
		initComponents();
	}

	private Utils utils = new Utils();

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
        JLabel lblHomePage = new JLabel("Purchase Logs");
        lblHomePage.setForeground(Color.WHITE);
        lblHomePage.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 32));        
        springLayout.putConstraint(SpringLayout.WEST, lblHomePage,  17, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblHomePage, 0, SpringLayout.SOUTH, lblAppName); 

		add(lblAppName);
		add(lblHomePage);
	}


}
