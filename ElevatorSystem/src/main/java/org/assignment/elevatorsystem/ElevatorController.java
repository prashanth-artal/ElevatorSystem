package org.assignment.elevatorsystem;

import java.util.List;

/* 
    ElevatorController interface with basic functions which can be implemented for any type ElevatorController class 
 */
public interface ElevatorController {
	
	/* function to move up or down to the particular floor */
	void addPickup(String direction,int floor);

	/* function to verify the floor is in the valid range of the building */
     int verifyFloorRange(int floor);
     List<Elevator> getElevators();
}
