// Lynn Tao
// Methods Programming Exercises (Part 2)
// April 26, 2023

import java.awt.*;
import javax.swing.*;

public class AnimalFace extends JPanel {
	
	Image koya;
	Image emoji;
	static int option;
	static int x;
	static int y;
	static int width;
	static int height;
	
	public AnimalFace() {
		setPreferredSize(new Dimension(600, 480));
		setBackground(new Color(255, 255, 255));
		
		// images for question 6
		koya =  Toolkit.getDefaultToolkit().getImage("koya4.jpg");
		emoji =  Toolkit.getDefaultToolkit().getImage("emoji.gif");

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// calls corresponding method based on option number
		if (option == 4) {
			drawAnimalFace(g, x, y);
		}
		else if (option == 5) {
			drawAnimalFace(g, x, y, width, height);
		}
		else if (option == 6) {
			decorPic(g);
		}
	}

	// Description: draws koya (bt21 koala)
	// Parameters: g (object used to draw, Graphics), x and y coordinates of location (int)
	// Return: nothing - draws koya on the screen
	public void drawAnimalFace(Graphics g, int x, int y) {
		// ear
		g.setColor(new Color(0, 0, 0));
		g.fillOval(x, y, 60, 60);
		g.setColor(new Color(129, 224, 253));
		g.fillOval(x+5, y+5, 50, 50);
		g.setColor(new Color(240, 240, 240));
		g.fillOval(x+18, y+17, 25, 25);

		// head
		g.setColor(new Color(0, 0, 0));
		g.fillOval(x-86, y+15, 136, 110);
		g.setColor(new Color(129, 224, 253));
		g.fillOval(x-80, y+20, 125, 100);


		// ear
		g.setColor(new Color(0, 0, 0));
		g.fillOval(x-110, y+10, 60, 60);
		g.setColor(new Color(129, 224, 253));
		g.fillOval(x-105, y+15, 50, 50);
		g.fillOval(x-75, y+40, 50, 50);
		g.setColor(new Color(240, 240, 240));
		g.fillOval(x-92, y+28, 25, 25);

		// eyes
		g.setColor(new Color(0, 0, 0));
		g.fillOval(x-45, y+70, 15, 15);
		g.fillOval(x+10, y+65, 15, 15);

		// nose
		g.fillOval(x-21, y+65, 26, 33);
		g.setColor(new Color(117, 104, 254));
		g.fillOval(x-18, y+69, 20, 25);

		// mouth
		g.setColor(new Color(0, 0, 0));
		g.fillOval(x-12, y+100, 10, 6);

	}

