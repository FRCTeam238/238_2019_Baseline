package org.usfirst.frc.team238.core;

import java.util.HashMap;


import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.IntakeWrist;
import org.usfirst.frc.team238.robot.TestCoreObject;



import RealBot.TrajectoryIntepreter;

import org.usfirst.frc.team238.commands.CommandStopEverything;
import org.usfirst.frc.team238.commands.CommandTestShiftHigh;
import org.usfirst.frc.team238.commands.CommandWristAngle;
import org.usfirst.frc.team238.lalaPaths.Straight140;
import org.usfirst.frc.team238.lalaPaths.goStraight;
import org.usfirst.frc.team238.lalaPaths.leftScale;
import org.usfirst.frc.team238.lalaPaths.leftSwitch;
import org.usfirst.frc.team238.lalaPaths.rightSwitch;
import org.usfirst.frc.team238.commands.CommandIntakeStraightOutAngle;
import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.commands.CommandElevatorBottomHeight;
import org.usfirst.frc.team238.commands.CommandElevatorDown;
import org.usfirst.frc.team238.commands.CommandElevatorScaleHeight;
import org.usfirst.frc.team238.commands.CommandElevatorSwitchHeight;
import org.usfirst.frc.team238.commands.CommandElevatorUp;
import org.usfirst.frc.team238.commands.CommandExtendWrist;
import org.usfirst.frc.team238.commands.CommandRetractWrist;
import org.usfirst.frc.team238.commands.CommandRunTrajectory;
import org.usfirst.frc.team238.commands.CommandTestShiftHigh;



public class OperatorCmdFactory {

	
	CommandStopEverything commandStopEverything;

	CommandRunTrajectory commadRunTrajectory;
	
	CommandRetractWrist commandRetractWrist;
	
	CommandExtendWrist commandExtendWrist;
	
	CommandElevatorUp commandElevatorUp;
	
	CommandElevatorDown commandElevatorDown;
	
	CommandElevatorBottomHeight commandElevatorBottomHeight;
	
	CommandElevatorScaleHeight commandElevatorScaleHeight;
	
	CommandElevatorSwitchHeight commandElevatorSwitchHeight;
	
	CommandIntakeStraightOutAngle commandIntakeStraightOutAngle;
	
	CommandWristAngle commandWristAngle;
	
	HashMap<Integer, Command> operatorCommands;
	
	
	public void init()
	{
	
	  operatorCommands = new HashMap<Integer, Command>(50);
	
	}
	
	/**
	 * Operator controls get binded here. Assigning a series of buttons and commands to a HashMap
	 * @param driveTrain
	 * @param theNavigation
	 * @param theVision
	 * @param theFuelHandler
	 * @param theClimber
	 * @param theRobot
	 * @return
	 */
	public HashMap<Integer, Command> createOperatorCommands(Robot theRobot)
	{
		//AbstractCommand cmd;
		 
		 CommandTestShiftHigh myCmdTestShiftHigh;
		 myCmdTestShiftHigh = new CommandTestShiftHigh(theRobot.myTestCoreObject);
		 operatorCommands.put(CrusaderCommon.OPERATOR_TRIGGER, myCmdTestShiftHigh);
		//Inputs get defined in CrusaderCommon
		Integer[] multiButtonTestInput = {1,2,3,4,5}; //Test : Button input
	  
		//Create command objects, passing objects into each of them
		//commandStopEverything = new CommandStopEverything();     //<-------------------------------- EXAMPLE
	
		CommandStopEverything cmd;
		cmd  = new CommandStopEverything(theRobot.myTestCoreObject);
		operatorCommands.put(CrusaderCommon.stopEverythingInput, cmd);
		
		return operatorCommands;
		
	}

	
}
