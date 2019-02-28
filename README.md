# ElevatorSystem
The Basic Elevator System which moves vertically up and down

Steps to Run the Programme:

1. Download the maven project "ElevatorSsystem".
2. Import to IDE.
3. Open the Project ElevatorSystem
4. There is an "Output" folder. Go to "Output"  Folder and delete "1.txt" file.(Make sure to delete this every time you run the programme)
5. Goto src/main/java folder
6. You will see 3 packages but make sure to move to org.assignment.elevatorsystem.service
7. Open Main.java
8. Right-click and run as java application.
9. You need to give inputs through console.
10. To view the Output at time then goto "Output" Folder and view 1.txt file.


Test:

Unit Test: I have included unit tests under src/test/java folder.

Functional Test: I have manually tested the functionality by considering continues steps of following actions

                 MoveUp     i) 2 --------------------------------------> 20
			           MoveUp    ii)               7 -----------------> 16
                 MoveDown iii)            6 <---------- 10
                 MoveDown  iv) 2 <-------------------------- 13
                 MoveUp     v)                                                              30 ------> 35
                 MoveUp    vi)                                    16 ----------------> 25 
                 MoveUp    vi)                          10 --------------> 20
                 MoveUp   vii)                   7------------> 15
 
 and verified the stops in 1.txt file which matches the implementation algorithm.
