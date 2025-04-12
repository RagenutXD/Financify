package com.financify.app;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.financify.components.Animation;
import com.financify.components.ExtraJPanel;
import com.financify.components.RoundBtn;
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
	private ExtraJPanel currentPanel;
	SpringLayout springLayout = new SpringLayout();
	Utils utils = new Utils();

	public AppLauncher(){
		try{
			createDirsDebug();
		} catch (IOException e){
			System.err.println("An error occured while creating the files");
			e.printStackTrace();
		}
		initJSONObjects();
		initComponents();
	}


	private void initComponents(){

		setSize(GlobalConstants.WINDOW_WIDTH, GlobalConstants.WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Financify");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(springLayout);

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(springLayout);
		sidePanel.setPreferredSize(new Dimension(GlobalConstants.SIDE_PANEL_WIDTH, getHeight()));
		sidePanel.setBackground(Color.decode("#282828"));
		springLayout.putConstraint(SpringLayout.WEST, sidePanel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WIDTH, sidePanel, 130, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, sidePanel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, sidePanel, 0, SpringLayout.SOUTH, getContentPane());

		ImageIcon _logoIcon = new ImageIcon(getClass().getClassLoader().getResource("com/financify/resources/logo/logo_colored.png"));
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(_logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		springLayout.putConstraint(SpringLayout.NORTH, logo, 7, SpringLayout.NORTH, sidePanel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, logo, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);

		Font fontBtn = utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 14);

		RoundBtn btnHome = new RoundBtn("Home");
		btnHome.setFont(fontBtn);
		btnHome.setPreferredSize(new Dimension(100, 40));
		btnHome.setBorderRadius(30);
		btnHome.setForeground(Color.white);
		btnHome.setBackground(Color.decode("#282828"));
		springLayout.putConstraint(SpringLayout.NORTH, btnHome, 30, SpringLayout.SOUTH, logo);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnHome, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnHome.setBackground(utils.interpolateColor(Color.decode("#282828"), Color.decode("#121212"), t));	
					}
				}, 0.1f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnHome.setBackground(utils.interpolateColor(Color.decode("#121212"), Color.decode("#282828"), t));	
					}
				}, 0.1f);
				super.mouseExited(e);
			}
		});

		RoundBtn btnStatistics = new RoundBtn("Statistics");
		btnStatistics.setFont(fontBtn);
		btnStatistics.setPreferredSize(new Dimension(100, 40));
		btnStatistics.setBorderRadius(30);
		btnStatistics.setForeground(Color.white);
		btnStatistics.setBackground(Color.decode("#282828"));
		springLayout.putConstraint(SpringLayout.NORTH, btnStatistics, 17, SpringLayout.SOUTH, btnHome);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnStatistics, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);
		btnStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnStatistics.setBackground(utils.interpolateColor(Color.decode("#282828"), Color.decode("#121212"), t));	
					}
				}, 0.1f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnStatistics.setBackground(utils.interpolateColor(Color.decode("#121212"), Color.decode("#282828"), t));	
					}
				}, 0.1f);
				super.mouseExited(e);
			}
		});
		btnStatistics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentPanel(new Statistics(globalStatsJSON, monthlySavedJSON));
			}
		});

		sidePanel.add(logo);
		sidePanel.add(btnHome);
		sidePanel.add(btnStatistics);

		add(sidePanel);

 	}

	private void setCurrentPanel(ExtraJPanel nextPanel) {
		System.out.println("hit");
	    if (currentPanel != null) {
			currentPanel.onExit();
	        remove(currentPanel);
			System.out.println("woah exists>");
	    }

	    currentPanel = nextPanel;
	    scrollPane = new JScrollPane(currentPanel);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Ensure vertical scrolling is enabled
		scrollPane.setBorder(null);
	    springLayout.putConstraint(SpringLayout.WEST, scrollPane, GlobalConstants.SIDE_PANEL_WIDTH, SpringLayout.WEST, getContentPane());
	    springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, getContentPane());
	    springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0, SpringLayout.NORTH, getContentPane());
	    springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0, SpringLayout.SOUTH, getContentPane());

	    currentPanel.revalidate();
	    currentPanel.repaint();
	    scrollPane.revalidate();
	    scrollPane.repaint();
	    add(scrollPane);
	}

	private JSONObject globalStatsJSON;
    private JSONObject monthlySavedJSON;

    private void initJSONObjects(){
        try{
            JSONParser parser = new JSONParser();
            globalStatsJSON = (JSONObject) parser.parse(utils.fileToString("\\global_stats.json"));
			monthlySavedJSON = (JSONObject) parser.parse(utils.fileToString("\\dates.json"));


            //FileWriter file = new FileWriter("src/com/financify/resources/global_stats.json");
            //file.write(globalStatsJSON.toString());
            //file.flush();
            //file.close();

            
        }catch(Exception e){
            System.out.println("Error in initializing jsons");
            e.printStackTrace();
        }
    }

	private void createDirs() throws IOException {
		// opens the user C:/User/<user> folder
		//if(new File(GlobalConstants.BASE_PATH).mkdir()){

			FileWriter fwDateJSON = new FileWriter(GlobalConstants.BASE_PATH + "\\dates.json");
			LocalDate d = LocalDate.now();
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy");
			String dateTemp = "{ \"" + String.valueOf(df.format(d)) + "\": " + GlobalConstants.DATE_JSON_TEMPLATE + "}";
			fwDateJSON.write(dateTemp);
			fwDateJSON.flush();
			fwDateJSON.close();

			FileWriter fwGlobals = new FileWriter(GlobalConstants.BASE_PATH + "\\global_stats.json");
			String globalTemp = utils.srcFileToString("com/financify/resources/global_stats.json");
			fwGlobals.write(globalTemp);
			fwGlobals.flush();
			fwGlobals.close();

		//}
	}

	private void createDirsDebug() throws IOException{
		/*
		 * This method is only used in testing so that i dont have to repeatedly delete the files
		 */
		int answer = Integer.valueOf(JOptionPane.showInputDialog("Use existing data(0)\nRefresh data(1)"));
		if(answer==0){
			if(new File(GlobalConstants.BASE_PATH).mkdir()){
				createDirs();	
			}
		}else{
			createDirs();
		}

	}

}
