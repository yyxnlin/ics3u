import java.util.Arrays;

public class ArraysExercises {

	public static void main(String[] args) {
		int [] numbers = {34, -12, 17, -67, 50, 99, -18};

		// a
		int negativeCount = 0;

		for(int i = 0; i < numbers.length; i++) {
			if (numbers[i] < 0) {
				negativeCount++;
			}
		}

		System.out.println(negativeCount);

		// b

		int greaterCount = 0;

		for(int i = 0; i < numbers.length; i++) {
			if (numbers[i] > 10) {
				greaterCount++;
			}
		}

		System.out.println(greaterCount);
		
		// c
		for(int i = 0; i < numbers.length/2; i++) {
			int num1 = numbers[i];
			int num2 = numbers[numbers.length-1-i];
			numbers[i] = num2;
			numbers[numbers.length-1-i] = num1;
		}
		
		for(int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
	}

}
