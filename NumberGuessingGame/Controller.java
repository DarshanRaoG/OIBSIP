

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
	private Model model;
	private View view;
	private int rounds,turns,lim;
	private boolean flag;
	private int temp;
	private int penalt;
	
	Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.flag = false;
		this.rounds = 1;
		this.turns = 10;
		this.lim = 10;
		this.penalt = 1;
		view.label4.setVisible(this.flag);
		
		view.addQuitGameListener(new quitGameActionListener());
		view.addSubmitButtonListener(new SubmitButtonActionListener());
		view.addMenuItem1Listener(new Menu1ActionListener());
		view.addMenuItem2Listener(new Menu2ActionListener());
		view.addMenuItem3Listener(new Menu3ActionListener());
		view.addStartButtonListener(new StartButtonActionListener());
		view.addHintButtonListener(new HintButtonActionListener());
	}

	private class StartButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			model.resetScore();
			view.displayScore("Score: "+model.getScore());
			model.rndGenerator(lim);
		}
	}
	
	private class HintButtonActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			view.label4.setVisible(!flag);
			flag = !flag;
			model.changePenalty(penalt);
			view.displayPenalty(penalt);
			penalt *= 2;
		}
	}
	
	private class quitGameActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			model.resetScore();
			model.setRounds(view,rounds);
			model.setTurns(view,turns);
			view.clearInput();
			view.displayAnswer("Can you guess it right?");
			penalt = 1;
			model.changePenalty(0);
			view.displayPenalty(0);
			flag = false;
		}
	}
	
	private class SubmitButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int inputNum = view.getInputNumber();
			if(inputNum < 0)
				return;
			model.check(view,inputNum);
			
			temp = model.getScore();
			if(!model.checkTurns() && model.checkRounds())
			{
				view.displayScore("Score: "+model.getScore());
			}
			else
			{
				view.displayScore("Score: "+temp);
			}
			
			if(flag)
			{
				model.changePenalty(penalt);
				view.displayPenalty(penalt);
				penalt *= 2;
			}
		}
	}
	
	private class Menu1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String menu1Text="";
			String tempText = e.getSource().toString();
			int index = tempText.indexOf("text=");
			for(int i = index+5;i<tempText.length()-1;i++)
			{
				menu1Text = menu1Text + tempText.charAt(i);
			}
			
			switch(menu1Text)
			{
				case "Amateur (10000 Guesses)":
					turns = 10000;
					model.changeMult(1);
					view.displayMulti(1);
					model.setTurns(view,10000);
					break;
					
				case "Novice (20 Guesses)":
					turns = 20;
					model.changeMult(2);
					view.displayMulti(2);
					model.setTurns(view,20);
					break;
					
				case "Expert (10 Guesses)":
					turns = 10;
					model.changeMult(3);
					view.displayMulti(3);
					model.setTurns(view,10);
					break;
					
				case "Master (5 Guesses)":
					turns = 5;
					model.changeMult(4);
					view.displayMulti(4);
					model.setTurns(view,5);
					break;
				
				case "Telepath (1 Guess)":
					model.changeMult(5);
					view.displayMulti(5);
					turns = 1;
					model.setTurns(view,1);
					break;
			}
		}
	}
	
	private class Menu2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String menu2Text="";
			String tempText = e.getSource().toString();
			int index = tempText.indexOf("text=");
			for(int i = index+5;i<tempText.length()-1;i++)
			{
				menu2Text = menu2Text + tempText.charAt(i);
			}
			
			switch(menu2Text)
			{
				case "1 - 10":
					lim = 10;
					model.rndGenerator(10);
					break;
					
				case "1 - 50":
					lim = 50;
					model.rndGenerator(50);
					break;
					
				case "1 - 100":
					lim = 100;
					model.rndGenerator(100);
					break;
			}
		}
	}
	
	private class Menu3ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String menu3Text="";
			String tempText = e.getSource().toString();
			int index = tempText.indexOf("text=");
			for(int i = index+5;i<tempText.length()-1;i++)
			{
				menu3Text = menu3Text + tempText.charAt(i);
			}
			
			switch(menu3Text)
			{
				case "1":
					rounds = 1;
					model.setRounds(view,1);
					break;
					
				case "3":
					rounds = 3;
					model.setRounds(view,3);
					break;
					
				case "5":
					rounds = 5;
					model.setRounds(view,5);
					break;
			}
		}
	}
}

