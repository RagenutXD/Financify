package com.financify.app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.TextField;
import java.awt.color.ICC_ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.processing.RoundEnvironment;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.financify.components.Animation;
import com.financify.components.BlankWrapper;
import com.financify.components.CoverPanel;
import com.financify.components.ExtraJPanel;
import com.financify.components.RoundBtn;
import com.financify.components.RoundPanel;
import com.financify.utils.*;

public class SelfNotes extends ExtraJPanel{

	private JSONArray notesJSON;
	private JPanel noteContainer;

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

		CoverPanel addCoverPanel = new CoverPanel();
		addCoverPanel.setLayout(springLayout);

		RoundPanel addPanel = new RoundPanel();
		addPanel.setLayout(springLayout);
		addPanel.setBorderRadius(40);
		addPanel.setBackground(Color.decode("#282828"));
		addPanel.setPreferredSize(new Dimension(600, 400));

		BlankWrapper addPanelWrapper = new BlankWrapper(addPanel);
		addPanelWrapper.setPreferredSize(new Dimension(600, 400));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addPanelWrapper, 0, SpringLayout.HORIZONTAL_CENTER, addCoverPanel);
		springLayout.putConstraint(SpringLayout.VERTICAL_CENTER, addPanelWrapper, 0, SpringLayout.VERTICAL_CENTER, addCoverPanel);
		
		addCoverPanel.add(addPanelWrapper);

		Font inputFont = utils.createFont("com/financify/resources/Poppins/Poppins-Regular.ttf", Font.PLAIN, 14);
		Font lblInputFont = utils.createFont("com/financify/resources/Poppins/Poppins-SemiBold.ttf", Font.PLAIN, 18); 

		JLabel lblAddPanel1 = new JLabel("Title");
		lblAddPanel1.setFont(lblInputFont);
		lblAddPanel1.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblAddPanel1, 50, SpringLayout.NORTH, addPanel);
		springLayout.putConstraint(SpringLayout.WEST, lblAddPanel1, 30, SpringLayout.WEST, addPanel);

		final int titleLim = 40;
		JLabel lblLimit1 = new JLabel("0/" + titleLim);
		lblLimit1.setFont(inputFont);
		lblLimit1.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblLimit1, 50, SpringLayout.NORTH, addPanel);
		springLayout.putConstraint(SpringLayout.EAST, lblLimit1, -30, SpringLayout.EAST, addPanel);

		JTextField txtTitle = new JTextField();
		txtTitle.setDocument(new TextInputLimit(titleLim));
		txtTitle.setFont(inputFont);
		txtTitle.setBorder(new EmptyBorder(10,10,10,10));
		txtTitle.setPreferredSize(new Dimension(540,45));
		txtTitle.setForeground(Color.white);
		txtTitle.setBackground(Color.decode("#121212"));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txtTitle, 0, SpringLayout.HORIZONTAL_CENTER, addPanel);
		springLayout.putConstraint(SpringLayout.NORTH, txtTitle, 0, SpringLayout.SOUTH, lblAddPanel1);
		txtTitle.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				lblLimit1.setText(displayLimits(txtTitle.getText().length(), titleLim));	
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				lblLimit1.setText(displayLimits(txtTitle.getText().length(), titleLim));	
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			
		});

		final int areaLim = 200;

		JLabel lblLimit2 = new JLabel("0/" + areaLim);
		lblLimit2.setFont(inputFont);
		lblLimit2.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblLimit2, 17, SpringLayout.SOUTH, txtTitle);
		springLayout.putConstraint(SpringLayout.EAST, lblLimit2, -30, SpringLayout.EAST, addPanel);
		JLabel lblAddPanel2 = new JLabel("Note");
		lblAddPanel2.setFont(lblInputFont);
		lblAddPanel2.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.NORTH, lblAddPanel2, 17, SpringLayout.SOUTH, txtTitle);
		springLayout.putConstraint(SpringLayout.WEST, lblAddPanel2, 30, SpringLayout.WEST, addPanel);

		JTextArea txtArea = new JTextArea();
		txtArea.setDocument(new TextInputLimit(areaLim));
		txtArea.setFont(inputFont);
		txtArea.setForeground(Color.white);
		txtArea.setBackground(Color.decode("#121212"));
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setBorder(new EmptyBorder(10,10,10,10));
		txtArea.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				lblLimit2.setText(displayLimits(txtArea.getText().length(), areaLim));
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				lblLimit2.setText(displayLimits(txtArea.getText().length(), areaLim));
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
			
		});

		/*
		 * A jscroll pane is used to contrain the txtareas size
		 * This is because a jtextarea grows in size when going past the col and row count
		 * And springlayout respects this growth, this causes it's parent component (addPanel)
		 * to expand and eventually fill the whole screen
		 * 
		 */
		BlankWrapper scrollPaneWrapper = new BlankWrapper(txtArea);
		scrollPaneWrapper.setPreferredSize(new Dimension(538,130));
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPaneWrapper, 0, SpringLayout.HORIZONTAL_CENTER, addPanel);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPaneWrapper, 0, SpringLayout.SOUTH, lblAddPanel2);

		RoundBtn btnAdd = new RoundBtn("Add");
		btnAdd.setPreferredSize(new Dimension(100, 40));
		btnAdd.setFont(inputFont);
		btnAdd.setBorderRadius(40);
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBackground(Color.decode("#993ff4"));
		springLayout.putConstraint(SpringLayout.SOUTH, btnAdd, -30, SpringLayout.SOUTH, addPanel);
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, -30, SpringLayout.EAST, addPanel);
		btnAdd.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				// add the data into the json obj
				JSONObject noteData = new JSONObject();
				noteData.put("title", txtTitle.getText());

				LocalDate currDate = LocalDate.now();
				DateTimeFormatter dtF = DateTimeFormatter.ofPattern("MMM-dd-yyyy")	;
				String date = dtF.format(currDate).toString();
				noteData.put("date", date);

				noteData.put("full_note", txtArea.getText());
				notesJSON.add(noteData);
				System.out.println(notesJSON.toJSONString());

				// recalculate the rows and cols of noteContainer
				int rowCount = Math.max(1, (notesJSON.size()+1)/3);

				// add the display
				displayNote(txtTitle.getText(), date);


				//close frame
				addCoverPanel.uncover();	
				txtTitle.setText("");
				txtArea.setText("");
			}
			

		});

		RoundBtn btnCancel = new RoundBtn("Cancel");
		btnCancel.setPreferredSize(new Dimension(100, 40));
		btnCancel.setFont(inputFont);
		btnCancel.setBorderRadius(40);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.setBackground(Color.white);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancel, -30, SpringLayout.SOUTH, addPanel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancel, -15, SpringLayout.WEST, btnAdd);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCoverPanel.uncover();	
				txtTitle.setText("");
				txtArea.setText("");
			}	
		});

		addPanel.add(lblAddPanel1);
		addPanel.add(txtTitle);
		addPanel.add(lblLimit1);
		addPanel.add(lblAddPanel2);
		addPanel.add(lblLimit2);
		addPanel.add(scrollPaneWrapper);
		addPanel.add(btnCancel);
		addPanel.add(btnAdd);

		noteContainer = new JPanel();
		noteContainer.setOpaque(false);
		System.out.println("Notes Json size: " + notesJSON.size());
		noteContainer.setLayout(new GridLayout(0, 3, 20, 20));
        springLayout.putConstraint(SpringLayout.WEST, noteContainer,  25, SpringLayout.WEST, this); 
        springLayout.putConstraint(SpringLayout.NORTH, noteContainer, 40, SpringLayout.SOUTH, lblSelfNotes); 

		RoundBtn btnRound = new RoundBtn();
		ImageIcon _addIcon = new ImageIcon(getClass().getClassLoader().getResource("com/financify/resources/icons/addicon.png"));
		btnRound.setIcon(_addIcon);
		btnRound.setForeground(Color.WHITE);
		btnRound.setPreferredSize(new Dimension(220,150));
		btnRound.setBackground(Color.decode("#993ff4"));
		btnRound.setBorderRadius(40);
		noteContainer.add(btnRound);
		btnRound.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						btnRound.setBackground(utils.interpolateColor(btnRound.getBackground(), Color.decode("#771dd2"), t));
					}
				}, 0.2f);
				super.mouseEntered(e);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						btnRound.setBackground(utils.interpolateColor(btnRound.getBackground(), Color.decode("#993ff4"), t));
					}
				}, 0.2f);
				super.mouseExited(e);
			}
		});
		btnRound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCoverPanel.cover(getRootPane());	
				addCoverPanel.revalidate();
				addCoverPanel.repaint();
			}			
		});

		// make a note panel for every entry
		initContainer();

		add(lblAppName);
		add(lblSelfNotes);
		add(noteContainer);

	}

	private void displayNote(String title, String date){
		RoundPanel notePanel = new RoundPanel();
		notePanel.setPreferredSize(new Dimension(220,150));
		notePanel.setBackground(Color.decode("#282828"));
		notePanel.setBorderRadius(40);
		SpringLayout springLayout = new SpringLayout();
		notePanel.setLayout(springLayout);

		// JLabel lblTitle = new JLabel(title);
		// lblTitle.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Bold.ttf", Font.PLAIN, 16));
		// lblTitle.setForeground(Color.white);
		// springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 20, SpringLayout.NORTH, notePanel);
		// springLayout.putConstraint(SpringLayout.WEST, lblTitle, 20, SpringLayout.WEST, notePanel);

		JTextArea _lblTitle = new JTextArea();
		_lblTitle.setEditable(false);
		_lblTitle.setText(title);
		_lblTitle.setBorder(null);
		_lblTitle.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Bold.ttf", Font.PLAIN, 16));
		_lblTitle.setBackground(Color.decode("#282828"));
		// _lblTitle.setBackground(Color.green);
		_lblTitle.setForeground(Color.white);
		_lblTitle.setLineWrap(true);
		_lblTitle.setWrapStyleWord(true);
		_lblTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked fromt the textArea, passing the event to panel");
				notePanel.dispatchEvent(e);
				super.mouseClicked(e);
			}	
		});

		BlankWrapper lblTitle = new BlankWrapper(_lblTitle);
		lblTitle.setPreferredSize(new Dimension(190, 80));
		springLayout.putConstraint(SpringLayout.NORTH, lblTitle, 20, SpringLayout.NORTH, notePanel);
		springLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, 0, SpringLayout.HORIZONTAL_CENTER, notePanel);

		JLabel lblDate = new JLabel(date);
		lblDate.setFont(utils.createFont("com/financify/resources/Poppins/Poppins-Light.ttf", Font.PLAIN, 14));
		lblDate.setForeground(Color.white);
		springLayout.putConstraint(SpringLayout.SOUTH, lblDate, -20, SpringLayout.SOUTH, notePanel);
		springLayout.putConstraint(SpringLayout.WEST, lblDate, 0, SpringLayout.WEST, lblTitle);
		
		notePanel.add(lblTitle);
		notePanel.add(lblDate);
		
		//TODO: Make the hovering on the text area still work 

		notePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						notePanel.setBackground(utils.interpolateColor(notePanel.getBackground(), Color.decode("#4A4A4A"), t));
						_lblTitle.setBackground(utils.interpolateColor(notePanel.getBackground(), Color.decode("#4A4A4A"), t*t));
					}
				}, 0.2f);
				super.mouseEntered(e);
			}	

			@Override
			public void mouseExited(MouseEvent e) {
				utils.playAnimation(new Animation(){
					@Override
					public void createAnimation(float t) {
						notePanel.setBackground(utils.interpolateColor(notePanel.getBackground(), Color.decode("#282828"), t));
						_lblTitle.setBackground(utils.interpolateColor(notePanel.getBackground(), Color.decode("#282828"), t*t));
					}
				}, 0.2f);
				super.mouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("woah");
				super.mouseClicked(e);
			}
		});

		noteContainer.add(notePanel, 1);
		noteContainer.revalidate();
		noteContainer.repaint();
	}

	private void initContainer(){
		for(int i = 0; i < notesJSON.size(); i++){
			JSONObject entry = (JSONObject) notesJSON.get(i);
			displayNote( (String) entry.get("title"), (String) entry.get("date"));
		}
	}
	
	private String displayLimits(int strlen, int limit){
		return strlen + "/" + limit;
	}

	@Override
	public void onExit() {
		try{
			FileWriter file = new FileWriter(GlobalConstants.BASE_PATH + "\\notes.json");
			file.write(notesJSON.toJSONString());
			file.flush();
			file.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
