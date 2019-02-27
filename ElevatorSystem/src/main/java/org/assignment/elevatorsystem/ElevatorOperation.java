package org.assignment.elevatorsystem;

import java.util.Scanner;

public interface ElevatorOperation {

	
	void moveUp(Scanner sc,ElevatorController controller);
	void moveDown(Scanner sc,ElevatorController controller);

}
