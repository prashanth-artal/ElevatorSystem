package org.assignment.elevatorsystem;

import java.util.List;

public interface ElevatorController {
	
	void addPickup(String up,int floor);
    int verifyFloorRange(int floor);
    List<Elevator> getElevators();
}
