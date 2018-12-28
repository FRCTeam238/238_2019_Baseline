package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.commands.CommandShiftHigh;
import org.usfirst.frc.team238.commands.CommandShiftLow;
import org.usfirst.frc.team238.commands.CommandTankDrive;
import org.usfirst.frc.team238.commands.NoDriverCommand;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.RobotDrive;

public class DriverCommandFactory 
{

	NoDriverCommand NoDriveCommand;
	CommandShiftHigh commandShiftHigh;
	CommandShiftLow commandShiftLow;
	
	HashMap <Integer, Command> driverLeftCommands;
	HashMap <Integer, Command> driverRightCommands;
	HashMap <Integer, Command> driverCommands;
	
	public void init()
	{
		driverLeftCommands = new HashMap<Integer, Command>(10);
		driverRightCommands = new HashMap<Integer, Command>(10);
		driverCommands = new HashMap<Integer, Command>(10);
	}
	
	/**
	 * Creates commands for the left driver joystick
	 * @param driveTrain
	 * @param myNavigation
	 * @param myVision
	 * @param myFuelHandler
	 * @return
	 */
	public HashMap<Integer, Command> createDriverLeftCommands(Drivetrain driveTrain, 
			Navigation myNavigation)
	{
		
		NoDriveCommand = new NoDriverCommand(driveTrain, true);
		
		driverLeftCommands.put(0, NoDriveCommand);
		
		commandShiftHigh = new CommandShiftHigh(driveTrain);
		
		driverLeftCommands.put(1, commandShiftHigh);

		
		//cmdReverseClimb = new CommandReverseClimber(theClimber);
		
		//driverLeftCommands.put(10, cmdReverseClimb);
		
		return driverLeftCommands;
		
	}

	/**
	 * Creates commands for the right driver joystick
	 * @param driveTrain
	 * @param myNavigation
	 * @param myVision
	 * @param myFuelHandler
	 * @return
	 */
	public HashMap<Integer, Command> createDriverRightCommands(Drivetrain driveTrain, 
			Navigation myNavigation)
	{
	  
		NoDriveCommand  = new NoDriverCommand(driveTrain, false);
		driverRightCommands.put(0, NoDriveCommand);
		
		//commandShiftLow = new CommandShiftLow(driveTrain);
		//driverRightCommands.put(1, commandShiftLow);

		return driverRightCommands;
		
	}
	
	/**
	 * Creates a command for both driver joysticks
	 * (In this case, enables the driver to actually drive the robot)
	 * @param myRobotDrive
	 * @return
	 */
	public HashMap<Integer, Command> createDriverCommands(Drivetrain theDT)
	{
		
	 	CommandTankDrive cmdToDriveTheRobot = new CommandTankDrive(theDT);
		
		driverCommands.put(0, cmdToDriveTheRobot);
		
		return driverCommands;
		
	}
}
