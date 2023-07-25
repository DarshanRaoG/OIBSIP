
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class Exam {
	private JFrame frame;
	private JPanel mainPanel,loginPanel,optionPanel,updatePanel,questionPanel,innerQPanel,innerInQPanel,innerQMPanel,innerSubPanel,timerPanel,scorePanel;
	
	private JLabel imgLabel,welcome,username,password;
	private JTextArea name;
	private JPasswordField pwd;
	private JButton loginButton,backButton;
	
	private JLabel wel,todo1,scoreLabel;
	private JButton startButton,changeButton;
	
	private JLabel aboutLabel,username1,currPassword,newPassword;
	private JTextArea name1;
	private JPasswordField currPwd,newPwd;
	private JButton updateButton;
	
	private JLabel q[] = new JLabel[10];
	private JRadioButton mcq[][] = new JRadioButton[10][4];
	private JButton submitButton;
	private int score = 0,currentQIndex = 0;
	private JRadioButton optionSelected = null;
	
	private String quests[] = new String[10],ans[] = new String[10],choices[][] = new String[10][4];
	private CardLayout cardLayout;
	
	private Map<String,String> uPList;
	
	private int seconds;
	private JLabel timerLabel;
	private Timer timer;
	
	Exam() {
		uPList = new HashMap<String,String>();
		loadUsersFromFile();
		
		frame = new JFrame("Online Examination");
		frame.setSize(1300,900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		
		
		//Login Panel
		loginPanel = new JPanel(new GridLayout(4,1));
		imgLabel = new JLabel(new ImageIcon("C:\\Users\\dell\\Desktop\\man.PNG"));
		loginPanel.setBackground(new Color(176, 224, 230));
		
		JPanel welcPanel = new JPanel();
		welcome = new JLabel("Online Examination");
		welcome.setFont(new Font("Calibri",Font.BOLD,32));
		welcPanel.add(welcome);
		welcPanel.setBackground(new Color(176,224,230));
		
		JPanel detailPanel = new JPanel(new GridLayout(2,2)); 
		JPanel unamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pwdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		username = new JLabel("Username: ");
		username.setFont(new Font("Calibri",Font.BOLD,24));
		password = new JLabel("Password: ");
		password.setFont(new Font("Calibri",Font.BOLD,24));
		
		name = new JTextArea(0,15);
		name.setFont(new Font("Calibri",Font.BOLD,20));
		pwd = new JPasswordField(15);
		pwd.setFont(new Font("Calibri",Font.BOLD,20));
		name.setBorder(new EmptyBorder(5,10,5,0));
		pwd.setBorder(new EmptyBorder(5,10,5,0));
		
		
		unamePanel.add(username);
		unamePanel.add(name);
		unamePanel.setBackground(new Color(176,224,230));
		pwdPanel.add(password);
		pwdPanel.add(pwd);
		pwdPanel.setBackground(new Color(176,224,230));
		
		detailPanel.add(unamePanel);
		detailPanel.add(pwdPanel);
		detailPanel.setBackground(new Color(176,224,230));
		
		JPanel buttonPanel = new JPanel();
		loginButton = new JButton("Login");
		loginButton.setFont(new Font("Arial",Font.PLAIN,20));
		loginButton.setPreferredSize(new Dimension(100,40));
		loginButton.setBackground(Color.green);
		buttonPanel.add(loginButton);
		buttonPanel.setBackground(new Color(176,224,230));
		
		
		loginPanel.add(imgLabel);
		loginPanel.add(welcPanel);
		loginPanel.add(detailPanel);
		loginPanel.add(buttonPanel);
		
		loginButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        handleLoginButton();
		    }
		});
		
		
		//Update Panel
		updatePanel = new JPanel(new GridLayout(4,1));
		updatePanel.setBackground(new Color(176,224,230));
		
		JPanel aboutPanel = new JPanel();
		aboutLabel = new JLabel("Update Profile and Password");
		aboutLabel.setFont(new Font("Calibri",Font.BOLD,32));
		aboutPanel.add(aboutLabel);
		aboutPanel.setBackground(new Color(176,224,230));
		
		JPanel newDetailPanel = new JPanel(new GridLayout(3,2)); 
		JPanel newUnamePanel = new JPanel(new FlowLayout());
		newUnamePanel.setBackground(new Color(176,224,230));
		JPanel currPwdPanel = new JPanel(new FlowLayout());
		currPwdPanel.setBackground(new Color(176,224,230));
		JPanel newPwdPanel = new JPanel(new FlowLayout());
		newPwdPanel.setBackground(new Color(176,224,230));
		
		username1 = new JLabel("Username:             ");
		username1.setFont(new Font("Calibri",Font.BOLD,24));
		currPassword = new JLabel("Current Password: ");
		currPassword.setFont(new Font("Calibri",Font.BOLD,24));
		newPassword = new JLabel("New Password:       ");
		newPassword.setFont(new Font("Calibri",Font.BOLD,24));
		
		name1 = new JTextArea(0,15);
		name1.setFont(new Font("Calibri",Font.BOLD,20));
		currPwd = new JPasswordField(15);
		currPwd.setFont(new Font("Calibri",Font.BOLD,20));
		newPwd = new JPasswordField(15);
		newPwd.setFont(new Font("Calibri",Font.BOLD,20));
		name1.setBorder(new EmptyBorder(5,10,5,0));
		currPwd.setBorder(new EmptyBorder(5,10,5,0));
		newPwd.setBorder(new EmptyBorder(5,10,5,0));
		
		
		newUnamePanel.add(username1);
		newUnamePanel.add(name1);
		currPwdPanel.add(currPassword);
		currPwdPanel.add(currPwd);
		newPwdPanel.add(newPassword);
		newPwdPanel.add(newPwd);
		
		newDetailPanel.add(newUnamePanel);
		newDetailPanel.add(currPwdPanel);
		newDetailPanel.add(newPwdPanel);
		
		JPanel buttonPanel1 = new JPanel();
		updateButton = new JButton("Update");
		updateButton.setFont(new Font("Arial",Font.PLAIN,20));
		updateButton.setPreferredSize(new Dimension(100,40));
		updateButton.setBackground(Color.green);
		buttonPanel1.add(updateButton);
		buttonPanel1.setBackground(new Color(176,224,230));
		
		updatePanel.add(aboutPanel);
		updatePanel.add(newDetailPanel);
		updatePanel.add(buttonPanel1);
		
		updateButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        handleUpdateButton();
		    }
		});
		
		//Options panel
		optionPanel = new JPanel(new GridLayout(3,1));
		optionPanel.setBackground(new Color(176,224,230));
		
		todo1 = new JLabel("Are you ready to give the exam?");
		todo1.setFont(new Font("Calibri",Font.BOLD,32));
		wel = new JLabel("Hi!");
		wel.setFont(new Font("Calibri",Font.BOLD,32));
		optionPanel.add(wel);
		optionPanel.add(todo1);
		wel.setHorizontalAlignment(JLabel.CENTER);
		wel.setVerticalAlignment(JLabel.CENTER);
		todo1.setHorizontalAlignment(JLabel.CENTER);
		todo1.setVerticalAlignment(JLabel.NORTH);
		
		JPanel buttonPanel2 = new JPanel();
		buttonPanel2.setBackground(new Color(176,224,230));
		JPanel buttonPanel2_1 = new JPanel();
		buttonPanel2_1.setBackground(new Color(176,224,230));
		JPanel buttonPanel2_2 = new JPanel();
		buttonPanel2_2.setBackground(new Color(176,224,230));
		
		
		startButton = new JButton("Start");
		startButton.setFont(new Font("Arial",Font.PLAIN,20));
		startButton.setPreferredSize(new Dimension(100,40));
		startButton.setBackground(Color.green);
		changeButton = new JButton("Update Profile");
		changeButton.setFont(new Font("Arial",Font.PLAIN,20));
		changeButton.setPreferredSize(new Dimension(170,40));
		changeButton.setBackground(Color.yellow);
		
		buttonPanel2_1.add(startButton);
		buttonPanel2_2.add(changeButton);
		buttonPanel2.add(buttonPanel2_1);
		buttonPanel2.add(buttonPanel2_2);
		
		optionPanel.add(buttonPanel2);
		
		startButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	if(currentQIndex != 0)
		    	{
		    		resetQuestions();
		    		currentQIndex = 0;
		    	}
		    	for (int i=0;i<10;i++) {
			        for (int j=0;j<4;j++) {
			            mcq[i][j].setSelected(false);
			        }
			    }
		        handleStartButton();
		        setTimer();
		    }
		});
		
		changeButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        handleChangeButton();
		    }
		});
		
		//Timer Panel
		timerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		timerLabel = new JLabel("00:05:00");
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		timerLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		timerPanel.add(timerLabel);
		timerPanel.setBackground(new Color(176,224,230));
		
		
		//Question and answer panel
		questionPanel = new JPanel(new GridLayout(5,1));
		questionPanel.setBackground(new Color(176,224,230));
		questionPanel.add(timerPanel);
		innerQPanel = new JPanel(new GridLayout(1,1));
		innerQPanel.setBackground(new Color(176,224,230));
		innerInQPanel = new JPanel(new BorderLayout());
		innerInQPanel.setBackground(new Color(176,224,230));
		innerQMPanel = new JPanel(new GridLayout(2,2));
		innerQMPanel.setBackground(new Color(176,224,230));
		innerSubPanel = new JPanel();
		innerSubPanel.setBackground(new Color(176,224,230));
		
		
		submitButton = new JButton("Submit");
		submitButton.setFont(new Font("Arial",Font.PLAIN,20));
		submitButton.setPreferredSize(new Dimension(100,40));
		submitButton.setBackground(Color.green);
		innerSubPanel.add(submitButton);
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleSubmitButton();
			}
		});
		
		
		for(int i=0;i<10;i++)
		{
			q[i] = new JLabel();
			for(int j=0;j<4;j++)
			{
				mcq[i][j] = new JRadioButton();
	            mcq[i][j].addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    handleRadioButtonClick((JRadioButton) e.getSource());
	                }
	            });
			}
		}
		
		getQuestions();
		getChoices();
		answers();
		score = 0;
		
		displayQuestionAndOptions(currentQIndex);
		
		//Score Panel
		scorePanel = new JPanel(new GridLayout(2,1));
		scoreLabel = new JLabel("Your score: "+score+"/100");
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		scoreLabel.setFont(new Font("Calibri",Font.BOLD,34));
		scorePanel.add(scoreLabel);        
		backButton = new JButton("Quit");
		backButton.setFont(new Font("Arial",Font.PLAIN,24));
		backButton.setBackground(Color.orange);
		JPanel backPanel = new JPanel();
		backPanel.add(backButton);
		backPanel.setBackground(new Color(176,224,230));
		scorePanel.add(backPanel);
		scorePanel.setBackground(new Color(176,224,230));

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentQIndex != 0)
				{
					if(currentQIndex == 10)
						currentQIndex--;
					resetQuestions();
					currentQIndex = 0;
				}
				clearTexts();
				cardLayout.show(mainPanel, "LOGIN");
			}
		});
		
		
		//rest of the code
		mainPanel.add(loginPanel,"LOGIN");
		mainPanel.add(updatePanel,"UPDATE");
		mainPanel.add(optionPanel,"OPTIONS");
		mainPanel.add(questionPanel,"QUESTIONS");
		mainPanel.add(scorePanel,"SCORE");
		frame.add(mainPanel);
		
		frame.setVisible(true);
	}
	
	void setTimer() {
		if(timer != null)
		{
			timer.stop();
			timerLabel.setText("00:05:00");
		}
		
		seconds = 300;
		
		timer = new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seconds--;
				updateTimerLabel();
			}
		});
		
		timer.start();
	}
	
	void updateTimerLabel() {
		int min = (seconds % 3600) / 60;
		int sec = seconds % 60;
		
		String timeString = String.format("%02d:%02d:%02d", 0, min, sec);
        timerLabel.setText(timeString);
        
        if(timeString.equals("00:00:00"))
        {
        	
			submitButton.setText("Submit");
			submitButton.setBackground(Color.green);
			scoreLabel.setText("Your score: "+score+"/100");
			resetQuestions();
        	currentQIndex = 0;
			score = 0;
			cardLayout.show(mainPanel, "SCORE");
        }
	}
	
	void resetQuestions() {
		questionPanel.remove(innerQPanel);
		questionPanel.remove(innerInQPanel);
		questionPanel.remove(innerSubPanel);
		innerQPanel.remove(q[currentQIndex]);
		
		for (int i=0;i<10;i++) {
	        for (int j=0;j<4;j++) {
	            mcq[i][j].setSelected(false);
	        }
	    }
		
		for(int j=0;j<4;j++)
		{
			innerQMPanel.remove(mcq[currentQIndex][j]);
		}
		
		questionPanel.remove(innerQPanel);
        innerInQPanel.remove(innerQMPanel);
        questionPanel.remove(innerInQPanel);
        questionPanel.remove(innerSubPanel);
        
        questionPanel.revalidate();
        questionPanel.repaint();
        innerInQPanel.revalidate();
        innerInQPanel.repaint();
        
        currentQIndex = 0;
		optionSelected = null;
		score = 0;
	}
	
	void getChoices() {
		choices[0][0] = "A scripting language";
		choices[0][1] = "A markup language";
		choices[0][2] = "An object-oriented programming language";
		choices[0][3] = "A database management system";
	
		choices[1][0] = "Microsoft";
		choices[1][1] = "Apple Inc.";
		choices[1][2] = "Oracle Corporation";
		choices[1][3] = "Sun Microsystems";
		
		choices[2][0] = ".java";
		choices[2][1] = ".class";
		choices[2][2] = ".exe";
		choices[2][3] = ".jac";
		
		choices[3][0] = "new";
		choices[3][1] = "create";
		choices[3][2] = "instance";
		choices[3][3] = "object";
		
		choices[4][0] = "Single inheritance";
		choices[4][1] = "Multiple inheritance";
		choices[4][2] = "Hybrid inheritance";
		choices[4][3] = "Explicit inheritance";
		
		choices[5][0] = "main";
		choices[5][1] = "start";
		choices[5][2] = "run";
		choices[5][3] = "execute";
		
		choices[6][0] = "const";
		choices[6][1] = "final";
		choices[6][2] = "static";
		choices[6][3] = "constant";
		
		choices[7][0] = "5";
		choices[7][1] = "6";
		choices[7][2] = "4";
		choices[7][3] = "Error";
		
		choices[8][0] = "try";
		choices[8][1] = "handle";
		choices[8][2] = "catch";
		choices[8][3] = "exception";
		
		choices[9][0] = "It is executed when an exception is caught.";
		choices[9][1] = "It is executed if no exception occurs.";
		choices[9][2] = "It is used to throw custom exceptions.";
		choices[9][3] = "It is executed after the \"catch\" block, regardless of whether an exception occurs or not.";
	}
	
	
	
	void getQuestions() {
		quests[0] = "1. What is Java?";
		
		quests[1] = "2. Which company originally developed Java?";
		
		quests[2] = "3. What is the extension of Java source files?";
		
		quests[3] = "4. In Java, which keyword is used to create a new instance of a class?";
		
		quests[4] = "5. Which type of inheritance is supported in Java?";
		
		quests[5] = "6. What is the entry point of a Java program?";
		
		quests[6] = "7. Which keyword is used to define a constant variable in Java?";
		
		quests[7] = "8. What is the output of the following for x = 5? System.out.println(x++);";
		
		quests[8] = "9. Which Java keyword is used to handle exceptions?";
		
		quests[9] = "10. What is the purpose of the \"finally\" block in Java exception handling?";
	}
	
	
	void handleLoginButton() {
		char[] passwordChars = pwd.getPassword();
        String password1 = new String(passwordChars);
		String user_name = name.getText();
		
		if(uPList.containsKey(user_name))
		{
			if(uPList.get(user_name).equals(password1))
			{
				wel.setText("Hi "+user_name+"!");
				cardLayout.show(mainPanel, "OPTIONS");
			}
			else
			{
				JOptionPane.showMessageDialog(frame,"Invalid Username or Password!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frame,"Invalid Username or Password!","Error",JOptionPane.ERROR_MESSAGE);
		}

		
	}
	
	void handleStartButton() {
		displayQuestionAndOptions(currentQIndex);
		cardLayout.show(mainPanel, "QUESTIONS");
	}
	
	void handleChangeButton() {
		clearTexts();
		cardLayout.show(mainPanel, "UPDATE");
	}
	
	void loadUsersFromFile() {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("uPFile.txt")))) 
		{
			String line;
			uPList = new HashMap<>();
		
		    while ((line = reader.readLine()) != null) 
		    {
		    	String userData[] = line.split(",");
		        uPList.put(userData[0],userData[1]);
		    }
		} 
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(frame,"Error retrieving user data to file!","Error",JOptionPane.ERROR_MESSAGE);
		}
    }
	
	void saveUser(String un,String pd) {
		uPList.put(un,pd);

		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(new File("uPFile.txt")));
			Set<String> userSet = new HashSet<>();
			 for (Map.Entry<String, String> entry : uPList.entrySet()) 
			 {
				 if(userSet.add(entry.getKey()))
				 {
					 pw.println(entry.getKey() + "," + entry.getValue());
				 }
			 }
			 
			 pw.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(frame, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void handleUpdateButton() {
		String nm = name1.getText();
		char temp1[] = currPwd.getPassword();
		char temp2[] = newPwd.getPassword();
		String cuP = new String(temp1);
		String neP = new String(temp2);
		
		if(uPList.containsKey(nm))
		{
			if(uPList.get(nm).equals(cuP))
			{
				if(neP != null)
				{
					saveUser(nm,neP);
					cardLayout.show(mainPanel, "LOGIN");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame,"Invalid Username or Password!","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(frame,"Invalid Username or Password!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	void handleRadioButtonClick(JRadioButton selectedRadioButton) {
		optionSelected = selectedRadioButton;
	}
	
	void handleSubmitButton() {
		 if (optionSelected == null) {
		     JOptionPane.showMessageDialog(frame,"No option selected!","Warning",JOptionPane.ERROR_MESSAGE);   
			 return; 
	    }
		 
		if(optionSelected != null)
		{
			String optSel = "";
			int ind = optionSelected.getText().indexOf("'>");
			int ind1 = optionSelected.getText().indexOf("</");
			char opSel[] = optionSelected.getText().toCharArray();
			
			for(int i=ind+2;i<ind1;i++)
			{
				optSel += opSel[i];
			}
			
			for(int i=0;i<10;i++)
			{
				if(optSel.equalsIgnoreCase(ans[i]))
				{
					score += 10;
					break;
				}
			}
		}
		
		currentQIndex++;
		if(currentQIndex >= 10)
		{
			submitButton.setText("Submit");
			submitButton.setBackground(Color.green);
			scoreLabel.setText("Your score: "+score+"/100");
			score = 0;
			cardLayout.show(mainPanel, "SCORE");
		}
		else
		{
			displayQuestionAndOptions(currentQIndex);
		}
		optionSelected = null;
		
	}
	
	 void displayQuestionAndOptions(int index) {
		if(index > 0)
		{
			questionPanel.remove(innerQPanel);
			questionPanel.remove(innerInQPanel);
			questionPanel.remove(innerSubPanel);
			innerQPanel.remove(q[index-1]);
			for(int j=0;j<4;j++)
			{
				innerQMPanel.remove(mcq[index-1][j]);
			}
		}
	 
        questionPanel.remove(innerQPanel);
        innerInQPanel.remove(innerQMPanel);
        questionPanel.remove(innerInQPanel);
        questionPanel.remove(innerSubPanel);
    
        q[index].setText(quests[index]);
        q[index].setFont(new Font("Calibri",Font.BOLD,32));
        q[index].setHorizontalAlignment(JLabel.CENTER);
        innerQPanel.add(q[index]);
        
        ButtonGroup currentButtonGroup = new ButtonGroup();
        
        for (int j = 0; j < 4; j++) {
        	mcq[index][j].setText("<html><body style='width: 400px;'>" + choices[index][j] + "</body></html>");
            mcq[index][j].setFont(new Font("Calibri",Font.PLAIN,24));
        	mcq[index][j].setBackground(new Color(176,224,230));
        	
        	mcq[index][j].setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        	mcq[index][j].setHorizontalAlignment(SwingConstants.CENTER);
            mcq[index][j].setVerticalAlignment(SwingConstants.CENTER);
            
            currentButtonGroup.add(mcq[index][j]);
            innerQMPanel.add(mcq[index][j]);
            mcq[index][j].setSelected(false);
        }
        
        currentButtonGroup.clearSelection();
        
        questionPanel.add(innerQPanel);
        innerInQPanel.add(innerQMPanel,BorderLayout.CENTER);
        questionPanel.add(innerInQPanel);
        if(index == 9)
		{
			submitButton.setText("Finish");
			submitButton.setBackground(Color.orange);
		}
        questionPanel.add(innerSubPanel);
        
        questionPanel.revalidate();
        questionPanel.repaint();
        innerInQPanel.revalidate();
        innerInQPanel.repaint();
	 }
	 
	void clearTexts() {
		name.setText("");
		pwd.setText("");
		name1.setText("");
		currPwd.setText("");
		newPwd.setText("");
	}
	
	void answers() {
		ans[0] = "An object-oriented programming language";
		
		ans[1] = "Sun Microsystems";
		
		ans[2] = ".java";
		
		ans[3] = "new";
		
		ans[4] = "Single inheritance";
		
		ans[5] = "main";
		
		ans[6] = "final";
		
		ans[7] = "5";

		ans[8] = "catch";
		
		ans[9] = "It is executed after the \"catch\" block, regardless of whether an exception occurs or not.";
	}
}
