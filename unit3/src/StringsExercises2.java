// Lynn Tao
// April 3, 2023
// Strings Exercises #2

import java.util.Scanner;

public class StringsExercises2 {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);

		while (true) {
			System.out.println("(1) Insert\n(2) Delete\n(3) Replace");
			System.out.print("Enter the option number (1/2/3): ");

			char option = in.nextLine().trim().charAt(0);

			System.out.print("\nEnter original phrase: ");
			String phrase = in.nextLine();

			
			if (option == '1') {
				// insert
				String pos = "";
				System.out.print("Enter the inserted string: ");
				String insertPhrase = in.nextLine();
				System.out.print("Enter the inserted position: ");
				int insertIndex = Integer.parseInt(in.nextLine().trim());

				while (insertIndex > phrase.length() || insertIndex < 0) {
					System.out.print("   Invalid. Enter an inserted position: ");
					insertIndex = Integer.parseInt(in.nextLine().trim());
				}
								
				phrase = phrase.substring(0, insertIndex) + insertPhrase + phrase.substring(insertIndex);
			}

			
                
			else if (option == '2') {
				// delete
				System.out.print("Enter the deleted string: ");
				String deletePhrase = in.nextLine();
	                
				for (int i = 0; i < phrase.length(); i++){
					int j = phrase.toLowerCase().indexOf(deletePhrase.toLowerCase());
					if (j != -1) {
						phrase = phrase.substring(0, j) + phrase.substring(j+deletePhrase.length());
						i += phrase.length();
					}
				}
			}

			else {
				// replace
				System.out.print("Enter replaced phrase: ");
				String replacePhrase = in.nextLine();
				System.out.print("Enter new phrase: ");
				String newPhrase = in.nextLine();

				for (int i = 0; i < phrase.length(); i++){
					int j = phrase.toLowerCase().indexOf(replacePhrase.toLowerCase());
					if (j != -1) {
						phrase = phrase.substring(0, j) + newPhrase + phrase.substring(j+replacePhrase.length());
						i += newPhrase.length();

					}
				}
			}
			System.out.println(phrase + "\n");

		}
	}

}