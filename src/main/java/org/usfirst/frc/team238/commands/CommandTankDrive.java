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
    private double speedAdjustment = 1.0;

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
   
    //Boolean xBox = SmartDashboard.getBoolean("xBox", false);
    DriverInput driverJS = ControlBoard.getDriverInput(false);
    
    
    leftJsValue = driverJS.leftSide * speedAdjustment;
    rightJsValue = driverJS.rightSide * speedAdjustment;
    
    
        //This represents x = ax^3+(1-a)x where leftJsValue = x; tuningValue = a;
        //the math is easy... but why are we doing this???
    /// maybe scaling the stick to power ratio -- accelate the "power" the closer you get to the high or low position on the sitck 
    leftJsValue = (tuningValue * (leftJsValue * leftJsValue * leftJsValue) + (1-tuningValue) * leftJsValue);
    rightJsValue = (tuningValue * (rightJsValue * rightJsValue * rightJsValue) + (1-tuningValue) * rightJsValue);
    
    myDrivetrain.drive(leftJsValue, rightJsValue);
    
   // Logger.Log("Left Motor Value in TELEOP = " + leftJsValue);
    //Logger.Log("Right Motor Value in TELEOP = " + rightJsValue);
   

  }
  
    public void execute(int btnpressed) {
        Logger.Log("CommandTankDrive.execute(int bnpressed): btnpressed = " + btnpressed);

        myDrivetrain.drive(1, 1);

    }

    public void setSpeedAdjustment(double val) {
        if (val > 1) {
            val = 1;
        }

        if (val < 0) {
            val = 0;
        }
        speedAdjustment = val;
    }

    public double getSpeedAdjustment() {
        return speedAdjustment;
    }

}
