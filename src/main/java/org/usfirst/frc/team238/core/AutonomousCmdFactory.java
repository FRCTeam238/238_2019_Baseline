package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.commands.CommandDriveBackwards;
import org.usfirst.frc.team238.commands.CommandTurnLeft;
import org.usfirst.frc.team238.commands.CommandTurnRight;
import org.usfirst.frc.team238.commands.CommandWaitForScoringPosition;
import org.usfirst.frc.team238.commands.CommandAutonLine;
import org.usfirst.frc.team238.commands.CommandDelay;
import org.usfirst.frc.team238.commands.CommandScoringPosition;
import org.usfirst.frc.team238.commands.CommandShiftHigh;
import org.usfirst.frc.team238.commands.CommandShiftLow;
import org.usfirst.frc.team238.commands.CommandTimeDriveFwd;
import org.usfirst.frc.team238.commands.CommandTurn;
import org.usfirst.frc.team238.commands.CommandResetElevatorEncoders;


public class AutonomousCmdFactory {
	

	
	HashMap <String, Command> autonomousCommands;
	//TODO change that static 10
	public void init(){
		autonomousCommands = new HashMap<String, Command>(10);
	}
	
	
	public HashMap<String, Command> createAutonomousCommands(Robot myRobot){
	    AbstractCommand cmd;
	    
	    cmd  = new CommandDriveForward(myRobot);
		autonomousCommands.put("CommandDriveForward", cmd );
		
		cmd = new CommandDriveBackwards(myRobot);
		autonomousCommands.put("CommandDriveBackwards", cmd);
		
		cmd = new CommandTurnLeft(myRobot);
		autonomousCommands.put("CommandTurnLeft", cmd);
		
		cmd = new CommandTurnRight(myRobot);		
		autonomousCommands.put("CommandTurnRight", cmd);
		
		cmd = new CommandDelay(myRobot);
		autonomousCommands.put("CommandDelay", cmd);
		
		//cmd = new CommandRunTrajectory(myRobot.myDriveTrain, myRobot.myNavigation, goStraight.objects );
		//autonomousCommands.put("CommandRunGoStraightTrajectory", cmd);
    
		// cmd = new CommandRunTrajectory(myRobot.myDriveTrain,  myRobot.myNavigation,leftSwitch.objects );
        // autonomousCommands.put("CommandRunLeftSwitchTrajectory", cmd);
        
        // cmd = new CommandRunTrajectory(myRobot.myDriveTrain,  myRobot.myNavigation,rightSwitch.objects );
        // autonomousCommands.put("CommandRunRightSwitchTrajectory", cmd);
        
       
        // cmd = new CommandRunTrajectory(myRobot.myDriveTrain, myRobot.myNavigation, ScaleLeftOppositeSide.objects );
        // autonomousCommands.put("CommandRunLeftScaleTrajectory", cmd);
        
        // cmd = new CommandRunTrajectory(myRobot.myDriveTrain, myRobot.myNavigation, ScaleLeftOppositeSide.objects );
        // autonomousCommands.put("CommandRunScaleLeftOppositeSideTrajectory", cmd);
   
        // //change objects to right lalaprofile
        // cmd = new CommandRunTrajectory(myRobot.myDriveTrain, myRobot.myNavigation, ScaleLeftOppositeSide.objects );
        // autonomousCommands.put("CommandRunScaleRightOppositeSideTrajectory", cmd);
        
        cmd = new CommandAutonLine(myRobot);
        autonomousCommands.put("CommandAutonLine", cmd);
        
        cmd = new CommandShiftHigh(myRobot);
        autonomousCommands.put("CommandShiftHigh", cmd);
        
        cmd = new CommandShiftLow(myRobot);
        autonomousCommands.put("CommandShiftLow", cmd);
       
        cmd = new CommandTurn(myRobot);
        autonomousCommands.put("CommandTurn", cmd);
        
        // cmd = new CommandRunTrajectory(myRobot.myDriveTrain,  myRobot.myNavigation,SwitchEndLeft.objects );
        // autonomousCommands.put("CommandRunSwitchEndLeftTrajectory", cmd);
        
        // cmd = new CommandRunTrajectory(myRobot.myDriveTrain,  myRobot.myNavigation,SwitchEndRight.objects );
        // autonomousCommands.put("CommandRunSwitchEndRightTrajectory", cmd);
        
        cmd = new CommandTimeDriveFwd(myRobot);
        autonomousCommands.put("CommandTimeDriveFwd", cmd);

        cmd = new CommandScoringPosition(myRobot);
        autonomousCommands.put("CommandScoringPosition", cmd);

        cmd = new CommandResetElevatorEncoders(myRobot);
        autonomousCommands.put("CommandResetElevatorEncoders", cmd);

        cmd = new CommandWaitForScoringPosition(myRobot);
        autonomousCommands.put("CommandWaitForScoringPosition", cmd);

		return autonomousCommands;
		
	}


}
