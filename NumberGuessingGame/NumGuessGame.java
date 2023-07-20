package numberGuessingGame;

import javax.swing.SwingUtilities;

public class NumGuessGame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			Model model = new Model();
			View view = new View();
			new Controller(model,view);
		});

	}

}
