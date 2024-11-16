// Lynn Tao
// Assignment #1
// Feb. 16, 2023
// This assignment acts as a cash register for a dessert shop! It asks for the amount of items you bought and calculates the total cost, change, as well as the number of bills/coins needed to make up the change.

import java.util.Scanner;


public class Assignment1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		// setting item prices
		final double TEA_PRICE = 5.69;
		final double CAKE_PRICE = 8.79;
		final double LATTE_PRICE = 6.99;
		final double SUSHI_PRICE = 1.49;
		final double MOCHI_PRICE = 1.19;
		
		// setting item names
		String teaName = "strawberry fruit tea";
		String cakeName = "Japanese cheesecake";
		String latteName = "matcha latte";
		String sushiName = "sushi";
		String mochiName = "red bean mochi";
		
		// welcome + asking for order
		System.out.println("WELCOME TO MY YUMMY-IN-THE-TUMMY DESSERT PLACE\n");
		System.out.print("How many strawberry fruit teas would you like? ");
		int teaQuantity = Integer.parseInt(in.nextLine());
		System.out.print("How many Japanese cheesecakes would you like? ");
		int cakeQuantity = Integer.parseInt(in.nextLine());
		System.out.print("How many matcha lattes would you like? ");
		int latteQuantity = Integer.parseInt(in.nextLine());
		System.out.print("How many sushis would you like? ");
		int sushiQuantity = Integer.parseInt(in.nextLine());
		System.out.print("How many red bean mochis would you like? ");
		int mochiQuantity = Integer.parseInt(in.nextLine());
		
		// calculating totals of each item 
		double totalTeaCost = TEA_PRICE * teaQuantity;
		double totalCakeCost = CAKE_PRICE * cakeQuantity;
		double totalLatteCost = LATTE_PRICE * latteQuantity;
		double totalSushiCost = SUSHI_PRICE * sushiQuantity;
		double totalMochiCost = MOCHI_PRICE * mochiQuantity;
		
		// calculating total cost before/after discounts and tax
		double subtotal = totalTeaCost + totalCakeCost + totalLatteCost + totalSushiCost + totalMochiCost; // cost before discounts/tax
		double discountedSubtotal = 0.5*totalCakeCost + 0.8*(totalTeaCost + totalLatteCost + totalSushiCost + totalMochiCost); // after 50% off most expensive and 20% off everything else
		double totalCost = 1.13 * discountedSubtotal; // adding 13% tax
		
		// checking that if quantity of item is not 1, add "s" to the name for plural form
		if (teaQuantity != 1) {
			teaName += "s";
		}
		
		if (cakeQuantity != 1) {
			cakeName += "s";
		}
		
		if (latteQuantity != 1) {
			latteName += "s";
		}
		
		if (sushiQuantity != 1) {
			sushiName += "s";
		}
		
		if (mochiQuantity != 1) {
			mochiName += "s";
		}
				
		// displaying the bill (quantity, price, and total price for each item)
		System.out.println("\nYOUR BILL\n");
		System.out.printf("%d %s @ $%.2f each = $%.2f\n", teaQuantity, teaName, TEA_PRICE, totalTeaCost);
		System.out.printf("%d %s @ $%.2f each = $%.2f\n", cakeQuantity, cakeName, CAKE_PRICE, totalCakeCost);
		System.out.printf("%d %s @ $%.2f each = $%.2f\n", latteQuantity, latteName, LATTE_PRICE, totalLatteCost);
		System.out.printf("%d %s @ $%.2f each = $%.2f\n", sushiQuantity, sushiName, SUSHI_PRICE, totalSushiCost);
		System.out.printf("%d %s @ $%.2f each = $%.2f\n", mochiQuantity, mochiName, MOCHI_PRICE, totalMochiCost);
		
		// displaying total cost before/after discount and tax
		System.out.printf("\nTotal = $%.2f\n", subtotal);
		System.out.printf("Total with discount = $%.2f\n", discountedSubtotal);
		System.out.printf("Total with tax = $%.2f\n", totalCost);

		// asking for amount paid and calculating change
		System.out.print("\nPlease enter the amount to be paid: $");
		double totalPaid = Double.parseDouble(in.nextLine());
		double change = Math.round((totalPaid - totalCost)*100)/100.0;
		System.out.printf("The change will be: $%.2f\n", change);
		
		// setting bill names (used later if plural form is needed)
		String fiftyDollarName = "fifty-dollar bill";
		String twentyDollarName = "twenty-dollar bill";
		String tenDollarName = "ten-dollar bill";
		String fiveDollarName = "five-dollar bill";
		String toonieName = "toonie";
		String loonieName = "loonie";
		String quarterName = "quarter";
		String dimeName = "dime";
		String nickelName = "nickel";
		String pennyName = "penny";

		// calculating number of bills/coins in full dollars needed to make up change (no cents)
		int fiftyDollarBills = (int) (change/50);
		int twentyDollarBills = (int) (change%50/20);
		int tenDollarBills = (int) (change%50%20/10);
		int fiveDollarBills = (int) (change%50%20%10/5);
		int toonies = (int) (change%50%20%10%5/2);
		int loonies = (int) (change%50%20%10%5%2);
		
		// calculating number of coins in cents needed to make up change
		int cents = (int) Math.round((change%1.0)*100); // for some reason if you don't round it, sometimes the value inside has a repeating decimal
		int quarters = (int) (cents/25);
		int dimes = (int) (cents%25/10);
		int nickels = (int) (cents%25%10/5);
		int pennies = (int) (cents%25%10%5);
		
		// changing name to plural form if the number of bills/coins needed is not 1
		if (fiftyDollarBills != 1) {
			fiftyDollarName += "s";
		}
		if (twentyDollarBills != 1) {
			twentyDollarName += "s";
		}
		if (tenDollarBills != 1) {
			tenDollarName += "s";
		}
		if (fiveDollarBills != 1) {
			fiveDollarName += "s";
		}
		if (toonies != 1) {
			toonieName += "s";
		}
		if (loonies != 1) {
			loonieName += "s";
		}
		if (quarters != 1) {
			quarterName += "s";
		}
		if (dimes != 1) {
			dimeName += "s";
		}
		if (nickels != 1) {
			nickelName += "s";
		}
		if (pennies != 1) {
			pennyName = "pennies";
		}
		
		// displaying number of bills/coins needed to make up change (don't display if none of that bill/coin are used)
		System.out.print("\nTo make up this amount, you will need: \n   ");
		
		int count = 0; // keeps track of how many types of bills/coins have been used (so it knows when to go to next line)
		if (fiftyDollarBills > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", fiftyDollarBills, fiftyDollarName);
			count ++;
		}
		
		if (twentyDollarBills > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", twentyDollarBills, twentyDollarName);
			count ++;
		}
		
		if (tenDollarBills > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", tenDollarBills, tenDollarName);
			count ++;
		}
		
		if (fiveDollarBills > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", fiveDollarBills, fiveDollarName);
			count ++;
		}
		
		if (toonies > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", toonies, toonieName);
			count ++;
		}
		
		if (loonies > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", loonies, loonieName);
			count ++;
		}
		
		if (quarters > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", quarters, quarterName);
			count ++;
		}
		
		if (dimes > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", dimes, dimeName);
			count ++;
		}
		
		if (nickels > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", nickels, nickelName);
			count ++;
		}
		
		if (pennies > 0) {
			if(count % 2 == 0 && count > 0) {
				System.out.print("\n   ");
			}
			System.out.printf("%-2d %-22s ", pennies, pennyName);
			count ++;
		}
		
	}
	
}
