import java.util.*;
import java.io.*;
public class stringsassignment3 {

	public static void main(String[] args) throws IOException {
		int option;
		Scanner s = new Scanner(System.in);
		int startSpaces = 0;
		int endSpaces = 0;
		String input;
		String file;
		String file2;
		String replace = "BFASTUWDMOE ";
		String replacement = "!@#$%^&*()=?";
		
		
		
		while (true) {
			System.out.println("Option 1: Encrypt\nOption 2: Decrypt\nOption 3: Exit");
			option = Integer.parseInt(s.nextLine());
			if (option != 3) {
				
				if (option == 1) {
					System.out.println("Enter the name of the file to read from:");
					file = s.nextLine();
					System.out.println("Enter name of the file to output to");
					file2 = s.nextLine();
					Scanner fileInput = new Scanner(new File(file + ".txt"));
					PrintWriter fileOutput = new PrintWriter(new FileWriter(file2 + ".txt"));
					while (fileInput.hasNextLine()) {
						input = fileInput.nextLine();
						input = input.toUpperCase();
						//Finding spaces
						for (int i = 0; i < input.length(); i ++) {
							if (input.charAt(i) != ' ') {
								i += input.length();
							}
							else {
								startSpaces ++;
							}
						}
						for (int i = input.length() - 1; i >= 0; i --) {
							if (input.charAt(i) != ' ') {
								i -= input.length();
							}
							else {
								endSpaces ++;
							}
						}
						input = input.trim();
						//Letter substitution
						for (int i = 0; i < input.length(); i++) {
							if (replace.indexOf(input.charAt(i)) >= 0) {
								input = input.substring(0, i) + replacement.charAt(replace.indexOf(input.charAt(i))) + input.substring(i + 1);
							}
						}
						
						
						//Swapping first and last 2 letters
						input = input.substring(input.length()-2) + input.substring(2, input.length()-2) + input.substring(0, 2);
						
						
						
						
						//Moving first half of string to last half
						if (input.length() % 2 == 0) {
							input = input.substring(input.length()/2) + input.substring(0, input.length()/2);
						}
						else {
							input = input.substring(input.length()/2 + 1) + input.substring(0, input.length()/2 + 1);
						}
					
						
						//Swapping middle 2 letters with previous 2 letters
						if (input.length() % 2 == 0) {
							input = input.substring(0, input.length()/2 - 3) + input.substring(input.length()/2 - 1, input.length()/2 + 1) + input.substring(input.length()/2 - 3, input.length()/2 -1) + input.substring(input.length()/2 +1);
						}
						else {
							input = input.substring(0, input.length()/2 - 2) + input.substring(input.length()/2, input.length()/2 + 2) + input.substring(input.length()/2 -2, input.length()/2) + input.substring(input.length()/2 + 2);
						}
						
						
						//Swapping every 2 letters
						if (input.length() % 2 == 0) {
							for (int i = 0; i < input.length(); i += 2) {
								input = input.substring(0,i) + input.charAt(i+1) + input.charAt(i) + input.substring(i + 2);
							}
						}
						else {
							for (int i = 0; i < input.length() - 1; i += 2) {
								input = input.substring(0,i) + input.charAt(i+1) + input.charAt(i) + input.substring(i + 2);
							}
						}
						
						fileOutput.println(startSpaces);
						fileOutput.println(input);
						fileOutput.println(endSpaces);
						
					}
					startSpaces = 0;
					endSpaces = 0;
					fileInput.close();
					fileOutput.close();
					System.out.println("done");
				}

				//option 2 - decrypt
				else {
					System.out.println("Enter the name of the file to read from:");
					file = s.nextLine();
					System.out.println("Enter name of the file to output to");
					file2 = s.nextLine();
					Scanner fileInput = new Scanner(new File(file + ".txt"));
					PrintWriter fileOutput = new PrintWriter(new FileWriter(file2 + ".txt"));
					while (fileInput.hasNextLine()) {
						startSpaces = Integer.parseInt(fileInput.nextLine());
						input = fileInput.nextLine();
						
						//Swapping every 2 letters
						if (input.length() % 2 == 0) {
							for (int i = 0; i < input.length(); i += 2) {
								input = input.substring(0,i) + input.charAt(i+1) + input.charAt(i) + input.substring(i + 2);
							}
						}
						else {
							for (int i = 0; i < input.length() - 1; i += 2) {
								input = input.substring(0,i) + input.charAt(i+1) + input.charAt(i) + input.substring(i + 2);
							}
						}
					
						
						
						//Swapping middle 2 letters with previous 2 letters
						if (input.length() % 2 == 0) {
							input = input.substring(0, input.length()/2 - 3) + input.substring(input.length()/2 - 1, input.length()/2 + 1) + input.substring(input.length()/2 - 3, input.length()/2 -1) + input.substring(input.length()/2 +1);
						}
						else {
							input = input.substring(0, input.length()/2 - 2) + input.substring(input.length()/2, input.length()/2 + 2) + input.substring(input.length()/2 -2, input.length()/2) + input.substring(input.length()/2 + 2);
						}
						
						
						
						
						//Moving first half of string to last half
						input = input.substring(input.length()/2) + input.substring(0, input.length()/2);

						
						//Swapping first and last 2 letters
						input = input.substring(input.length()-2) + input.substring(2, input.length()-2) + input.substring(0, 2);
						
						
						//Letter substitution
						for (int i = 0; i < input.length(); i++) {
							if (replacement.indexOf(input.charAt(i)) >= 0) {
								input = input.substring(0, i) + replace.charAt(replacement.indexOf(input.charAt(i))) + input.substring(i + 1);
							}
						}
						
						endSpaces = Integer.parseInt(fileInput.nextLine());
						//Start Spacing
						for (int i = 0; i != startSpaces; i++) {
							input = " " + input;
						}
						
						//End Spacing
						for (int i = 0; i != endSpaces; i++) {
							input = input + " ";
						}
						
						input = input.toLowerCase();
						
						System.out.println(input);
						fileOutput.println(input);
						
					}
					fileInput.close();
					fileOutput.close();
					System.out.println("done");
					startSpaces = 0;
					endSpaces = 0;
				}
				
				
			}
			else {
				break;
			}
		}
		
		
	}

}
