package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.DriverInput;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.ControlBoard;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandTankDrive extends AbstractCommand {

  Drivetrain myDrivetrain;

  public CommandTankDrive(Robot myRobot) {
	    this.myDrivetrain = myRobot.myDriveTrain;
  }

  public void prepare() {

  }

  @Override
  public void execute() {
    double leftJsValue = 0;
    double rightJsValue = 0;
    
    double tuningValue = SmartDashboard.getNumber("DRIVETRAIN TUNING", 0.2);
   
    Boolean xBox = SmartDashboard.getBoolean("xBox", true);
    DriverInput driverJS = ControlBoard.getDriverInput(xBox);
    
    
    leftJsValue = driverJS.leftSide;
    rightJsValue = driverJS.rightSide;
    
    
    //This represents x = ax^3+(1-a)x where leftJsValue = x; tuningValue = a;
    leftJsValue = (tuningValue * (leftJsValue * leftJsValue * leftJsValue) + (1-tuningValue) * leftJsValue);
    rightJsValue = (tuningValue * (rightJsValue * rightJsValue * rightJsValue) + (1-tuningValue) * rightJsValue);
    
    myDrivetrain.drive(leftJsValue, rightJsValue);
    
   // Logger.Log("Left Motor Value in TELEOP = " + leftJsValue);
    //Logger.Log("Right Motor Value in TELEOP = " + rightJsValue);
   

  }


}
