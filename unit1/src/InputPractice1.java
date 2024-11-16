import java.util.Scanner;

public class InputPractice1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int firstMark = Integer.parseInt(in.nextLine());
		int secondMark = Integer.parseInt(in.nextLine());
		int thirdMark = Integer.parseInt(in.nextLine());
		String name = in.nextLine();
		
		double average = (firstMark + secondMark + thirdMark)/3.0;
		System.out.println(name + ", your marks from last semester were " + firstMark + "%, " + secondMark + "%, and " + thirdMark + "%.");
		System.out.println("Your average is " + average + "%.");
	}

}
