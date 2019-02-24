package org.assignment.elevatorsystem;

import java.util.List;
import java.util.Scanner;

import org.assignment.elevatorsystem.util.StaticValues;

public class GlobalElevatorController implements ElevatorController{

	private List<Elevator> elevators;

	public GlobalElevatorController(List<Elevator> elevators) {
		this.elevators   =   elevators;
	}

	/**
	 * Represents pick up (floor) : Signal to move to the particular floor
	 * 
	 * @param floor assumption: the same pickup floor will not be requested while it's being processed.  Logic should
	 *             be handled by hypothetical button class.
	 */

	public void addPickup(String up,int floor) {

		//		floor = verifyFloor(floor);   
		boolean chkElevatorAssigned = false;

		for (Elevator elevator : elevators) {

			System.out.println(elevator.getDestinationQueue());
			if(elevator.getState() == State.UP && up.equals("moveUp") ) {
				if(!elevator.isIdle() && elevator.isInPath(floor)) {
					elevator.prependDestination(floor);
					chkElevatorAssigned = true;

				}
				else {
					elevator.queueDestination(floor);
					chkElevatorAssigned = true;
				}


			}

			if(elevator.getState() == State.DOWN &&  up.equals("moveDown")  ) {

				if(!elevator.isIdle() && elevator.isInPath(floor)) {
					elevator.prependDestination(floor);
					chkElevatorAssigned = true;

				}
				else {
					elevator.queueDestination(floor);
					chkElevatorAssigned = true;
				}


			}
		}


		for (Elevator elevator : elevators) {

			if(!chkElevatorAssigned) {
				elevator.queueDestination(floor);
				break;
			}
		}


	}


	public int verifyFloor(int floor) {


		if(floor>StaticValues.ELEVATOR_END_FLOOR  || floor < StaticValues.ELEVATOR_START_FLOOR) {
			System.out.println(" ENTER VALID FLOOR NUMBER BTWN "+ StaticValues.ELEVATOR_START_FLOOR+"  -  "+StaticValues.ELEVATOR_END_FLOOR);
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			return verifyFloor(value);
		}


		return floor;
	}

}
