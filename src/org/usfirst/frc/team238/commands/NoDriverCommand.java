package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Drivetrain;

public class NoDriverCommand extends AbstractCommand {
	
	Drivetrain myRobotDrive;
	boolean shifter;
	
	public void prepare(){
		
	}
	
	public NoDriverCommand(Drivetrain myRobotDrive, boolean isShifter){
		
		this.myRobotDrive = myRobotDrive;
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
