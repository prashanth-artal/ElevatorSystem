package org.assignment.elevatorsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

import org.assignment.elevatorsystem.service.Main;

public class ElevatorImplementation implements Elevator,Runnable{

	private final int elevatorId;
	private final int minFloor;
	private final int maxFloor;
	private final int maxCapacity;
	private int currentFloor;
	
	BlockingDeque<Integer> destinationQueue;
	Writer output;


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
		//moveNext();
	}

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

//		if (currentFloor == destination) {
			destinationQueue.poll();
//		}

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

	public boolean QContains(int current) {
		if(destinationQueue.contains(current)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void stop() {
		
	}
	public void moveUp(int destination) {

		state = State.UP;
		while(currentFloor<destination) {
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
		writeToFile("\n\nReached Floor:"+destination);
		writeToFile("\n\t\t\tOPENING DOOR!!!!");
		writeToFile("\n\t\t\tCLOSING DOOR!!!!");

	}

	public synchronized void writeToFile(String msg)  {
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


	public void moveDown(int destination) {

		state = State.DOWN;
		System.out.println(state);

		while(currentFloor>destination) {
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

		writeToFile("\n\nReached floor:"+destination);
		writeToFile("\n\t\t\tOPENING DOOR!!!!");
		writeToFile("\n\t\t\tCLOSING DOOR!!!!");


	}


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
