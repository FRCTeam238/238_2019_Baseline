package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Robot;

public class NoDriverCommand extends AbstractCommand {
	
	Drivetrain myRobotDrive;
	boolean shifter;
	
	public void prepare(){
		
	}
	
	public NoDriverCommand(Robot myRobot, boolean isShifter){
		
		this.myRobotDrive = myRobot.myDriveTrain;
		this.shifter = isShifter;
	}

	
	public void execute() {
	    //Logger.Log("SHIFT " + shifter);
		myRobotDrive.nobtnPressed(shifter);
		//myRobotDrive.shiftLow();
	}

	
	public void execute(double overRideValue) {
		
		
	}

}
