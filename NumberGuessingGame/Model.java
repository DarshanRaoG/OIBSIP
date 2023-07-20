package numberGuessingGame;

public class Model {
	private int randomNum;
	private int totalTurns;
	private int turnsLeft;
	private int roundsLeft;
	private int score;
	private int limit;
	
	
	Model() {
		rndGenerator(10);
		totalTurns = 10;
		turnsLeft = 10;
		roundsLeft = 1;
		score = 0;
	}
	
	void setRounds(View view,int r) {
		this.roundsLeft = r;
		view.setRoundLabel(r);
	}
	
	void setTurns(View view,int n) {
		this.totalTurns = n;
		this.turnsLeft = n;
		view.setTurnLabel(n);
	}
	
	int getScore() {
		return this.score;
	}
	
	int calculateScore() {
		int diff = this.totalTurns - this.turnsLeft;
		if(diff == 1)
			this.score += 50;
		else if(diff > 1 && diff <= (limit/2))
			this.score += 25;
		else if(diff > (limit/2) + 1)
			this.score += 10;
		else
			this.score += 5;
		
		return this.score;
	}
	
	void resetScore() {
		this.score = 0;
	}
	
	void rndGenerator(int limit) {
		this.limit = limit;
		this.randomNum = (int)(Math.random() * limit) + 1;
	}
	
	boolean checkRounds() {
		return this.roundsLeft != 0;
	}
	
	boolean checkTurns() {
		return this.turnsLeft == 0;
	}
	
	void check(View view,int input) {
		if(this.checkRounds())
		{
			if(this.checkTurns())
			{
				this.roundsLeft--;
				this.turnsLeft = this.totalTurns;
				calculateScore();
				view.setRoundLabel(this.roundsLeft);
				if(this.roundsLeft != 0)	view.setTurnLabel(this.turnsLeft);
				view.displayAnswer("Answer is:"+this.randomNum);
				rndGenerator(this.limit);
			}
			else if(input == this.randomNum)
			{
				this.roundsLeft--;
				this.turnsLeft = this.totalTurns;
				calculateScore();
				view.setRoundLabel(this.roundsLeft);
				view.setTurnLabel(this.turnsLeft);
				view.displayAnswer("Correct!");
				rndGenerator(this.limit);
			}
			else if(input > this.randomNum)
			{
				this.turnsLeft--;
				calculateScore();
				view.setRoundLabel(this.roundsLeft);
				view.setTurnLabel(this.turnsLeft);
				view.displayAnswer(input + " is greater than the answer");
			}
			else if(input < this.randomNum)
			{
				this.turnsLeft--;
				calculateScore();
				view.setRoundLabel(this.roundsLeft);
				view.setTurnLabel(this.turnsLeft);
				view.displayAnswer(input + " is lesser than the answer");
			}
		}
		else
		{
			view.setRoundLabel(this.roundsLeft);
			view.setTurnLabel(this.turnsLeft);
			view.displayAnswer("");
		}
	}
}
