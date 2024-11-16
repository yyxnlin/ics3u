import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class cringe {

	/*
	 * implement the following methods so that the program runs:
	 * 
	 * generateArray() <- must not generate 0
	 * displayArray()
	 * rangeOfArray()
	 * listHalfNumbers()
	 * sortArray()
	 * mergeArray()
	 * 
	 * read the code below to help you decide how to write the methods
	 * 
	 * after you've done implementing the methods, make sure to
	 * uncomment the proper section of the code below so that
	 * the program actually calls the methods
	 * 
	 */

	/*
	 * DO NOT change any of the variables or methods below
	 * It will break the program
	 */


	double[] arr1, arr2;
	JFrame frame;
	JPanel mainPanel, arrPanel, btnPanel, labelPanel;
	JTextArea arr1Text, arr2Text, mainText;
	JButton btnGenerate, btnDisplay, btnRange, btnListHalf, btnSort, btnMerge;
	JTextField minInputField, maxInputField, sizeInputField, rowInputField;
	
	public static double[] generateArray (double max, double min, int c) {
		//Description: This program will create an array of random size with random numbers
		//Parameter: biggest possible number in array, smallest possible number in array, size of array
		//Return: 
		
		double [] array = new double [c];
		for (int i = 0; i < c; i ++) {
			array [i] = Math.round(((Math.random()*(max - min +1)) + min)*10.0)/10.0;
		}
		return array;
			
	}
	
	public static String displayArray (double[] a, int pos) {
		String string = "";
		int i = 0;
		while (i < a.length) {
			for (int s = 0; s < pos; s ++) {
				if (i < a.length) {
					string += String.format("%8.1f", a[i]);
					i ++;
				}
			}
			string += "\n";
		}
		return string;
	}

	public static double rangeOfArray(double [] a) {
		double lowest = Integer.MAX_VALUE;
		double highest = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i ++) {
			if (a[i] > highest) {
				highest = a[i];
			}
			else if (a[i] < lowest) {
				lowest = a[i];
			}
		}
		return (highest-lowest);
	}
	
	
	public static double[] listHalfNumbers (double[] a) {
		int length = 0;
		for (int i = 0; i < a.length; i ++) {
			if (a[i] > rangeOfArray(a)/2.0) {
				length += 1;
			}
		}
		double[] b = new double [length];
		int s = 0;
		for (int i = 0; i < a.length; i ++ ) {
			if (a[i] > rangeOfArray(a)/2.0) {
				b[s] = a[i];
				s++;
			}
		}
		return b;
	}
	
	public static double[] sortArray( double [] a) {
		double temp;
		for (int i = 0;  i < a.length; i ++) {
			for (int s = i + 1; s < a.length; s++) {
				if (a[i] > a[s]) {
					temp = a[s];
					a[s] = a[i];
					a[i] = temp;
				}
			}
				
		}	
		return a;
	}
	
	public static double[] mergeArray (double[] a, double[]b) {
		double temp;
		double [] c = new double [a.length + b.length];
		for (int i = 0; i < a.length; i ++) {
			c[i] = a[i]; 
		}
		for (int i = a.length; i < a.length + b.length; i ++) {
			c[i] = b[i - a.length]; 
		}
		
		for (int i = 0;  i < c.length; i ++) {
			for (int s = i + 1; s < c.length; s++) {
				if (c[i] > c[s]) {
					temp = c[s];
					c[s] = c[i];
					c[i] = temp;
				}
			}
				
		}	
		return c;
	}
	
	public cringe() {
		initJComponents(); //you can ignore this

		// you can start reading from here

		btnGenerate = new JButton("Generate Array");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isNumeric(maxInputField.getText())) return;
				if(!isNumeric(minInputField.getText())) return;
				if(!isInteger(sizeInputField.getText())) return;
				
				//uncomment the following line(s) 
				//once you are done implementing the method
				
				double max = Double.parseDouble(maxInputField.getText());
				double min = Double.parseDouble(minInputField.getText());
				int size = Integer.parseInt(sizeInputField.getText());
				
				arr1 = generateArray(max, min, size);
				arr2 = generateArray(max, min, size);
				//uncomment the following line(s) 
				//once you are done implementing the method
				
				arr1Text.setText("arr1:\n" + displayArray(arr1, 8));
				arr2Text.setText("arr2:\n" + displayArray(arr2, 8));
				
			}
		});

		btnDisplay = new JButton("Display Array");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;
				if(!isInteger(rowInputField.getText())) return;

				//uncomment the following line(s) 
				//once you are done implementing the method
				
				int numPerRow = Integer.parseInt(rowInputField.getText());
				String arrayToString = displayArray(arr1, numPerRow);
				mainText.setText(arrayToString);
			
			}
		});

		btnRange = new JButton("Range of Array");
		btnRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method
				
				double range = rangeOfArray(arr1);
				mainText.setText(String.valueOf(range));
				
			}
		});

		btnListHalf = new JButton("List Half Numbers");
		btnListHalf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method
				
				double [] listHalf = listHalfNumbers(arr1);
				int numPerRow = Integer.parseInt(rowInputField.getText());
				mainText.setText(displayArray(listHalf, numPerRow));
			}
		});

		btnSort = new JButton("Sort Array");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method
				
				sortArray(arr1);
				int numPerRow = Integer.parseInt(rowInputField.getText());
				mainText.setText(displayArray(arr1, numPerRow));
				
			}
		});

		btnMerge = new JButton("Merge Array");
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method
				
				sortArray (arr1);
				sortArray (arr2);
				double[] mergedArray = mergeArray(arr1, arr2);
				int numPerRow = Integer.parseInt(rowInputField.getText());
				mainText.setText(displayArray(mergedArray, numPerRow));
				
			}
		});

		//you can stop reading at this point

		initMoreJComponents(); //you can ignore this(unless you're doing the bonus)
	}

	//can be used for reference(only if you can understand it)
	//can be modified for the bonus

	void initJComponents() {
		frame = new JFrame("Array Methods Demonstration");
		Font COURIER = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		arr1Text = new JTextArea("arr1: ");
		arr1Text.setPreferredSize(new Dimension(600, 200));
		arr1Text.setLineWrap(true);
		arr1Text.setFont(COURIER);
		arr1Text.setEditable(false);
		arr2Text = new JTextArea("arr2: ");
		arr2Text.setPreferredSize(new Dimension(600, 200));
		arr2Text.setLineWrap(true);
		arr2Text.setFont(COURIER);
		arr2Text.setEditable(false);
		JPanel arr1Panel = new JPanel();
		arr1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		arr1Panel.add(arr1Text);
		JPanel arr2Panel = new JPanel();
		arr2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		arr2Panel.add(arr2Text);
		mainPanel.add(arr1Panel);
		mainPanel.add(arr2Panel);

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0, 6));
		JLabel maxLabel = new JLabel("Max: ");
		maxLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		inputPanel.add(maxLabel);
		maxInputField = new JTextField("100");
		inputPanel.add(maxInputField);
		JLabel minLabel = new JLabel("Min: ");
		minLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		inputPanel.add(minLabel);
		minInputField = new JTextField("0");
		inputPanel.add(minInputField);
		JLabel sizeLabel = new JLabel("Size: ");
		sizeLabel.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		inputPanel.add(sizeLabel);
		sizeInputField = new JTextField("10");
		inputPanel.add(sizeInputField);
		mainPanel.add(inputPanel);

		JPanel rowInputPanel = new JPanel();
		rowInputPanel.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		rowInputPanel.setLayout(new BoxLayout(rowInputPanel, BoxLayout.X_AXIS));
		rowInputField = new JTextField("1");
		rowInputPanel.add(new JLabel("Numbers per row: "));
		rowInputPanel.add(rowInputField);
		mainPanel.add(rowInputPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0, 3));
	}

	void initMoreJComponents() {
		Font COURIER = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		btnPanel.add(btnGenerate);
		btnPanel.add(btnDisplay);
		btnPanel.add(btnRange);
		btnPanel.add(btnListHalf);
		btnPanel.add(btnSort);
		btnPanel.add(btnMerge);

		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainText = new JTextArea();
		mainText.setPreferredSize(new Dimension(600, 200));
		mainText.setMaximumSize(new Dimension(600, 200));
		mainText.setMinimumSize(new Dimension(600, 200));
		mainText.setLineWrap(true);
		mainText.setFont(COURIER);
		mainText.setEditable(false);
		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		mainText.setFont (font);


		labelPanel.setAlignmentY(Panel.TOP_ALIGNMENT);

		mainPanel.add(btnPanel);
		mainPanel.add(labelPanel);

		JPanel finalPanel = new JPanel();
		finalPanel.setLayout(new GridLayout(0,2));
		finalPanel.add(mainPanel);
		finalPanel.add(mainText);
		frame.add(finalPanel);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public boolean isNumeric(String s) {
		try {  
			Double.parseDouble(s);  
			return true;
		} catch(NumberFormatException e){  
			return false;  
		} catch(NullPointerException e) {
			return false;
		}
	}

	public boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new cringe();
	}
}