// Lynn Tao
// April 13, 2023
// Assignment #3: Secret Messages
// This program allows users to encrypt and decrypt text files following a set of rules. Some letters will also be replaced with symbols following a key, which can be shifted.

import java.io.*;
import java.util.Scanner;

public class StringsAssignment3Encryption {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("[e] Encrypt\n[d] Decrypt\n[x] Exit ");
		System.out.print("  Type a letter: ");
		String action = in.nextLine();
		
		while (action.toLowerCase().charAt(0) != 'x'){
			// check if option is invalid
			while (action.toLowerCase().charAt(0) != 'e' && action.toLowerCase().charAt(0) != 'd'){
				System.out.println("[e] Encrypt\n[d] Decrypt\n[x] Exit ");
				System.out.print("  Type a letter: ");
				action = in.nextLine();
			}

			// ask for input/output files
			System.out.print("Input file name (no .txt extension): ");
			String inputFileName = in.nextLine();
			Scanner inFile = new Scanner(new File(inputFileName + ".txt"));
			System.out.print("Output file name (no .txt extension): ");
			String outputFileName = in.nextLine();
			PrintWriter outFile = new PrintWriter(new FileWriter(outputFileName + ".txt"));
			
			Integer shiftKey = 0;
			
			// letter/symbol substitutions
			String letters = "BFASTUWDMOE ";
			String symbols = "!@#$%^&*()=?";	

			// encryption
			if (action.toLowerCase().charAt(0) == 'e') {
				String spaces = "";

				// ask if they want to shift key substitutions
				System.out.print("Do you want to shift the key? (y/n) ");
				String shiftInput = in.nextLine();

				while (shiftInput.toLowerCase().charAt(0) != 'y' && shiftInput.toLowerCase().charAt(0) != 'n') {
					System.out.print("   Invalid. Do you want to shift the key? (y/n) ");
					shiftInput = in.nextLine();
				}

				// if they do want to shift
				if (shiftInput.toLowerCase().charAt(0) == 'y'){
					System.out.print("   Enter amount of spaces to shift: ");
					shiftKey = Integer.parseInt(in.nextLine());
					shiftKey %= 12; // change value so that it is between -12 and 12

					// if positive, move letters right 
					if (shiftKey > 0) {
						letters = letters.substring(letters.length() - shiftKey) + letters.substring(0, letters.length() - shiftKey);
					}
					
					// if negative, move letters left
					else if (shiftKey < 0){
						letters = letters.substring(shiftKey * -1) + letters.substring(0, shiftKey * -1);
					}
				}

				// read file line by line
				while (inFile.hasNextLine()) {
					String line = inFile.nextLine();
					int leadingSpaces = 0;
					int trailingSpaces = 0;

					// count leading spaces			
					for (int i = 0; i < line.length(); i++) {
						if (line.charAt(i) == ' ') 
							leadingSpaces++;
						else 
							break;
					}

					// count trailing spaces			
					for (int i = line.length()-1; i > 0; i--) {
						if (line.charAt(i) == ' ') 
							trailingSpaces++;
						else 
							break;
					}

					spaces += leadingSpaces + " " + trailingSpaces + "\n"; // contains number of leading/trailing spaces separated by a space

					// remove leading/trailing spaces and change to uppercase
					line = line.toUpperCase().trim();

					// swap letter and symbol
					for (int i = 0; i < line.length(); i++) {
						for (int j = 0; j < 12; j++) {
							if (line.charAt(i) == letters.charAt(j)) 
								line = line.substring(0, i) + symbols.charAt(j) + line.substring(i+1);
						}
					}

					// swap first and last two characters
					line = line.substring(line.length()-2) + line.substring(2, line.length()-2) + line.substring(0, 2);

					// even number of characters
					if (line.length() % 2 == 0) {
						// move first half to second half
						line = line.substring(line.length()/2) + line.substring(0, line.length()/2);
						// swap two characters next to middle of string
						line = line.substring(0, line.length()/2 - 3) + line.substring(line.length()/2 - 1, line.length()/2 + 1) + line.substring(line.length()/2 - 3, line.length()/2 - 1) + line.substring(line.length()/2 + 1);
					}

					// odd number of characters
					else {
						// move first half to second half
						line = line.substring((line.length()+1)/2) + line.substring(0, (line.length()+1)/2);

						// swap two characters next to middle of string
						line = line.substring(0, (line.length()-1)/2 - 2) + line.substring((line.length()-1)/2, (line.length()-1)/2 + 2) + line.substring((line.length()-1)/2 - 2, (line.length()-1)/2) + line.substring((line.length()-1)/2 + 2);
					}


					// swap every two letters
					for (int i = 0; i < line.length()-1; i += 2) {
						line = line.substring(0, i) + line.charAt(i+1) + line.charAt(i) + line.substring(i+2);
					}

					outFile.println(line); // print to file
				}
				// key shift + list of spaces counts separated by xxxxxx (after all the lines of text)
				outFile.println("xxxxxxxxxxxxxxxxxx");
				outFile.println(shiftKey);
				outFile.print(spaces);

			}

			// decryption
			else if (action.toLowerCase().charAt(0) == 'd') {
				String decryptedText = "";
				int numLines = 0;
				String line = inFile.nextLine();

				// read each line of encrypted text
				while (!line.equals("xxxxxxxxxxxxxxxxxx")) {
					numLines ++; // keep track of how many lines of encrypted text there are

					// new line for each line of decrypted text
					if (numLines != 1) {
						decryptedText += "\n";
					}
					// swap every two letters
					for (int i = 0; i < line.length()-1; i += 2) {
						line = line.substring(0, i) + line.charAt(i + 1) + line.charAt(i) + line.substring(i+2);
					}

					// even number of characters
					if (line.length() %2 == 0) {
						// swap two characters next to middle of string
						line = line.substring(0, line.length()/2 - 3) + line.substring(line.length()/2 - 1, line.length()/2 + 1) + line.substring(line.length()/2 - 3, line.length()/2 - 1) + line.substring(line.length()/2 + 1);

						// move first half to second half
						line = line.substring(line.length()/2) + line.substring(0, line.length()/2);
					}

					// odd number of characters
					else {
						// swap two characters next to middle of string
						line = line.substring(0, (line.length()-1)/2 - 2) + line.substring((line.length()-1)/2, (line.length()-1)/2 + 2) + line.substring((line.length()-1)/2 - 2, (line.length()-1)/2) + line.substring((line.length()-1)/2 + 2);
						// move first half to second half
						line = line.substring((line.length())/2) + line.substring(0, (line.length())/2);
					}

					// swap first and last two characters
					line = line.substring(line.length()-2) + line.substring(2, line.length()-2) + line.substring(0, 2);
					decryptedText += line;
					line = inFile.nextLine();

				}

				// changing letters to match key shift 
				shiftKey = Integer.parseInt(inFile.nextLine());

				// if positive, move letters right 
				if (shiftKey > 0) {
					letters = letters.substring(letters.length() - shiftKey) + letters.substring(0, letters.length() - shiftKey);
				}
				// if negative, movie letters left
				else if (shiftKey < 0){
					letters = letters.substring(shiftKey * -1) + letters.substring(0, shiftKey * -1);
				}

				// substituting characters
				for (int i = 0; i < decryptedText.length(); i++) {
					// swap symbol and letter
					for (int j = 0; j < 12; j++) {
						if (decryptedText.charAt(i) == symbols.charAt(j)) 
							decryptedText = decryptedText.substring(0, i) + letters.charAt(j) + decryptedText.substring(i+1);
					}
				}

				// change to lowercase
				decryptedText = decryptedText.toLowerCase();
				decryptedText += "\n"; // blank line at end so index isn't out of bounds in next step
				
				// adding back leading/trailing spaces
				for (int i = 0; i < numLines; i++) {
					String outputLine = "";
					String spaceLine = inFile.nextLine(); // number of leading/trailing spaces separated by space
					String textLine = decryptedText.substring(0, decryptedText.indexOf("\n")); // corresponding line of text without leading/trailing spaces

					decryptedText = decryptedText.substring(decryptedText.indexOf("\n") + 1); // remove current (first) line of text

					// add back leading spaces
					for (int j = 0; j < Integer.parseInt(spaceLine.substring(0, spaceLine.indexOf(' '))); j++) {
						outputLine += " ";
					}

					outputLine += textLine; // line of text in between leading/trailing spaces

					// add back trailing spaces
					for (int j = 0; j < Integer.parseInt(spaceLine.substring(spaceLine.indexOf(' ') + 1)); j++) {
						outputLine += " ";
					}
					
					// if not the last line, print so that it goes to next line
					if (i != numLines - 1) 
						outFile.println(outputLine);
					
					// print last line separately so that there isn't the empty line at the end :(
					else 
						outFile.print(outputLine);
				}
			}
			
			// close input/output files
			inFile.close();
			outFile.close();
			System.out.println("Outputted to " + outputFileName + ".txt\n");

			// show options again
			System.out.println("[e] Encrypt\n[d] Decrypt\n[x] Exit ");
			System.out.print("  Type a letter: ");
			action = in.nextLine();

		}

	}

}




