package org.assignment.elevatorsystem;

import java.util.concurrent.BlockingDeque;



/*
    Elevator interface with basic functions of an elevator
    which can be implemented for any type Elevator class 
 */
public interface Elevator {
	
	/* returns the ground floor number of the building*/
	int getMinFloor();
	/* returns the top floor number of the building */
	int getMaxFloor();
	/* returns the current position of the elevator in the building*/
	int getCurrentFloor();
	/* Data structure which consumes the floor number provided by the external signal*/
	BlockingDeque<Integer> getDestinationQueue();
	/* Function to moveup to the destination floor*/
	void moveUp(int destination);
	/* Function to movedon to the destination floor*/
	void moveDown(int destination);
	/* Function which decides to move up or move down based on the current floor of the elevator */
	void moveNext();
	/* Function to insert the floor number to the head of the blocking deque*/
	void prependDestination(int floor);
	/* Function to insert the floor number to rail of the blocking deque*/
	void queueDestination(int floor);
	/* Function to check whether the floor is in the path of current floor and destination floor*/
	boolean isInPath(int floor);
	/* to check the elevator's capacity is full*/
	boolean isFull();
	/* to check the elevator's current state is idle */
	boolean isIdle(); 
	/* returns the current state of elevator */
	public ElevatorState getState();
//	void run();
}
