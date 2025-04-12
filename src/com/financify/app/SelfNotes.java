package com.financify.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.SpringLayout;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.financify.components.Animation;
import com.financify.components.CoverPanel;
import com.financify.components.ExtraJPanel;
import com.financify.components.RoundBtn;
import com.financify.components.RoundPanel;
import com.financify.utils.*;

public class SelfNotes extends ExtraJPanel{

	private JSONArray notesJSON;

	public SelfNotes(JSONArray notesJSON){
		this.notesJSON = notesJSON;
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
        JLabel lblSelfNotes = new JLabel("Self Notes");
        lblSelfNotes.setForeground(Color.WHITE);
        lblSelfNotes.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 32));        
        springLayout.putConstraint(SpringLayout.WEST, lblSelfNotes,  17, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblSelfNotes, 0, SpringLayout.SOUTH, lblAppName); 

		CoverPanel coverPanel = new CoverPanel();

		RoundBtn btnAdd = new RoundBtn("Add New Note");
		btnAdd.setPreferredSize(new Dimension(700, 60));
		btnAdd.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16));
		btnAdd.setForeground(Color.white);
		btnAdd.setBackground(Color.decode("#282828"));
		btnAdd.setBorderRadius(50);
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 30, SpringLayout.SOUTH, lblSelfNotes);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnAdd, -10, SpringLayout.HORIZONTAL_CENTER, this);

		add(lblAppName);
		add(lblSelfNotes);
		add(btnAdd);
		
	}

}
