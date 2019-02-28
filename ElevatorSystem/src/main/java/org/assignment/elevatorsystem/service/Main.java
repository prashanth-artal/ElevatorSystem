package org.assignment.elevatorsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.assignment.elevatorsystem.Elevator;
import org.assignment.elevatorsystem.ElevatorController;
import org.assignment.elevatorsystem.ElevatorOperation;
import org.assignment.elevatorsystem.ElevatorOperationImpl;
import org.assignment.elevatorsystem.GlobalElevatorController;
import org.assignment.elevatorsystem.util.StaticValues;
import org.assignment.elevatorsystem.util.Verify;


/*

   ELEVATOR ALGORITHM : The elevator algorithm, a simple algorithm by which a single elevator can decide where to stop, is summarized as follows:

    1.Continue traveling in the same direction while there are remaining
      requests in that same direction.
    2.If there are no further requests in that direction, then stop and
      become idle, or change direction if there are requests in the 
      opposite direction.[Wikipedia]


   TEST CASES:

   TESTCASE 1.    
                   MoveUp  i)  2 ---------> 5
                  MoveUp  ii)                 6 -------> 15


   TESTCASE 2.  
                   MoveUp  i)  2 --------------------> 13
                  MoveUp  ii)                7 -----------> 16

   TESTCASE 3.   
                   MoveUp  i)  2 ------------------------------------------------> 25
                   MoveUp  ii)                 9 ----------------------> 20

   TESTCASE 4.  
                 MoveUp     i) 2 --------------------------------------> 20
                 MoveUp    ii)                7 ----------> 16     
                 MoveDown iii)          4 <------ 10

   TESTCASE 5. 
                 MoveUp     i) 2 --------------------------------------> 20
			     MoveUp    ii)               7 -----------------> 16
                 MoveDown iii)            6 <---------- 10
                 MoveDown  iv) 2 <-------------------------- 13
                 MoveUp     v)                                                              30 ------> 35
                 MoveUp    vi)                                    16 ----------------> 25 
                 MoveUp   vii)                          10 --------------> 20
                 MoveUp  viii)                   7------------> 15

 */


public class Main {



	public static void main(String[] args) {

		/* Create Elevator Object with unique id */
		Elevator elevator_1 = new Elevator( 1 ,StaticValues .ELEVATOR_END_FLOOR, StaticValues .ELEVATOR_START_FLOOR, StaticValues .ELEVATOR_CAPACITY);

		/* Create List of Elevator Object*/
		List<Elevator> elevatorList = new ArrayList<Elevator>();

		/* add all Elevator objects to Elevator List*/
		elevatorList.add(elevator_1);

		/* Create Elevator Controller Object*/
		ElevatorController controller_1= new GlobalElevatorController(elevatorList);

		/* Start the Elevator Thread to run independetly*/
		Thread th1 = new Thread(elevator_1);
		th1.start();

        
        ElevatorOperation operation = new ElevatorOperationImpl();
        
		while(true) {

			/* Choose options to either move up or move down*/
			System.out.println( StaticValues .OPTIONS );
			Scanner sc  = new Scanner(System.in);

			/* Verify the direction is valid Integer*/
			int direction = Verify .verifyDirectionIsInteger(sc);

			/* If direction 1 then Elevator ready to move up*/
			if(direction == 1) {
				
				operation.moveUp(controller_1);
				
			}
			/* If direction 2 then Elevator ready to move down*/
			else if(direction == 2) {

				operation.moveDown(controller_1);

			}
			else {
				break;
			}

		}


		try {
			th1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
