package org.assignment.elevatorsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.assignment.elevatorsystem.Elevator;
import org.assignment.elevatorsystem.ElevatorController;
import org.assignment.elevatorsystem.ElevatorImplementation;
import org.assignment.elevatorsystem.GlobalElevatorController;
import org.assignment.elevatorsystem.util.StaticValues;


/*
 
   ELEVATOR ALGORITHM : The elevator algorithm, a simple algorithm by which a single elevator can decide where to stop, is summarized as follows:

    1.Continue traveling in the same direction while there are remaining
      requests in that same direction.
    2.If there are no further requests in that direction, then stop and
      become idle, or change direction if there are requests in the 
      opposite direction.[Wikipedia]


   TEST CASES:
    
   TESTCASE 1.    
               MoveUp  i)  2 ---------5
               MoveUp  ii)              6---------15
                 
                 
   TESTCASE 2.  
                MoveUp  i) 2 ------------------13
                MoveUp  ii)                7---------16
                  
   TESTCASE 3.   
                MoveUp  i)      2 ---------------------------------- 25
                MoveUp  ii)                 9-------------------20
                  
   TESTCASE 4.  
                 MoveUp i) 2 --------------------------------------20
                 MoveUp ii)                7---------16     
                 MoveDown iii)          4<------ 10
    
 */


public class Main {


	public static void main(String[] args) {


		int maxEndFloor    = StaticValues.ELEVATOR_END_FLOOR;
		int startFloor     = StaticValues.ELEVATOR_START_FLOOR;

		ElevatorImplementation elevator_1 =  new ElevatorImplementation(1,startFloor, maxEndFloor, 3);

		List<Elevator> elevatorList       = new ArrayList<Elevator>();
		elevatorList         .add(elevator_1);

		ElevatorController controller_1   = new GlobalElevatorController(elevatorList);

		Thread th1                        = new Thread(elevator_1);
		th1                        .start();



		while(true) {

			System.out.println(" Press \n 1. moveUp \n 2. moveDown \n 3. To exit");
			Scanner sc  = new Scanner(System.in);
			int val     = sc.nextInt();

			if(val == 1) {

				System.out.println("Enter your current floor :");

				int currentFloor = sc          .nextInt();
				currentFloor     = controller_1.verifyFloor(currentFloor);
				controller_1                   .addPickup("moveUp", currentFloor);


				System.out.println("Enter your interest floor :");
				int interestFloor = sc          .nextInt();
				interestFloor     = controller_1.verifyFloor(interestFloor);

				while(interestFloor   <  currentFloor) {

					System.out.println("You chose to move up:\n");
					System.out.println("Re-enter your interest floor :");
					interestFloor = sc          .nextInt();
					interestFloor = controller_1.verifyFloor(interestFloor);

				}

				controller_1                    .addPickup("moveUp", interestFloor);
  
			}
			else if(val == 2) {

				System.out.println("Enter your current floor :");
				int currentFloor = sc           .nextInt();
				currentFloor     = controller_1 .verifyFloor(currentFloor);
				controller_1                    .addPickup("moveDown", currentFloor);

				System.out.println("Enter your interest floor :");
				int interestFloor = sc          .nextInt();
				interestFloor     = controller_1.verifyFloor(interestFloor);

				while(interestFloor   >  currentFloor) {

					     System.out.println("You chose to move up:\n");
				         System.out.println("Re-enter your interest floor :");
				                	interestFloor = sc          .nextInt();
					                interestFloor = controller_1.verifyFloor(interestFloor);

				}
				controller_1                    .addPickup("moveDown", interestFloor);

			}
			else {
				break;
			}

		}
	}

}
