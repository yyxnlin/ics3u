// Lynn Tao
// Methods Programming Exercises (Part 1)
// April 26, 2023

import java.util.Scanner;

public class MethodsProgrammingExercises {

	// Description: calculates product of all numbers between two numbers
	// Parameters: starting + ending number (integers)
	// Return: product of all numbers in between (inclusive of starting, exclusive of ending)
	public static int product (int start, int end) {
		if (start >= end) {
			return 0;
		}

		int output = 1;

		for (int i = start; i < end; i++) {
			output *= i;
		}

		return output;
	}

	// Description: draws a rectangle (filled or unfilled)
	// Parameters: number of rows and columns (int), whether it is filled or not (boolean), symbol to draw with (char)
	// Return: String containing the drawn rectangle
	public static String createRect(int rows, int columns, boolean filled, char symbol) {
		String output = "";

		// fill rectangle
		if(filled) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					output += symbol;
				}
				output += "\n";
			}
		}

		// don't fill rectangle
		else {
			// first row
			for (int i = 0; i < columns; i++) {
				output += symbol;
			}
			output += "\n";

			// middle rows
			for (int i = 1; i < rows-1; i++) {
				output += symbol;
				for (int j = 1; j < columns-1; j++) {
					output += ' ';
				}
				output += symbol;
				output += "\n";
			}

			// last row
			for (int i = 0; i < columns; i++) {
				output += symbol;
			}
		}

		return output;

	}

	// Description: checks if a quadratic equation has real roots
	// Parameters: values of coefficients a, b, and c (double)
	// Return: true/false (if it has any real roots)
	public static boolean hasRealRoots(double a, double b, double c) {
		double discriminant = b*b - 4*a*c;

		if (discriminant >= 0) {
			return true;
		}
		else {
			return false;
		}

	}

	// Description: calculates + prints the real roots of a quadratic equation
	// Parameters: values of coefficients a, b, and c (double)
	// Return: nothing - only prints roots or "no real roots" in console
	public static void solveQuadratic(double a, double b, double c) {
		double discriminant = b*b - 4*a*c;
		String output = "";

		// if there are no real roots, display "no real roots"
		if (!hasRealRoots(a, b, c)) {
			output = "No real roots";
		}

		// finding roots
		else {
			double root1 = (-1*b + Math.sqrt(discriminant))/(2*a);
			double root2 = (-1*b - Math.sqrt(discriminant))/(2*a);

			output += Math.round(root1*10)/10.0;

			// if there are two distinct real roots, print them both
			if (root1 != root2) {
				output += ", " + Math.round(root2*10)/10.0;
			}
		}

		System.out.println(output);

	}


	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		
		// Q1
		System.out.print("Enter starting number: ");
		int a = Integer.parseInt(in.nextLine());
		System.out.print("Enter ending number: ");
		int b = Integer.parseInt(in.nextLine());
		System.out.println(product(a, b));



		// Q2
//		// get height
//		System.out.print("Enter height: ");
//		int a = Integer.parseInt(in.nextLine());
//		while (a <= 0) {
//			System.out.print("   Invalid. Enter height: ");
//			a = Integer.parseInt(in.nextLine());
//		}
//
//		// get length
//		System.out.print("Enter length: ");
//		int b = Integer.parseInt(in.nextLine());
//		while (b <= 0) {
//			System.out.print("   Invalid. Enter length: ");
//			b = Integer.parseInt(in.nextLine());
//		}
//
//		// ask if they want it to be filled or not
//		System.out.print("Filled? (y/n): ");
//		String filledInput = in.nextLine();
//
//		boolean filled = false;
//
//		while (!filledInput.equalsIgnoreCase("y") && !filledInput.equalsIgnoreCase("n")) {
//			System.out.print("   Invalid. Filled? (y/n): ");
//			filledInput = in.nextLine();
//		}
//
//		if (filledInput.equalsIgnoreCase("y")) {
//			filled = true;
//		}
//
//		// ask which symbol to fill with
//		System.out.print("Enter symbol to fill with: ");
//		char symbol = in.nextLine().charAt(0);
//		System.out.println(createRect(a, b, filled, symbol));




//		// Q3
//		System.out.print("Enter first number: ");
//		double a = Double.parseDouble(in.nextLine());
//		System.out.print("Enter second number: ");
//		double b = Double.parseDouble(in.nextLine());
//		System.out.print("Enter third number: ");
//		double c = Double.parseDouble(in.nextLine());
//
//		if (hasRealRoots(a, b, c)) {
//			solveQuadratic (a, b, c);
//		}
//		else {
//			System.out.println("No real roots");
//		}
	}

}
