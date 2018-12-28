package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandCurlForward extends AbstractCommand 
{

  String direction="";
  Alliance teamColor;
  Robot theRobot;
  Drivetrain myRobotDrive;
  Navigation myNavigation;
  
  double motorValue;
  double targetValue;
  double newTargetYaw;
  
  double stallValue;
  int autonomousCount;
  double yawValue;
  double yawErrorTotal;
  double blueTargetValue; //formerly known as ultrasonicTarget;
  double CurrentDrawLimit = 20.0; // Limit for CurrentDraw
  double yawPConstant = CrusaderCommon.DRIVE_FORWARD_P_VALUE; // Proportional constant for drive
  double yawIConstant = CrusaderCommon.DRIVE_FORWARD_I_VALUE;
  double yawCorrectionMaxPercent = CrusaderCommon.DRIVE_FORWARD_MAX_YAW_PERCENT;   // percent of motorValue for max yaw
  double previousEncoderTicks;
  double collisionToggle;
  
  double finalMotorValueLeft;
  double finalMotorValueRight;
  
  double turnValue = 0;
  
  boolean collisionDetected;
  
  int stage = CrusaderCommon.CURL_START;

  
  int delayCount = 0;
  double delayStart = 0;
  double delayCurrent = 0;
  double delayElapsed = 0;
  double startTime = 0;
  
  public CommandCurlForward(Drivetrain theRobotDrive, Navigation myNavigationForTarget, Robot myRobot){
    
    this.myRobotDrive = theRobotDrive;
    this.myNavigation = myNavigationForTarget;
    this.theRobot = myRobot;
  }
  
  @Override
  public void execute() {
       
    turnValue = SmartDashboard.getNumber("Curl Turn Value", 0.5);
    
    //Logger.Log("CommandCurlForward(): Calculated Motor Value is " + calculatedMotorValue);
    
    //Stages for execution 
    switch(stage)
    {
      case CrusaderCommon.CURL_START: //Stage 1: Drive forward
      {
        
        driveStraight();
        
        break;
        
      }
      
      case CrusaderCommon.CURL_TURN: //Stage 2: Swing to one side or another
      {
        
        
        
        //Selects a side and slows down one side in order to do a swinging action
        switch(direction){
          
          case "Left":
            myRobotDrive.drive(turnValue, motorValue);
            Logger.Log("CommandCurlForward(): We Are Going Left","CommandCurlForwardLog");
            break;
         
          case "Right":
            myRobotDrive.drive(motorValue, turnValue);
            Logger.Log("CommandCurlForward(): We Are Going Right","CommandCurlForwardLog");
            break;
            
          default:
              //do nothing
            break;
          }
        
        break;
        
      }
       
//      case 3: //Stage 3: Drive Forward again
//      {
//        driveStraight();
//        
//        break;
//          
//      }
        
    }
      
  }
    

  @Override
  public void prepare() {
    
    
    yawErrorTotal = 0;
    
    //myNavigation.resetNAVX();
   // myNavigation.zeroYaw();
    myRobotDrive.resetEncoders();
    yawValue = myNavigation.getYaw();
    stage = CrusaderCommon.CURL_START;
    delayCount = 0;
    
    
//    teamColor = Alliance.Blue;
//    direction = "Left";
     
    //teamColor = theRobot.getAllianceTeam();
    if (teamColor == Alliance.Red)
    {
      
      direction = "Right";
      
    }else{
      
      direction = "Left";
      
      targetValue = blueTargetValue;
      
    }
   
    Logger.Log("CommandCurlForward(): Direction  : " + direction,"CommandCurlForwardLog");
  
    
  }

  public void setParams(String params[]) {
    
   
    
    Logger.Log("CommandCurlForward(): Direction is: "+direction,"CommandCurlForwardLog");
    
    // this is the distance to travel on red side
    if ((params[0] != null) || (!params[0].isEmpty())) {
      
      targetValue = Double.parseDouble(params[0]) * CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;
      
    } else {
      
      targetValue = 0;
    }
    
    //this is the speed of the drive train
    if ((params[1] != null) || (!params[1].isEmpty())) {
      
      motorValue = Double.parseDouble(params[1]);
      
    } else {
      
      motorValue = 1;
      
    }
    
    //this is the angle to curl to
    if ((params[2] != null) || (!params[2].isEmpty())) {
      
      newTargetYaw = Integer.parseInt(params[2]);

    } else {
      
      newTargetYaw = 0; // Don't turn if there's no input

    }
    
    //this is the distance to travel on blue side
    if ((params[3] != null) || (!params[3].isEmpty())) 
    {
      blueTargetValue = Double.parseDouble(params[3]) * CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;
    } 
    else 
    {
      blueTargetValue = 0;
    }
    
    if ((params[4] != null) || (!params[4].isEmpty())) 
    {
      collisionToggle = Double.parseDouble(params[4]);
    } 
    else 
    {
      collisionToggle = 0;
    }
      
     myNavigation.setTargetValues(newTargetYaw);
          
  }

  @Override
  public boolean done() {
    
    boolean doness = false;
    double amountOfTicks;
    
    amountOfTicks = myRobotDrive.getEncoderTicks();
    
    switch(stage){
      
      case CrusaderCommon.CURL_START: //Checks if we're at the targeted distance and increments to stage 2
        
        boolean areWeDone = (amountOfTicks > targetValue);
        Logger.Log("CommandCurlForward(): done current  : " + amountOfTicks,"CommandCurlForwardLog");
        Logger.Log("CommandCurlForward(): done target : " + targetValue,"CommandCurlForwardLog");
        if(areWeDone){
          stage++;
          startTime = Timer.getFPGATimestamp();
        }
        
        break; 
      
      case CrusaderCommon.CURL_TURN: //Checks if we are at a certain angle and increments to stage 3
        
       // double currentTime = Timer.getFPGATimestamp();
        //double finalTime = currentTime - startTime;
        //if((finalTime) > 10)
        //{
          
          if(collisionToggle != 1)
          {
            //System.out.println("FINAL TIME = " + finalTime);
            collisionDetected = myNavigation.haveWeCollided();
          }
        //}
        
        if(myNavigation.areWeThereYet())
        {
          myRobotDrive.drive(0, 0);
          //myFuelHandler.theIntake.IntakeStop();
          doness = true;
        }
        
        //THIS DAMN COLLISION IS CAUSING PROBLEMS IN OUTER CURL      
       
        if((collisionDetected))
        {        
          timerInMillis();
          stage++;      
        }
        
        break;
        
      case CrusaderCommon.CURL_FINISH_TURN:
        
        if(timerInMillis() < CrusaderCommon.COLLISION_DELAY_IN_MILLIS)
        {
          
          switch(direction){
            
            case "Left":
              myRobotDrive.drive(0, 1);
              break;
           
            case "Right":
              myRobotDrive.drive(1, 0);
              break;
              
            default:
                //do nothing
              break;
            }
          
          
        }
        else
        {
          myRobotDrive.drive(0, 0);
          doness = true;
        }
        
        
        break;
       
    }
      
    return doness;
    
  }
 

  //THIS ALSO EXISTS IN TURN AWAY FROM BOILER
  public double getError()
  {
    double error;
    double currentYaw = Math.abs(myNavigation.getYaw());
    
    error = targetValue - currentYaw;
    
    return error;
  }
  
  public void driveStraight()
  {
    
    double currentYaw = myNavigation.getYaw();
    double yawError = currentYaw - yawValue; // Positive yaw is right turn so positive error is right turn
    double yawCorrection = yawPConstant * yawError * motorValue;
    
    yawCorrection = Math.min(yawCorrection, yawCorrectionMaxPercent * motorValue); 

    finalMotorValueLeft = motorValue - yawCorrection;
    finalMotorValueRight = motorValue + yawCorrection;
    
    myRobotDrive.drive(finalMotorValueLeft, finalMotorValueRight);
    
    Logger.Log("CommandCurlForward(): LeftMotorValue = "+ finalMotorValueLeft); 
    Logger.Log("CommandCurlForward(): RightMotorValue = " + finalMotorValueRight);
    Logger.Log("CommandCurlForward(): CurrentYaw: "+ currentYaw+ "  YawError: "+ yawError+ "  YawCorrection: "+ yawCorrection);

  }
  
  /**
   * This is a timer that tracks time in milliseconds
   * @return
   */
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

}
