package org.assignment.elevatorsystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


public class ElevatorImplementation implements Elevator,Runnable{

	private final int elevatorId;
	private final int minFloor;
	private final int maxFloor;
	private final int maxCapacity;
	private int currentFloor;
	
	private BlockingDeque<Integer> destinationQueue;
	private Writer output;
	private State state;

	public ElevatorImplementation(int elevatorId,int minFloor, int maxFloor, int maxCapacity) {
		
		this.elevatorId =elevatorId;
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		this.maxCapacity = maxCapacity;
		currentFloor = 0;
		destinationQueue	= new LinkedBlockingDeque<>(300);

	}


	public int getElevatorId() {
		return elevatorId;
	}


	public int getMinFloor() {
		return minFloor;
	}
	
	public int getMaxCapacity() {
		return maxCapacity;
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

   /* Function to insert floor to Deque*/
	public void queueDestination(int floor) {
		//O(N)
		/* Insert floor only if not present in Deque*/
		if (!QContains(floor)) {
			destinationQueue.add(floor);
		}
	}

   /* Function to insert floor number to head of Deque*/
	public void prependDestination(int floor) {
		destinationQueue.addFirst(floor);
	}
     
	
	public void moveNext() {
		if (destinationQueue.isEmpty()) {
			return;
		}
		
		int destination = destinationQueue.peek();
		/* If current floor of elevator is less than destination then
		 * Elevator must move up */		
		if (currentFloor < destination) {
			moveUp(destination);
		} 	/* If current floor of elevator is greater than destination then
		 * Elevator must move down */		
		else if (currentFloor > destination) {
			moveDown(destination);
		}

	    /* Remove Element from queue after reach*/
		destinationQueue.poll();

	}


	public void wait3seconds() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void wait2seconds() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
    
	/* Check whether floor present in already present in Queue */
	public boolean QContains(int floor) {
		if(destinationQueue.contains(floor)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/* Function to move up to the floor*/
	public void moveUp(int floor) {

		state = State.UP;
		while(currentFloor<floor) {
			
			if(QContains(currentFloor)) {
				writeToFile("\n\nReached Floor:"+currentFloor);
				writeToFile("\n\t\t\tOPENING DOOR!!!!");
				writeToFile("\n\t\t\tCLOSING DOOR!!!!");
				destinationQueue.remove(currentFloor);
			}
			
			writeToFile(new Date()+"Elevator No: "+elevatorId+"  Crossing "+currentFloor);
			currentFloor++;
			wait3seconds();
		}
		
		writeToFile("\n\nReached Floor:"+floor);
		writeToFile("\n\t\t\tOPENING DOOR!!!!");
		writeToFile("\n\t\t\tCLOSING DOOR!!!!");

	}

	public void writeToFile(String msg)  {
		String fileName = "..\\ElevatorSystem\\Output\\"+elevatorId+".txt";
		PrintWriter printWriter = null;
		File file = new File(fileName);
		try {
			if (!file.exists()) file.createNewFile();
			printWriter = new PrintWriter(new FileOutputStream(fileName, true));
			printWriter.write("\n"+msg);
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}

    /* Function to move down to the floor */
	public void moveDown(int floor) {

		state = State.DOWN;
		System.out.println(state);

		while(currentFloor>floor) {
			
			if(QContains(currentFloor)) {
				writeToFile("\n\nReached Floor:"+currentFloor);
				writeToFile("\n\t\t\tOPENING DOOR!!!!");
				writeToFile("\n\t\t\tCLOSING DOOR!!!!");
				destinationQueue.remove(currentFloor);
			}
			
			writeToFile(new Date()+"Elevator No: "+elevatorId+"  Crossing "+currentFloor);
			currentFloor--;
			wait3seconds();
			
		}

		writeToFile("\n\nReached floor:"+floor);
		writeToFile("\n\t\t\tOPENING DOOR!!!!");
		writeToFile("\n\t\t\tCLOSING DOOR!!!!");


	}

     /*  Function to check if the  request floor is in between current floor and destination */
	public boolean isInPath(int floor) {
		
		if (destinationQueue.isEmpty()) {
			return false;
		}
		
		int destination = destinationQueue.peek();		
		boolean downTest = (floor <= currentFloor && floor >= destination);		
		boolean upTest = (floor >= currentFloor && floor <= destination);		
		System.out.println(currentFloor+" "+floor+" "+destination+" "+downTest+" "+upTest);		
		return (floor >= currentFloor && floor <= destination) || (floor <= currentFloor && floor >= destination);
		
	}


	public boolean isFull() {
		//would use maxCapacity here
		return false;
	}

    /* Check if the Elevator is Idle*/
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
	
	public void setCurrentFloor(int floor) {
		this.currentFloor = floor;
	}


}
