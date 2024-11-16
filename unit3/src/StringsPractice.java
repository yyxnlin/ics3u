import java.util.Scanner;

public class StringsPractice {

	public static void main(String[] args) {
		Scanner in = new Scanner (System.in);
		System.out.print("Enter the first string: ");
		String str1 = in.nextLine();

		while (!str1.equalsIgnoreCase("finish")) {
			System.out.print("Enter the second string: ");
			String str2 = in.nextLine();

			str1 = str1.toLowerCase();
			str2 = str2.toLowerCase();
			String output = "";

			for (int i = 0; i < str1.length(); i++) {
				if (str2.indexOf(str1.charAt(i)) != -1 && output.indexOf(str1.charAt(i)) == -1 && Character.isLetter(str1.charAt(i))) {
					output += str1.charAt(i);
				}
			}

			if (output.equals(""))
				System.out.println("no common letters");
			else
				System.out.println(output);

			System.out.print("Enter first string: ");
			str1 = in.nextLine();
		}
	}

}
