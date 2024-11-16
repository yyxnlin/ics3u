// Lynn Tao
// Assignment #2: The Wong Dice Game (with bonus)
// March 10, 2023
// This is a user vs. computer game where you can gamble to roll your dice as many times as you want in a round to gain more points! However, if you roll a 1, you lose EVERYTHING you earned in that round! Whoever reaches 100 points first wins!! 


import java.util.Scanner;

public class DiceAssignmentBonus {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Welcome to the Wong Dice Game!");

		// asking for user's name
		System.out.print("\nWhat is your name? ");
		String name = in.nextLine();

		// choosing one die/two dice game mode
		System.out.print("\nSelect a game mode: \n  (1) One die\n  (2) Two dice\n\nType the number (1/2): ");
		char gameMode = (in.nextLine() + " ").charAt(0);
		while (gameMode != '1' && gameMode != '2') { // 1 (one die) or 2 (2 dice)
			System.out.print("    Invalid input. Please select 1 or 2: ");
			gameMode = (in.nextLine() + " ").charAt(0);
		}
		System.out.println();

		// initializing variables
		final int winningScore = 100;
		
		int computerWins = 0;
		int userWins = 0;
		
		String lineBorder = "---------------------------";
		String starBorder = "******************************";

		// game in progress
		while (true) {
			// declaring/initializing variables
			int computerScore = 0;
			int userScore = 0;
			String keyClicked;

			// option 1: 1 die
			if (gameMode == 49) {
				// when game is running (both points under 100)
				while (computerScore < winningScore && userScore < winningScore) {
					// user's turn
					int userRoundScore = 0;
					char playAgain = 'y';

					System.out.println(name + "'s turn:");

					// user is playing
					while (playAgain == 'y' || playAgain == 'Y') {
						// rolling dice + calculating score
						int diceRoll = (int) (Math.random()*6) + 1;
						System.out.println("    Dice roll = " + diceRoll);
						if (diceRoll == 1) { // 1 is rolled -> round score is 0, turn ends
							userRoundScore = 0;
							System.out.println("Round over. " + name + "'s round score reset to 0.");
							break; // user's turn ends
						}

						userRoundScore += diceRoll; // anything other than 1 is rolled -> add to round score

						// if user wants to replay
						System.out.print("       Do you want to roll again? (y/n) ");
						playAgain = (in.nextLine() + " ").charAt(0);

						while (playAgain != 'n' && playAgain != 'N' && playAgain != 'Y' && playAgain != 'y') { // error checking
							System.out.print("          Invalid input. Please type y/n: ");
							playAgain = (in.nextLine() + " ").charAt(0);
						}

					}
					
					// user's turn is over from typing 'n' (not by rolling a 1) -> display total round score
					if (userRoundScore > 0) {
						userScore += userRoundScore;
						System.out.printf("Round over. %s's total round score is %d.\n", name, userRoundScore);
					}

					// display current scores
					System.out.printf("%s\n%10s %10s\n%10d %10d\n%s\n", lineBorder, name, "Computer", userScore, computerScore, lineBorder);

					// user's score is greater than 100 -> user wins and game ends
					if (userScore >= winningScore) {
						System.out.printf("******* WINNER: %s!! *******\n", name);
						userWins++;

						System.out.printf("    %-9s| %d wins\n    Computer | %d wins\n%s\n", name, userWins, computerWins, starBorder);
						break;
					}

					// user's score is not greater than 100, then game continues
					else {
						// asking for computer's turn 
						System.out.print("Press ENTER for computer's turn: ");
						keyClicked = in.nextLine();
						while (!keyClicked.equals("")) { // error checking
							System.out.print("    Invalid input. Press ENTER for computer's turn: ");
							keyClicked = in.nextLine();
						}
						System.out.println();
					}


					// computer's turn
					int computerRoundScore = 0;
					System.out.println("Computer's turn:");
					int turns = (int) (Math.random()*6) + 3; // random number from 3-8 (number of times to roll)

					// looping for number of turns to roll
					for (int i = 0; i < turns && computerRoundScore + computerScore < winningScore; i++) {
						int diceRoll = (int) (Math.random()*6) + 1;
						System.out.println("    Dice roll = " + diceRoll);

						if (diceRoll == 1) { // computer rolls 1 -> round score set to 0, loses its turn
							computerRoundScore = 0;
							System.out.println("Round over. Computer's round score is reset to 0.");
							break;
						}

						computerRoundScore += diceRoll; // anything other than 1 is rolled -> increase round score

						// waiting for user to click enter for next die
						System.out.print("       Press ENTER to continue.");
						keyClicked = in.nextLine();
						while (!keyClicked.equals("")) { // error checking
							System.out.print("          Invalid input. Press ENTER to continue: ");
							keyClicked = in.nextLine();
						}
					}

					
					// if computer's turn ends by running out of chances (not by rolling a 1)
					if (computerRoundScore > 0) {
						computerScore += computerRoundScore;
						System.out.printf("Round over. Computer's total round score is %d.\n", userRoundScore);
					}

					// display current scores
					System.out.printf("%s\n%10s %10s\n%10d %10d\n%s\n", lineBorder, name, "Computer", userScore, computerScore, lineBorder);

					// if computer gets 100 points, computer wins
					if (computerScore >= winningScore) {
						System.out.println("***** WINNER: COMPUTER!! *****");
						computerWins++;

						System.out.printf("    %-9s| %d wins\n    Computer | %d wins\n%s\n", name, userWins, computerWins, starBorder);
						break;

					}

					// if computer doesn't win, wait for user to click enter for their turn
					else {
						// asking for user's turn (keep asking until enter is clicked)
						System.out.print("Press ENTER for " + name + "'s turn: ");
						keyClicked = in.nextLine();
						while (!keyClicked.equals("")) { // error checking
							System.out.print("    Invalid input. Press ENTER for " + name + "'s turn: ");
							keyClicked = in.nextLine();
						}
					}
				}	

				// when a winner is decided (someone reaches 100 points), ask if user wants to play again
				System.out.print("Do you want to play again? (y/n) ");
				char playAgain = (in.nextLine() + " ").charAt(0);
				while (playAgain != 'n' && playAgain != 'N' && playAgain != 'Y' && playAgain != 'y') { // error checking
					System.out.print("       Invalid input. Please type y/n: ");
					playAgain = (in.nextLine() + " ").charAt(0);
				}
				// if user doesn't want to play again, break
				if(playAgain == 'n' || playAgain == 'N') {
					break;
				}
				System.out.println();

			}

			// option 2: two dice
			else {
				// when game is running (both points under 100)
				while (computerScore < winningScore && userScore < winningScore) {
					// user's turn
					int userRoundScore = 0;
					char playAgain = 'y';

					System.out.println(name + "'s turn:");

					// user is playing
					while (playAgain == 'y' || playAgain == 'Y') {
						// rolling dice + calculating score
						int diceOne = (int) (Math.random()*6) + 1;
						int diceTwo = (int) (Math.random()*6) + 1;
						System.out.println("    Die one = " + diceOne);
						System.out.println("    Die two = " + diceTwo);

						if (diceOne == 1 && diceTwo == 1) { // double 1's rolled -> +25 points
							userRoundScore += 25;
							System.out.println("Congrats! You rolled two 1's and got 25 more points!");
							System.out.print("Roll again! Press ENTER to continue.");
							keyClicked = in.nextLine();
							while (!keyClicked.equals("")) {
								System.out.print("          Invalid input. Press ENTER to continue: ");
								keyClicked = in.nextLine();
							}
							continue; // must roll again
						}


						else if (diceOne == 1 || diceTwo == 1) { // single 1 rolled -> lose turn + round score 0
							userRoundScore = 0;
							System.out.println("You rolled a single 1! Round over. " + name + "'s round score reset to 0.");
							break; // user's turn ends
						}

						else if (diceOne == diceTwo) { // double rolled -> double score
							userRoundScore += 2*(diceOne + diceTwo);
							System.out.println("You rolled a double and earned twice the points!");
							System.out.print("Roll again! Press ENTER to continue.");
							keyClicked = in.nextLine();
							while (!keyClicked.equals("")) {
								System.out.print("          Invalid input. Press ENTER to continue: ");
								keyClicked = in.nextLine();
							}
							continue; // must roll again
						}

						userRoundScore += diceOne + diceTwo; // no doubles/1's rolled -> add to round score
						

						// if user wants to replay
						System.out.print("       Do you want to roll again? (y/n) ");
						playAgain = (in.nextLine() + " ").charAt(0);

						while (playAgain != 'n' && playAgain != 'N' && playAgain != 'Y' && playAgain != 'y') { // error checking
							System.out.print("          Invalid input. Please type y/n: ");
							playAgain = (in.nextLine() + " ").charAt(0);
						}

					}
					
					// if user didn't get 1 and turn is over (user typed 'n')
					if (userRoundScore > 0) {
						userScore += userRoundScore;
						System.out.printf("Round over. %s's total round score is %d.\n", name, userRoundScore);
					}

					// display current scores
					System.out.printf("%s\n%10s %10s\n%10d %10d\n%s\n", lineBorder, name, "Computer", userScore, computerScore, lineBorder);

					// if user's score is greater than 100, then user wins and game ends
					if (userScore >= winningScore) {
						System.out.printf("******* WINNER: %s!! *******\n", name);
						userWins++;

						System.out.printf("    %-9s| %d wins\n    Computer | %d wins\n%s\n", name, userWins, computerWins, starBorder);
						break;
					}

					// user's score is not greater than 100 -> game continues
					else {
						// asking for computer's turn 
						System.out.print("Press ENTER for computer's turn: ");
						keyClicked = in.nextLine();
						while (!keyClicked.equals("")) { // error checking
							System.out.print("    Invalid input. Press ENTER for computer's turn: ");
							keyClicked = in.nextLine();
						}
						System.out.println();
					}


					// computer's turn
					int computerRoundScore = 0;
					System.out.println("Computer's turn:");
					int turns = (int) (Math.random()*6) + 3;

					for (int i = 0; i < turns && computerRoundScore + computerScore < winningScore; i++) {
						int diceOne = (int) (Math.random()*6) + 1;
						int diceTwo = (int) (Math.random()*6) + 1;
						System.out.println("    Die one = " + diceOne);
						System.out.println("    Die two = " + diceTwo);


						if (diceOne == 1 && diceTwo == 1) { // double 1's rolled -> +25 points
							computerRoundScore += 25;
							if (i == turns - 1) { // if computer is on its last turn, add another round since it must roll again
								turns++;
							}
							System.out.println("       Double 1! +25 points!");
							if (computerScore + computerRoundScore >= winningScore) {
								break;
							}
							System.out.print("       Press ENTER to continue.");
							keyClicked = in.nextLine();
							while (!keyClicked.equals("")) {
								System.out.print("          Invalid input. Press ENTER to continue: ");
								keyClicked = in.nextLine();
							}
							continue; // computer must roll again							

						}

						else if (diceOne == 1 || diceTwo == 1) { // single 1 rolled -> lose turn + round score 0
							computerRoundScore = 0;
							System.out.println("Single 1! Round over. Computer's round score reset to 0.");
							break; // computer's turn ends
						}

						else if (diceOne == diceTwo) { // double rolled -> double score
							computerRoundScore += 2*(diceOne + diceTwo);
							if (i == turns - 1) { // computer is on its last turn -> add another round (must roll again)
								turns++;
							}
							System.out.println("       Double! x2 points!");
							if (computerScore + computerRoundScore >= winningScore) {
								break;
							}
							System.out.print("       Press ENTER to continue.");
							keyClicked = in.nextLine();
							while (!keyClicked.equals("")) { // error checking
								System.out.print("          Invalid input. Press ENTER to continue: ");
								keyClicked = in.nextLine();
							}
							continue; // computer must roll again
						}

						computerRoundScore += diceOne + diceTwo; // no doubles/1's rolled -> add to round score
						
						// waiting for user to click enter for next die
						System.out.print("       Press ENTER to continue.");
						keyClicked = in.nextLine();
						while (!keyClicked.equals("")) { // error checking
							System.out.print("          Invalid input. Press ENTER to continue: ");
							keyClicked = in.nextLine();
						}
					}


					// computer's turn ends by running out of chances or reaching 100 points (and not by rolling a 1)
					if (computerRoundScore > 0) {
						computerScore += computerRoundScore;
						System.out.printf("Round over. Computer's total round score is %d.\n", computerRoundScore);
					}
					
					// display current scores
					System.out.printf("%s\n%10s %10s\n%10d %10d\n%s\n", lineBorder, name, "Computer", userScore, computerScore, lineBorder);

					// computer gets 100 points -> computer wins
					if (computerScore >= winningScore) {
						System.out.println("***** WINNER: COMPUTER!! *****");
						computerWins++;

						System.out.printf("    %-9s| %d wins\n    Computer | %d wins\n%s\n", name, userWins, computerWins, starBorder);
						break;

					}

					// computer doesn't win ->  wait for user to click enter for their turn
					else {
						// asking for user's turn 
						System.out.print("Press ENTER for " + name + "'s turn: ");
						keyClicked = in.nextLine();
						while (!keyClicked.equals("")) { // error checking
							System.out.print("    Invalid input. Press ENTER for" + name + "'s turn: ");
							keyClicked = in.nextLine();
						}
					}
				}	

				// when a player reaches 100 points, ask if user wants to play again 
				System.out.print("Do you want to play again? (y/n) ");
				char playAgain = (in.nextLine() + " ").charAt(0);
				while (playAgain != 'n' && playAgain != 'N' && playAgain != 'Y' && playAgain != 'y') { // error checking
					System.out.print("    Invalid input. Please type y/n: ");
					playAgain = (in.nextLine() + " ").charAt(0);
				}
				// if user doesn't want to play again, break
				if(playAgain == 'n' || playAgain == 'N') {
					break;
				}
				System.out.println();
			}
		}

		// game over
		System.out.println("\nThank you for playing.");
		System.out.printf("%s won %d times and Computer won %d times.", name, userWins, computerWins);
	}

}
