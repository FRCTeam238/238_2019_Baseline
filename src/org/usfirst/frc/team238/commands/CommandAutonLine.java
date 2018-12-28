
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandAutonLine extends AbstractCommand {

  Drivetrain myRobotDrive;
 // PowerDistributionPanel myPowerDistributionPanel;

  Navigation myNavigation;
  
 
  double angle, topSpeed, distance;
  double rotateOutput;
  
  AutonLineRunnable run;
  
  final double acceleration = 100; // in/sec^2
  
  // boolean debug;

  public CommandAutonLine(Drivetrain robotDrive, Navigation navigation) {
    
    // this.debug = SmartDashboard.getBoolean("Debug");
    this.myRobotDrive = robotDrive;
    //this.myPowerDistributionPanel = new PowerDistributionPanel();
    myNavigation = navigation;
    

  }

  public void prepare() {
     // myNavigation.zeroYaw();
      run = new AutonLineRunnable(myRobotDrive, myNavigation, distance, topSpeed, myNavigation.getYaw());
     started=false;
      myRobotDrive.shiftHigh();
    //Logger.Log("CommandDriveForward.prepare");

  }
  
  /*public void execute()
  {
      
      myRobotDrive.magicDrive(targetValue);
      
  }*/
  
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
