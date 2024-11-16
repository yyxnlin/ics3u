// Lynn Tao
// Assignment #2: The Wong Dice Game (no bonus, if bonus doesn't work)
// March 10, 2023
// This is a user vs. computer game where you can gamble to roll your dice as many times as you want in a round to gain more points! However, if you roll a 1, you lose EVERYTHING you earned in that round! Whoever reaches 100 points first wins!!

import java.util.Scanner;

public class DiceAssignment {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int dice = 0 ;
		boolean userContinue = true; 
		int userScore = 0;
		int i = 1;
		int computerScore = 0;
		int roundScore = 0;
		int computerTurn = 0;
		int userWins = 0;
		int computerWins = 0;
		int continuePlay = 1;
		String placeHolder = " ";
		System.out.print("What is your name? ");
		String name = s.nextLine();
		while (continuePlay == 1) {
			while (userScore < 20 && computerScore < 20) {
				// User Turn
				System.out.println(name + "'s turn:"); 
				while (userContinue == true) {
					dice =(int)(Math.random()*6+1);
					System.out.println("Dice roll = " + dice);
					if (dice == 1) {
						System.out.printf("Round over. %s's score is reset to 0.%n%n", name);
						roundScore = 0;
						userContinue = false;

					}
					else { 
						roundScore += dice;
						System.out.println("Do you want to play again? (y/n)");
						char response = s.nextLine ().charAt (0);
						while (response != 'y' && response != 'Y' && response != 'n' && response != 'N') {
							System.out.println("Invalid response. Please try again.");
							response = s.nextLine ().charAt (0); 
						}
						if (response == 'n' || response == 'N') {
							userContinue = false;
							userScore += roundScore;
							System.out.printf("Round over. %s's score is %d%n", name, roundScore);
						}
					}
				} 
				System.out.printf("%s total score: %d%nComputer total score: %d%n%n", name, userScore, computerScore);
				roundScore = 0;
				if (userScore < 20) {
					//Random number of turns for computer
					computerTurn = (int) (Math.random() * 6) + 3;
					System.out.println("Print enter for computer's turn:");
					placeHolder = s.nextLine();
					while (!placeHolder.equals("")) {
						System.out.println("Invalid! Press enter to continue");
						placeHolder = s.nextLine();
					}
					System.out.println("Computer's Turn:");
					//Computer Turn
					while (i <= (computerTurn)) {
						dice =(int)(Math.random()*6+1);
						System.out.println("Dice roll = " + dice);
						
						if (dice == 1) {
							System.out.printf("Round over. Computer's score is reset to 0.%n%n");
							roundScore = 0;
							i += computerTurn;
						}
						else if (dice + roundScore + computerScore >= 20) {
							roundScore += dice;
							i += computerTurn;
						}
						else { 
							roundScore += dice;
							i++;
							System.out.print("Press enter to continue.");
							placeHolder = s.nextLine();
							while (!placeHolder.equals("")) {
								System.out.println("Invalid! Press enter to continue");
								placeHolder = s.nextLine();
							}
							
						}	
					}
					
					
					
					if (dice != 1) {
						System.out.printf("Round over. Computers's score is %d%n", roundScore);
					}
					computerScore += roundScore;
					System.out.printf("%s total score: %d%nComputer total score: %d%n%n", name, userScore, computerScore);
					if (userScore < 20 && computerScore < 20) {
						System.out.printf("Press enter for %s's turn.", name);
						placeHolder = s.nextLine();
						while (!placeHolder.equals("")) {
							System.out.println("Invalid! Press enter to continue");
							placeHolder = s.nextLine();
						}
						
						roundScore = 0;
						userContinue = true;
						i = 1;
					}
				}
				
			}
			if (userScore >= 20) {
				userWins += 1;
				System.out.printf("%s has won! %s: %d\tComputer: %d%n%s won %d times and Computer won %d times.", name, name, userScore, computerScore, name, userWins, computerWins);
			}
			else {
				computerWins += 1;
				System.out.printf("Computer has won! %s: %d\tComputer: %d%n%s won %d times and Computer won %d times.\n", name, userScore, computerScore, name, userWins, computerWins);	
			}
			System.out.println("Do you want to play again?");
			char response = s.nextLine ().charAt (0);
			while (response != 'y' && response != 'Y' && response != 'n' && response != 'N') {
				System.out.println("Invalid response. Please try again.");
				response = s.nextLine ().charAt (0); 
			}
			if (response == 'n' || response == 'N') {
				continuePlay = 0;
			}
			else {
				userScore = 0;
				computerScore = 0;
				roundScore = 0;
				userContinue = true;
			}
			
			
		}
		System.out.printf("Thank you for playing.\n%s has won %d times and Computer won %d times.", name, userWins, computerWins);
	}	

}
