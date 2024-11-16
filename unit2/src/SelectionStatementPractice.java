import java.util.Scanner;

public class SelectionStatementPractice {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your age: ");
		int age = Integer.parseInt(in.nextLine());
		double price = 28.50;
		if(age <= 12) {
			price *= 0.5;
		}
		
		else if(age < 18) {
			price *= 0.7;
		}
		
		else if(age >= 65) {
			price *= 0.35;
		}
		
		else {
			System.out.print("How many cats do you have? ");
			int cats = Integer.parseInt(in.nextLine());
			
			if (cats >= 1) {

				System.out.print("Do you have a cat named Suki or Spotty or Snuggles? ");
				String answer = in.nextLine();
				
				if (answer.equals("yes")){
					price = 0;
				}
				
				else if (answer.equals("no")){
					if (cats == 1) {
						price *= 0.95;
					}
					
					else if (cats > 1) {
						price *= 0.92;
					}
				}
			}
			
			else {
				price *= 1.02;
			}
			
		}
		System.out.printf("Price: $%2s", Math.round(price*100.0)/100.0);
	}

}
