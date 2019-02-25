package org.assignment.elevatorsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestElevatorImplementation {
	
	private ElevatorImplementation impl;
	
	@Before
	public void setUp() {
		impl = new ElevatorImplementation(1, 0, 5, 1);
	}
    
	@Test
	public void testElevatorImplementation() {
	    assertNotNull(impl);
	}
   
	@Test
	public void testQueueDestination() {
		impl.queueDestination(2);
		assertEquals("Failed poll", 2L, (long)impl.getDestinationQueue().poll());
	}
	
	
	@Test
	public void testQueueContains() {
		impl.queueDestination(2);
		impl.queueDestination(1);
		assertEquals("Floor not exist", true, impl.QContains(2));
		assertEquals("Expected 2nd floor", 2, (long)impl.getDestinationQueue().poll());
		assertTrue(!impl.QContains(2));
	}
	
	
}
