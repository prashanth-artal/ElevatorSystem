package org.assignment.elevatorsystem.util;

import java.util.Scanner;

public class Verify {

	
	public static int verifyDirectionIsInteger(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Directions are numbers!! press 1. MoveUp 2. MoveDown");
			sc.nextLine();
		}
		return sc.nextInt();
	}
	
	
	public static int verifyFloorIsInteger(Scanner sc) {
		while (!sc.hasNextInt()) {
			System.out.println("Floor Numbers are Integers!! Please Enter Integer number!!");
			sc.nextLine();
		}
		return sc.nextInt();
	}
}
