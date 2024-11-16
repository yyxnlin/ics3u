// Lynn Tao
// Assignment #4: Connect Four Game
// May 26, 2023
// This is a game of Connect Four where you try to get four in a row, column, or diagonal by placing down objects from the top! 

/*
 * Bonus: 
 * if the column is full, the player currently NOT at the top of that column is allowed to click in that column
 * the bottom piece is pushed out, everything else shifts down 1 square, and the new piece is added to the top
 * checks the whole board if there are any winners - if there are two winners (two 4-in-a-row's from both players) then it is a tie
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class ConnectFourBonus extends JPanel implements ActionListener, MouseListener, KeyListener
{
	static JFrame frame, pieceSelect;
	final int BANANA = -1;
	final int STRAWBERRY = 1;
	final int EMPTY = 0;
	final int SQUARE_SIZE = 60;
	final int TOP_OFFSET = 42;
	final int BORDER_SIZE = 4;

	boolean full = false;
	int[] fullColumns = new int[7];
	int count = 0;

	int[] [] board;
	int currentPlayer;
	int currentColumn;
	Image firstImage, secondImage, patrick;

	Timer timer;

	// For drawing images offScreen (prevents Flicker)
	// These variables keep track of an off screen image object and
	// its corresponding graphics object
	Image offScreenImage;
	Graphics offScreenBuffer;

	Clip bgm, boink, wahoo;

	boolean gameOver;

	public ConnectFourBonus ()
	{
		// Setting the defaults for the panel
		setPreferredSize (new Dimension (7 * SQUARE_SIZE + 2 * BORDER_SIZE + 1, (6 + 1) * SQUARE_SIZE + TOP_OFFSET + BORDER_SIZE + 1));
		setLocation (100, 10);
		setBackground (new Color (200, 200, 200));
		setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

		board = new int [8] [9];

		// Set up the Menu
		// Set up the Game MenuItems
		JMenuItem newOption, exitOption, pieceOption;
		newOption = new JMenuItem ("New");
		exitOption = new JMenuItem ("Exit");
		pieceOption = new JMenuItem ("Choose piece");

		// Set up the Game Menu
		JMenu gameMenu = new JMenu ("Game");
		// Add each MenuItem to the Game Menu (with a separator)
		gameMenu.add (newOption);
		gameMenu.addSeparator ();
		gameMenu.add (exitOption);
		gameMenu.addSeparator ();
		gameMenu.add (pieceOption);

		JMenuBar mainMenu = new JMenuBar ();
		mainMenu.add (gameMenu);
		// Set the menu bar for this frame to mainMenu
		frame.setJMenuBar (mainMenu);


		MediaTracker tracker = new MediaTracker (this);
		firstImage = Toolkit.getDefaultToolkit ().getImage ("unicorn1.png");
//		firstImage = Toolkit.getDefaultToolkit ().getImage ("banana.gif");

		tracker.addImage (firstImage, 0);
		secondImage = Toolkit.getDefaultToolkit ().getImage ("unicorn4.png");
//		secondImage = Toolkit.getDefaultToolkit ().getImage ("strawberry.gif");

		tracker.addImage (secondImage, 1);

		// Use a media tracker to make sure all of the images are
		// loaded before we continue with the program


		// Set up the icon image (Tracker not needed for the icon image)
		Image iconImage = Toolkit.getDefaultToolkit ().getImage ("unicorn1.gif");
		frame.setIconImage (iconImage);

		// Start a new game and then make the window visible
		newGame ();

		newOption.setActionCommand ("New");
		newOption.addActionListener (this);
		exitOption.setActionCommand ("Exit");
		exitOption.addActionListener (this);
		pieceOption.setActionCommand ("Choose piece");
		pieceOption.addActionListener (this);

		setFocusable (true); // Need this to set the focus to the panel in order to add the keyListener
		addKeyListener (this);

		addMouseListener (this);

		// **************** sound ****************//

		try {
			AudioInputStream sound = AudioSystem.getAudioInputStream(new File ("bgm01.wav"));

			// background music
			bgm = AudioSystem.getClip();
			bgm.open(sound);

			// drop sound
			sound = AudioSystem.getAudioInputStream(new File ("boink.wav"));
			boink = AudioSystem.getClip();
			boink.open(sound);

			// game over sound
			sound = AudioSystem.getAudioInputStream(new File ("wahoo.wav"));
			wahoo = AudioSystem.getClip();
			wahoo.open(sound);
		}
		catch (Exception e) {
		}

		// start background music + keep looping
				bgm.start();
				bgm.loop(Clip.LOOP_CONTINUOUSLY); // WHEE

		// **************** cursor ****************//
		//	Loading the image into an Image object
		patrick = Toolkit.getDefaultToolkit().getImage("patrick.png");

		// Defining the hotspot to the centre of the object
		Point hotspot = new Point (0, 0);
		Toolkit toolkit = Toolkit.getDefaultToolkit ();
		Cursor cursor = toolkit.createCustomCursor (patrick, hotspot, "sponge");
		frame.setCursor (cursor);
		frame.pack();
		frame.setVisible(true);
	} // Constructor


	// To handle normal menu items
	public void actionPerformed (ActionEvent event)
	{
		String eventName = event.getActionCommand ();
		if (eventName.equals ("New")) {
			newGame ();
		}

		else if (eventName.equals ("Exit")) {
			System.exit (0);
		}

		else if (eventName.equals ("Choose piece")) {
			selectPiece();
		}

	}


	public void newGame ()
	{
		currentPlayer = BANANA;
		clearBoard (board);
		gameOver = false;
		currentColumn = 3;
		repaint ();
	}

	//------------YOUR CODE GOES HERE  ------------------//

	// Description: remove all pieces from the board (set all values to 0)
	// Parameters: int[][] board is the 2d array containing the current layout of the board
	// Return: no return (sets every value in array to 0)
	public void clearBoard (int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = EMPTY;
			}
		}
	}

	// Description: finds and returns the next empty row to drop the piece in
	// Parameters: int[][] board is the 2d array containing the current layout of the board, int column is the chosen column number
	// Return: int value of next empty row number
	public int findNextRow (int[][] board, int column) {
		for (int row = 1; row < 7; row++) {
			if (board[row][column] != 0){ // if reached a row that is not empty
				if (row == 1) { // if first row isn't empty, then column is full
					return -1;
				}
				return (row - 1); // otherwise, the row beside must've been empty
			}
		}
		return 6; // if all rows from 1-6 were equal to 0, then column is completely empty
	}

	// Description: checks if there is 4 in a row/column/diagonal
	// Parameters: int[][] board is the 2d array containing the current layout of the board, int lastRow is row# of last piece dropped, int lastColumn is column# of last piece dropped
	// Return: int of STRAWBERRY (1), BANANA (-1), or EMPTY (0) of which player won
	public int checkForWinner (int[][] board, int lastRow, int lastColumn)
	{

		int player = board[lastRow][lastColumn];

		// vertical
		if(lastRow < 4) {
			if (player == board[lastRow + 1][lastColumn] && player == board[lastRow + 2][lastColumn] && player == board[lastRow + 3][lastColumn]) {
				return player;
			}
		}

		// horizontal
		for (int i = Math.max(1, lastColumn - 3); i <= Math.min(lastColumn, 4); i++) {
			if (player == board[lastRow][i] && player == board[lastRow][i + 1] && player == board[lastRow][i + 2] && player == board[lastRow][i + 3]) {
				return player;
			}
		}

		// diagonal
		int downSpace = Math.min(6-lastRow, 3);
		int rightSpace = Math.min(7 - lastColumn, 3);

		int upSpace = Math.min(lastRow - 1, 3);
		int leftSpace = Math.min(lastColumn - 1, 3);

		//		System.out.printf("DOWN %d, RIGHT %d, UP %d, LEFT %d", downSpace, rightSpace, upSpace, leftSpace);
		// top left/bottom right diagonal
		for(int i = -1*Math.min(upSpace, leftSpace); i <= Math.min(downSpace, rightSpace); i++) {
			if (player == board[lastRow + i][lastColumn + i] 
					&& player == board[lastRow + i + 1][lastColumn + i + 1] 
							&& player == board[lastRow + i + 2][lastColumn + i + 2] 
									&& player == board[lastRow + i + 3][lastColumn + i + 3]) {
				return player;
			}
		}

		// top right/bottom left diagonal
		for(int i = -1 * Math.min(upSpace, rightSpace); i <= Math.min(downSpace, leftSpace); i++) {
			if (player == board[lastRow + i][lastColumn - i] 
					&& player == board[lastRow + i + 1][lastColumn - i - 1] 
							&& player == board[lastRow + i + 2][lastColumn - i - 2] 
									&& player == board[lastRow + i + 3][lastColumn - i - 3]) {
				return player;
			}
		}

		return EMPTY;

	}

	// Description: Allow user to pick new images for two pieces and sets them
	// Parameters: none
	// Return: no return (sets icons into new images)
	public void selectPiece() {
		// new panel for piece options
		JPanel panel2 = new JPanel (); 
		// loading images
		ImageIcon uni1 = new ImageIcon ("unicorn1.png");
		ImageIcon uni2 = new ImageIcon ("unicorn2.png");
		ImageIcon uni3 = new ImageIcon ("unicorn3.png");
		ImageIcon uni4 = new ImageIcon ("unicorn4.png");
		ImageIcon uni5 = new ImageIcon ("unicorn5.png");
		ImageIcon uni6 = new ImageIcon ("unicorn6.png");
		// jlabels for each image
		JLabel uni1Label = new JLabel(uni1);
		JLabel uni2Label = new JLabel(uni2);
		JLabel uni3Label = new JLabel(uni3);
		JLabel uni4Label = new JLabel(uni4);
		JLabel uni5Label = new JLabel(uni5);
		JLabel uni6Label = new JLabel(uni6);

		// jlabels for player labels
		JLabel player1Label = new JLabel("Player 1 (Banana):");
		JLabel player2Label = new JLabel("Player 2 (Strawberry):");

		// buttons groups for two players' pieces
		ButtonGroup player1Group = new ButtonGroup();
		ButtonGroup player2Group = new ButtonGroup();

		// radiobuttons for 6 pieces (first three for player 1, second three for player 2)
		JRadioButton[] buttonList = new JRadioButton [6];
		buttonList[0] = new JRadioButton ("", true);
		buttonList[1] = new JRadioButton ("");
		buttonList[2] = new JRadioButton ("");
		buttonList[3] = new JRadioButton ("", true);
		buttonList[4] = new JRadioButton ("");
		buttonList[5] = new JRadioButton ("");

		// Create and add each radio button to the panel
		panel2.setLayout(new GridLayout(2, 4));

		panel2.add(player1Label);

		panel2.add (buttonList[0]);
		player1Group.add(buttonList[0]);
		panel2.add(uni1Label);

		panel2.add (buttonList[1]);
		player1Group.add(buttonList[1]);
		panel2.add(uni2Label);

		panel2.add (buttonList[2]);
		player1Group.add(buttonList[2]);
		panel2.add(uni3Label);

		panel2.add(player2Label);

		panel2.add (buttonList[3]);
		player2Group.add(buttonList[3]);
		panel2.add(uni4Label);

		panel2.add (buttonList[4]);
		player2Group.add(buttonList[4]);
		panel2.add(uni5Label);

		panel2.add (buttonList[5]);
		player2Group.add(buttonList[5]);
		panel2.add(uni6Label);

		// seeing which button is selected
		int choice = JOptionPane.showConfirmDialog (frame, panel2,
				"Unicorn Options",
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.DEFAULT_OPTION);

		// Update images if OK is selected
		int[] players = new int[2]; // contains index of 2 images chosen
		int i = 0;
		if (choice == JOptionPane.OK_OPTION) {
			for (int index = 0 ; index < buttonList.length ; index++)
				if (buttonList[index].isSelected ()) {
					players[i] = index;
					i++;
				}
		}

		// updating images to new images
		MediaTracker tracker = new MediaTracker (this);
		firstImage = Toolkit.getDefaultToolkit ().getImage ("unicorn" + (players[0]+1) + ".png");
		tracker.addImage (firstImage, 0);
		secondImage = Toolkit.getDefaultToolkit ().getImage ("unicorn" + (players[1]+1) + ".png");
		tracker.addImage (secondImage, 1);

		try {
			tracker.waitForAll ();
		}

		catch (InterruptedException e) {
		}
	}


	//----------------------------------------------------//


	public void handleAction (int x, int y)
	{
		if (gameOver)
		{
			JOptionPane.showMessageDialog (this, "Please Select Game...New to start a new game",
					"Game Over", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int column = (x - BORDER_SIZE) / SQUARE_SIZE + 1;
		int row = findNextRow (board, column);


		boink.setFramePosition (0); //<-- play sound file again from beginning
				boink.start (); // WHEE

		boolean strawberryWin = false;
		boolean bananaWin = false;
		
		// row is full
		if (row < 0) {
//			System.out.println(row); // whee
			// current player isn't player currently at top -> shift everything down
			if (currentPlayer != board[1][column]) {
				full = true;
				for (int i = 6; i > 1; i--) {
					board[i][column] = board[i-1][column];
				}
				
				animatePiece (column);
				board[1][column] = currentPlayer;

				// **************whee** //
//				System.out.println();
//				for (int i = 0; i < board.length; i++) {
//					System.out.println(Arrays.toString(board[i]));
//				}
				// **************** //
				
				// check the whole column for any winners
				for (int i = 1; i <= 6; i++) {
					int winner = checkForWinner (board, i, column);
//					System.out.println(winner + ", i " + i + ", col " + column);
					if (winner == STRAWBERRY) {
						strawberryWin = true;
					}
					else if (winner == BANANA) {
						bananaWin = true;
					}
				}
			}
			
			// current player isnt player currently at top -> don't allow them to go in this column
			else {
				JOptionPane.showMessageDialog (this, "You cannot go in this column!!!",
						"Column is Full", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}

		// row not full -> add piece and check normally
		else {
			animatePiece (currentPlayer, column, row);
			board [row] [column] = currentPlayer;
			int winner = checkForWinner (board, row, column);
			
			if (winner == STRAWBERRY) {
				strawberryWin = true;
			}
			else if (winner == BANANA) {
				bananaWin = true;
			}
		}

		// one winner banana
		if (bananaWin && !strawberryWin)
		{
			wahoo.setFramePosition (0);
						wahoo.start(); // WHEE
			gameOver = true;
			repaint ();
			JOptionPane.showMessageDialog (this, "Player 1 (Banana) Wins!!!",
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// one winner strawberry
		else if (strawberryWin && !bananaWin)
		{
			wahoo.setFramePosition (0);
						wahoo.start(); // WHEE
			gameOver = true;
			repaint ();
			JOptionPane.showMessageDialog (this, "Player 2 (Strawberry) Wins!!!",
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// banana and strawberry both win
		else if (bananaWin && strawberryWin) {
			wahoo.setFramePosition (0);
						wahoo.start(); // WHEE
			gameOver = true;
			repaint ();
			JOptionPane.showMessageDialog (this, "Tie!!!",
					"GAME OVER", JOptionPane.INFORMATION_MESSAGE);
		}
		
		// no winner -> continue game
		else
			// Switch to the other player
			currentPlayer *= -1;
		currentColumn = 3;

		repaint ();
	}


	// MouseListener methods
	public void mouseClicked (MouseEvent e)
	{
		int x, y;
		x = e.getX ();
		y = e.getY ();

		handleAction (x, y);
	}


	public void mouseReleased (MouseEvent e)
	{
	}


	public void mouseEntered (MouseEvent e)
	{
	}


	public void mouseExited (MouseEvent e)
	{
	}


	public void mousePressed (MouseEvent e)
	{
	}


	//KeyListener methods
	public void keyPressed (KeyEvent kp)
	{

		if (kp.getKeyCode () == KeyEvent.VK_RIGHT)
		{
			if (currentColumn < 6)
				currentColumn++;
		}
		else if (kp.getKeyCode () == KeyEvent.VK_DOWN)
		{
			handleAction ((currentColumn) * SQUARE_SIZE + BORDER_SIZE, 0);
		}
		else if (kp.getKeyCode () == KeyEvent.VK_LEFT)
		{
			if (currentColumn > 0)
				currentColumn--;
		}
		else
			return;
		repaint ();
	}


	public void keyReleased (KeyEvent e)
	{
	}


	public void keyTyped (KeyEvent e)
	{
	}

	// row not full
	public void animatePiece (int player, int column, int finalRow)
	{
		Graphics g = getGraphics ();

		// Find the x and y positions for each row and column
		int xPos = (4 - 1) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + 0 * SQUARE_SIZE;
		offScreenBuffer.clearRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);


		for (double row = 0 ; row < finalRow ; row += 0.10)
		{
			// Find the x and y positions for each row and column
			xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
			yPos = (int) (TOP_OFFSET + row * SQUARE_SIZE);
			// Redraw the grid for this column
			for (int gridRow = 1 ; gridRow <= 6 ; gridRow++)
			{
				// Draw the squares
				offScreenBuffer.setColor (Color.black);
				offScreenBuffer.drawRect ((column - 1) * SQUARE_SIZE + BORDER_SIZE,
						TOP_OFFSET + gridRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}

			// Draw each piece, depending on the value in board
			if (player == BANANA)
				offScreenBuffer.drawImage (firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			else if (player == STRAWBERRY)
				offScreenBuffer.drawImage (secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);

			// Transfer the offScreenBuffer to the screen
			g.drawImage (offScreenImage, 0, 0, this);
			delay (3);
			offScreenBuffer.clearRect (xPos + 1, yPos + 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);
		}

	}

	// when row is full 
	public void animatePiece(int column) {
		Graphics g = getGraphics ();
		// Find the x and y positions for each row and column
		int xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
		int yPos = TOP_OFFSET + 0 * SQUARE_SIZE;
		offScreenBuffer.clearRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);

		for (double rowY = 0; rowY < SQUARE_SIZE; rowY += 10)
		{
			for (int rowNum = 6; rowNum > 0; rowNum--) {
				// Find the x and y positions for each row and column
				yPos = (int) (TOP_OFFSET + (rowNum-1) * SQUARE_SIZE + rowY);

				// **************** irrelevant **************** //
				// Redraw the grid for this column
				for (int gridRow = rowNum ; gridRow <= 6 ; gridRow++)
				{
					// Draw the squares
					offScreenBuffer.setColor (Color.black);
					offScreenBuffer.drawRect ((column - 1) * SQUARE_SIZE + BORDER_SIZE,
							TOP_OFFSET + gridRow * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
				}
				// **************** whee ***************** //

				// Draw each piece, depending on the value in board
				if (board[rowNum][column] == BANANA)
					offScreenBuffer.drawImage (firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
				else if (board[rowNum][column] == STRAWBERRY)
					offScreenBuffer.drawImage (secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			}
			// Transfer the offScreenBuffer to the screen
			g.drawImage (offScreenImage, 0, 0, this);
			delay (10); 
			offScreenBuffer.clearRect (xPos + 1, yPos + 1, SQUARE_SIZE - 2, SQUARE_SIZE - 2);
		}

	}
	// Avoid flickering -- smoother graphics
	public void update (Graphics g)
	{
		paint (g);
	}


	public void paintComponent (Graphics g)
	{

		// Set up the offscreen buffer the first time paint() is called
		if (offScreenBuffer == null)
		{
			offScreenImage = createImage (this.getWidth (), this.getHeight ());
			offScreenBuffer = offScreenImage.getGraphics ();
		}

		// All of the drawing is done to an off screen buffer which is
		// then copied to the screen.  This will prevent flickering
		// Clear the offScreenBuffer first
		offScreenBuffer.clearRect (0, 0, this.getWidth (), this.getHeight ());

		// Redraw the board with current pieces
		for (int row = 1 ; row <= 6 ; row++)
			for (int column = 1 ; column <= 7 ; column++)
			{
				// Find the x and y positions for each row and column
				int xPos = (column - 1) * SQUARE_SIZE + BORDER_SIZE;
				int yPos = TOP_OFFSET + row * SQUARE_SIZE;

				// Draw the squares
				offScreenBuffer.setColor (Color.black);
				offScreenBuffer.drawRect (xPos, yPos, SQUARE_SIZE, SQUARE_SIZE);

				// Draw each piece, depending on the value in board
				if (board [row] [column] == BANANA)
					offScreenBuffer.drawImage (firstImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
				else if (board [row] [column] == STRAWBERRY)
					offScreenBuffer.drawImage (secondImage, xPos, yPos, SQUARE_SIZE, SQUARE_SIZE, this);
			}

		// Draw next player
		if (!gameOver)
			if (currentPlayer == BANANA)
				offScreenBuffer.drawImage (firstImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);
			else
				offScreenBuffer.drawImage (secondImage, currentColumn * SQUARE_SIZE + BORDER_SIZE, TOP_OFFSET, SQUARE_SIZE, SQUARE_SIZE, this);

		// Transfer the offScreenBuffer to the screen
		g.drawImage (offScreenImage, 0, 0, this);
	}
	

	/** Purpose: To delay the given number of milliseconds
	 * @param milliSec The number of milliseconds to delay
	 */
	private void delay (int milliSec)
	{
		try
		{
			Thread.sleep (milliSec);
		}
		catch (InterruptedException e)
		{
		}
	}


	public static void main (String[] args)
	{
		frame = new JFrame ("Connect Four");
		ConnectFourBonus myPanel = new ConnectFourBonus ();

		frame.add (myPanel);
		frame.pack ();
		frame.setVisible (true);

	} // main method
} // ConnectFourWorking class