package org.assignment.elevatorsystem;

import java.util.concurrent.BlockingDeque;



public interface Elevator {
	
	int getMinFloor();
	int getMaxFloor();
	int getCurrentFloor();
	BlockingDeque<Integer> getDestinationQueue();
	void moveUp(int destination);
	void moveDown(int destination);
	void moveNext();
	void prependDestination(int floor);
	void queueDestination(int floor);
	boolean isInPath(int floor);
	boolean isFull();
	boolean isIdle(); 
	public State getState();
//	void run();
}
