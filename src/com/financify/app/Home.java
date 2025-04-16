package com.financify.app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.event.ObjectChangeListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.json.simple.JSONObject;

import com.financify.components.Animation;
import com.financify.components.BlankWrapper;
import com.financify.components.CircleGradient;
import com.financify.components.CoverPanel;
import com.financify.components.ExtraJPanel;
import com.financify.components.RoundBtn;
import com.financify.components.RoundPanel;
import com.financify.utils.*;;

public class Home extends ExtraJPanel{
	
	private Utils utils = new Utils();
	private JSONObject globalStatsJSON;
	private JSONObject monthlySavedJSON;
	private JLabel lblMoney;
	private CoverPanel saveCoverPanel;
	private CoverPanel spendCoverPanel;
	
	public Home(JSONObject globalStatsJSON, JSONObject monthlySavedJSON){
		this.globalStatsJSON = globalStatsJSON;
		this.monthlySavedJSON = monthlySavedJSON;
		initComponents();
	}

	private void initComponents(){
		SpringLayout springLayout = new SpringLayout();

        setPreferredSize(new Dimension(770, GlobalConstants.WINDOW_HEIGHT)); // Set preferred size larger than the scroll pane
		setBackground(Color.decode("#121212"));
		setLayout(springLayout);

        JLabel lblAppName = new JLabel("Welcome To");
        lblAppName.setForeground(Color.decode("#d2a2fc"));
        lblAppName.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 18));        
        springLayout.putConstraint(SpringLayout.WEST, lblAppName,  18, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblAppName, 24, SpringLayout.NORTH, this); 
        JLabel lblHomePage = new JLabel("FINANCIFY");
        lblHomePage.setForeground(Color.WHITE);
        lblHomePage.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Medium.ttf", Font.PLAIN, 32));        
        springLayout.putConstraint(SpringLayout.WEST, lblHomePage,  17, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, lblHomePage, 0, SpringLayout.SOUTH, lblAppName); 

		CircleGradient cGrad = new CircleGradient(150);
		cGrad.setBackground(Color.decode("#993ff4"));
		cGrad.setFadeIntensity(0.0f);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, cGrad, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.NORTH, cGrad, 80, SpringLayout.NORTH, lblHomePage);

		lblMoney = new JLabel();
		double moni = (double) globalStatsJSON.get("currentSaved");	
		lblMoney.setText( "P" + String.valueOf(moni));
		lblMoney.setForeground(Color.white);
		lblMoney.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Bold.ttf", Font.PLAIN, 64));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblMoney, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, lblMoney, 0, SpringLayout.VERTICAL_CENTER, cGrad);


		JLabel lbl0 = new JLabel("You Have Saved");
		lbl0.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 16));
		lbl0.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lbl0, 0, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lbl0, 17, SpringLayout.NORTH, lblMoney);

		int offsetForBtn = 50 + 12; // 50 is half the btn width and 12 is half the target margin (24)

		/*
		 * Trying smth new here, instead of having the cover panels in the initComponents
		 * I'll clean it up abit and just make the cover panels a private variable
		 * so ill initialize it in another method 
		 */

		initSaveCoverPanel();
		initSpendCoverPanel();

		
		RoundBtn btnSave = new RoundBtn("Save");
		btnSave.setPreferredSize(new Dimension(100,50));
		btnSave.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-SemiBold.ttf", Font.PLAIN, 16));
		btnSave.setBorderRadius(40);
		btnSave.setBackground(Color.decode("#993ff4"));
		btnSave.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSave, offsetForBtn, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnSave, 0, SpringLayout.SOUTH, lblMoney);
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						btnSave.setBackground(utils.interpolateColor(btnSave.getBackground(), Color.decode("#771dd2"), t));
						btnSave.setForeground(utils.interpolateColor(btnSave.getForeground(), Color.decode("#aaaaaa"), t));
					}
				}, 0.2f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						btnSave.setBackground(utils.interpolateColor(btnSave.getBackground(), Color.decode("#993ff4"), t));
						btnSave.setForeground(utils.interpolateColor(btnSave.getForeground(), Color.white, t));
					}
				}, 0.2f);
				super.mouseExited(e);
			}
		});
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveCoverPanel.cover(getRootPane());
			}
			
		});

		RoundBtn btnSpend = new RoundBtn("Spend");
		btnSpend.setPreferredSize(new Dimension(100,50));
		btnSpend.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-SemiBold.ttf", Font.PLAIN, 16));
		btnSpend.setBorderRadius(40);
		btnSpend.setBorderColor(Color.white);
		btnSpend.setBorderThickness(1);
		btnSpend.setBackground(new Color(0,0,0,0));
		btnSpend.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSpend, -offsetForBtn, SpringLayout.HORIZONTAL_CENTER, this);
		springLayout.putConstraint(SpringLayout.NORTH, btnSpend, 0, SpringLayout.SOUTH, lblMoney);
		btnSpend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spendCoverPanel.cover(getRootPane());	
			}	
		});

		btnSpend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						btnSpend.setBorderColor(utils.interpolateColor(btnSpend.getBorderColor(), Color.decode("#aaaaaa"), t));
						btnSpend.setForeground(utils.interpolateColor(btnSpend.getForeground(), Color.decode("#aaaaaa"), t));
					}
				}, 0.2f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						btnSpend.setBorderColor(utils.interpolateColor(btnSpend.getBorderColor(), Color.white, t));
						btnSpend.setForeground(utils.interpolateColor(btnSpend.getForeground(), Color.white, t));
					}
				}, 0.2f);
				super.mouseExited(e);
			}
		});

		utils.playAnimation(new Animation(){
			@Override
			public void createAnimation(float t) {
				cGrad.setFadeIntensity((float) utils.interpolateDouble(0.5f, 0.0f, (float) Math.sqrt(Math.sqrt(t))));
				cGrad.setRadius(utils.interpolateInt(190, 150, (float) Math.sqrt(Math.sqrt(t))));
				cGrad.revalidate();
				cGrad.repaint();
				double finalMon = (double) globalStatsJSON.get("currentSaved");
				double interpMon =  utils.interpolateDouble(finalMon/2, finalMon, ShapeFunctions.easeOutExpo(t)) ;
				float roundedMon = (float) Math.round(interpMon * 100) / 100;
				lblMoney.setText( "P" +String.valueOf(roundedMon) );
				lblMoney.revalidate();
				lblMoney.repaint();
			}			
		}, 0.8f);


		add(lblAppName);
		add(lblHomePage);
		add(lblMoney);
		add(lbl0);
		add(btnSave);
		add(btnSpend);

		// add this last so it stays at the back
		// im too lazy to jlayered pane this ho
		add(cGrad);

	}

	private void initSaveCoverPanel() {
		saveCoverPanel = new CoverPanel();
		SpringLayout springLayout = new SpringLayout();
		saveCoverPanel.setLayout(springLayout);

		Font inputFont = utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 14);
		Font lblInputFont = utils.createFont("com/financify/resources/Poppins/Poppins-SemiBold.ttf", Font.PLAIN, 18); 

		RoundPanel _savePanel = new RoundPanel();
		_savePanel.setLayout(springLayout);
		_savePanel.setBackground(Color.decode("#282828"));
		_savePanel.setBorderRadius(40);

		JLabel lblTxt = new JLabel("Save Amount");
		lblTxt.setFont(lblInputFont);
		lblTxt.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblTxt, 30, SpringLayout.NORTH, _savePanel);
		springLayout.putConstraint(SpringLayout.WEST, lblTxt, 30, SpringLayout.WEST, _savePanel);

		JTextField txtSave = new JTextField();
		txtSave.setFont(inputFont);
		txtSave.setPreferredSize(new Dimension(240, 45));
		txtSave.setDocument(new MoneyTextInput());
		txtSave.setBorder(new EmptyBorder(10,10,10,10));
		txtSave.setForeground(Color.white);
		txtSave.setBackground(Color.decode("#121212"));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtSave, 0, SpringLayout.HORIZONTAL_CENTER, _savePanel);
		springLayout.putConstraint(SpringLayout.NORTH, txtSave, 0, SpringLayout.SOUTH, lblTxt);

		RoundBtn btnAdd = new RoundBtn("Add");
		btnAdd.setPreferredSize(new Dimension(100, 40));
		btnAdd.setFont(inputFont);
		btnAdd.setBorderRadius(30);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(Color.decode("#993ff4"));
		springLayout.putConstraint(SpringLayout.SOUTH, btnAdd, -30, SpringLayout.SOUTH, _savePanel);
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, -30, SpringLayout.EAST, _savePanel);
		btnAdd.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				// get the amount saved and round it
				Double money = Double.parseDouble(txtSave.getText());
				money = (double) Math.round(money*100)/100;
				System.out.println(money);

				// put it into the json object
				double totalSaved = (double) globalStatsJSON.get("totalSaved") + money;
				double currentSaved = (double) globalStatsJSON.get("currentSaved") + money;

				// round values here too so that value wont be a repeating number, ex: 12.33333...
				totalSaved = (double) Math.round(totalSaved*100)/100;
				currentSaved = (double) Math.round(currentSaved*100)/100;

				globalStatsJSON.put("totalSaved", totalSaved);
				globalStatsJSON.put("currentSaved", currentSaved);

				// update money display
				lblMoney.setText("P" + currentSaved);

				// UPDATE THE VALUES ON DATES.JSON
				LocalDate d = LocalDate.now(); // get the month and year of today first
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy MMM");

				// separate the date format like 2025 Mar
				// into an array where the index 0  is the year and index 1 is the month 
				String[] currDate = df.format(d).split(" ");
					
				// put into the json
				JSONObject year = (JSONObject) monthlySavedJSON.get(currDate[0]);
				double savedThisMonth = (double) year.get(currDate[1]) + money;
				savedThisMonth = (double) Math.round(savedThisMonth*100)/100;
				year.put(currDate[1], savedThisMonth); 
				monthlySavedJSON.put(currDate[0], year);
				
				saveCoverPanel.uncover();
				txtSave.setText("");
			}		
		});

		RoundBtn btnCancel = new RoundBtn("Cancel");
		btnCancel.setPreferredSize(new Dimension(100, 40));
		btnCancel.setFont(inputFont);
		btnCancel.setBorderThickness(1);
		btnCancel.setBackground(new Color(0,0,0,0));
		btnCancel.setBorderColor(Color.white);
		btnCancel.setBorderRadius(30);
		btnCancel.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -30, SpringLayout.SOUTH, _savePanel);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 30, SpringLayout.WEST, _savePanel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveCoverPanel.uncover();
				txtSave.setText("");
			}		
		});

		_savePanel.add(lblTxt);
		_savePanel.add(txtSave);
		_savePanel.add(btnAdd);
		_savePanel.add(btnCancel);

		BlankWrapper savePanel = new BlankWrapper(_savePanel);
		savePanel.setPreferredSize(new Dimension(300, 200));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, savePanel, 0, SpringLayout.HORIZONTAL_CENTER, saveCoverPanel);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, savePanel, 0, SpringLayout.VERTICAL_CENTER, saveCoverPanel);

		saveCoverPanel.add(savePanel);
	}

	private void initSpendCoverPanel() {
		spendCoverPanel = new CoverPanel();
		SpringLayout springLayout = new SpringLayout();
		spendCoverPanel.setLayout(springLayout);

		Font inputFont = utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 14);
		Font lblInputFont = utils.createFont("com/financify/resources/Poppins/Poppins-SemiBold.ttf", Font.PLAIN, 18); 

		RoundPanel _spendPanel = new RoundPanel();
		_spendPanel.setLayout(springLayout);
		_spendPanel.setBackground(Color.decode("#282828"));
		_spendPanel.setBorderRadius(40);

		JLabel lblTxt1 = new JLabel("Item Name");
		lblTxt1.setFont(lblInputFont);
		lblTxt1.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblTxt1, 30, SpringLayout.NORTH, _spendPanel);
		springLayout.putConstraint(SpringLayout.WEST, lblTxt1, 30, SpringLayout.WEST, _spendPanel);

		int itemNameLim = 30;

		JLabel lblLim = new JLabel("0/"+itemNameLim);
		lblLim.setFont(inputFont);
		lblLim.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblLim, 30, SpringLayout.NORTH, _spendPanel);
		springLayout.putConstraint(SpringLayout.EAST, lblLim, -30, SpringLayout.EAST, _spendPanel);

		JTextField txtName = new JTextField();
		txtName.setDocument(new TextInputLimit(itemNameLim));
		txtName.setFont(inputFont);
		txtName.setPreferredSize(new Dimension(240, 45));
		txtName.setBorder(new EmptyBorder(10,10,10,10));
		txtName.setForeground(Color.white);
		txtName.setBackground(Color.decode("#121212"));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtName, 0, SpringLayout.HORIZONTAL_CENTER, _spendPanel);
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 0, SpringLayout.SOUTH, lblTxt1);
		txtName.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				lblLim.setText(utils.displayLimits(txtName.getText().length(), itemNameLim));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				lblLim.setText(utils.displayLimits(txtName.getText().length(), itemNameLim));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			
		});

		JLabel lblTxt2 = new JLabel("Spend Amount");
		lblTxt2.setFont(lblInputFont);
		lblTxt2.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblTxt2, 17, SpringLayout.SOUTH, txtName);
		springLayout.putConstraint(SpringLayout.WEST, lblTxt2, 30, SpringLayout.WEST, _spendPanel);

		JTextField txtSpend = new JTextField();
		txtSpend.setFont(inputFont);
		txtSpend.setPreferredSize(new Dimension(240, 45));
		txtSpend.setDocument(new MoneyTextInput());
		txtSpend.setBorder(new EmptyBorder(10,10,10,10));
		txtSpend.setForeground(Color.white);
		txtSpend.setBackground(Color.decode("#121212"));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtSpend, 0, SpringLayout.HORIZONTAL_CENTER, _spendPanel);
		springLayout.putConstraint(SpringLayout.NORTH, txtSpend, 0, SpringLayout.SOUTH, lblTxt2);

		RoundBtn btnSpend = new RoundBtn("Add");
		btnSpend.setPreferredSize(new Dimension(100, 40));
		btnSpend.setFont(inputFont);
		btnSpend.setBorderRadius(30);
		btnSpend.setForeground(Color.WHITE);
		btnSpend.setBackground(Color.decode("#993ff4"));
		springLayout.putConstraint(SpringLayout.SOUTH, btnSpend, -30, SpringLayout.SOUTH, _spendPanel);
		springLayout.putConstraint(SpringLayout.EAST, btnSpend, -30, SpringLayout.EAST, _spendPanel);
		btnSpend.addActionListener(new ActionListener() {
			@Override
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				// get the amount saved and round it
				Double money = Double.parseDouble(txtSpend.getText());
				money = (double) Math.round(money*100)/100;
				System.out.println(money);

				// put it into the json object
				double totalSpent = (double) globalStatsJSON.get("totalSpent") + money;
				double currentSaved = (double) globalStatsJSON.get("currentSaved") - money;

				// round values here too so that value wont be a repeating number, ex: 12.33333...
				currentSaved = (double) Math.round(currentSaved*100)/100;

				globalStatsJSON.put("currentSaved", currentSaved);
				globalStatsJSON.put("totalSpent", totalSpent);

				// update money display
				lblMoney.setText("P" + currentSaved);

				// UPDATE THE VALUES ON DATES.JSON
				LocalDate d = LocalDate.now(); // get the month and year of today first
				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy MMM");

				// separate the date format like 2025 Mar
				// into an array where the index 0  is the year and index 1 is the month 
				String[] currDate = df.format(d).split(" ");
					
				// put into the json
				JSONObject year = (JSONObject) monthlySavedJSON.get(currDate[0]);
				double savedThisMonth = (double) year.get(currDate[1]) - money;
				savedThisMonth = (double) Math.round(savedThisMonth*100)/100;
				year.put(currDate[1], savedThisMonth); 
				monthlySavedJSON.put(currDate[0], year);
				
				spendCoverPanel.uncover();
				txtName.setText("");
				txtSpend.setText("");
			}		
		});

		RoundBtn btnCancel = new RoundBtn("Cancel");
		btnCancel.setPreferredSize(new Dimension(100, 40));
		btnCancel.setFont(inputFont);
		btnCancel.setBorderThickness(1);
		btnCancel.setBackground(new Color(0,0,0,0));
		btnCancel.setBorderColor(Color.white);
		btnCancel.setBorderRadius(30);
		btnCancel.setForeground(Color.WHITE);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -30, SpringLayout.SOUTH, _spendPanel);
		springLayout.putConstraint(SpringLayout.WEST, btnCancel, 30, SpringLayout.WEST, _spendPanel);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spendCoverPanel.uncover();
				txtName.setText("");
				txtSpend.setText("");
			}		
		});

		_spendPanel.add(lblTxt1);
		_spendPanel.add(lblLim);
		_spendPanel.add(txtName);
		_spendPanel.add(lblTxt2);
		_spendPanel.add(txtSpend);
		_spendPanel.add(btnSpend);
		_spendPanel.add(btnCancel);

		BlankWrapper spendPanel = new BlankWrapper(_spendPanel);
		spendPanel.setPreferredSize(new Dimension(300, 290));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, spendPanel, 0, SpringLayout.HORIZONTAL_CENTER, spendCoverPanel);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, spendPanel, 0, SpringLayout.VERTICAL_CENTER, spendCoverPanel);

		spendCoverPanel.add(spendPanel);

	}

	@Override
	public void onExit() {
		try{
			FileWriter globalStatsFile = new FileWriter(GlobalConstants.BASE_PATH + "\\global_stats.json");
			globalStatsFile.write(globalStatsJSON.toJSONString());
			globalStatsFile.flush();
			globalStatsFile.close();

			FileWriter datesFile = new FileWriter(GlobalConstants.BASE_PATH + "\\dates.json");
			datesFile.write(monthlySavedJSON.toJSONString());
			datesFile.flush();
			datesFile.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	
	}	

}
