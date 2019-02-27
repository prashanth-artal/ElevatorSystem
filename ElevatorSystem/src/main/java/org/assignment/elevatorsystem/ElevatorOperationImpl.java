package org.assignment.elevatorsystem;

import java.util.Scanner;

import org.assignment.elevatorsystem.util.StaticValues;
import org.assignment.elevatorsystem.util.Verify;

public class ElevatorOperationImpl implements ElevatorOperation{

	@Override
	public void moveUp(Scanner sc, ElevatorController controller) {
		// TODO Auto-generated method stub
		
		System.out.println( StaticValues .CURRENT_FLOOR );		
		/* Verify the floor number is valid Integer*/
		int currentFloor = Verify .verifyFloorIsInteger(sc);

		/* Verify the floor number is within the range*/
		currentFloor     = controller.verifyFloorRange( currentFloor );

		/* Assign the direction and floor number to controller to pickup*/
		controller.addPickup( StaticValues .MOVE_UP, currentFloor );


		System.out.println(StaticValues .INTEREST_FLOOR);
		/* Verify the floor number is valid Integer*/
		int interestFloor = Verify .verifyFloorIsInteger(sc);

		/* Interest floor should be greater than current floor while moving up */
		while(interestFloor   <  currentFloor) {

			System.out.println(StaticValues .MV_UP_INT_LESS_CURR);					
			/* Verify the floor number is valid Integer*/
			interestFloor = Verify .verifyFloorIsInteger(sc);
			/* Verify the floor number is within the range*/
			interestFloor = controller.verifyFloorRange(interestFloor);

		} 

		/* Assign the direction and floor number to controller to pickup*/
		controller.addPickup( StaticValues .MOVE_UP, interestFloor );

	}

	@Override
	public void moveDown(Scanner sc, ElevatorController controller) {
		// TODO Auto-generated method stub
		System.out.println( StaticValues .CURRENT_FLOOR );
		/* Verify the floor number is valid Integer*/
		int currentFloor =  Verify .verifyFloorIsInteger(sc);
		
		/* Verify the floor number is within the range*/
		currentFloor     = controller.verifyFloorRange(currentFloor);
		
		/* Assign the direction and floor number to controller to pickup*/
		controller.addPickup( StaticValues .MOVE_DOWN, currentFloor );

		System.out.println(StaticValues .INTEREST_FLOOR);
		/* Verify the floor number is valid Integer*/
		int interestFloor = Verify .verifyFloorIsInteger(sc);
		
		/* Verify the floor number is within the range*/
		interestFloor     = controller.verifyFloorRange(interestFloor);

		/* Interest floor should be greater than current floor while moving down */
		while(interestFloor   >  currentFloor) {

			System.out.println(StaticValues .MV_UP_INT_GT_CURR);
			interestFloor =  Verify .verifyFloorIsInteger(sc);
			interestFloor = controller.verifyFloorRange(interestFloor);

		}
		
		/* Assign the direction and floor number to controller to pickup*/
		controller.addPickup( StaticValues .MOVE_DOWN, interestFloor);
	}

}
