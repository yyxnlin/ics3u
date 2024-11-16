// Lynn Tao
// March 29, 2023
// Strings Programming Exercises #1 

import java.util.Scanner;

public class StringsExercises1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		//	question 1
//		System.out.print("Enter a string: ");
//		String s = in.nextLine();
//		s = s.toUpperCase();
//
//		for (int i = s.length()-1; i >= 0; i--) {
//			if (s.charAt(i) != ' ') {
//				System.out.print(s.charAt(i));
//			}
//		}

		//	question 2
//		System.out.print("Enter a string: ");
//		String s = in.nextLine();
//		s = s.toUpperCase();
//
//		while (!s.equalsIgnoreCase("STOP")) {
//
//
//			int vowels = 0;
//			int consonants = 0;
//			int symbols = 0;
//
//			for (int i = 0; i < s.length(); i++) {
//				if (s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U' ) {
//					vowels++;
//				}
//				else if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
//					consonants++;
//				}
//				else {
//					symbols++;
//				}
//			}
//			System.out.printf("Vowels = %d, Consonants = %d, Symbols = %d\n\n", vowels, consonants, symbols);
//
//			System.out.print("Enter a string: ");
//			s = in.nextLine();
//			s = s.toUpperCase();
//
//		}
		
		//	question 3
//		System.out.print("Enter a string: ");
//		String s = in.nextLine();
//		s = s.toUpperCase();
//
//		while (!s.equalsIgnoreCase("DONE")) {
//			boolean palindrome = true;
//
//			for (int i = 0; i <= s.length()/2; i++) {
//				if (s.charAt(i) != s.charAt(s.length()-1-i)) {
//					palindrome = false;
//					break;
//				}
//			}
//			System.out.println(palindrome);
//
//			s = "";
//			while (s.equals("")) {
//				System.out.print("Enter a string: ");
//				s = in.nextLine();
//				s = s.toUpperCase();
//			}
//		}


		//	question 4
//		System.out.print("Enter a string: ");
//		String s = in.nextLine();
//		s = s.trim();
//		while (!s.equalsIgnoreCase("DONE")) {
//			int letterCase = 0;
//
//			for (int i = 0; i < s.length(); i++) {
//				if (Character.isUpperCase(s.charAt(i))) {
//					letterCase = 0;
//					break;
//				}
//
//				else if (Character.isLowerCase(s.charAt(i))) {
//					letterCase = 1;
//					break;
//				}
//
//			}
//
//			s = s.toUpperCase();
//
//			for (int i = 0; i < s.length(); i+=1) {
//				if(!Character.isLetter(s.charAt(i))) {
//					System.out.print(s.charAt(i));
//				}
//
//				else if(letterCase % 2 == 0) {
//					System.out.print(s.charAt(i));
//					letterCase += 1;
//				}			
//
//				else {
//					System.out.print(Character.toLowerCase(s.charAt(i)));
//					letterCase += 1;
//
//				}
//			}
//
//			System.out.print("\nEnter a string: ");
//			s = in.nextLine();
//			s = s.trim();
//		}

		//	question 5
//		System.out.print("Enter a string: ");
//		String s = in.nextLine();
//
//		for (int i = 0; i < s.length(); i++) {
//			System.out.println(s.substring(0, s.length()-i));
//		}
//		System.out.println();
//		for (int i = 0; i < s.length(); i++) {
//			System.out.println(s.substring(i, s.length()));
//		}
//		System.out.println();
//		for (int i = 0; i < s.length(); i++) {
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//			}
//			System.out.println(s.substring(i, s.length()));
//		}
//		System.out.println();
//
//		for (int i = s.length()-1; i >= 0; i--) {
//			for (int j = 1; j <s.length()-i; j++) {
//				System.out.print(" ");
//
//			}
//			System.out.print(s.charAt(0));
//			for (int j = 1; j <= i; j++) {
//				System.out.print("-" + s.charAt(j));
//
//			}
//			System.out.println();
//		}
//
//		System.out.println();
//
//
//		for (int i = s.length()-1; i >= 0; i--) {
//			for (int j = 1; j <s.length()-i; j++) {
//				System.out.print(" ");
//
//			}
//			for (int j = i; j >= 0; j--) {
//				System.out.print(s.charAt(j));
//			}
//			for (int j = 0; j <= i; j++) {
//				System.out.print(s.charAt(j));
//			}
//			System.out.println();
//		}

		// question 5 (bonus)
//		System.out.print("Enter a string: ");
//		String s = in.nextLine();
//
//		for (int i = 0; i < s.length(); i++) {
//			// 1
//			System.out.print(s.substring(0, s.length()-i));
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//			}
//			System.out.print("   ");
//
//			// 2
//			System.out.print(s.substring(i, s.length()));
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//			}
//			System.out.print("   ");
//
//			// 3
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//			}
//			System.out.print(s.substring(i, s.length()));
//			System.out.print("   ");
//
//			// 4
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//
//			}
//			System.out.print(s.charAt(0));
//			for (int j = 1; j < s.length()- i; j++) {
//				System.out.print("-" + s.charAt(j));
//
//			}
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//
//			}
//			System.out.print("   ");
//
//			// 5
//			for (int j = 0; j < i; j++) {
//				System.out.print(" ");
//
//			}
//			for (int j = s.length()-i-1; j >= 0; j--) {
//				System.out.print(s.charAt(j));
//			}
//			for (int j = 0; j < s.length()-i; j++) {
//				System.out.print(s.charAt(j));
//			}
//
//			System.out.println();
//		}

	}

}
