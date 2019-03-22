package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.commands.CommandShiftHigh;
import org.usfirst.frc.team238.commands.CommandShiftLow;
import org.usfirst.frc.team238.commands.CommandTankDrive;
import org.usfirst.frc.team238.commands.NoDriverCommand;
import org.usfirst.frc.team238.robot.Robot;

public class DriverCommandFactory 
{

	NoDriverCommand NoDriveCommand;
	CommandShiftHigh commandShiftHigh;
	CommandShiftLow commandShiftLow;
    
    Robot theRobot;

    HashMap <Integer, Command> driverCommands;
    HashMap <Integer, Command> driverLeftCommands;
    HashMap <Integer, Command> driverRightCommands;
    
    public DriverCommandFactory( Robot myRobot) {

        driverCommands = new HashMap<Integer, Command>(10);
        driverLeftCommands = new HashMap<Integer, Command>(10);
        driverRightCommands = new HashMap<Integer, Command>(10);
        
        this.theRobot = myRobot;
    }


	
	
	/**
	 * Creates a command for both driver joysticks
	 * (In this case, enables the driver to actually drive the robot)
	 * @param myRobotDrive
	 * @return
	 */
	public HashMap<Integer, Command> createDriverCommands()
	{
		
	 	CommandTankDrive cmdToDriveTheRobot = new CommandTankDrive(theRobot);
		
        driverCommands.put(0, cmdToDriveTheRobot);
		
		return driverCommands;
		
    }
    
    
    // public void init()
	// {
		
	// 	driverCommands = new HashMap<Integer, Command>(10);
	// }
	
	
    //Keeping in case we have todo a rollback to 2 Driver JS
    ////in init 
    //driverLeftCommands = new HashMap<Integer, Command>(10);
    //driverRightCommands = new HashMap<Integer, Command>(10);
        
	//HashMap <Integer, Command> driverLeftCommands;
    //HashMap <Integer, Command> driverRightCommands;
    /**
	 * Creates commands for the left driver joystick
	 *
	 * @param myRobot
	 * @return
	 */
	public HashMap<Integer, Command> createDriverLeftCommands(Robot myRobot)
	{
		
		//NoDriveCommand = new NoDriverCommand(myRobot, true);
		
		//driverLeftCommands.put(0, NoDriveCommand);

        CommandTankDrive cmdToDriveTheRobot = new CommandTankDrive(theRobot);
		driverLeftCommands.put(0, cmdToDriveTheRobot);

		
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
	public HashMap<Integer, Command> createDriverRightCommands(Robot myRobot)
	{
	  
		//NoDriveCommand  = new NoDriverCommand(myRobot, false);
		//driverRightCommands.put(0, NoDriveCommand);
        
        CommandTankDrive cmdToDriveTheRobot = new CommandTankDrive(theRobot);
        driverRightCommands.put(0, cmdToDriveTheRobot);
        driverRightCommands.put(2, cmdToDriveTheRobot);
        		
		return driverRightCommands;
		
    }
    
}
