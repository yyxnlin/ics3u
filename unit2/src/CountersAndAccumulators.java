import java.util.Scanner;

public class CountersAndAccumulators {

	public static void main(String[] args) {
		//		// 2.2.1
		//		for (int i = 1; i <= 10; i++) {
		//			System.out.println(i);
		//		}
		//		
		//		// 2.2.2
		//		for (int i = 10; i >= 1; i--) {
		//			System.out.println(i);
		//		}
		//		
		//		// 2.2.3		
		//		for (int i = 1; i <= 30; i+= 3) {
		//			System.out.println(i);
		//		}
		//		
		//		// 2.2.6
		//		Scanner in = new Scanner (System.in);	
		//		int sum = 0;
		//		for (int i = 1; i <= 5; i++) {
		//			sum += Integer.parseInt(in.nextLine());
		//		}
		//		System.out.println(sum);

		//		// 2.2.7
		//		Scanner in = new Scanner (System.in);	
		//		int sum = 0;
		//		for (int i = 1; i <= 5; i++) {
		//			sum += Integer.parseInt(in.nextLine());
		//		}
		//		System.out.println(sum/5.0);

		//		// 2.2.8
		//		Scanner in = new Scanner (System.in);	
		//		int num = 0;
		//		for (int i = 1; i <= 5; i++) {
		//			num = Math.max(num, Integer.parseInt(in.nextLine()));
		//		}
		//		System.out.println(num);

		//		// 2.2.9
		//		Scanner in = new Scanner (System.in);	
		//		int num = 0;
		//		for (int i = 1; i <= 5; i++) {
		//			num = Math.min(num, Integer.parseInt(in.nextLine()));
		//		}
		//		System.out.println(num);

		//		// 2.2.10
		//		Scanner in = new Scanner (System.in);	
		//		int num = Integer.parseInt(in.nextLine());
		//		for (int i = num-1; i >= 1; i--) {
		//			num *= i;
		//		}
		//		System.out.println(num);

		//		// 2.2.11
		//		Scanner in = new Scanner (System.in);	
		//		int num = Integer.parseInt(in.nextLine());
		//		int triangular = 0;
		//		for (int i = 1; i <= num; i++) {
		//			triangular += i;
		//		}
		//		System.out.println(triangular);

		//		// 2.2.12
		//		Scanner in = new Scanner (System.in);	
		//		int num = Integer.parseInt(in.nextLine());
		//		int current = 1;
		//		int previous = 1;
		//		for (int i = 1; i <= num-2; i++) {
		//			current += previous;
		//			previous = current-previous;
		//		}
		//		System.out.println(current);



//		Scanner in = new Scanner (System.in);	
//		int num = Integer.parseInt(in.nextLine());
//		
//		for (int i = 1; i <= num; i++) {
//			System.out.print((int)Math.pow(i, 2) + " ");
//		}
		
		
//		Scanner in = new Scanner (System.in);	
//		int num = Integer.parseInt(in.nextLine());
//		int sum = 0;
//		
//		if (num > 0) {
//			for (int i = 2; i <= num; i++) {
//				sum += Math.pow(i, 2);
//				System.out.print(" + " + (int)Math.pow(i, 2));
//			}
//			System.out.print(" = " + sum);
//		}
		
		
		
		
//		int sum = 0;
//		int count = 0;
//		while (sum != 12) {
//			count++;
//			int dice1 = (int) (6 * Math.random() + 1);
//			int dice2 = (int) (6 * Math.random() + 1);
//			sum = dice1 + dice2;
//			System.out.printf("Dice 1 = %d, Dice 2 = %d    Sum = %d\n", dice1, dice2, dice1+dice2);
//		}
//		System.out.printf("It took me %d tries", count);

		
		
		
		
//		Scanner in = new Scanner (System.in);	
//		System.out.println("Welcome to my Number Mine game! You have 5 guesses to dodge my mine!");
//		int number = (int) (10*Math.random());
//		boolean guessed = false;
//		
//		for (int i = 1; i <= 5; i++) {
//			System.out.print("Guess #" + i + ": ");
//			int guess = Integer.parseInt(in.nextLine());
//			
//			if (guess == number) {
//				System.out.println("UH OH!! You have unfortunately stepped on my mine!!");
//				guessed = true;
//				break;
//			}
//		}
//		
//		if (!guessed) {
//			System.out.println("WOOHOOOOO!!! Good job! My secret number is " + number + "!");
//		}
		
		
		
		
//		int number = 8;
//		boolean prime = true;
//		
//		if(number == 1) {
//			prime = false;
//		}
//		
//		for (int i = 2; i <= Math.sqrt(number); i++) {
//			if (number % i == 0) {
//				prime = false;
//				break;
//			}
//		}
//		
//		if (prime) {
//			System.out.println("YES");
//		}
//		else {
//			System.out.println("NO");
//		}
		
		
//		Scanner in = new Scanner (System.in);	
//		int number = 1;
//		
//		while (number > 0) {
//			System.out.print("Enter a number: ");
//			number = Integer.parseInt(in.nextLine());
//			if (number >= 2){
//				System.out.print(2);
//			}
//			for (int i = 3; i < number; i++) {
//				boolean prime = true;
//					
//				for (int j = 2; j <= Math.sqrt(i); j++) {
//					if (i % j == 0) {
//						prime = false;
//						break;
//					}
//				}
//				
//				if (prime) {
//					System.out.print(", " + i);
//				}
//			}
//			System.out.println("\n");
//		}
		
//		Scanner in = new Scanner (System.in);	
//		System.out.print("enter a number: ");
//		long num = Long.parseLong(in.nextLine());
//		long largest = 0;
//		if(num % 2 == 0) {
//			largest = 2;
//		}
//		
//		boolean prime = true;
//
//		for(int i = 1; i < num; i+=2) {	
//			prime = true;
//			if (num % i == 0) {
//				if(i == 1) {
//					prime = false;
//				}
//				
//				for (int j = 2; j <= Math.sqrt(i); j++) {
//					if (i % j == 0) {
//						prime = false;
//						break;
//					}
//				}
//				num /= i;
//
//				if (prime) {
//					largest = i;
//				}
//			}
//		}
//
//		System.out.println(largest);
		
//		int sum = 0;
//		for (int i = -21; i >= -57; i-=6) {
//			sum += Math.pow(i, 3) + Math.pow(-i+3, 3);
//
//		}
//		sum += Math.pow(-63, 3);
//		System.out.println(sum);
		
//		int i = 0;
//		for (; ; i++ ) {
//			System.out.println(i);
//			i++;
//		}
		
		int i = 0;
		
		if (i == 0);
		else {
		}
		
		
	}

}
