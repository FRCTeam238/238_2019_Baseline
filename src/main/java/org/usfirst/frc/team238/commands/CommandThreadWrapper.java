
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Robot;

public class CommandThreadWrapper extends AbstractCommand {

  String [] inputParams;
  Robot myRobot;
  double angle, topSpeed, distance;
  double rotateOutput;
  String  navObject;
  AutonLineRunnable run;
  
  final double acceleration = 100; // in/sec^2
  
  // boolean debug;

  public CommandThreadWrapper(Robot myRobot) {
    
    // this.debug = SmartDashboard.getBoolean("Debug");
    //this.myRobotDrive = myRobot.myDriveTrain;
    //this.myPowerDistributionPanel = new PowerDistributionPanel();
    //myNavigation = myRobot.myNavigation;
    this.myRobot = myRobot;

  }

  public void prepare() {
     
	  run = new AutonLineRunnable(myRobot, distance, topSpeed, navObject );
     
	  started=false;
      
	  Logger.Log("CommandThreadWrapper.prepare");

  }
  
  
  boolean started = false;
  
  public void execute() {
   
	  if(!started) {
       started=true;
       new Thread(run).start();
   }


  }

  public void setParams(String params[]) {

	inputParams = params;
	
//	if ((params[0] != null) || (!params[0].isEmpty())) {
//      distance = Double.parseDouble(params[0]) ;//CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH; //4560;
//    } else {
//      distance = 0;
//    }
//
//    if ((params[1] != null) || (!params[1].isEmpty())) {
//      topSpeed = Double.parseDouble(params[1]);
//    } else {
//      topSpeed = 1;
//    }



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
