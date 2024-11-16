// Lynn Tao
// Methods and Arrays Programming Exercises Q1-3

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MethodsArrayExercisesPart1 {

	public static void main(String[] args) throws FileNotFoundException {

//		// Q1
		Scanner in = new Scanner (System.in);
		Scanner inFile = new Scanner(new File("names.txt"));

		String s = "";
		int lines = 0;

		// read file and save as a string
		while (inFile.hasNextLine()) {
			s += inFile.nextLine() + "\n";
			lines++;
		}

		inFile.close();

		// save each name on each line into a String array
		String[] names = new String[lines];
		for (int i = 0; i < lines; i++) {
			names[i] = s.substring(0, s.indexOf("\n"));
			s = s.substring(s.indexOf("\n")+1);
		}

		// ask how many names to draw
		System.out.print("Enter number of names to draw: ");
		int numNames = Integer.parseInt(in.nextLine());

		// repeat until user enters -1
		while (numNames != -1) {
			String[] winners = drawNames(numNames, names);

			// display winners' names
			System.out.print("Lucky winners are: " + winners[0]);
			for (int i = 1; i < winners.length; i++) {
				System.out.print(", " + winners[i]);
			}
			
			// ask again
			System.out.print("\n\nEnter number of names to draw: ");
			numNames = Integer.parseInt(in.nextLine());
		}

		// Q2
//		Scanner in = new Scanner (System.in);
//		System.out.print("Enter a number: ");
//		int input = in.nextInt();
//		while (input != -1) {
//			System.out.println(convert(input));
//			System.out.print("Enter a number: ");
//			input = in.nextInt();
//		}


		// Q3
//		char[] origArray = {'A', 'B', 'C', 'D', 'E'};
//		System.out.println(shiftElements(origArray, -106));
	}
	
	// Description: select a number of winners from an array of names
	// Parameters: int numNames is number of winners to draw, String[] names is array of names
	// Return: String array of winners' names
	public static String[] drawNames(int numNames, String[] names) {
		String[] winners = new String[numNames];
		
		// pick random indices to set as winners
		for (int i = 0; i < numNames; i++) {
			int random = (int) (Math.random()*(names.length)); 
			winners[i] = names[random];
		}
		
		return winners;

	}
	
	// Description: convert numerical number to words form
	// Parameters: int number is the original integer to convert
	// Return: String containing the number in words
	public static String convert(int number) {
		String[] words = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String output = "";

		while (number != 0) {
			int lastDigit = number % 10;
			number /= 10;
			output = words[lastDigit] + " " + output;
		}

		output = output.trim();
		return output;
	}

	// Description: shift elements in an array a certain number of positions left/right
	// Parameters: char[] origArray is original array of characters, int shift is the number of positions to shift (negative = left, positive = right)
	// Return: char[] of shifted array
	public static char[] shiftElements(char[] origArray, int shift) {
		int arrayLength = origArray.length;
		shift %= arrayLength;

		if (shift < 0) {
			shift += arrayLength;
		}

		char[] newArray = new char[arrayLength];
		int index = 0;

		for (int i = shift; i < arrayLength; i++) {
			newArray[i] = origArray[index];
			index++;
		}
		
		for (int i = 0; i < shift; i++) {
			newArray[i] = origArray[index];
			index++;
		}

		return newArray;
	}

}
