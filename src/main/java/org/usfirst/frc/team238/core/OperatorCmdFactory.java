package org.usfirst.frc.team238.core;

import java.util.HashMap;


import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.commands.CommandElevatorBottomHeight;
import org.usfirst.frc.team238.commands.CommandElevatorDown;
import org.usfirst.frc.team238.commands.CommandElevatorScaleHeight;
import org.usfirst.frc.team238.commands.CommandElevatorSwitchHeight;
import org.usfirst.frc.team238.commands.CommandElevatorUp;
import org.usfirst.frc.team238.commands.CommandExtendWrist;
import org.usfirst.frc.team238.commands.CommandIntakeIn;
import org.usfirst.frc.team238.commands.CommandIntakeOut;
import org.usfirst.frc.team238.commands.CommandIntakeOutFast;
import org.usfirst.frc.team238.commands.CommandRetractWrist;
import org.usfirst.frc.team238.commands.CommandShoulderAngle;
import org.usfirst.frc.team238.commands.CommandStopEverything;
import org.usfirst.frc.team238.commands.CommandTestShiftHigh;
import org.usfirst.frc.team238.commands.CommandWristAngle;



public class OperatorCmdFactory {

	
	CommandStopEverything commandStopEverything;

	//CommandRunTrajectory commadRunTrajectory;
	
	CommandRetractWrist commandRetractWrist;
	
	CommandExtendWrist commandExtendWrist;
	
	CommandElevatorUp commandElevatorUp;
	
	CommandElevatorDown commandElevatorDown;
	
	CommandElevatorBottomHeight commandElevatorBottomHeight;
	
	CommandElevatorScaleHeight commandElevatorScaleHeight;
	
	CommandElevatorSwitchHeight commandElevatorSwitchHeight;
	
	//CommandIntakeStraightOutAngle commandIntakeStraightOutAngle;
	
	CommandWristAngle commandWristAngle;
	
	HashMap<Integer, Command> operatorCommands;
	
	
	public void init()
	{
	
	  operatorCommands = new HashMap<Integer, Command>(50);
	
	}
	
	/**
	 * Operator controls get binded here. Assigning a series of buttons and commands to a HashMap
	 * 
	 * @param theRobot
	 * @return
	 */
	public HashMap<Integer, Command> createOperatorCommands(Robot theRobot)
	{
		AbstractCommand cmd;
		 
        //Create command objects, passing objects into each of them
         
        cmd  = new CommandStopEverything(theRobot);
        operatorCommands.put(CrusaderCommon.stopEverythingInput, cmd);
        
		// cmd = new CommandRetractWrist(theRobot.myShoulder);
		// operatorCommands.put(CrusaderCommon.OPERATOR_TRIGGER, cmd);

        

        cmd = new CommandShoulderAngle(theRobot.myShoulder);
        operatorCommands.put(1, cmd);
        operatorCommands.put(2, cmd);        
        operatorCommands.put(4, cmd);
        	  
	  
        cmd = new CommandIntakeIn(theRobot.myShoulder);
        operatorCommands.put(5, cmd);
      
        cmd = new CommandIntakeOut(theRobot.myShoulder);
        operatorCommands.put(6, cmd);
       
         cmd= new CommandExtendWrist(theRobot.myShoulder);
        operatorCommands.put(22, cmd);

        cmd = new CommandRetractWrist(theRobot.myShoulder);
        operatorCommands.put(23, cmd);


        cmd = new CommandElevatorUp(theRobot.myElevator);
        operatorCommands.put(21, cmd);

        cmd = new CommandElevatorDown(theRobot.myElevator);
        operatorCommands.put(20, cmd);

        cmd = new CommandElevatorBottomHeight(theRobot.myElevator);
        operatorCommands.put(24, cmd);

        cmd = new CommandElevatorScaleHeight(theRobot.myElevator);
        operatorCommands.put(26, cmd);

        cmd = new CommandElevatorSwitchHeight(theRobot.myElevator);
        operatorCommands.put(25, cmd);
        
      
      
      
        //cmd = new CommandWristManualOverride(theRobot.myShoulder);
        //operatorCommands.put(7, cmd);
        //operatorCommands.put(8, cmd);
      //commandIntakeStraightOutAngle = new CommandIntakeStraightOutAngle(intake);

      cmd = new CommandIntakeOutFast(theRobot.myShoulder);
      operatorCommands.put(28, cmd);
	  
	  
	  //Assigns all command arrays and their specific inputs to the HashMap
	  
        //operatorCommands.put(9, commadRunTrajectory);

      //Shift CLimb may be used for level 3
	  //operatorCommands.put(27, commandShiftClimb);
      
		return operatorCommands;
		
	}

	
}
