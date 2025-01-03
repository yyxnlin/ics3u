import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class ConnectFourdsssf extends JPanel implements ActionListener, MouseListener, KeyListener
{
    static JFrame frame;
    final int BANANA = -1;
    final int STRAWBERRY = 1;
    final int EMPTY = 0;
    final int SQUARE_SIZE = 60;
    final int TOP_OFFSET = 42;
    final int BORDER_SIZE = 4;

    int[] [] board;
    int currentPlayer;
    int currentColumn;
    Image firstImage, secondImage;

    Timer timer;

    // For drawing images offScreen (prevents Flicker)
    // These variables keep track of an off screen image object and
    // its corresponding graphics object
    Image offScreenImage;
    Graphics offScreenBuffer;

    boolean gameOver;

    public ConnectFourdsssf ()
    {
	// Setting the defaults for the panel
	setPreferredSize (new Dimension (7 * SQUARE_SIZE + 2 * BORDER_SIZE + 1, (6 + 1) * SQUARE_SIZE + TOP_OFFSET + BORDER_SIZE + 1));
	setLocation (100, 10);
	setBackground (new Color (200, 200, 200));
	setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));

	board = new int [8] [9];

	// Set up the Menu
	// Set up the Game MenuItems
	JMenuItem newOption, exitOption;
	newOption = new JMenuItem ("New");
	exitOption = new JMenuItem ("Exit");

	// Set up the Game Menu
	JMenu gameMenu = new JMenu ("Game");
	// Add each MenuItem to the Game Menu (with a separator)
	gameMenu.add (newOption);
	gameMenu.addSeparator ();
	gameMenu.add (exitOption);

	JMenuBar mainMenu = new JMenuBar ();
	mainMenu.add (gameMenu);
	// Set the menu bar for this frame to mainMenu
	frame.setJMenuBar (mainMenu);

	// Use a media tracker to make sure all of the images are
	// loaded before we continue with the program
	MediaTracker tracker = new MediaTracker (this);
	firstImage = Toolkit.getDefaultToolkit ().getImage ("banana.gif");
	tracker.addImage (firstImage, 0);
	secondImage = Toolkit.getDefaultToolkit ().getImage ("strawberry.gif");
	tracker.addImage (secondImage, 1);

	//  Wait until all of the images are loaded
	try
	{
	    tracker.waitForAll ();
	}
	catch (InterruptedException e)
	{
	}

	// Set up the icon image (Tracker not needed for the icon image)
	Image iconImage = Toolkit.getDefaultToolkit ().getImage ("banana.gif");
	frame.setIconImage (iconImage);

	// Start a new game and then make the window visible
	newGame ();

	newOption.setActionCommand ("New");
	newOption.addActionListener (this);
	exitOption.setActionCommand ("Exit");
	exitOption.addActionListener (this);

	setFocusable (true); // Need this to set the focus to the panel in order to add the keyListener
	addKeyListener (this);

	addMouseListener (this);

    } // Constructor


    
    // To handle normal menu items
    public void actionPerformed (ActionEvent event)
    {
	String eventName = event.getActionCommand ();
	if (eventName.equals ("New"))
	{
	    newGame ();
	}
	else if (eventName.equals ("Exit"))
	{
	    System.exit (0);
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

    public void clearBoard (int[] [] board)
    {
    	for (int i = 0; i < board.length; i ++) {
    		for (int s = 0; s < board[i].length; s ++) {
    			board[i][s] = EMPTY;
    		}
    	}
    }


    public int findNextRow (int[][] board, int column) {
		for (int row = 1; row <= 7; row++) {
			if (board[row][column] != 0){
				if (row == 1) {
					return -1;
				}
				return (row - 1);
			}
		}
		return -1;
	}


    public int checkForWinner (int[] [] board, int lastRow, int lastColumn)
    {
	return 0;
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
	if (row <= 0)
	{
	    JOptionPane.showMessageDialog (this, "Please Select another Column",
		    "Column is Full", JOptionPane.WARNING_MESSAGE);
	    return;
	}

	animatePiece (currentPlayer, column, row);
	board [row] [column] = currentPlayer;

	int winner = checkForWinner (board, row, column);

	if (winner == BANANA)
	{
	    gameOver = true;
	    repaint ();
	    JOptionPane.showMessageDialog (this, "Banana Wins!!!",
		    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);

	}
	else if (winner == STRAWBERRY)
	{
	    gameOver = true;
	    repaint ();
	    JOptionPane.showMessageDialog (this, "Strawberry Wins!!!",
		    "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
	}
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
	ConnectFourdsssf myPanel = new ConnectFourdsssf ();

	frame.add (myPanel);
	frame.pack ();
	frame.setVisible (true);

    } // main method
} // ConnectFourdsssfWorking class