import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;

public class ArrayMethods {

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

		for (int i = 0; i < size; i ++) {
			int random = (int)(Math.random()*(max*10 - min*10 + 1)) + (int) (min*10);
			arr[i] = random/10.0;
		}	

		return arr;
	}

	public static String displayArray(double arr[], int num) {
		String output = "";
		int j = 0;
		int i = 0;

		for(int k = 0; k < arr.length; k++) {
			if (j < num) {
				output += String.format("%10.1f", arr[i*num + j]);
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
			if (arr[i] < min) {
				min = arr[i];
			}
			else if (arr[i] > max) {
				max = arr[i];
			}
		}
		return (Math.round(10*(max-min))/10.0);
	}

	public static double[] listHalfNumbers(double[] arr) {
		double halfValue = rangeOfArray(arr)/2;
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > halfValue) {
				count++;
			}
		}
		double[] newArr = new double[count];

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
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					double temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

	public static double[] mergeArray(double[] arr1, double[] arr2) {
		int len = arr1.length + arr2.length;
		double[] mergedArr = new double[len];
		int i = 0;
		int j = 0;

		for (int count = 0; count < len; count++) {
			if (i == arr1.length) {
				mergedArr[count] = arr2[j];
				j++;
			}

			else if (j == arr2.length) {
				mergedArr[count] = arr1[i];
				i++;
			}

			else if (arr1[i] < arr2[j]) {
				mergedArr[count] = arr1[i];
				i++;
			}
			else {
				mergedArr[count] = arr2[j];
				j++;
			}

		}
		return mergedArr;
	}

	public static double[] removeSublist(double[] arr1, double[] arr2) {
		int count = 0;
		System.out.println("orig arr: " + Arrays.toString(arr1));


		double[] arr3 = new double[arr1.length];

		for (int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i];
		}

		for (int i = 0; i < arr1.length - arr2.length; i++) {
			boolean found = true;
			for (int j = 0; j < arr2.length; j++) {
				if (arr1[i + j] != arr2[j]) {
					found = false;
					break;
				}
			}

			if (found) {
				for (int j = i; j < i+arr2.length; j++) {
					arr3[j] = -1;
				}
				i+= arr2.length;
				count++;

			}
		}

		System.out.println("count: " + count);
		int j = 0;
		System.out.println("arr 3   : " + Arrays.toString(arr3));

		int newLen = arr1.length - count * arr2.length;
		double[] newArr = new double[newLen];

		for (int i = 0; i < arr1.length; i++) {
			if(arr3[i] != -1) {
				newArr[j] = arr3[i];
				j++;
			}
		}
		System.out.println("new arr : " + Arrays.toString(newArr));

		return newArr;

	}
	double[] arr1, arr2;
	JFrame frame;
	JPanel mainPanel, arrPanel, btnPanel, labelPanel;
	JTextArea arr1Text, arr2Text, mainText;
	JButton btnGenerate, btnDisplay, btnRange, btnListHalf, btnSort, btnMerge, btnSublist;
	JTextField minInputField, maxInputField, sizeInputField, rowInputField, arr2SizeInputField;

	public ArrayMethods() {
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
				System.out.println(arrayToString);


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
				System.out.println(Arrays.toString(listHalf));
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
				System.out.println(Arrays.toString(arr1));
				System.out.println(Arrays.toString(arr2));
				double[] mergedArray = mergeArray(arr1, arr2);


				System.out.println(Arrays.toString(mergedArray));
				int numPerRow = Integer.parseInt(rowInputField.getText());
				mainText.setText(displayArray(mergedArray, numPerRow));

			}
		});

		btnSublist = new JButton("Remove Sublist");
		btnSublist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arr1 == null) return;

				//uncomment the following line(s) 
				//once you are done implementing the method
				double max = Double.parseDouble(maxInputField.getText());
				double min = Double.parseDouble(minInputField.getText());
				
				System.out.println(Arrays.toString(removeSublist(arr1, arr2)));
				int arr2size = Integer.parseInt(arr2SizeInputField.getText());

				arr2Text.setText(displayArray(generateArray(max, min, arr2size), 8));

			}
		});
		//you can stop reading at this point

		initMoreJComponents(); //you can ignore this(unless you're doing the bonus)
	}

	//can be used for reference(only if you can understand it)
	//can be modified for the bonus

	void initJComponents() {
		frame = new JFrame("Array Methods Demonstration");
		Font COURIER = new Font("Courier", Font.PLAIN, 11);
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

		arr2SizeInputField = new JTextField("5");
		rowInputPanel.add(new JLabel("arr 2 size: "));
		rowInputPanel.add(arr2SizeInputField);

		mainPanel.add(rowInputPanel);

		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0, 3));
	}

	void initMoreJComponents() {
		Font COURIER = new Font("Courier", Font.PLAIN, 11);
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
		mainText.setFont(COURIER);
		mainText.setEditable(false);
		Font font = new Font ("Courier", Font.PLAIN, 11);
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
		new ArrayMethods();
	}
}