
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandDriveForward extends AbstractCommand {

  Drivetrain myRobotDrive;
  Navigation myNavigation;
  //PowerDistributionPanel myPowerDistributionPanel;
  
  double motorValue;
  double targetValue;
  double stallValue;
  double yawValue;
  double yawErrorTotal;
  double ultrasonicTarget;
  double CurrentDrawLimit = 20.0; // Limit for CurrentDraw
  double yawPConstant = CrusaderCommon.DRIVE_FORWARD_P_VALUE; // Proportional constant for drive
  double yawIConstant = CrusaderCommon.DRIVE_FORWARD_I_VALUE;
  double yawCorrectionMaxPercent = CrusaderCommon.DRIVE_FORWARD_MAX_YAW_PERCENT;   // percent of motorValue for max yaw
  double previousEncoderTicks;
  double okToCheckForCollision = 0;
  
  int collisionDelay = 250;
  int delayCount = 0;
  double delayStart = 0;
  double delayCurrent = 0;
  double delayElapsed = 0;
  
  
  // boolean debug;

  public CommandDriveForward(Drivetrain robotDrive, Navigation myNav) {
    
    // this.debug = SmartDashboard.getBoolean("Debug");
    this.myRobotDrive = robotDrive;
    this.myNavigation = myNav;
   // this.myPowerDistributionPanel = new PowerDistributionPanel();

  }

  public void prepare() {

    yawErrorTotal = 0;
  //  myNavigation.zeroYaw();
    myRobotDrive.resetEncoders();
    yawValue = myNavigation.getYaw();
    previousEncoderTicks = 0;
    delayCount = 0;
    resetVals();
    
    //Logger.Log("CommandDriveForward.prepare");

  }
  
  /*public void execute()
  {
      
      myRobotDrive.magicDrive(targetValue);
      
  }*/
  
  public void execute() {
    
      SmartDashboard.putNumber("TARGET VALUE = ", targetValue);
    myRobotDrive.shiftLow();
    
    double theError = targetValue - myRobotDrive.getEncoderTicks(); 
    
    
    motorValue = pidCalc(CrusaderCommon.STRAIGHT_P_VALUE, 
                         targetValue,
                         theError,
                         CrusaderCommon.STRAIGHT_MAX_ERROR,
                         CrusaderCommon.STRAIGHT_MAX_MOTOR_VALUE,
                         CrusaderCommon.STRAIGHT_I_VALUE);
                        
    
    double convert = CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;
    double currentYaw = myNavigation.getYaw();
    double yawError = currentYaw - yawValue; // Positive yaw is right turn so positive error is right turn
    double yawCorrection = yawPConstant * yawError * motorValue;
    
    //Logger.Log("YAW_ERROR = " + yawError);
    //Logger.Log("YAW_CORRECTION = " + yawCorrection);
    
    yawCorrection = Math.min(yawCorrection, yawCorrectionMaxPercent * motorValue); 

    double finalMotorValueLeft = motorValue - yawCorrection;
    double finalMotorValueRight = motorValue + yawCorrection;
    
    
    //RM SmartDashboard.putNumber("LEFT_MOTOR", finalMotorValueLeft);
    //RM SmartDashboard.putNumber("RIGT_MOTOR", finalMotorValueRight);
    
    //Logger.Log("CommandDriveForward(): LeftMotorValue = "+ finalMotorValueLeft); 
    //Logger.Log("CommandDriveForward(): RightMotorValue = " + finalMotorValueRight);
    //Logger.Log("CommandDriveForward(): CurrentYaw: "+ currentYaw+ "  YawError: "+ yawError+ "  YawCorrection: "+ yawCorrection);
    
    myRobotDrive.drive(-finalMotorValueLeft, -finalMotorValueRight);
    
    /*
     * SmartDashboard.putNumber("YawError", yawError);
     * SmartDashboard.putNumber("CurrenTYaw", currentYaw);
     * //SmartDashboard.putNumber("YawErrorTotal", yawErrorTotal);
     * SmartDashboard.putNumber("YawCorrection", yawCorrection);
     * SmartDashboard.putNumber("finalMotorValueLeft", finalMotorValueLeft);
     * SmartDashboard.putNumber("finalMotorValueRight", finalMotorValueRight);
     * double averageSpeed = (finalMotorValueLeft + finalMotorValueRight) / 2;
     * SmartDashboard.putNumber("Average Speed", averageSpeed);
     * 
     * myRobotDrive.driveSpeed(finalMotorValueLeft, finalMotorValueRight);
     */


  }

  public void setParams(String params[]) {

    if ((params[0] != null) || (!params[0].isEmpty())) {
      targetValue = Double.parseDouble(params[0]) * CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;//CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH; //4560;
    } else {
      targetValue = 0;
    }

    if ((params[1] != null) || (!params[1].isEmpty())) {
      motorValue = Double.parseDouble(params[1]);
    } else {
      motorValue = 1;
    }

    if ((params[2] != null) || (!params[2].isEmpty())) {
      stallValue = Double.parseDouble(params[2]);
    } else {
      stallValue = 0;
    }
    if ((params[3] != null) || (!params[3].isEmpty())) {
      ultrasonicTarget = Double.parseDouble(params[3]);
    } else {
      ultrasonicTarget = 0;
    }

    okToCheckForCollision = targetValue *.25;
  }

  public boolean done() {
  
    double amountOfTicks;
    
    //boolean areWeCollided;
    
    amountOfTicks = myRobotDrive.getEncoderTicks();
    
    Logger.Log("CommandDriveForward() : Target Value = "+ targetValue+ " Amount Of Ticks = "+ amountOfTicks);
    
    boolean areWeDone = (amountOfTicks > targetValue);


    if (areWeDone) 
    {
      myRobotDrive.drive(0, 0);
      SmartDashboard.putNumber("WE STOPPED AT", amountOfTicks);
    }
    
      
    return areWeDone;
  }
  
  
  public double getError()
  {
    double error;
    double amountOfTicks = myRobotDrive.getEncoderTicks();
    
    error = targetValue - amountOfTicks;
    
    return error;
  }
  
  public double timerInMillis()
  {
    if(delayCount == 0)
    {
      
      delayStart = System.currentTimeMillis();
      delayCount++;
      
    }
    else
    {
      delayCurrent = System.currentTimeMillis();
    }
    
    delayElapsed = delayCurrent - delayStart;
    
    return delayElapsed;
  }
//  public boolean done() {
//      
//      double amountOfTicks;
//      
//      //boolean areWeCollided;
//      
//      amountOfTicks = myRobotDrive.getEncoderTicks();
//      
//      Logger.Log("CommandDriveForward() : Target Value = "+ targetValue+ " Amount Of Ticks = "+ amountOfTicks);
//      
//      boolean areWeDone = (amountOfTicks > targetValue);
//
//      if((!areWeDone) && (stallValue != 0)) 
//      {
//        //if we run into a wall and still arent There yet consider it done 
//        
//        if(ultrasonicTarget > 0)
//        {
//          
//          if(timerInMillis() > ultrasonicTarget)
//            areWeDone = true;
//          
//        }
//        
//        if (amountOfTicks > okToCheckForCollision)
//        {
//          
//          areWeCollided = myNavigation.haveWeCollided();
//          
//          if(areWeCollided)
//          {
//            
//            //if(myNavigation.getCollisionDelay() > CrusaderCommon.COLLISION_DELAY_IN_MILLIS)
//            //{
//              areWeDone = true;
//            //}
//            
//          }
//        }
//
//           
//      }
//
//      if (areWeDone) 
//      {
//        myRobotDrive.drive(0, 0);
//        //RM SmartDashboard.putNumber("WE STOPPED AT", amountOfTicks);
//      }
//      
//        
//      return areWeDone;
//    }
}
