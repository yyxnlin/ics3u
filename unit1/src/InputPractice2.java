import java.util.Scanner;

public class InputPractice2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.print("How many minutes do you spend on video games in a day? ");
		int totalMinutes = Integer.parseInt(in.nextLine());
		System.out.print("What is the name of your favourite game? ");
		String game = in.nextLine();
		int hours = totalMinutes/60;
		int minutes = totalMinutes % 60;
		
		System.out.print("You spend " + hours + " hours and " + minutes + " minutes on video games everyday and your favourite game is " + game + "!");
	}

}
