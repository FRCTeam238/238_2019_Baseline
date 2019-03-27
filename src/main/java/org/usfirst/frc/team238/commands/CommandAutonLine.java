
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Robot;

public class CommandAutonLine extends AbstractCommand {

  Robot myRobot;
  
  double topSpeed, distance;
    double rotateOutput;
    String navObject;
  
  AutonLineRunnable run;
  
  final double acceleration = 100; // in/sec^2

 
  public CommandAutonLine(Robot myRobot ) {
    
    this.myRobot = myRobot;
    
  }

  public void prepare() {
     
	 run = new AutonLineRunnable(myRobot, distance, topSpeed,navObject);
     started=false;
     //myRobot.myDriveTrain.shiftHigh();
    
     //Logger.Log("CommandDriveForward.prepare");

  }
  
  
  boolean started = false;
  
  public void execute() {
   if(!started) {
       started=true;
       new Thread(run).start();
   }


  }

  public void setParams(String params[]) {

    if ((params[0] != null) || (!params[0].isEmpty())) {
      distance = Double.parseDouble(params[0]) ;//CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH; //4560;
    } else {
      distance = 0;
    }

    if ((params[1] != null) || (!params[1].isEmpty())) {
      topSpeed = Double.parseDouble(params[1]);
        } else {
            topSpeed = 1;
        }
    
    if ((params[2] != null) || (!params[2].isEmpty())) {
            navObject = params[2];
        } else {
            navObject = "NAVBOARD";
        }
    // either the NAVBOARD or VISION


  }

  public boolean done() {
      return run.done();
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
