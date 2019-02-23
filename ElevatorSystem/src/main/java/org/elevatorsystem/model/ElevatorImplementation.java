package org.elevatorsystem.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.elevatorsystem.interfaces.Elevator;
import org.elevatorsystem.interfaces.State;



public class ElevatorImplementation implements Elevator,Runnable{

	private final int elevatorId;
	private final int minFloor;
	private final int maxFloor;
	private final int maxCapacity;
	private int currentFloor;
	BlockingDeque<Integer> destinationQueue;


	State state;

	public ElevatorImplementation(int elevatorId,int minFloor, int maxFloor, int maxCapacity) {
		this.elevatorId =elevatorId;
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		this.maxCapacity = maxCapacity;
		currentFloor = 0;
		destinationQueue	= new LinkedBlockingDeque<>(20);
	}


	public int getElevatorId() {
		return elevatorId;
	}


	public int getMinFloor() {
		return minFloor;
	}


	public int getMaxFloor() {
		return maxFloor;
	}


	public int getCurrentFloor() {
		return currentFloor;
	}


	public BlockingDeque<Integer> getDestinationQueue() {
		return destinationQueue;
	}


	public void queueDestination(int floor) {
		//O(N)
		if (!destinationQueue.contains(floor)) {
			destinationQueue.add(floor);
		}
	}


	public void prependDestination(int floor) {
		destinationQueue.addFirst(floor);
	}

	//	private final Lock queueLock = new ReentrantLock();
	public void moveNext() {
		if (destinationQueue.isEmpty()) {
			return;
		}
		int destination = destinationQueue.peek();
		if (currentFloor < destination) {
			moveUp(destination);
		} else if (currentFloor > destination) {
			moveDown(destination);
		}

		if (currentFloor == destination) {
			destinationQueue.poll();
		}


		//		queueLock.unlock();
	}


	public void moveUp(int destination) {

		state = State.UP;
		while(currentFloor<destination) {
			System.out.println("Elevator No: "+elevatorId+"  Entering "+currentFloor);
			currentFloor++;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(" reached : "+currentFloor);
		//		state = State.IDLE;

	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	public void moveDown(int destination) {

		state = State.DOWN;
		System.out.println(state);

		while(currentFloor>destination) {
			System.out.println("Elevator No: "+elevatorId+"  Entering "+currentFloor);
			currentFloor--;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println(" reached : "+currentFloor);
		//		state = State.IDLE;


	}


	public boolean isInPath(int floor) {
		if (destinationQueue.isEmpty()) {
			return false;
		}
		int destination = destinationQueue.peek();
		return (floor >= currentFloor && floor <= destination) || (floor <= currentFloor && floor >= destination);
	}


	public boolean isFull() {
		//would use maxCapacity here
		return false;
	}


	public boolean isIdle() {
		if(destinationQueue.isEmpty()) {
			state = State.IDLE;
		}
		return destinationQueue.isEmpty();
	}


	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			if(destinationQueue.isEmpty()) {
				continue;
			}
			moveNext();
		}
	}


}