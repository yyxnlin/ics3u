import java.util.Scanner;

public class StringsTest {

	public static void main(String[] args) {
//		String str = "hahaha";
//		System.out.println(str.substring(str.length()));
		
//		Scanner in = new Scanner (System.in);
//		System.out.print("What is your name? ");
//		String s = in.nextLine().trim();
//		String first = "";
//		String last = "";	
//		
//		int name = 1;
//		
//		for(int i = s.length()-1; i >= 0; i--) {
//			if (s.charAt(i) == ' ' && name == 1) 
//				name ++;
//			else if (name == 1)
//				last = s.charAt(i) + last;
//			else
//				first = s.charAt(i) + first;
//		}
//		
//		String lastCap = (last.charAt(0)+ "").toUpperCase();
//		
//		for (int i = 1; i < last.length(); i++) 
//			lastCap += last.charAt(i);
//		
//		if (first != "") {
//			String firstCap = (first.charAt(0)+ "").toUpperCase();
//			
//			for (int i = 1; i < first.length(); i++) 
//				firstCap += first.charAt(i);
//			
//			System.out.println(lastCap + ", " + firstCap);
//		}
//		else
//			System.out.println(lastCap);
		
//		Scanner in = new Scanner (System.in);
//		System.out.print("What is your name? ");
//		String s = in.nextLine().trim();
//
//		int space = 0;
//		
//		for (int i = s.length() - 1; i >= 0; i--) {
//			if (s.charAt(i) == ' ') {
//				space = i;
//				break;
//			}
//		}
//		if (space == 0) {
//			System.out.println(s);
//		}
//		else {
//			// get last name
//			for (int i = space + 1; i < s.length(); i++) {
//				System.out.print(s.charAt(i));
//			}
//			System.out.print(", ");
//			
//			// get first name
//			for (int i = 0; i < space; i++) {
//				System.out.print(s.charAt(i));
//			}
//		}
		
		
		// shortened code
		Scanner in = new Scanner (System.in);
		System.out.print("What is your name? ");
		String s = in.nextLine().trim();

		int space = 0;
		
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == ' ') {
				space = i;
				break;
			}
		}
		if (space == 0) {
			System.out.println(s);
		}
		else {
			// get last name
			System.out.print(s.substring(space + 1, s.length()) + ", ");
			
			// get first name
			System.out.print(s.substring(0, space));

		}
		

	}

}
