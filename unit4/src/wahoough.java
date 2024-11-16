import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class wahoough {

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

	public static double[] generateArray(double max, double min, int size) {
		double[] arr = new double[size];

		if(max < min) {
			double[] emptyArr = new double[0];
			return emptyArr;
		}
		for (int i = 0; i < size; i ++) {
			int random = (int)(Math.random()*(max*10 - min*10 + 1)) + (int) (min*10); // random whole number
			arr[i] = random/10.0; // divide by 10 for double with 1 decimal place
		}	

		return arr;
	}

	public static String displayArray(double arr[], int num) {
		String output = "";
		int j = 0;
		int i = 0;

		for(int k = 0; k < arr.length; k++) {
			if (j < num) {
				output += String.format("%8.1f", arr[i*num + j]);
				j++;

			}
			else {
				i++;
				j = 0;
				k--;
				output += "\n";
			}

		}

		return output;
	}

	public static double rangeOfArray(double[] arr) {
		double min = arr[0];
		double max = arr[0];

		for (int i = 0; i < arr.length; i++) {
			// find minimum
			if (arr[i] < min) {
				min = arr[i];
			}
			// find maximum
			else if (arr[i] > max) {
				max = arr[i];
			}
		}
		// range is different between max and min rounded to one decimal place
		return (Math.round(10*(max-min))/10.0);
	}

	public static double[] listHalfNumbers(double[] arr) {
		double halfValue = rangeOfArray(arr)/2; // half of the value of range
		int count = 0; // keeps track of how many numbers are larger than half the value

		// check each number in the array to see if it is larger than half the value
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > halfValue) {
				count++;
			}
		}

		// new array containing all the values greater than half the value
		double[] newArr = new double[count];

		// store the values greater than half the value in the new array
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > halfValue) {
				newArr[j] = arr[i];
				j++;
			}
		}
		return newArr;
	}

	public static void sortArray(double[] arr) {
		// loop through every element of array up till second last element, find smallest value for rest of array after each index
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i + 1; // index of minimum first set to index after current index
			
			for (int j = minIndex + 1; j < arr.length; j++) {
				// if an element is smaller than current minimum, update the index of minimum to this one
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			// if current element is greater than the minimum element after this element, swap places
			if(arr[i] > arr[minIndex]) {
				double currentValue = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = currentValue;
			}
		}
	}

	public static double[] mergeArray(double[] arr1, double[] arr2) {
		int len = arr1.length + arr2.length; // length of merged array is sum of individual lengths
		double[] mergedArr = new double[len];
		int i = 0; // keeps track of current index in arr1
		int j = 0; // keeps track of current index in arr2

		for (int count = 0; count < len; count++) {
			// if end of arr1 is reached
			if (i == arr1.length) {
				mergedArr[count] = arr2[j]; // remaining array is filled with remaining elements of arr2
				j++; // arr2 index goes forward by 1
			}
			
			// if end of arr2 is reached
			else if (j == arr2.length) {
				mergedArr[count] = arr1[i]; // remaining array is filled with remaining elements of arr1
				i++; // arr1 index goes forward by 1
			}

			// if value of current index of arr1 is less than value of current index of arr2
			else if (arr1[i] < arr2[j]) {
				mergedArr[count] = arr1[i]; // add value of current index of arr1 to merged array
				i++; // arr1 index goes forward by 1
			}
			
			// if value of current index of arr1 is greater/equal to value of current index of arr2
			else {
				mergedArr[count] = arr2[j]; // add value of current index of arr2 to merged array
				j++; // arr2 index goes forward by 1
			}

		}
		return mergedArr;
	}

	public static double[] removeSublist(double[] arr1, double[] arr2) {
		int count = 0; // keeps track of number of occurrences of arr2 inside arr1

		// make a copy of arr1
		double[] origArr = new double[arr1.length];

		for (int i = 0; i < arr1.length; i++) {
			origArr[i] = arr1[i];
		}

		// loop through first array up to last possible first index of second array
		for (int i = 0; i <= origArr.length - arr2.length; i++) {
			boolean found = true;
			
			// see if arr2 starts at current index i in origArr
			for (int j = 0; j < arr2.length; j++) {
				// if a value is not equal, it is automatically false as it cannot appear starting from this index
				if (origArr[i + j] != arr2[j]) {
					found = false;
					break; // break loop to move on to next index in origArr
				}
			}

			// if it is found (never broke from above section), set all values of arr2 inside origArr to -1000
			if (found) {
				for (int j = i; j < i + arr2.length; j++) {
					origArr[j] = -1000;
				}
				i += arr2.length; // continue searching for arr2 after the sublist in arr1
				count++;
			}
		}

		int newLen = origArr.length - count * arr2.length; // length of new array removes elements of sublist from original array
		double[] newArr = new double[newLen]; // updated array with sublist removed
		
		int j = 0;

		// copy all elements from origArr to newArr if value is not -1000 (which would be excluded)
		for (int i = 0; i < origArr.length; i++) {
			if(origArr[i] != -1000) {
				newArr[j] = origArr[i];
				j++;
			}
		}
		
		
//		arr1 = newArr;
		System.out.println(Arrays.toString(arr1));
		System.out.println(Arrays.toString(newArr));
		
		return(newArr);
	}
	
	double[] arr1, arr2;
	
	JFrame frame;
	JPanel mainPanel, arrPanel, btnPanel, labelPanel;
	JTextArea arr1Text, arr2Text, mainText;
	JButton btnGenerate, btnDisplay, btnRange, btnListHalf, btnSort, btnMerge, btnSublist;
	JTextField minInputField, maxInputField, sizeInputField, rowInputField, arr2SizeInputField;

	public wahoough() {
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

		btnSublist = new JButton("Remove Sublist");
		btnSublist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				
				double max = Double.parseDouble(maxInputField.getText());
				double min = Double.parseDouble(minInputField.getText());

				int arr2size = Integer.parseInt(arr2SizeInputField.getText());
				arr2 = generateArray(max, min, arr2size);
				int numPerRow = Integer.parseInt(rowInputField.getText());
				arr2Text.setText(displayArray(arr2, 8));
				arr1 = removeSublist(arr1, arr2);
				mainText.setText(displayArray(arr1, numPerRow));


			}
		});
		//you can stop reading at this point

		initMoreJComponents(); //you can ignore this(unless you're doing the bonus)
	}

	//can be used for reference(only if you can understand it)
	//can be modified for the bonus

	void initJComponents() {
		frame = new JFrame("Array Methods Demonstration");
		Font GOODFONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		arr1Text = new JTextArea("arr1: ");
		arr1Text.setPreferredSize(new Dimension(600, 200));
		arr1Text.setLineWrap(true);
		arr1Text.setFont(GOODFONT);
		arr1Text.setEditable(false);
		arr2Text = new JTextArea("arr2: ");
		arr2Text.setPreferredSize(new Dimension(600, 200));
		arr2Text.setLineWrap(true);
		arr2Text.setFont(GOODFONT);
		arr2Text.setEditable(false);
		JPanel arr1Panel = new JPanel();
		arr1Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		arr1Panel.add(arr1Text);
		JPanel arr2Panel = new JPanel();
		arr2Panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		arr2Panel.add(arr2Text);
		mainPanel.add(arr1Panel);
		mainPanel.add(arr2Panel);
		
		mainText = new JTextArea();
		mainText.setFont(GOODFONT);


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

		JPanel inputPanel2 = new JPanel();
		inputPanel2.setLayout(new GridLayout(0, 6));
		
		rowInputField = new JTextField("1");
		inputPanel2.add(new JLabel("Numbers per row: "));
		inputPanel2.add(rowInputField);

		arr2SizeInputField = new JTextField("2");
		inputPanel2.add(new JLabel("arr 2 size: "));
		inputPanel2.add(arr2SizeInputField);
		
		mainPanel.add(inputPanel2);

		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0, 3));
	}

	void initMoreJComponents() {
		Font GOODFONT = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		btnPanel.add(btnGenerate);
		btnPanel.add(btnDisplay);
		btnPanel.add(btnRange);
		btnPanel.add(btnListHalf);
		btnPanel.add(btnSort);
		btnPanel.add(btnMerge);
		btnPanel.add(btnSublist);

		labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		labelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainText = new JTextArea();
		mainText.setPreferredSize(new Dimension(600, 200));
		mainText.setMaximumSize(new Dimension(600, 200));
		mainText.setMinimumSize(new Dimension(600, 200));
		mainText.setLineWrap(true);
		mainText.setFont(GOODFONT);
		mainText.setEditable(false);

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
		new wahoough();
	}
}