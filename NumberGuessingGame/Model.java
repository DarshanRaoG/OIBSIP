
public class Model {
	private int randomNum;
	private int totalTurns;	
	private int turnsLeft;
	private int roundsLeft;
	private int score;
	private int limit;
	private int multiplier;
	private int penalty;
	
	Model() {
		rndGenerator(10);
		totalTurns = 10;
		turnsLeft = 10;
		roundsLeft = 1;
		score = 0;
		multiplier = 1;
		penalty = 0;
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
	
	void changeMult(int m) {
		this.multiplier = m;
	}
	
	void changePenalty(int p) {
		this.penalty = p;
	}
	
	int calculateScore() {
		this.score = (100 - ((this.totalTurns-this.turnsLeft) * 10)) * this.multiplier - this.penalty;
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
				view.setRoundLabel(this.roundsLeft);
				if(this.roundsLeft != 0)	
				{	
					calculateScore();
					view.setTurnLabel(this.turnsLeft);
					view.displayAnswer("Answer is: "+this.randomNum+", now next round!");
				}
				else
				{
					view.displayAnswer("Answer is: "+this.randomNum);
				}
				this.penalty = 0;
				view.displayPenalty(penalty);
				rndGenerator(this.limit);
			}
			else if(input == this.randomNum)
			{
				this.roundsLeft--;
				this.turnsLeft = this.totalTurns;
				view.setRoundLabel(this.roundsLeft);
				calculateScore();
				if(this.roundsLeft != 0)	
				{	
					view.setTurnLabel(this.turnsLeft);
				}
				else
				{
					view.setTurnLabel(0);
				}
				this.penalty = 0;
				view.displayPenalty(penalty);
				view.displayAnswer("Correct!");
				view.displayHint("");
				rndGenerator(this.limit);
			}
			else if(input > this.randomNum)
			{
				this.turnsLeft--;
				calculateScore();
				view.setRoundLabel(this.roundsLeft);
				view.setTurnLabel(this.turnsLeft);
				view.displayAnswer("Wrong guess");
				view.displayHint(input + " is greater than the answer");
			}
			else if(input < this.randomNum)
			{
				this.turnsLeft--;
				calculateScore();
				view.setRoundLabel(this.roundsLeft);
				view.setTurnLabel(this.turnsLeft);
				view.displayAnswer("Wrong guess");
				view.displayHint(input + " is lesser than the answer");
			}
		}
		else
		{
			view.setRoundLabel(this.roundsLeft);
			if(this.roundsLeft != 0)
				view.setTurnLabel(this.turnsLeft);
		}
	}
}
