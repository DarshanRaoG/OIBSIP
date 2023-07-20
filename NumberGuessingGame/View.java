package numberGuessingGame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class View {
	private JFrame frame;
	private JPanel panel0,panel1,panel2,panel3,panel4,panel5,panelX;
	private JLabel label1,label2,label3,label4,scoreLabel,roundLabel,turnLabel;
	private JRadioButtonMenuItem amateur,novice,expert,master,telepath;
	private JRadioButtonMenuItem range1,range2,range3;
	private JRadioButtonMenuItem round1,round3,round5;
	private JTextArea inputField;
	private JButton startButton,submitButton,quitGame;
	
	View() {
		frame = new JFrame("Guess the number");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		
		label1 = new JLabel("Number Guessing Game!");
		label1.setFont(new Font("Arial",Font.BOLD,50));
		label1.setForeground(Color.BLUE);
		panel0 = new JPanel();
		panel0.add(label1);
		panel0.setBackground(Color.orange);
		
		
		//Difficulty selection
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Settings");
		menu.setFont(new Font("Arial",Font.BOLD,15));
		
		JMenu menu1 = new JMenu("Difficulty");
		menu1.setFont(new Font("Arial",Font.BOLD,15));
		
		amateur = new JRadioButtonMenuItem("Amateur (Unlimited Guesses)");
		novice = new JRadioButtonMenuItem("Novice (20 Guesses)");
		expert = new JRadioButtonMenuItem("Expert (10 Guesses)");
		master = new JRadioButtonMenuItem("Master (5 Guesses)");
		telepath = new JRadioButtonMenuItem("Telepath (1 Guess)");
		
		ButtonGroup bg1 = new ButtonGroup();
		
		bg1.add(amateur);
		bg1.add(novice);
		bg1.add(expert);
		bg1.add(master);
		bg1.add(telepath);
		
		menu1.add(amateur);
		menu1.add(novice);
		menu1.add(expert);
		menu1.add(master);
		menu1.add(telepath);
		
		expert.setSelected(true);
		
		menu.add(menu1);
		
		//Range selection
		
		JMenu menu2 = new JMenu("Range");
		menu2.setFont(new Font("Arial",Font.BOLD,15));
		
		range1 = new JRadioButtonMenuItem("1 - 10");
		range2 = new JRadioButtonMenuItem("1 - 50");
		range3 = new JRadioButtonMenuItem("1 - 100");
		
		ButtonGroup bg2 = new ButtonGroup();
				
		bg2.add(range1);
		bg2.add(range2);
		bg2.add(range3);
		
		menu2.add(range1);
		menu2.add(range2);
		menu2.add(range3);
		
		range1.setSelected(true);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		menu.add(menu2);
		
		//No. of rounds selection
		
		JMenu menu3 = new JMenu("Rounds");
		menu3.setFont(new Font("Arial",Font.BOLD,15));
		
		round1 = new JRadioButtonMenuItem("1");
		round3 = new JRadioButtonMenuItem("3");
		round5 = new JRadioButtonMenuItem("5");
				
		ButtonGroup bg3 = new ButtonGroup();
						
		bg3.add(round1);
		bg3.add(round3);
		bg3.add(round5);
		
		menu3.add(round1);
		menu3.add(round3);
		menu3.add(round5);
		
		round1.setSelected(true);
		
		JSeparator separator1 = new JSeparator();
		menu.add(separator1);
		menu.add(menu3);
		
		menuBar.add(menu);
		
		menu1.setPreferredSize(new Dimension(90,40));
		menu2.setPreferredSize(new Dimension(90,40));
		menu3.setPreferredSize(new Dimension(90,40));
		
        menu.setPreferredSize(new Dimension(75,25));
		menu.setOpaque(true);
		
        amateur.setPreferredSize(new Dimension(235,30));
        novice.setPreferredSize(new Dimension(235,30));
        expert.setPreferredSize(new Dimension(235,30));
        master.setPreferredSize(new Dimension(235,30));
        telepath.setPreferredSize(new Dimension(235,30));
        
        amateur.setFont(new Font("Arial",Font.BOLD,15));
        novice.setFont(new Font("Arial",Font.BOLD,15));
        expert.setFont(new Font("Arial",Font.BOLD,15));
        master.setFont(new Font("Arial",Font.BOLD,15));
        telepath.setFont(new Font("Arial",Font.BOLD,15));
        
        range1.setPreferredSize(new Dimension(100,30));
        range2.setPreferredSize(new Dimension(100,30));
        range3.setPreferredSize(new Dimension(100,30));
        
        range1.setFont(new Font("Arial",Font.BOLD,15));
        range2.setFont(new Font("Arial",Font.BOLD,15));
        range3.setFont(new Font("Arial",Font.BOLD,15));
        
        round1.setPreferredSize(new Dimension(100,30));
        round3.setPreferredSize(new Dimension(100,30));
        round5.setPreferredSize(new Dimension(100,30));
        
        round1.setFont(new Font("Arial",Font.BOLD,15));
        round3.setFont(new Font("Arial",Font.BOLD,15));
        round5.setFont(new Font("Arial",Font.BOLD,15));
        
		//The Game
		panel1 = new JPanel(new BorderLayout());
		label2 = new JLabel("Click on Start!");
		label2.setFont(new Font("Arial",Font.PLAIN,20));
		label2.setBorder(new EmptyBorder(0,210,0,0));
		JPanel innerPanel = new JPanel(new BorderLayout());
		JPanel innerPanel0 = new JPanel();
		
		startButton = new JButton("Start");
		startButton.setPreferredSize(new Dimension(100, 40));
		startButton.setFont(new Font("Arial",Font.BOLD,20));
		submitButton = new JButton("Guess");
		submitButton.setFont(new Font("Arial",Font.BOLD,20));
		submitButton.setPreferredSize(new Dimension(140, 50));
		quitGame = new JButton("New Game");
		quitGame.setFont(new Font("Arial",Font.BOLD,20));
		quitGame.setPreferredSize(new Dimension(140, 50));
		
		innerPanel0.add(startButton);
		innerPanel0.setBackground(Color.orange);
		
		JLabel innerLabel = new JLabel();
		innerLabel.setIcon(new ImageIcon(getClass().getResource("image.png")));
		
		JPanel innerPanel1 = new JPanel();
		
		innerPanel1.add(innerLabel);
		innerPanel1.setBackground(Color.orange);
		
		innerPanel.add(innerPanel0,BorderLayout.NORTH);
		innerPanel.add(innerPanel1,BorderLayout.CENTER);
		
		
		
		
		panel1.setBorder(new EmptyBorder(120,120,120,120));
		panel1.add(label2,BorderLayout.NORTH);
		panel1.add(innerPanel,BorderLayout.CENTER);
		
		panel2 = new JPanel(new GridLayout(0,1));
		
		label3 = new JLabel("Enter your guess:");
		label3.setFont(new Font("Arial",Font.BOLD,25));
		label3.setBorder(new EmptyBorder(0,290,0,0));
		
		inputField = new JTextArea(0,5);
		inputField.setLineWrap(true);
        inputField.setWrapStyleWord(true);

		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Arial",Font.BOLD,25));
		scoreLabel.setBorder(new EmptyBorder(0,350,0,0));
		
		label4 = new JLabel();
		label4.setBorder(new EmptyBorder(0,280,0,0));
		label4.setFont(new Font("Arial",Font.BOLD,25));
		
		panel3 = new JPanel();
		inputField.setFont(new Font("Arial",Font.BOLD,20));
		inputField.setBorder(new EmptyBorder(5,70,5,0));
		panel3.add(inputField);
		panel3.setBackground(Color.green);
		
		panel4 = new JPanel();
		panel4.add(submitButton);
		panel4.setBackground(Color.green);
		
		panel5 = new JPanel(new FlowLayout(FlowLayout.LEADING));
		panel5.setBackground(Color.green);
		
		panel5.add(quitGame);
		
		roundLabel = new JLabel("Rounds left: 1");
		roundLabel.setFont(new Font("Arial",Font.BOLD,25));
		turnLabel = new JLabel("Guesses left: 10");
		turnLabel.setFont(new Font("Arial",Font.BOLD,25));
		
		JLabel seperatingLabel = new JLabel(",");
		seperatingLabel.setFont(new Font("Arial",Font.BOLD,25));
		panel5.add(roundLabel);
		panel5.add(seperatingLabel);
		panel5.add(turnLabel);
		
		panelX = new JPanel(new BorderLayout());
		panelX.add(panel5,BorderLayout.NORTH);
		
		panel2.add(scoreLabel);
		panel2.add(label3);
		panel2.add(panel3);
		panel2.add(panel4);
		panel2.add(label4);
		
		panelX.add(panel2,BorderLayout.CENTER);
		
		panel1.setBackground(Color.orange);
		panel2.setBackground(Color.GREEN);
		panelX.setBackground(Color.GREEN);


		//Rest of the code
		
		CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        
        cardPanel.add(panel1);
        cardPanel.add(panelX);
        
        startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(cardPanel);
                frame.setJMenuBar(null);
                frame.remove(panel0);
			}
		});
		
        quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(cardPanel);
				frame.setJMenuBar(menuBar);
				frame.add(panel0,BorderLayout.NORTH);
			}
		});
        
        
        frame.setLayout(new BorderLayout());
		frame.add(panel0,BorderLayout.NORTH);
		frame.add(cardPanel,BorderLayout.CENTER);
		
		menuBar.setBackground(new Color(111,111,111));
		
		JMenu menu4 = new JMenu("About");
		JMenu menu5 = new JMenu("Credits");
		JMenuItem menuItem = new JMenuItem("By Darshan Rao G.");
		menu5.add(menuItem);
		menuItem.setEnabled(false);
		menu4.add(menu5);
		menuBar.add(menu4);
		
		menu4.setFont(new Font("Arial",Font.BOLD,15));
		menu5.setFont(new Font("Arial",Font.BOLD,15));
		menuItem.setFont(new Font("Arial",Font.BOLD,15));
		
		frame.setJMenuBar(menuBar);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
	public void addMenuItem1Listener(ActionListener m1Listener) {
		amateur.addActionListener(m1Listener);
		novice.addActionListener(m1Listener);
		expert.addActionListener(m1Listener);
		master.addActionListener(m1Listener);
		telepath.addActionListener(m1Listener);
	}
	
	public void addMenuItem2Listener(ActionListener m2Listener) {
		range1.addActionListener(m2Listener);
		range2.addActionListener(m2Listener);
		range3.addActionListener(m2Listener);
	}
	
	public void addMenuItem3Listener(ActionListener m3Listener) {
		round1.addActionListener(m3Listener);
		round3.addActionListener(m3Listener);
		round5.addActionListener(m3Listener);
	}
	
	public void addStartButtonListener(ActionListener sListener) {
		startButton.addActionListener(sListener);
	}
	
	public void addQuitGameListener(ActionListener qListener) {
		quitGame.addActionListener(qListener);
	}
	
	public void addSubmitButtonListener(ActionListener sbListener) {
		submitButton.addActionListener(sbListener);
	}
	
	public void setRoundLabel(int rd) {
		this.roundLabel.setText("Rounds left: "+rd);
	}
	
	public void setTurnLabel(int tn) {
		if(tn == 10000)
			this.turnLabel.setText("Guesses left: Infinite");
		else
			this.turnLabel.setText("Guesses left: "+tn);
	}
	
	public int getInputNumber() {
		if(this.inputField.getText() == null || this.inputField.getText().equals(""))
		{
			displayAnswer("Enter a number!");
			return -1;
		}
		try
		{
			return Integer.parseInt(this.inputField.getText());
		}
		catch(Exception e)
		{
			displayAnswer("Enter a number!");
			return -1;
		}
	}
	
	public void displayAnswer(String answerText) {
		label4.setText(answerText);
	}
	
	public void displayScore(String scoreText) {
		scoreLabel.setText(scoreText);
	}
}
