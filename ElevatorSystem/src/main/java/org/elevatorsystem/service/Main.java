package org.elevatorsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.elevatorsystem.interfaces.Elevator;
import org.elevatorsystem.interfaces.ElevatorController;
import org.elevatorsystem.model.ElevatorImplementation;
import org.elevatorsystem.model.GlobalElevatorController;



public class Main {

	public static void main(String[] args) {

		ElevatorImplementation elevator_1 =  new ElevatorImplementation(1,0, 20, 3);

		List<Elevator> elevatorList = new ArrayList<Elevator>();
		elevatorList.add(elevator_1);
	
		ElevatorController controller_1=new GlobalElevatorController(elevatorList);
		
		Thread th1 = new Thread(elevator_1);
		th1.start();

	
		
		while(true) {

			System.out.println(" Press \n 1. moveUp \n 2. moveDown \n 3. To exit");
			Scanner sc = new Scanner(System.in);
			int val = sc.nextInt();

			if(val == 1) {
				System.out.println("Enter your current floor :");
				int currentFloor = sc.nextInt();
				controller_1.addPickup("moveUp", currentFloor);


				System.out.println("Enter your interest floor :");
				int interestFloor = sc.nextInt();
				controller_1.addPickup("moveUp", interestFloor);
			}
			else if(val == 2) {
				System.out.println("Enter your current floor :");
				int currentFloor = sc.nextInt();
				controller_1.addPickup("moveDown", currentFloor);

				System.out.println("Enter your interest floor :");
				int interestFloor = sc.nextInt();
				controller_1.addPickup("moveDown", interestFloor);

			}
			else {
				break;
			}
			
		}
	}

}
