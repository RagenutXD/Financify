package com.financify.app;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.EmptyStackException;

import com.financify.components.BlankWrapper;
import com.financify.components.ExtraJPanel;
import com.financify.components.RoundPanel;
import com.financify.utils.GlobalConstants;
import com.financify.utils.Utils;

public class PurchaseLogs extends ExtraJPanel{
	
	public PurchaseLogs(JSONArray purchaseLogsJSON){
		this.purchaseLogsJSON = purchaseLogsJSON;
		initComponents();
	}

	private Utils utils = new Utils();
	private JSONArray purchaseLogsJSON;

	private void initComponents(){
		SpringLayout springLayout = new SpringLayout();

        setPreferredSize(new Dimension(770, 900)); // Set preferred size larger than the scroll pane
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

		JLabel lblRecents = new JLabel("Recent Purchases");
		lblRecents.setForeground(Color.WHITE);
		lblRecents.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.NORTH, lblRecents, 30, SpringLayout.SOUTH, lblHomePage);
		springLayout.putConstraint(SpringLayout.WEST, lblRecents, 17, SpringLayout.WEST, this);

		JPanel recentLogContainer = new JPanel();	
		recentLogContainer.setLayout(new GridLayout(3, 1, 0,10));
		recentLogContainer.setOpaque(false);
		recentLogContainer.setMaximumSize(new Dimension( 723,0));
		recentLogContainer.setBackground(Color.green);
		springLayout.putConstraint(SpringLayout.NORTH, recentLogContainer, 10, SpringLayout.SOUTH, lblRecents);
		springLayout.putConstraint(SpringLayout.WEST, recentLogContainer, 17, SpringLayout.WEST, this);

		initRecents(recentLogContainer);

		JLabel lblAll = new JLabel("All Purchases");
		lblAll.setForeground(Color.WHITE);
		lblAll.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16));
		springLayout.putConstraint(SpringLayout.NORTH, lblAll, 10, SpringLayout.SOUTH, recentLogContainer);
		springLayout.putConstraint(SpringLayout.WEST, lblAll, 17, SpringLayout.WEST, this);

		JPanel allLogContainer = new JPanel();	
		allLogContainer.setLayout(new GridLayout(0, 1, 0,10));
		allLogContainer.setOpaque(false);
		allLogContainer.setMaximumSize(new Dimension(723,0));
		allLogContainer.setBackground(Color.green);

		initAllLogs(allLogContainer);

		BlankWrapper allLogWrapper = new BlankWrapper(allLogContainer);
		allLogWrapper.setPreferredSize(new Dimension((int) allLogContainer.getMaximumSize().getWidth(), 330));
		springLayout.putConstraint(SpringLayout.NORTH, allLogWrapper, 10, SpringLayout.SOUTH, lblAll);
		springLayout.putConstraint(SpringLayout.WEST, allLogWrapper, 17, SpringLayout.WEST, this);


		add(lblAppName);
		add(lblHomePage);
		add(lblRecents);
		add(recentLogContainer);
		add(lblAll);
		add(allLogWrapper);
	}

	private void initRecents(JPanel gridContainer){
		// if there have been less than 3 purchases, go with the size of the json
		int size = Math.min(purchaseLogsJSON.size(), 3);

		for(int i = purchaseLogsJSON.size()-1; i >= purchaseLogsJSON.size()-size; i--){
			JSONObject log = ((JSONObject)purchaseLogsJSON.get(i));
			String item = ((String)log.get("item"));
			String date = ((String)log.get("date"));
			double cost = (double)log.get("cost");
			addLogDisplay(gridContainer, item, date, cost, -1);
		}
	}

	private void initAllLogs(JPanel gridContainer){
		for(int i = 0; i < purchaseLogsJSON.size(); i++){
			JSONObject log = ((JSONObject)purchaseLogsJSON.get(i));
			String item = ((String)log.get("item"));
			String date = ((String)log.get("date"));
			double cost = (double)log.get("cost");
			addLogDisplay(gridContainer, item, date, cost, 0);
		}
	}

	private void addLogDisplay(JPanel gridContainer, String item, String date, double cost, int index){
		RoundPanel logContainer = new RoundPanel();
		SpringLayout springLayout = new SpringLayout();
		logContainer.setLayout(springLayout);
		logContainer.setBorderRadius(40);
		logContainer.setPreferredSize(new Dimension(((int)gridContainer.getMaximumSize().getWidth()), 100));
		logContainer.setBackground(Color.decode("#242424"));

		if(item.equals("")) item = "Unnamed Entry";
		JLabel lblItem = new JLabel(item);
		lblItem.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Bold.ttf", Font.PLAIN, 32));
		lblItem.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, lblItem, 10, SpringLayout.NORTH, logContainer);
		springLayout.putConstraint(SpringLayout.WEST, lblItem, 20, SpringLayout.WEST, logContainer);

		JLabel lblDate = new JLabel(date);
		lblDate.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 14));
		lblDate.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.NORTH, lblDate, 0, SpringLayout.SOUTH, lblItem);
		springLayout.putConstraint(SpringLayout.WEST, lblDate, 20, SpringLayout.WEST, logContainer);

		JLabel lblCost = new JLabel("P" + cost);
		lblCost.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-SemiBold.ttf", Font.PLAIN, 24));
		lblCost.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, lblCost, 0, SpringLayout.VERTICAL_CENTER, logContainer);
		springLayout.putConstraint(SpringLayout.EAST, lblCost, -20, SpringLayout.EAST, logContainer);

		logContainer.add(lblItem);
		logContainer.add(lblDate);
		logContainer.add(lblCost);

		gridContainer.add(logContainer, index);
	}


}
