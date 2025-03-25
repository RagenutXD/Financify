package com.financify.app;

import com.financify.utils.GlobalConstants;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class TestPanel extends JPanel{
	
	GlobalConstants globaleConstants = new GlobalConstants();

	public TestPanel(){
		SpringLayout springLayout = new SpringLayout();
		setBackground(Color.decode("#121212"));
		
		setLayout(springLayout);
		JLabel text = new JLabel("CARELESS whisper");
		text.setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
		text.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.WEST, text, globaleConstants.SIDE_PANEL_WIDTH+10, SpringLayout.WEST, this);
		add(text);
	}

}
