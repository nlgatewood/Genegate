package genegate;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import genegate.processor.DNAProcessor;

public class GenegateGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel activeJPanel;
	
	/*----------------------------------------------------------
	 * Genegate(). CONSTRUCTOR
	 *----------------------------------------------------------*/
	public GenegateGUI() {
		
		super("Genegate Workbench");
		setSize(675, 600);
		
		createFrameMenu();
		homePanel();
		
		setVisible(true);
	}
	
	/*----------------------------------------------------------
	 * createFrameMenu(). Create the JFrame's Main Menu Bar
	 *----------------------------------------------------------*/
	private void createFrameMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		JMenu mainMenu = new JMenu("Main");
		JMenu toolsMenu = new JMenu("Tools");
		JMenuItem menuItem = null;
		
		/* Menu ActionListener
		 *---------------------*/
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	
	    		remove(activeJPanel);
	    		
	    		//Go to the 'GC Content Analysis' panel
	    		if(e.getActionCommand().equals("GC Content Analysis")){
	    			
	    			basePairAnalysisPanel(e);
	    		}
	    		//Go to the 'DNA-RNA Transcription' panel
	    		else if(e.getActionCommand().equals("DNA-RNA Transcription")){
	    			
	    			dnaTranscriptionPanel(e);
	    		}
	    		//Go to the 'DNA-Protein Translation' panel
	    		else if(e.getActionCommand().equals("DNA-Protein Translation")){
	    		
	    			dnaTranslationPanel(e);
	    		}
	    		else{
	    			activeJPanel = new JPanel();
	    			
	    			activeJPanel.setLayout(new BoxLayout(activeJPanel, BoxLayout.X_AXIS));
	    			JLabel fatLabel = new JLabel("Error: Invalid page - "+e.getActionCommand());
	    			activeJPanel.add(fatLabel);
	    			add(activeJPanel);
	    		}
	    		
	    		revalidate();
	    		repaint();
	        }
	    };
		
		/* Create the 'Main' submenu
		 ----------------------------*/
		mainMenu.setMnemonic(KeyEvent.VK_M);
		mainMenu.getAccessibleContext().setAccessibleDescription("Main Menu");
		menuBar.add(mainMenu);
		
		//Create Main Menu items
		menuItem = new JMenuItem("Close", KeyEvent.VK_C);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("Close Application");
		menuItem.addActionListener(actionListener);
		mainMenu.add(menuItem);
		
		
		/* Create the 'Tools' submenu
		 ----------------------------*/
		toolsMenu.setMnemonic(KeyEvent.VK_T);
		toolsMenu.getAccessibleContext().setAccessibleDescription("Tools Menu");
		menuBar.add(toolsMenu);
		
		//Create tools Menu items
		menuItem = new JMenuItem("GC Content Analysis", KeyEvent.VK_G);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("G-C Base Pair frequency Analysis");
		menuItem.addActionListener(actionListener);
		toolsMenu.add(menuItem);
		
		//DNA-RNA Transcription tool
		menuItem = new JMenuItem("DNA-RNA Transcription", KeyEvent.VK_R);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("DNA to RNA Transcription Tool");
		menuItem.addActionListener(actionListener);
		toolsMenu.add(menuItem);
		
		//DNA-PROTEIN Translation
		menuItem = new JMenuItem("DNA-Protein Translation", KeyEvent.VK_P);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription("DNA-Protein Translation Tool");
		menuItem.addActionListener(actionListener);
		toolsMenu.add(menuItem);
		
		setJMenuBar(menuBar);
	}
	
	/*----------------------------------------------------------
	 * homePanel(). Creates the Home Screen
	 *----------------------------------------------------------*/
	private void homePanel() {
		
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
		JTextArea inputText;
		JLabel percentLabel;
		JTextField percentText;
		JLabel countLabel;
		JTextField countText;
		JButton submitButton;
		JButton clearButton;
		JButton exitButton;					//Exit Button
		
		activeJPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc  = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Create the Label
		JLabel fatLabel = new JLabel("Enter grams of fat:   "+e.getActionCommand());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		activeJPanel.add(fatLabel, gbc);
		
		//Create the text area
		inputText = new JTextArea("", 20, 30);
		inputText.setPreferredSize(new Dimension(300, 100));
		inputText.setMinimumSize(inputText.getPreferredSize());
		inputText.setLineWrap(true);
		JScrollPane scrollPane = new JScrollPane(inputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setMinimumSize(inputText.getPreferredSize());
		gbc.gridx = 0;
		gbc.gridy = 1;
		activeJPanel.add(scrollPane, gbc);

		//Create the GC Percent Result Label
		percentLabel = new JLabel("GC Content Percent: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
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
		activeJPanel.add(countText, gbc);
		
		//Create the Submit button
		submitButton = new JButton("Submit");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(15,0,0,10);
		activeJPanel.add(submitButton, gbc);
		
		//Add the Clear Button
		clearButton = new JButton("Clear");
		gbc.gridx = 1;
		gbc.gridy = 4;
		activeJPanel.add(clearButton, gbc);
		
		//Create the Exit button
		exitButton = new JButton("Exit");
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.insets = new Insets(15,355,0,10);
		activeJPanel.add(exitButton, gbc);
		
		//Create the button action buttons
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	
	        	if(actionEvent.getActionCommand().equals("Submit")){
	        		
	        		DNAProcessor dnaO = new DNAProcessor(inputText.getText());
	        	
	        		percentText.setText(dnaO.calculateGCContent()+"%");
	        		countText.setText(Integer.toString(dnaO.getSequenceLength()));
	        	}
	        	else if(actionEvent.getActionCommand().equals("Clear")){
	        		
	        		inputText.setText("");
	        		percentText.setText("");
	        		countText.setText("");
	        	}
	        	//Else, if 'Exit' Button is clicked - Exit this Panel. Return to Welcome Screen
	        	else if(actionEvent.getActionCommand().equals("Exit")){
	        		
	        		remove(activeJPanel);
	        		
	        		homePanel();
		    		revalidate();
		    		repaint();
	        	}
	        }
	    };
	      
	    submitButton.addActionListener(actionListener);
	    clearButton.addActionListener(actionListener);
	    exitButton.addActionListener(actionListener);
		
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
		JButton exitButton;					//Exit Button
		
		activeJPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc  = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Create the Input TextArea Label
		inputLabel = new JLabel(e.getActionCommand());
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		inputLabel.setFont(inputLabel.getFont().deriveFont(20.0f));
		gbc.insets = new Insets(0,0,20,0);
		activeJPanel.add(inputLabel, gbc);
		
		//Create the Input TextArea Label
		inputLabel = new JLabel("Enter a DNA sequence below:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0,0,0,0);
		activeJPanel.add(inputLabel, gbc);
		
		//Create the Input text area
		inputText = new JTextArea("", 10, 50);
		inputText.setPreferredSize(new Dimension(300, 100));
		inputText.setMinimumSize(inputText.getPreferredSize());
		inputText.setLineWrap(true);
		JScrollPane inputScrollPane = new JScrollPane(inputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputScrollPane.setMinimumSize(inputScrollPane.getPreferredSize());
		gbc.gridx = 0;
		gbc.gridy = 3;
		activeJPanel.add(inputScrollPane, gbc);
		
		//Create the Output TextArea Label
		outputLabel = new JLabel("Transcription Output:");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15,0,0,0);
		activeJPanel.add(outputLabel, gbc);
		
		//Create the output text area
		outputText = new JTextArea("", 10, 50);
		outputText.setPreferredSize(new Dimension(30, 100));
		outputText.setMinimumSize(outputText.getPreferredSize());
		outputText.setLineWrap(true);
		JScrollPane outputScrollPane = new JScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputScrollPane.setMinimumSize(outputText.getPreferredSize());
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(0,0,0,0);
		activeJPanel.add(outputScrollPane, gbc);
		
		//Create the Submit button
		submitButton = new JButton("Submit");
		submitButton.setPreferredSize(new Dimension(90, 40));
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15,0,0,10);
		activeJPanel.add(submitButton, gbc);
		
		//Add the Clear Button
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(90, 40));
		gbc.gridx = 1;
		gbc.gridy = 6;
		activeJPanel.add(clearButton, gbc);
		
		//Create the Exit button
		exitButton = new JButton("Exit");
		gbc.gridx = 2;
		gbc.gridy = 6;
		gbc.insets = new Insets(15,355,0,10);
		activeJPanel.add(exitButton, gbc);
		
		//Create the button action buttons
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	
	        	if(actionEvent.getActionCommand().equals("Submit")){
	        		
	        		DNAProcessor dnaO = new DNAProcessor(inputText.getText());
	        		outputText.setText(dnaO.transcribeToRNA());
	        	}
	        	else if(actionEvent.getActionCommand().equals("Clear")){
	        		
	        		inputText.setText("");
	        		outputText.setText("");
	        	}
	        	//Else, if 'Exit' Button is clicked - Exit this Panel. Return to Welcome Screen
	        	else if(actionEvent.getActionCommand().equals("Exit")){
	        		
	        		remove(activeJPanel);
	        		
	        		homePanel();
		    		revalidate();
		    		repaint();
	        	}
	        }
	    };
	      
	    submitButton.addActionListener(actionListener);
	    clearButton.addActionListener(actionListener);
	    exitButton.addActionListener(actionListener);
		
		add(activeJPanel);
	}
	
	/*----------------------------------------------------------
	 * dnaProteinTranslationPanel(ActionEvent e). Creates the JFrame
	 *----------------------------------------------------------*/
	private void dnaTranslationPanel(ActionEvent e){
		
		activeJPanel = new JPanel();
		JLabel orfLabel;	
		JComboBox<Integer> orfComboBox;		//ORF choices(1,2,3)
		JLabel inputLabel;
		JTextArea inputText;				//Input entered by the user
		JLabel outputLabel;
		JTextArea outputText;				//Output returned
		JButton submitButton;				//Submit Button
		JButton clearButton;				//Clear Button
		JButton exitButton;					//Exit Button
		
		activeJPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc  = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		//Create the Input TextArea Label
		inputLabel = new JLabel(e.getActionCommand());
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		inputLabel.setFont(inputLabel.getFont().deriveFont(20.0f));
		gbc.insets = new Insets(0,0,20,0);
		activeJPanel.add(inputLabel, gbc);
		
		//Create the ORF Label
		orfLabel = new JLabel("Select ORF: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15,0,15,0);
		activeJPanel.add(orfLabel, gbc);
		
		//ORF Combo Box
		Integer[] orfs = {1, 2, 3};
		orfComboBox = new JComboBox<Integer>(orfs);
		orfComboBox.setPreferredSize(new Dimension(10,20));
		orfComboBox.setMinimumSize(orfComboBox.getPreferredSize());
		gbc.gridx = 1;
		gbc.gridy = 2;
		activeJPanel.add(orfComboBox, gbc);
		
		//Create the Input TextArea Label
		inputLabel = new JLabel("Enter a DNA sequence below:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 4;
		gbc.insets = new Insets(0,0,0,0);
		activeJPanel.add(inputLabel, gbc);
		
		//Create the Input text area
		inputText = new JTextArea("", 10, 50);
		inputText.setPreferredSize(new Dimension(300, 100));
		inputText.setMinimumSize(inputText.getPreferredSize());
		inputText.setLineWrap(true);
		JScrollPane inputScrollPane = new JScrollPane(inputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputScrollPane.setMinimumSize(inputText.getPreferredSize());
		gbc.gridx = 0;
		gbc.gridy = 4;
		activeJPanel.add(inputScrollPane, gbc);
		
		//Create the Output TextArea Label
		outputLabel = new JLabel("Transcription Output:");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(15,0,0,0);
		activeJPanel.add(outputLabel, gbc);
		
		//Create the output text area
		outputText = new JTextArea("", 10, 50);
		outputText.setPreferredSize(new Dimension(30, 100));
		outputText.setMinimumSize(outputText.getPreferredSize());
		outputText.setLineWrap(true);
		JScrollPane outputScrollPane = new JScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				 JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(0,0,0,0);
		outputScrollPane.setMinimumSize(outputText.getPreferredSize());
		activeJPanel.add(outputScrollPane, gbc);
		
		//Create the Submit button
		submitButton = new JButton("Submit");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15,0,0,10);
		activeJPanel.add(submitButton, gbc);
		
		//Add the Clear Button
		clearButton = new JButton("Clear");
		gbc.gridx = 1;
		gbc.gridy = 7;
		activeJPanel.add(clearButton, gbc);
		
		//Create the Exit button
		exitButton = new JButton("Exit");
		gbc.gridx = 2;
		gbc.gridy = 7;
		gbc.insets = new Insets(15,355,0,10);
		activeJPanel.add(exitButton, gbc);
		
		/* JPanel ActionListener
		 *---------------------*/
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	
	        	//If the 'Submit' button is clicked
	        	if(actionEvent.getActionCommand().equals("Submit")){
	        		
	        		DNAProcessor dnaO = new DNAProcessor(inputText.getText());
	        		
	        		int selectedOrf = (int) orfComboBox.getSelectedItem();
	        		outputText.setText(dnaO.translateToProtein(selectedOrf));
	        	}
	        	//If the 'Clear' Button is clicked - reset all fields
	        	else if(actionEvent.getActionCommand().equals("Clear")){
	        		
	        		orfComboBox.setSelectedItem(1);
	        		inputText.setText("");
	        		outputText.setText("");
	        	}
	        	//Else, if 'Exit' Button is clicked - Exit this Panel. Return to Welcome Screen
	        	else if(actionEvent.getActionCommand().equals("Exit")){
	        		
	        		remove(activeJPanel);
	        		
	        		homePanel();
		    		revalidate();
		    		repaint();
	        	}
	        }
	    };
	    
	    //Add the 'Submit', 'Clear', and 'Exit' buttons to the ActionListener
	    submitButton.addActionListener(actionListener);
	    clearButton.addActionListener(actionListener);
	    exitButton.addActionListener(actionListener);
		
		add(activeJPanel);
	}
}
