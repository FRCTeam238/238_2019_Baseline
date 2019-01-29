package org.usfirst.frc.team238.core;

import java.util.HashMap;


import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.commands.CommandStopEverything;
import org.usfirst.frc.team238.commands.CommandTestShiftHigh;



public class OperatorCmdFactory {

	
	// CommandStopEverything commandStopEverything;

	// CommandRunTrajectory commadRunTrajectory;
	
	// CommandRetractWrist commandRetractWrist;
	
	// CommandExtendWrist commandExtendWrist;
	
	// CommandElevatorUp commandElevatorUp;
	
	// CommandElevatorDown commandElevatorDown;
	
	// CommandElevatorBottomHeight commandElevatorBottomHeight;
	
	// CommandElevatorScaleHeight commandElevatorScaleHeight;
	
	// CommandElevatorSwitchHeight commandElevatorSwitchHeight;
	
	// CommandIntakeStraightOutAngle commandIntakeStraightOutAngle;
	
	// CommandWristAngle commandWristAngle;
	
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
		 
		 
		cmd = new CommandTestShiftHigh(theRobot.myTestCoreObject);
		operatorCommands.put(CrusaderCommon.OPERATOR_TRIGGER, cmd);
		
	  
		cmd  = new CommandStopEverything(theRobot.myTestCoreObject);
		operatorCommands.put(CrusaderCommon.stopEverythingInput, cmd);
		
		return operatorCommands;
		
	}

	
}
