package org.assignment.elevatorsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.assignment.elevatorsystem.util.StaticValues;
import org.junit.Before;
import org.junit.Test;

public class TestElevatorImplementation {
	
	private Elevator impl;
	
	@Before
	public void setUp() {
		impl = new Elevator(1, StaticValues.ELEVATOR_START_FLOOR ,StaticValues.ELEVATOR_END_FLOOR, 10);
	}
	
    /* Test Elevator Object Creation*/	
	@Test
	public void testElevatorImplementation() {
		
		/* Check if the object created is not null*/
	    assertNotNull(impl);
	    
	}
   
	/* Test Correctness of data inserted to Queue of an Elevator */	
	@Test
	public void testQueueDestination() {
		
		/* insert value to queue via queueDestinaition function*/
		impl.queueDestination(2);
		
		/* poll the value from queue and compare whether its same as the inserted */
		assertEquals("Failed poll", 2L, (long)impl.getDestinationQueue().poll());
	}
	
	
	/* Test data contains in a deque and correctness of queue operations (FIFO) */
	@Test
	public void testQueueContains() {
		
		/*insert 1st value 2 to deque*/
		impl.queueDestination(2);
		
		/*insert 2nd value 1 to deque*/
		impl.queueDestination(1);
		
		/* check if deque contains  value 2*/
		assertEquals("Floor not exist", true, impl.QContains(2));
		
		/* check if value is 2 if we poll it once*/
		assertEquals("Expected 2nd floor", 2, (long)impl.getDestinationQueue().poll());
		
		/*check if deque still contains value 2 once it is polled*/
		assertTrue(!impl.QContains(2));
		
	}
	
	/* Test Elevator moveUp function*/
	@Test
	public void testMoveUp() {
		
		/* Provide destination floor number */
        int destination = 0;
        
        /*  call move up function*/
		impl.moveUp(destination);
		
		/*compare the current floor with the destination input*/
		assertEquals("Fail to move up",destination ,impl.getCurrentFloor());
		
	}
	
	/* Test Elevator moveDown function*/
	@Test
	public void testMoveDown() {
		
		/* set current floor number */
		impl.setCurrentFloor(5);
		
		/* Provide destination floor number */
		int destination =4;
		
		/* Call move up function */
		impl.moveDown(destination);
		
		/*compare the current floor with the destination input*/
		assertEquals("Fail to move up",destination ,impl.getCurrentFloor());
		
	}
	
	/* Test Elevator moveNext function */
	@Test
	public void testMoveNext() {
		
		/* set destination floor 3 & default current floor is 0*/
		int destination = 3;
		
		/* add destination value to queue */
		impl.queueDestination(destination);
		
		/*call moveNext*/
		impl.moveNext();
		
		/*compare the current floor with the destination input*/
		assertEquals("Fail to move up",destination ,impl.getCurrentFloor());
				
		
		
		
		
		/* set destination floor 4 */
		destination = 4;
		
		/* set current floor is 7 */
		impl.setCurrentFloor(7);
		
		/* add destination value to queue */
		impl.queueDestination(destination);
		
		/*call moveNext*/
		impl.moveNext();
		
		/*compare the current floor with the destination input*/
		assertEquals("Fail to move up",destination ,impl.getCurrentFloor());				
		
	}
	
	/* Test floor in path of current and destination */
	@Test
	public void testInPath() {
		
		/* set destination floor to 4*/
		int destination = 4;
		
		/* set current floor to 7*/
		impl.setCurrentFloor(7);
		
		/* insert destination floor value to queue*/
		impl.queueDestination(destination);
		
		int inputFloor = 5;
		/* check input floor is present in the path of destination and current floor of elevator*/
		assertTrue(impl.isInPath(inputFloor));
		
		
		
		/* set destination floor to 6*/
		destination = 6;
		
		/* set current floor to 0*/
		impl.setCurrentFloor(0);
		
		/* insert destination floor value to queue*/
		impl.queueDestination(destination);
		
		inputFloor = 3;
		/* check input floor is present in the path of destination and current floor of elevator*/
		assertTrue(impl.isInPath(inputFloor));
		
	}
	
	
}
