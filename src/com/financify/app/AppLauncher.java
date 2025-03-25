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

	private JPanel currentPanel;
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

		JPanel sidePanel = new JPanel();
		sidePanel.setSize(globalConstants.SIDE_PANEL_WIDTH, getHeight());
		sidePanel.setBackground(Color.decode("#282828"));

		add(sidePanel);

	}

	private void setCurrentPanel(JPanel nextPanel){

		if(currentPanel != null){
			remove(currentPanel);
		}

		currentPanel = nextPanel;
		add(currentPanel);
		revalidate();
		repaint();

	}

}