	// Description: draws koya (bt21 koala) with custom length/width 
	// Parameters: g (object used to draw, Graphics), x and y coordinates of location (int), width and height (int)
	// Return: nothing - draws koya on the screen 
	public void drawAnimalFace(Graphics g, int x, int y, int width, int height) {

		// scale ratio
		double widthScale = 1.0*width/60;
		double heightScale = 1.0*height/60;

		x = (int) (x + 110*widthScale); 

		// ear
		g.setColor(new Color(0, 0, 0));
		g.fillOval((int)(widthScale*x - widthScale*x + x), (int)(heightScale*y - heightScale*y + y), (int)(60*widthScale), (int)(60*heightScale));
		g.setColor(new Color(129, 224, 253));
		g.fillOval((int)(widthScale*(x+5) - widthScale*x + x), (int)(heightScale*(y+5) - heightScale*y + y), (int)(50*widthScale), (int)(50*heightScale));
		g.setColor(new Color(240, 240, 240));
		g.fillOval((int)(widthScale*(x+18) - widthScale*x + x), (int)(heightScale*(y+17) - heightScale*y + y), (int)(25*widthScale), (int)(25*heightScale));

		// head
		g.setColor(new Color(0, 0, 0));
		g.fillOval((int)(widthScale*(x-86) - widthScale*x + x), (int)(heightScale*(y+15) - heightScale*y + y), (int)(136*widthScale), (int)(110*heightScale));
		g.setColor(new Color(129, 224, 253));
		g.fillOval((int)(widthScale*(x-80) - widthScale*x + x), (int)(heightScale*(y+20) - heightScale*y + y), (int)(125*widthScale), (int)(100*heightScale));

		// ear
		g.setColor(new Color(0, 0, 0));
		g.fillOval((int)(widthScale*(x-110) - widthScale*x + x), (int)(heightScale*(y+10) - heightScale*y + y), (int)(60 * widthScale), (int)(60*heightScale));
		g.setColor(new Color(129, 224, 253));
		g.fillOval((int)(widthScale*(x-105) - widthScale*x + x), (int)(heightScale*(y+15) - heightScale*y + y), (int)(50 * widthScale), (int)(50*heightScale));
		g.fillOval((int)(widthScale*(x-75) - widthScale*x + x), (int)(heightScale*(y+40) - heightScale*y + y), (int)(50 * widthScale), (int)(50*heightScale));
		g.setColor(new Color(240, 240, 240));
		g.fillOval((int)(widthScale*(x-92) - widthScale*x + x), (int)(heightScale*(y+28) - heightScale*y + y), (int)(25 * widthScale), (int)(25*heightScale));

		// eyes
		g.setColor(new Color(0, 0, 0));
		g.fillOval((int)(widthScale*(x-45) - widthScale*x + x), (int)(heightScale*(y+70) - heightScale*y + y), (int)(15*widthScale), (int)(15*heightScale));
		g.fillOval((int)(widthScale*(x+10) - widthScale*x + x), (int)(heightScale*(y+65) - heightScale*y + y), (int)(15*widthScale), (int)(15*heightScale));

		// nose
		g.fillOval((int)(widthScale*(x-21) - widthScale*x + x), (int)(heightScale*(y+65) - heightScale*y + y), (int)(26*widthScale), (int)(33*heightScale));
		g.setColor(new Color(117, 104, 254));
		g.fillOval((int)(widthScale*(x-18) - widthScale*x + x), (int)(heightScale*(y+69) - heightScale*y + y), (int)(20*widthScale), (int)(25*heightScale));

		// mouth
		g.setColor(new Color(0, 0, 0));
		g.fillOval((int)(widthScale*(x-12) - widthScale*x + x), (int)(heightScale*(y+100) - heightScale*y + y), (int)(10*widthScale), (int)(6*heightScale));

	}

	// Description: decorates a picture of koya with random number of emojis (10-30) of random sizes on the screen
	// Parameters: g (object used to draw, Graphics)
	// Return: nothing - draws koya with decorated emojis on the screen
	public void decorPic(Graphics g) {
		super.paintComponent(g);

		g.drawImage(koya, 130, 130, 300, 270, this);
		
		// number of times to display emoji
		int count = (int)(Math.random() * 21) + 10;
				
		// loop number of times for emoji
		for(int i = 0; i < count; i++) {

			// x and y coordinates of emoji
			int x = (int)(Math.random() * 570);
			int y = (int)(Math.random() * 440);
			
			// if it covers koya, reselect coordinates
			while (x > 100 && x < 430 && y > 100 && y < 400) {
				x = (int)(Math.random() * 570);
				y = (int)(Math.random() * 440);
			}
			
			// random size of emoji
			int size = (int)(Math.random() * 30) + 20;
			g.drawImage(emoji, x, y, size, size, this);
		}
		
		
	}

	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Graphics Exercises");
		AnimalFace myPanel = new AnimalFace();
		
		option = Integer.parseInt(JOptionPane.showInputDialog (myPanel, "Question 4, 5, or 6?"));

		if (option == 4) {
			x = Integer.parseInt(JOptionPane.showInputDialog (myPanel, "Please enter x coordinate"));
			y = Integer.parseInt(JOptionPane.showInputDialog (myPanel, "Please enter y coordinate"));
		}
		else if (option == 5) {
			x = Integer.parseInt(JOptionPane.showInputDialog (myPanel, "Please enter x coordinate"));
			y = Integer.parseInt(JOptionPane.showInputDialog (myPanel, "Please enter y coordinate"));
			width = Integer.parseInt(JOptionPane.showInputDialog (myPanel, "Please enter width"));
			height = Integer.parseInt(JOptionPane.showInputDialog (myPanel, "Please enter height"));
		}
		
		frame.add(myPanel);
		frame.pack();
		frame.setVisible(true);
		
	}

}