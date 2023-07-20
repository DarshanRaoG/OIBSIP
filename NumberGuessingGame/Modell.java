
package numberGuessingGame;

public class Modell {
	private int randomNum;
	private int totalTurns;
	private int turnsLeft;
	private int score;
	public int rounds;
	
	Modell() {
		this.randomNum = -1;
		this.totalTurns = 1;
		this.turnsLeft = 1;
		this.score = 0;
		this.rounds = 0;
	}
	
	int getScore() {
		return this.score;
	}
	
	int calculateScore() {
		this.score += (this.totalTurns - this.turnsLeft) * 10;
		return this.score;
	}
	
	void resetScore() {
		this.score = 0;
	}
	
	void updateTurns(int n) {
		this.totalTurns = n;
		this.turnsLeft = n;
	}
	
	void rndGenerator(int limit) {
		this.randomNum = (int)(Math.random() * limit) + 1;
	}
	
	void check(int input) {
		if(this.turnsLeft == 0)
		{
			System.out.println("You are out of turns, answer is: "+this.randomNum);
			return;
		}
		
		if(input == this.randomNum)
		{
			System.out.println("Correct! You took "+(this.totalTurns - this.turnsLeft)+" turns to guess!");
		}
		else if(input > this.randomNum)
		{
			System.out.println(input+" is higher than the answer");
			this.turnsLeft--;
		}
		else if(input < this.randomNum)
		{
			System.out.println(input+" is lower than the answer");
			this.turnsLeft--;
		}
		
		calculateScore();
	}
}
