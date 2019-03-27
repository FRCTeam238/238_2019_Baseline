/**
 * 
 */
package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Robot;

/**
 * @author Crusader
 *
 */
public class CommandTimeDriveFwd extends AbstractCommand {

  int        count;
  int        targetValue;
  double     start;
  double     current;
  double     elapsed;
  double	 motorValue;
  Drivetrain myRobotdrive;
  Navigation myNavigation;

  /**
   * 
   */
  public CommandTimeDriveFwd(Robot myRobot) {

	  this.myRobotdrive = myRobot.myDriveTrain;

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.usfirst.frc.team238.core.Command#execute()
   */
  @Override
  public void execute() {
    
    myRobotdrive.resetEncoders();
   
    //This if else is used to have this delay work in time rather than iterations
    if(count == 0)
    {
      
      start = System.currentTimeMillis();
      count++;
      
    }
    else
    {
      current = System.currentTimeMillis();
    }
    
    elapsed = current - start;
    
    this.myRobotdrive.drive(-motorValue, -motorValue);
    
    Logger.Log("drive for a while Elapsed Time (millis): "+elapsed);
    
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.usfirst.frc.team238.core.Command#prepare()
   */
  @Override
  public void prepare() {
    // TODO Auto-generated method stub
    myRobotdrive.resetEncoders();
   // myNavigation.zeroYaw();
    count = 0;
    current = 0;
  }

  public void setParams(String params[]) {

	    if ((params[0] != null) || (!params[0].isEmpty())) {
	      targetValue = Integer.parseInt(params[0]);
	    } else {
	      targetValue = 0;
	    }

	    if ((params[1] != null) || (!params[1].isEmpty())) {
	      motorValue = Double.parseDouble(params[1]);
	    } else {
	      motorValue = 1;
	    }
  
  }

  public boolean done() {
    boolean isDone = false;

    if (elapsed > targetValue) {
      isDone = true;
      this.myRobotdrive.drive(0.0, 0.0);
    }

    return isDone;
  }

}
