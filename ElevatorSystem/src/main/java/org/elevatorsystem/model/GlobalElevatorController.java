package org.elevatorsystem.model;

import java.util.List;

import org.elevatorsystem.interfaces.Elevator;
import org.elevatorsystem.interfaces.ElevatorController;
import org.elevatorsystem.interfaces.State;

public class GlobalElevatorController implements ElevatorController{

	private List<Elevator> elevators;

	public GlobalElevatorController(List<Elevator> elevators) {
		this.elevators = elevators;
	}

	/**
	 * Represents pick up (origin floor).  Attempt to delegate request immediately, but if no elevators presently
	 * available, then add to controller's queue, and attempt again during step().
	 *
	 * @param floor assumption: the same pickup floor will not be requested while it's being processed.  Logic should
	 *             be handled by hypothetical button class.
	 */

	public void addPickup(String up,int floor) {

		boolean chkElevatorAssigned = false;
		for (Elevator elevator : elevators) {

			System.out.println(elevator.getDestinationQueue());
			if(elevator.getState() == State.UP && up.equals("moveUp") ) {
				if(!elevator.isIdle() && elevator.getDestinationQueue().peek() > floor) {
					elevator.moveUp(floor);
					chkElevatorAssigned = true;
				}
				if(!elevator.isIdle() && elevator.getDestinationQueue().peek() < floor) {
					elevator.queueDestination(floor);
					chkElevatorAssigned = true;
				}
			}

			if(elevator.getState() == State.DOWN &&  up.equals("moveDown")  ) {

				if(!elevator.isIdle() && elevator.getDestinationQueue().peek() < floor) {
					elevator.moveDown(floor);
					chkElevatorAssigned = true;
				}
				if(!elevator.isIdle() && elevator.getDestinationQueue().peek() > floor) {
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


}
