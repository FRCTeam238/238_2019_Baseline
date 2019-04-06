package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.commands.CommandSetTankDriveSpeedAdjustment;
import org.usfirst.frc.team238.commands.CommandTankDrive;
import org.usfirst.frc.team238.commands.CommandTankDriveTurbo;
import org.usfirst.frc.team238.commands.NoDriverCommand;
import org.usfirst.frc.team238.robot.Robot;

public class DriverCommandFactory 
{

	NoDriverCommand NoDriveCommand;
    private CommandTankDrive tankDriveCommand;
    private CommandSetTankDriveSpeedAdjustment speedAdjCommand;;
    private CommandTankDriveTurbo cmdTurbo;
        
    Robot theRobot;

    public DriverCommandFactory( Robot myRobot) {
        
        this.theRobot = myRobot;
        tankDriveCommand = new CommandTankDrive(myRobot);
        speedAdjCommand = new CommandSetTankDriveSpeedAdjustment(this.tankDriveCommand, 0.3);
        cmdTurbo = new CommandTankDriveTurbo(myRobot, 1, 1);
    }

	/**
	 * Creates a command for both driver joysticks
	 * (In this case, enables the driver to actually drive the robot)
	 * @param myRobotDrive
	 * @return
	 */
	public HashMap<Integer, Command> createDriverCommands()
	{
		HashMap<Integer, Command> commands = new HashMap<Integer, Command>(10);
		
        commands.put(0, tankDriveCommand);
		
		return commands;
		
    }

    /**
	 * Creates commands for the left driver joystick
	 *
	 * @param myRobot
	 * @return
	 */
	public HashMap<Integer, Command> createDriverLeftCommands(Robot myRobot)
	{
		HashMap<Integer, Command> commands = new HashMap<Integer, Command>(10);
		
		//NoDriveCommand = new NoDriverCommand(myRobot, true);
		

        commands.put(0, this.tankDriveCommand);
        commands.put(1, cmdTurbo);
        commands.put(3, speedAdjCommand);
		return commands;
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
		HashMap<Integer, Command> commands = new HashMap<Integer, Command>(10);
	  
		//NoDriveCommand  = new NoDriverCommand(myRobot, false);
		//driverRightCommands.put(0, NoDriveCommand);
        
      
        commands.put(0, this.tankDriveCommand);
        commands.put(1, cmdTurbo);
        commands.put(3, speedAdjCommand);

        return commands;
    }
    
}
