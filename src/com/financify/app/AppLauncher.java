package com.financify.app;

import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;

import com.financify.utils.*;

public class AppLauncher extends JFrame{

	public static void main (String[] args){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppLauncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppLauncher().setVisible(true);
            }
        });
    }

	private JScrollPane scrollPane;
	private JPanel currentPanel;
	SpringLayout springLayout = new SpringLayout();
	GlobalConstants globalConstants = new GlobalConstants();
	CustomFont customFont = new CustomFont();

	public AppLauncher(){
		initComponents();
		setCurrentPanel(new Statistics());
	}

	private void initComponents(){

		setSize(globalConstants.WINDOW_WIDTH, globalConstants.WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setTitle("Financify");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(springLayout);

		JPanel sidePanel = new JPanel();
		sidePanel.setSize(globalConstants.SIDE_PANEL_WIDTH, getHeight());
		sidePanel.setBackground(Color.decode("#282828"));
		springLayout.putConstraint(SpringLayout.WEST, sidePanel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WIDTH, sidePanel, 130, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, sidePanel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, sidePanel, 0, SpringLayout.SOUTH, getContentPane());

		add(sidePanel);

	}

	private void setCurrentPanel(JPanel nextPanel) {
	    if (currentPanel != null) {
	        remove(currentPanel);
	    }

	    currentPanel = nextPanel;
	    scrollPane = new JScrollPane(currentPanel);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Ensure vertical scrolling is enabled
		scrollPane.setBorder(null);
	    springLayout.putConstraint(SpringLayout.WEST, scrollPane, globalConstants.SIDE_PANEL_WIDTH, SpringLayout.WEST, getContentPane());
	    springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, getContentPane());
	    springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, getContentPane());
	    springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, getContentPane());

	    currentPanel.revalidate();
	    currentPanel.repaint();
	    scrollPane.revalidate();
	    scrollPane.repaint();
	    add(scrollPane);
	}

}
