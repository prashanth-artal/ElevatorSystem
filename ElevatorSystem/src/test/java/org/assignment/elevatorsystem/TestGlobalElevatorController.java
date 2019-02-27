package org.assignment.elevatorsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assignment.elevatorsystem.util.StaticValues;
import org.junit.Before;
import org.junit.Test;

public class TestGlobalElevatorController {

	 ElevatorController impl;
	
	@Before
	public void setUp() {
		
		/* create elevator object */
		Elevator e1 = new ElevatorImplementation(1,StaticValues.ELEVATOR_START_FLOOR,StaticValues.ELEVATOR_END_FLOOR,10);
		
		/* create elevator list and add elevator object to the list*/
		List<Elevator> elevatorList = new ArrayList<Elevator>();
		elevatorList.add(e1);
		
		/* create elevator controller object*/
		impl  =new GlobalElevatorController(elevatorList);
		
	}
	
	@Test
	public void testElevatorControllerImplementation() {
		
		/* Check if the elevator controller is not null */
		 assertNotNull(impl);		 
	}
	
	
	@Test
	public void testAddPickUp() {
		
		/* Move Up signal from 2nd floor*/
		int signalFloor =2;
		impl.addPickup("moveUp", signalFloor);
		
		List<Elevator> ElevatorList = impl.getElevators();
		for(Elevator elevator :ElevatorList) {
			
			/* based on the  current position of elevator it moves next */
			elevator.moveNext();		
			
			/* Check if the elevator current floor is signal floor */
			assertEquals("Failed poll", signalFloor, elevator.getCurrentFloor());
		}

		
		/* No Change in signal floor*/
        impl.addPickup("moveUp", signalFloor);	
	
		for(Elevator elevator :ElevatorList) {
			
			elevator.moveNext();	
			
			/* Elevator doesn't move as signal floor is elevator's current floor*/
			assertEquals("Failed poll", signalFloor,	elevator.getCurrentFloor());
		}
		
		/* Move Up signal from 10th floor*/
		signalFloor = 10;
        impl.addPickup("moveUp", signalFloor);	
		
		for(Elevator elevator :ElevatorList) {
			
			/* based on the  current position of elevator it moves next position */
			elevator.moveNext();		
			
			/* Check if the elevator current floor is signal floor */
			assertEquals("Failed poll", 10,	elevator.getCurrentFloor());
		}
		
		/* Move Down signal from 5th floor*/
		signalFloor = 5;
        impl.addPickup("moveDown", signalFloor);	
		
		for(Elevator elevator :ElevatorList) {
			
			/* based on the  current position of elevator it moves next position */
			elevator.moveNext();		
			
			/* Check if the elevator current floor is signal floor */
			assertEquals("Failed poll", 5,	elevator.getCurrentFloor());
		}
		
	}
	
	/* Verify Floor Number range mentioned in util package staticvalues class */
	@Test
	public void testVerifyFloor() {
		
		/* floor number is 0*/
		int floorNumber = 0;
		/* verify 0 is in the specified range */
		int verifiedFloorNumber = impl.verifyFloorRange(floorNumber);		
	
		assertEquals("Failed poll", verifiedFloorNumber,0);
		
		/* floor number is 70*/
		floorNumber = 70;
		/* verify 70 is in the specified range */
		verifiedFloorNumber = impl.verifyFloorRange(floorNumber);
		assertEquals("Failed poll", verifiedFloorNumber,3);
		
	}
	
	
	
	
}
