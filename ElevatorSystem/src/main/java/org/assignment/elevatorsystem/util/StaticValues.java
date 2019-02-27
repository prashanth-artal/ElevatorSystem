package org.assignment.elevatorsystem.util;

public class StaticValues {
   
	 public static final int  ELEVATOR_START_FLOOR   = 0;
	 public static final int  ELEVATOR_END_FLOOR     = 50;
	 public static final int  ELEVATOR_CAPACITY      = 10;
	 public static final String OPTIONS = "Press \n 1. moveUp \n 2. moveDown \n 3. To exit";
	 public static final String CURRENT_FLOOR=  "Enter your current  floor :";
	 public static final String INTEREST_FLOOR= "Enter your interest floor :";
     public static final String MV_UP_INT_LESS_CURR= "You chose to move up!!\n Interest floor must be greater than Current floor \n Re-enter your interest floor :";
     public static final String MV_UP_INT_GT_CURR= "You chose to move down!!\n Interest floor must be less than Current floor \n Re-enter your interest floor :";
     public static final String MOVE_UP = "moveUp";
	 public static final String MOVE_DOWN = "moveDown";
	 
}
