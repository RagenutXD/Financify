package com.financify.app;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.financify.components.Animation;
import com.financify.components.ExtraJPanel;
import com.financify.components.RoundBtn;
import com.financify.components.RoundPanel;
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
	private JLayeredPane layeredPane;
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

		setCurrentPanel(new Home(globalStatsJSON, monthlySavedJSON, purchaseLogsJSON));
	}


	private void initComponents(){

		setSize(GlobalConstants.WINDOW_WIDTH, GlobalConstants.WINDOW_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Financify");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(springLayout);

		Color hoverColor = Color.decode("#202020");
		Color sidePanelColor = Color.decode("#282828");
		Color pressColor = Color.decode("#101010");

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(springLayout);
		sidePanel.setPreferredSize(new Dimension(GlobalConstants.SIDE_PANEL_WIDTH, getHeight()));
		sidePanel.setBackground(sidePanelColor);
		sidePanel.setBounds(0, 0, GlobalConstants.SIDE_PANEL_WIDTH, getHeight());
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
		btnHome.setBackground(sidePanelColor);
		springLayout.putConstraint(SpringLayout.NORTH, btnHome, 30, SpringLayout.SOUTH, logo);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnHome, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);
		btnHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnHome.setBackground(utils.interpolateColor(sidePanelColor, hoverColor, t));	
					}
				}, 0.1f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnHome.setBackground(utils.interpolateColor(hoverColor, sidePanelColor, t));	
					}
				}, 0.1f);
				super.mouseExited(e);
			}
		});
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						if(btnHome.isHovered){
							btnHome.setBackground(utils.interpolateColor(pressColor, hoverColor, t*t));	
						}else{
							btnHome.setBackground(utils.interpolateColor(pressColor, sidePanelColor, t*t));	
							
						}
					}	
				}, 0.3f);
				setCurrentPanel(new Home(globalStatsJSON, monthlySavedJSON, purchaseLogsJSON));
			}
		});

		RoundBtn btnPurchaseLogs = new RoundBtn("Logs");
		btnPurchaseLogs.setFont(fontBtn);
		btnPurchaseLogs.setPreferredSize(new Dimension(100, 40));
		btnPurchaseLogs.setBorderRadius(30);
		btnPurchaseLogs.setForeground(Color.white);
		btnPurchaseLogs.setBackground(sidePanelColor);
		springLayout.putConstraint(SpringLayout.NORTH, btnPurchaseLogs, 17, SpringLayout.SOUTH, btnHome);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnPurchaseLogs, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);
		btnPurchaseLogs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnPurchaseLogs.setBackground(utils.interpolateColor(sidePanelColor, hoverColor, t));	
					}
				}, 0.1f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnPurchaseLogs.setBackground(utils.interpolateColor(hoverColor, sidePanelColor, t));	
					}
				}, 0.1f);
				super.mouseExited(e);
			}
		});
		btnPurchaseLogs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						if(btnPurchaseLogs.isHovered){
							btnPurchaseLogs.setBackground(utils.interpolateColor(pressColor, hoverColor, t*t));	
						}else{
							btnPurchaseLogs.setBackground(utils.interpolateColor(pressColor, sidePanelColor, t*t));	
							
						}
					}	
				}, 0.3f);
				setCurrentPanel(new PurchaseLogs());
			}
		});

		RoundBtn btnSelfNotes = new RoundBtn("Self Notes");
		btnSelfNotes.setFont(fontBtn);
		btnSelfNotes.setPreferredSize(new Dimension(100, 40));
		btnSelfNotes.setBorderRadius(30);
		btnSelfNotes.setForeground(Color.white);
		btnSelfNotes.setBackground(sidePanelColor);
		springLayout.putConstraint(SpringLayout.NORTH, btnSelfNotes, 17, SpringLayout.SOUTH, btnPurchaseLogs);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSelfNotes, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);
		btnSelfNotes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnSelfNotes.setBackground(utils.interpolateColor(sidePanelColor, hoverColor, t));	
					}
				}, 0.1f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnSelfNotes.setBackground(utils.interpolateColor(hoverColor, sidePanelColor, t));	
					}
				}, 0.1f);
				super.mouseExited(e);
			}
		});
		btnSelfNotes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						if(btnSelfNotes.isHovered){
							btnSelfNotes.setBackground(utils.interpolateColor(pressColor, hoverColor, t*t));	
						}else{
							btnSelfNotes.setBackground(utils.interpolateColor(pressColor, sidePanelColor, t*t));	
							
						}
					}	
				}, 0.3f);
				setCurrentPanel(new SelfNotes(notesJSON));
			}
		});

		RoundBtn btnStatistics = new RoundBtn("Statistics");
		btnStatistics.setFont(fontBtn);
		btnStatistics.setPreferredSize(new Dimension(100, 40));
		btnStatistics.setBorderRadius(30);
		btnStatistics.setForeground(Color.white);
		btnStatistics.setBackground(sidePanelColor);
		springLayout.putConstraint(SpringLayout.NORTH, btnStatistics, 17, SpringLayout.SOUTH, btnSelfNotes);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnStatistics, 0, SpringLayout.HORIZONTAL_CENTER, sidePanel);
		btnStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnStatistics.setBackground(utils.interpolateColor(sidePanelColor, hoverColor, t));	
					}
				}, 0.1f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						btnStatistics.setBackground(utils.interpolateColor(hoverColor, sidePanelColor, t));	
					}
				}, 0.1f);
				super.mouseExited(e);
			}
		});
		btnStatistics.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				utils.playAnimation(new Animation() {
					@Override
					public void createAnimation(float t) {
						if(btnStatistics.isHovered){
							btnStatistics.setBackground(utils.interpolateColor(pressColor, hoverColor, t*t));	
						}else{
							btnStatistics.setBackground(utils.interpolateColor(pressColor, sidePanelColor, t*t));	
							
						}
					}	
				}, 0.3f);
				setCurrentPanel(new Statistics(globalStatsJSON, monthlySavedJSON));
			}
		});

		scrollPane = new JScrollPane();

		sidePanel.add(logo);
		sidePanel.add(btnHome);
		sidePanel.add(btnPurchaseLogs);
		sidePanel.add(btnSelfNotes);
		sidePanel.add(btnStatistics);



		// use layered panes instead of just adding directly 
		layeredPane = getLayeredPane();	
		layeredPane.add(sidePanel, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(scrollPane, JLayeredPane.DEFAULT_LAYER);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try{
					currentPanel.onExit();
				}catch(NullPointerException nullPointerException){
					System.err.println("This panel does not exist or isn't an extraJPanel");
					nullPointerException.printStackTrace();
				}
				super.windowClosing(e);
			}	
		});

 	}

	private void setCurrentPanel(ExtraJPanel nextPanel) {
	    if (currentPanel != null) {
			currentPanel.onExit();
			currentPanel = null;
	    }

	    currentPanel = nextPanel;
		scrollPane.setViewportView(currentPanel);
	    scrollPane.revalidate();
	    scrollPane.repaint();

		// for the scrollbars and the border of the scroll pane
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Ensure vertical scrolling is enabled
		scrollPane.setBorder(null);

		scrollPane.setBounds(GlobalConstants.SIDE_PANEL_WIDTH, 0, getWidth() - GlobalConstants.SIDE_PANEL_WIDTH, getHeight());

		
		revalidate();
		repaint();
	}

	private JSONObject globalStatsJSON;
    private JSONObject monthlySavedJSON;
	private JSONArray notesJSON;
	private JSONArray purchaseLogsJSON;

    private void initJSONObjects(){
        try{

            JSONParser parser = new JSONParser();
            globalStatsJSON = (JSONObject) parser.parse(utils.fileToString("\\global_stats.json"));
			monthlySavedJSON = (JSONObject) parser.parse(utils.fileToString("\\dates.json"));
			notesJSON = (JSONArray) parser.parse(utils.fileToString("\\notes.json"));
			purchaseLogsJSON = (JSONArray) parser.parse(utils.fileToString("\\purchase_logs.json"));

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

			FileWriter fwNotes = new FileWriter(GlobalConstants.BASE_PATH + "\\notes.json");
			fwNotes.write("[\n]");
			fwNotes.flush();
			fwNotes.close();

			FileWriter fwPurchaseLogs = new FileWriter(GlobalConstants.BASE_PATH + "\\purchase_logs.json");
			fwPurchaseLogs.write("[\n]");
			fwPurchaseLogs.flush();
			fwPurchaseLogs.close();

		//}
	}

	private void createDirsDebug() throws IOException{
		/*
		 * This method is only used in testing so that i dont have to repeatedly delete the files
		 */
		int answer = Integer.valueOf(JOptionPane.showInputDialog("This is used for debugging so that devs don't have to repeatedly keep deleting the Financify folder\nIf this is your first time opening the app type 1\nUse existing data(0)\nRefresh data(1)"));
		if(answer==0){
			if(new File(GlobalConstants.BASE_PATH).mkdir()){
				createDirs();	
			}
		}else{
			createDirs();
		}

	}

}
