package genegate;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import genegate.processor.DNAProcessor;

public class GenegateGUI extends JFrame {

	JPanel activeJPanel;
	
	/*----------------------------------------------------------
	 * Genegate(). CONSTRUCTOR
	 *----------------------------------------------------------*/
	public GenegateGUI() {
		
		super("Genegate Workbench");
		createMenu();
		welcomePanel();
		setVisible(true);
	}
	
	/*----------------------------------------------------------
	 * createFrame(). Creates the JFrame
	 *----------------------------------------------------------*/
	private void createMenu() {
		
		setSize(600, 600);
		
		//Container contentPane = getContentPane();
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Main");
		JMenu toolsMenu = new JMenu("Tools");
		JMenuItem menuItem = null;
		
		//Create Menu action event
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	    		remove(activeJPanel);
	    		
	    		if(e.getActionCommand().equals("GC Content Analysis")){
	    			
	    			basePairAnalysisPanel(e);
	    		}
	    		else if(e.getActionCommand().equals("DNA-RNA Transcription")){
	    			
	    			dnaTranscriptionPanel(e);
	    		}
	    		else{
	    			activeJPanel = new JPanel();
	    			
	    			activeJPanel.setLayout(new BoxLayout(activeJPanel, BoxLayout.X_AXIS));
	    			JLabel fatLabel = new JLabel("NOOOOO:   "+e.getActionCommand());
	    			activeJPanel.add(fatLabel);
	    			add(activeJPanel);
	    		}
	    		
	    		revalidate();
	    		repaint();
	        }
	    };
		
		/* Create the 'Main' submenu
		 ----------------------------*/
		mainMenu.setMnemonic(KeyEvent.VK_A);
		mainMenu.getAccessibleContext().setAccessibleDescription("Main Menu");
		menuBar.add(mainMenu);
		
		//Create Main Menu items
		menuItem = new JMenuItem("Open", KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menuItem.addActionListener(actionListener);
		mainMenu.add(menuItem);
		
		/* Create the 'Tools' submenu
		 ----------------------------*/
		toolsMenu.setMnemonic(KeyEvent.VK_A);
		toolsMenu.getAccessibleContext().setAccessibleDescription("Tools Menu");
		menuBar.add(toolsMenu);
		
		//Create tools Menu items
		menuItem = new JMenuItem("GC Content Analysis", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("G-C Base Pair frequency Analysis");
		menuItem.addActionListener(actionListener);
		toolsMenu.add(menuItem);
		
		//
		menuItem = new JMenuItem("DNA-RNA Transcription", KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("DNA to RNA Transcription Tool");
		menuItem.addActionListener(actionListener);
		toolsMenu.add(menuItem);
		
		setJMenuBar(menuBar);
	}
	
	/*----------------------------------------------------------
	 * welcomePanel(). Creates the JFrame
	 *----------------------------------------------------------*/
	private void welcomePanel() {
		
		activeJPanel = new JPanel();
		
		activeJPanel.setLayout(new BoxLayout(activeJPanel, BoxLayout.X_AXIS));
		JLabel fatLabel = new JLabel("Welcome to Genegate!");
		activeJPanel.add(fatLabel);
		add(activeJPanel);
	}
	
	/*----------------------------------------------------------
	 * basePairAnalysisPanel(ActionEvent e). Creates the JFrame
	 *----------------------------------------------------------*/
	private void basePairAnalysisPanel(ActionEvent e){
		
		activeJPanel = new JPanel();
		JTextArea geneText;
		JTextField percentText;
		JLabel countLabel;
		JTextField countText;
		JButton submitButton;
		JButton clearButton;
		
		activeJPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc  = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Create the Label
		JLabel fatLabel = new JLabel("Enter grams of fat:   "+e.getActionCommand());
		gbc.gridx = 0;
		gbc.gridy = 0;
		//gbc.gridwidth = 2;
		activeJPanel.add(fatLabel, gbc);
		
		//Create the text area
		geneText = new JTextArea("Text Here...", 20, 30);
		geneText.setPreferredSize(new Dimension(300, 100));
		geneText.setMinimumSize(geneText.getPreferredSize());
		geneText.setLineWrap(true);
		gbc  = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		JScrollPane scrollPane = new JScrollPane(geneText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setMinimumSize(geneText.getPreferredSize());
		
		activeJPanel.add(scrollPane, gbc);

		//Create the GC Percent Result Label
		JLabel percentLabel = new JLabel("GC Content Percent: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		activeJPanel.add(percentLabel, gbc);
		
		//Create the GC Percent Result Text Box
		percentText = new JTextField("", 7);
		percentText.setPreferredSize(new Dimension(10,20));
		percentText.setMinimumSize(percentText.getPreferredSize());
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(10,10,10,10);
		activeJPanel.add(percentText, gbc);
		
		//Create the BP Count result Label
		countLabel = new JLabel("Total # of BP: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		activeJPanel.add(countLabel, gbc);
		
		//Create the BP Count result Text Box
		countText = new JTextField("", 7);
		countText.setPreferredSize(new Dimension(10,20));
		countText.setMinimumSize(countText.getPreferredSize());
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(10,10,10,10);
		activeJPanel.add(countText, gbc);
		
		//Create the Submit button
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(90, 40));
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(10,10,10,10);
		activeJPanel.add(submitButton, gbc);
		
		//Add the Clear Button
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(90, 40));
		gbc.gridx = 1;
		gbc.gridy = 4;
		activeJPanel.add(clearButton, gbc);
		
		//Create the button action buttons
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	
	        	DNAProcessor dnaO = new DNAProcessor(geneText.getText());
	        	
	        	percentText.setText(dnaO.calculateGCContent()+"%");
	        	countText.setText(Integer.toString(dnaO.getSequenceLength()));
	        }
	    };
	      
	    submitButton.addActionListener(actionListener);
		
		add(activeJPanel);
	}
	
	/*----------------------------------------------------------
	 * basePairAnalysisPanel(ActionEvent e). Creates the JFrame
	 *----------------------------------------------------------*/
	private void dnaTranscriptionPanel(ActionEvent e){
		
		activeJPanel = new JPanel();
		JLabel inputLabel;
		JTextArea inputText;
		JLabel outputLabel;
		JTextArea outputText;
		JButton submitButton;
		JButton clearButton;
		
		activeJPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc  = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Create the Input TextArea Label
		inputLabel = new JLabel(e.getActionCommand());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		inputLabel.setFont(inputLabel.getFont().deriveFont(20.0f));
		gbc.insets = new Insets(0,0,20,0);
		activeJPanel.add(inputLabel, gbc);
		
		//Create the Input TextArea Label
		inputLabel = new JLabel("Enter a DNA sequence below:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(0,0,0,0);
		activeJPanel.add(inputLabel, gbc);
		
		//Create the Input text area
		inputText = new JTextArea("", 10, 50);
		inputText.setPreferredSize(new Dimension(300, 100));
		inputText.setMinimumSize(inputText.getPreferredSize());
		inputText.setLineWrap(true);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		JScrollPane inputScrollPane = new JScrollPane(inputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputScrollPane.setMinimumSize(inputText.getPreferredSize());
		
		activeJPanel.add(inputScrollPane, gbc);
		
		//Create the Output TextArea Label
		outputLabel = new JLabel("Transcription Output:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15,0,0,0);
		activeJPanel.add(outputLabel, gbc);
		
		//Create the output text area
		outputText = new JTextArea("", 10, 50);
		outputText.setPreferredSize(new Dimension(30, 100));
		outputText.setMinimumSize(outputText.getPreferredSize());
		outputText.setLineWrap(true);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.insets = new Insets(0,0,0,0);
		JScrollPane outputScrollPane = new JScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
												 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputScrollPane.setMinimumSize(outputText.getPreferredSize());
		
		activeJPanel.add(outputScrollPane, gbc);
		
		//Create the Submit button
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(90, 40));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15,0,0,10);
		activeJPanel.add(submitButton, gbc);
		
		//Add the Clear Button
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(90, 40));
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.EAST;
		activeJPanel.add(clearButton, gbc);
		
		//Create the button action buttons
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	
	        	DNAProcessor dnaO = new DNAProcessor(inputText.getText());
	        	outputText.setText(dnaO.transcribeToRNA());
	        }
	    };
	      
	    submitButton.addActionListener(actionListener);
		
		add(activeJPanel);
	}
}
