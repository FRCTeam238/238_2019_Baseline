package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.DriverInput;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.ControlBoard;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandTankDrive extends AbstractCommand {

    Drivetrain myDrivetrain;
    private double speedAdjustment = 1.0;
    Joystick rightStick = new Joystick(0);

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
    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("pipeline").setNumber(0); // Pipeline 0

    // This codebase is odd
    if(rightStick.getRawButton(1)){
        table.getEntry("camMode").setNumber(0); // Vision mode
        table.getEntry("ledMode").setNumber(3); // Force LEDs on
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");

        double x = tx.getDouble(0); // X value read by limelight
        double y = ty.getDouble(0); // Y value read by limelight

        double heightDifference = CrusaderCommon.LIMELIGHT_HEIGHT - CrusaderCommon.HATCH_TARGET_HEIGHT; // Difference in height between camera and target
        double yTangent = Math.tan(Math.toRadians(90 - CrusaderCommon.LIMELIGHT_ANGLE - y)); // Tangent of total angle
        double distance = yTangent * heightDifference; // Distance between camera and target

        double throttle = distance * 0.5; // Scale-up for throttle - make this not a magic number later
        double sideToSide = x * 0.5; // Scale-up for angular adjustment - make this not a magic number later
        // Note that these scalars are seperate from universal P value - they merely exist to 
        // normalize throttle and side-to-side adjust in relation to each other

        myDrivetrain.drive(throttle - sideToSide, throttle + sideToSide); // Drive using calculated values
    }else{ // When tracking button is not pressed
        table.getEntry("camMode").setNumber(1); // Camera mode
        table.getEntry("ledMode").setNumber(1); // Force LEDs off
        myDrivetrain.drive(leftJsValue, rightJsValue); // Drive using joystick input
    }
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
