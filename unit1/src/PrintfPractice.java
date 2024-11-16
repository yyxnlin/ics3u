
public class PrintfPractice {

	public static void main(String[] args) {
//		int number = 2147483647;
//		System.out.println(number);
//		number++;
//		System.out.println(number);
		
//		int number = 123456;
//		System.out.printf ("Num:%8d", number); // Num:^^123456 (field width = 8)
//		System.out.printf ("Num:%-8d", number); // Num:123456^^ (spaces at end)
//		System.out.printf ("Num:%2d", number); // Num:+123,456^^
//		System.out.printf ("Num:%+,-10d", number); // Num:123456^^^^
//		System.out.printf ("Num:%08d", number); // Num:00123456

		double num = 1234.5678;
//		System.out.printf ("Real:%f\n", num); // Real:1234.567800
//		System.out.printf ("Real:%.2f\n", num); // Real:1234.57
//		System.out.printf ("Real:%.7f\n", num); // Real:1234.5678000
//		System.out.printf ("Real:%+,.1f\n", num); // Real:+1,234.6
//		System.out.printf ("Real:%010.1f\n", num); // Real:00001234.6
//		System.out.printf ("Real:%12f\n", num); // Real:^1234.567800
//		System.out.printf ("Real:%3.5f\n", num); // Real:1234.56780
//		System.out.printf ("Real:%1f\n", num); // Real:1234.567800
//		System.out.printf ("Real:%2.0f\n", num); // Real:1235
//		System.out.printf ("Real:%+011.2f\n", num); // Real:+0001234.57
//		System.out.printf ("Real:%+15f\n", num); // Real:^^^+1234.567800
//		System.out.printf ("Real:%,015f\n", num); // Real:0001,234.567800
		
//		String item = "Almonds";
//		double pricePerKg = 12.34;
//		double weight = 3.4566;
//		double cost = weight * pricePerKg;
//		System.out.printf ("%-10s: %10f kg @ $%+10.1f per kg will cost $%8.2f%n%n\n%nDone", item, weight, pricePerKg, cost);
//		int count = 9798;
//		double stat = 5629.75;
//		String word = "Batmobile";
//		
//		System.out.printf ("%.5s", word);
		
//		System.out.println(Math.sqrt(4));
		
		System.out.printf ("Real:%+09.1f\n", num); // Real:0001,234.567800
//		System.out.printf ("Real:%010.2f", num); // Real:0001,234.567800

	}

}
