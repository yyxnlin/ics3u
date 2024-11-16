// Lynn Tao
// Methods and Arrays Programming Exercises Q4

// cards are in a folder called "cards" under project folder

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;


public class MethodsArraysExercisesPart2 implements ActionListener {
	JFrame frame;
	JPanel myPanel, bottomPanel, cardPanelOne, cardPanelTwo, playerOnePanel, playerTwoPanel, buttonsPanel, resultsPanel, label1Panel, label2Panel, score1Panel, score2Panel;
	JLabel label1, label2, label3, score1, score2, result, card1, card2, card3, card4;
	JTextField text1, text2, text3;
	JButton button1, button2;
	int playerOneScore, playerTwoScore;
	int[] randomCards;
	static ImageIcon[] images;

	public MethodsArraysExercisesPart2 () {
		frame = new JFrame ("Card game");
		frame.setPreferredSize(new Dimension(600, 400));
		frame.setLocation(300, 300);

		myPanel = new JPanel ();
		myPanel.setLayout(new BorderLayout ());
		myPanel.setBorder (BorderFactory.createEmptyBorder (50, 50, 50, 50));

		// playerOnePanel and playerTwoPanel contains the name label, cards, and score of each player
		playerOnePanel = new JPanel ();
		playerOnePanel.setLayout(new BorderLayout());

		playerTwoPanel = new JPanel (new BorderLayout());
		playerTwoPanel.setLayout(new BorderLayout());

		// cardPanelOne and cardPanelTwo contains the two cards for each of the two players
		cardPanelOne = new JPanel();
		cardPanelOne.setLayout(new FlowLayout());

		cardPanelTwo = new JPanel();
		cardPanelTwo.setLayout(new FlowLayout());

		// labels for player 1 and player 2
		label1 = new JLabel ("Player 1:");
		label2 = new JLabel ("Player 2:");

		// panels containing labels for player 1 and 2
		label1Panel = new JPanel();
		label1Panel.setLayout(new FlowLayout());
		label1Panel.add(label1);

		label2Panel = new JPanel();
		label2Panel.setLayout(new FlowLayout());
		label2Panel.add(label2);

		// get 4 random cards (cards 1 and 2 is for player 1, cards 3 and 4 is for player 2)
		randomCards = drawCards();

		// get scores of player 1 and 2
		playerOneScore = getScore(randomCards)[0];
		playerTwoScore = getScore(randomCards)[1];

		// put cards on jlabel/jpanel
		card1 = new JLabel (images[randomCards[0]]);
		card2 = new JLabel (images[randomCards[1]]);
		card3 = new JLabel (images[randomCards[2]]);
		card4 = new JLabel (images[randomCards[3]]);

		cardPanelOne.add(card1);
		cardPanelOne.add(card2);
		cardPanelTwo.add(card3);
		cardPanelTwo.add(card4);

		// put scores on jlabel/jpanel
		score1 = new JLabel ("Score: " + playerOneScore);
		score2 = new JLabel ("Score: " + playerTwoScore );

		score1Panel = new JPanel();
		score1Panel.setLayout(new FlowLayout());
		score1Panel.add(score1);

		score2Panel = new JPanel();
		score2Panel.setLayout(new FlowLayout());
		score2Panel.add(score2);

		// put player label, cards, and score onto player panels
		playerOnePanel.add(BorderLayout.NORTH, label1Panel);
		playerOnePanel.add(BorderLayout.CENTER, cardPanelOne);
		playerOnePanel.add(BorderLayout.SOUTH, score1Panel);

		playerTwoPanel.add(BorderLayout.NORTH, label2Panel);
		playerTwoPanel.add(BorderLayout.CENTER, cardPanelTwo);
		playerTwoPanel.add(BorderLayout.SOUTH, score2Panel);

		// bottom panel containing results and buttons
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 1));

		// play again/exit buttons
		button1 = new JButton ("Play again");
		button1.setActionCommand ("Again");
		button1.addActionListener (this);

		button2 = new JButton ("Exit");
		button2.setActionCommand ("Exit");
		button2.addActionListener (this);

		// put buttons onto panel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());

		buttonsPanel.add(button1);
		buttonsPanel.add(button2);

		// put winner on jlabel and jpanel
		result = new JLabel (getResults());

		resultsPanel = new JPanel();
		resultsPanel.setLayout(new FlowLayout());
		resultsPanel.add(result);
		
		// put results and buttons onto bottom panel
		bottomPanel.add(resultsPanel);
		bottomPanel.add(buttonsPanel);

		// on large panel, put two player panels onto side by side, bottom panel at bottom
		myPanel.add(BorderLayout.WEST, playerOnePanel);
		myPanel.add(BorderLayout.EAST, playerTwoPanel);
		myPanel.add(BorderLayout.SOUTH, bottomPanel);

		frame.add (myPanel);
		frame.pack ();
		frame.setVisible (true);

	}

	public void actionPerformed (ActionEvent event) {
		String eventName = event.getActionCommand();
		if (eventName.equals ("Exit")) {
			System.exit(0);
		}
		else if (eventName.equals ("Again")) {		
			// redraw 4 cards
			randomCards = drawCards();

			// get new score
			playerOneScore = getScore(randomCards)[0];
			playerTwoScore = getScore(randomCards)[1];

			// update icons/text that display the cards, score, and result
			card1.setIcon(images[randomCards[0]]);
			card2.setIcon(images[randomCards[1]]);
			card3.setIcon(images[randomCards[2]]);
			card4.setIcon(images[randomCards[3]]);
			score1.setText("Score: " + playerOneScore);
			score2.setText("Score: " + playerTwoScore);
			result.setText(getResults());

		}
	}

	// Description: Get 4 different random cards out of 52
	// Parameters: none
	// Return: int array of 4 numbers from 0-51 (representing which card # to pick)
	public int[] drawCards() {
		String used = ""; // keeps track of which cards are already drawn
		int[] cards = new int[4];
		for (int i = 0; i < 4; i++) {
			int randomCard = (int) (Math.random()* 52);
			
			// if card is already drawn, draw a new one
			while (used.indexOf(randomCard + ", ") != -1) {
				randomCard = (int) (Math.random()* 52);
			}
			used += randomCard + ", ";
			cards[i] = randomCard;
		}

		return cards;
	}

	// Description: calculates score of two players
	// Parameters: array with the 4 random cards (first two elements are player 1's cards, last two elements are player 2's cards)
	// Return: int array with 2 elements containing scores of player 1 and player 2
	public int[] getScore(int[] cards) {
		int playerOneScore = 0;
		int playerTwoScore = 0;

		// first player has first two cards in array
		for (int i = 0; i < 2; i++) {
			if (cards[i] < 4) { // ace = 11 points
				playerOneScore += 11;
			}
			else if (cards[i] > 39) { // face card = 10 points
				playerOneScore += 10;
			}
			else { // remaining cards has same number of points as the number on the card
				playerOneScore += cards[i]/4 + 1;
			}
		}

		// second player has last two cards in array
		for (int i = 2; i < 4; i++) {
			if (cards[i] < 4) { // ace = 11 points
				playerTwoScore += 11;
			}
			else if (cards[i] > 39) { // face card = 10 points
				playerTwoScore += 10;
			}
			else { // remaining cards has same number of points as the number on the card
				playerTwoScore += cards[i]/4 + 1;
			}
		}

		int[] scores = {playerOneScore, playerTwoScore};
		return scores;
	}

	// Description: loads 52 card images
	// Parameters: none
	// Return: ImageIcon array of 52 cards
	public static ImageIcon[] getImages() {
		ImageIcon[] images = new ImageIcon [52];
		char[] suits = {'c', 'd', 'h', 's'};

		int count = 0;
		
		// loop through 13 ranks
		for (int i = 1; i < 14; i++) {
			// loop through 4 suits
			for (int j = 0; j < 4; j++) {
				ImageIcon image;
				if (i < 10)
					image = new ImageIcon("cards/0" + i + suits[j] + ".gif");
				else
					image = new ImageIcon("cards/" + i + suits[j] + ".gif");

				images[count] = image;
				count ++;
			}
		}
		return images;
	}

	// Description: finds which player has higher score
	// Parameters: none
	// Return: String containing the winner or a tie
	public String getResults() {
		String output;
		if (playerOneScore > playerTwoScore) {
			output = "Player 1 wins!";
		}

		else if (playerOneScore < playerTwoScore) {
			output = "Player 2 wins!";
		}

		else {
			output = "Tie!";

		}
		return output;
	}

	public static void main (String [] args){
		// load images
		images = getImages();
		new MethodsArraysExercisesPart2 ();

	}
}

