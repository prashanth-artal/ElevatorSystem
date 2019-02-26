package org.assignment.elevatorsystem;

import java.util.List;
import java.util.Scanner;

import org.assignment.elevatorsystem.util.StaticValues;

public class GlobalElevatorController implements ElevatorController{

	private List<Elevator> elevators;

	public GlobalElevatorController(List<Elevator> elevators) {
		this.elevators   =   elevators;
	}

	
	public void addPickup(String direction,int floor) {

		boolean chkElevatorAssigned = false;

		for (Elevator elevator : elevators) {

			if(elevator.getState() == State.UP && direction.equals("moveUp") ) {
				if(floor<elevator.getCurrentFloor()) {
					boolean currDirection = true;
					while(!elevator.isIdle()) {
						if(currDirection) {
							System.out.println("Wait!! Elevator is moving "+elevator.getState()+" and you chose "+floor);
							currDirection = false;
						}
					}
					elevator.queueDestination(floor);
					chkElevatorAssigned = true;
				}

				else if(!elevator.isIdle() && elevator.isInPath(floor)) {
					elevator.prependDestination(floor);
					chkElevatorAssigned = true;

				}
				else {
					elevator.queueDestination(floor);
					chkElevatorAssigned = true;
				}


			}

			if(elevator.getState() == State.DOWN &&  direction.equals("moveDown")  ) {

				if(floor > elevator.getCurrentFloor()) {
					boolean currDirection = true;
					while(!elevator.isIdle()) {
						if(currDirection) {
							System.out.println("Wait!! Elevator is moving "+elevator.getState()+" and you chose "+floor);
							currDirection = false;
						}
					}
					elevator.queueDestination(floor);
					chkElevatorAssigned = true;
				}
				else if(!elevator.isIdle() && elevator.isInPath(floor)) {
					elevator.prependDestination(floor);
					chkElevatorAssigned = true;

				}
				else {
					elevator.queueDestination(floor);
					chkElevatorAssigned = true;
				}


			}
//			System.out.println(elevator.getDestinationQueue());

		}


		for (Elevator elevator : elevators) {

			if(!chkElevatorAssigned) {
				boolean currDirection = true;
				while(!elevator.isIdle()) {
					if(currDirection) {
						System.out.println("Wait!! Elevator is moving "+elevator.getState()+" and you chose to move "+direction);
						currDirection = false;
					}
					continue;
				}
				elevator.queueDestination(floor);
//				System.out.println(elevator.getDestinationQueue());
				break;
			}

		}


	}


	public int verifyFloor(int floor) {


		if(floor>StaticValues.ELEVATOR_END_FLOOR  || floor < StaticValues.ELEVATOR_START_FLOOR) {
			System.out.println(" ENTER VALID FLOOR NUMBER BETWN "+ StaticValues.ELEVATOR_START_FLOOR+"  -  "+StaticValues.ELEVATOR_END_FLOOR);
			Scanner sc = new Scanner(System.in);
			int value = sc.nextInt();
			//			sc.close();
			return verifyFloor(value);
		}

		return floor;
	}
	
	
	public List<Elevator> getElevators(){
		
		return elevators;
		
	}

}
