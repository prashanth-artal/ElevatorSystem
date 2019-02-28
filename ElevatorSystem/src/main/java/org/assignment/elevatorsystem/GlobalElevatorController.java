package org.assignment.elevatorsystem;

import java.util.List;
import java.util.Scanner;

import org.assignment.elevatorsystem.util.StaticValues;

/*
   The basic functionality of this Elevator Controller is to control the elevators movements
   according to the requests made by different people across different floors of valid range.
   
 */
public class GlobalElevatorController implements ElevatorController{

	/* Global Elevator Controller has-a list of elevators*/
	private List<Elevator> elevators;

	
	public GlobalElevatorController(List<Elevator> elevators) {
		this.elevators   =   elevators;
	}

	/*
	  addPickup function assigns the floor to the particular elevator(which is running independently) Deque.
	  @parameter direction : indicates the requested direction of movement of elevator. Move Up or Move Down.
	  @parameter floor     : indicates the floor number the request is being made / request to move towards a particular floor.
	
	 */
	public void addPickup(String direction,int floor) {

	
		boolean chkFloorAssignedToElevator = false;

		for (Elevator elevator : elevators) {

			/* The below condition check whether the elevator already in motion moving up and 
			 * the external request made is also for moving up */			
			if(elevator.getState() == ElevatorState.UP && direction.equals("moveUp") ) {
				
				/* Check if the current elevator position higher than the external request
				 * Ex : Elevator is moving up and its crossing 5 floor and external request made is from 0 to 4
				 */
				if(floor<elevator.getCurrentFloor()) {
					boolean currDirection = true;
					
					/*
					 * Wait until the current trip to destination is complete.
					 */
					while(!elevator.isIdle()) {
						if(currDirection) {
							System.out.println("Wait!! Elevator is already moving "+elevator.getState()+"\nand its crossing "+elevator.getCurrentFloor()+" floor\nyou are in  "+floor+" floor");
							currDirection = false;
						}
					}
					
					/* Add the request floor the elevator's Deque*/
					elevator.queueDestination(floor);
					
					/* Make chkFloorAssignedToElevator true to indicate that request floor
					 *  is assigned to particular Elevator  */					
					chkFloorAssignedToElevator = true;
					
				}

				 /* Check if the elevator is moving up and the request floor is in path 
				  * of elevator current floor and destination			
				  */
				else if(!elevator.isIdle() && elevator.isInPath(floor)) {
					/* Add the floor to the present head of the Deque of Elevator*/
					elevator.prependDestination(floor);
					
					/* Make chkFloorAssignedToElevator true to indicate that request floor
					 *  is assigned to particular Elevator  */	
					chkFloorAssignedToElevator = true;
				}
				
				/* Check if the elevator is moving up and the request floor is not in 
				 * the path and not requested by prior floor of elevator's current position*/
				else {
					/* Add the request floor the elevator's Deque*/
					elevator.queueDestination(floor);
					/* Make chkFloorAssignedToElevator true to indicate that request floor
					 *  is assigned to particular Elevator  */
					chkFloorAssignedToElevator = true;
				}


			}

			/* The below condition check whether the elevator already in motion moving down and 
			 * the external request made is also for moving down */	
			if(elevator.getState() == ElevatorState.DOWN &&  direction.equals("moveDown")  ) {

				/* Check if the current elevator position lower than the external request
				 * Ex : Elevator is moving down and its crossing 5 floor and external request made is from  6 to 50
				 */
				if(floor > elevator.getCurrentFloor()) {
					boolean currDirection = true;
					
					/*
					 * Wait until the current trip to destination is complete.
					 */
					while(!elevator.isIdle()) {
						if(currDirection) {
							System.out.println("Wait!! Elevator is already moving "+elevator.getState()+"\nand its crossing "+elevator.getCurrentFloor()+" floor\nyou are in  "+floor+" floor");
							currDirection = false;
						}
					}
					
					/* Add the request floor the elevator's Deque*/
					elevator.queueDestination(floor);
					
					/* Make chkFloorAssignedToElevator true to indicate that request floor
					 *  is assigned to particular Elevator  */		
					chkFloorAssignedToElevator = true;
				}
				 /* Check if the elevator is moving down and the request floor is in path 
				  * of elevator current floor and destination			
				  */
				else if(!elevator.isIdle() && elevator.isInPath(floor)) {
					
					/* Add the floor to the present head of the Deque of Elevator*/
					elevator.prependDestination(floor);
					
					/* Make chkFloorAssignedToElevator true to indicate that request floor
					 *  is assigned to particular Elevator  */		
					chkFloorAssignedToElevator = true;

				}
				else {
					/* Add the request floor the elevator's Deque*/
					elevator.queueDestination(floor);
					/* Make chkFloorAssignedToElevator true to indicate that request floor
					 *  is assigned to particular Elevator  */
					chkFloorAssignedToElevator = true;
				}


			}
		}


		for (Elevator elevator : elevators) {
            
			if(!chkFloorAssignedToElevator) {
				boolean currDirection = true;
				/* If elevator is in motion and the requested direction is opposite to current elevator's direction 
				 * then wait until the current request is completed by elevator*/
				while(!elevator.isIdle()) {
					if(currDirection) {
						System.out.println("Wait!! Until Elevator completes current trip.\nElevator is moving "+elevator.getState()+" and you chose to move "+direction);
						currDirection = false;
					}
					continue;
				}
				/* Add the request floor the elevator's Deque*/
				elevator.queueDestination(floor);
				break;
			}

		}


	}


	/* Verify floor is within the valid range*/
	public int verifyFloorRange(int floor) {
		

		if(floor>StaticValues.ELEVATOR_END_FLOOR  || floor < StaticValues.ELEVATOR_START_FLOOR) {
			System.out.println(" ENTER VALID FLOOR NUMBER BETWN "+ StaticValues.ELEVATOR_START_FLOOR+"  -  "+StaticValues.ELEVATOR_END_FLOOR);
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			//			sc.close();
			return verifyFloorRange(value);
		}

		return floor;
	}
	
	
	public List<Elevator> getElevators(){
		
		return elevators;
		
	}

}
