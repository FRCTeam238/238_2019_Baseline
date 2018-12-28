package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.DriverStation;

public class CommandTurnLeft extends AbstractCommand {

  Drivetrain myRobotDrive;
  Navigation myNavigation; // Pass variable here

  double motorValue;
  double finalMotorValue;
  double targetValue;
  double newTargetYaw;
  double currentYaw;
  
  int    count;

  public CommandTurnLeft(Drivetrain theRobotDrive, Navigation myNavigationForTarget) {

    this.myRobotDrive = theRobotDrive;
    this.myNavigation = myNavigationForTarget;
    count = 0;

  }

  public void prepare() {
  
    //myNavigation.zeroYaw();
    resetVals();

  }

  public void execute() {
  
    double yaw = myNavigation.getYaw();
    double calculatedValue;
    
    
    double error = 0; //FIX THIS 
    calculatedValue = pidCalc(  CrusaderCommon.TURN_P_VALUE,
                                targetValue,
                                error,
                                CrusaderCommon.TURN_MAX_ERROR,
                                CrusaderCommon.TURN_MAX_MOTOR_VALUE,
                                CrusaderCommon.TURN_I_VALUE);
    
    currentYaw = myNavigation.getYaw();
    
    myRobotDrive.turnLeft(calculatedValue, calculatedValue);
   
    myNavigation.navxValues();
    
    Logger.Log("CommandTurnLeft(): Our yaw = "+yaw+"\n"+"CommandTurnLeft(): Our Target yaw is = "+targetValue);
    Logger.Log("CALCULATED VALUE = " + calculatedValue);

  }

  public void setParams(String params[]) {
    
    //Logger.Log("!!!!!DEBUG!!!!!!!!PARAMETERS!!!!  "+ params[0]);

    if ((params[0] != null) || (!params[0].isEmpty())) {
      targetValue = Double.parseDouble(params[0]);
    } else {
      targetValue = 0;
    }

    if ((params[1] != null) || (!params[1].isEmpty())) {
      motorValue = Double.parseDouble(params[1]);
    } else {
      motorValue = 1;
    }

    if ((params[2] != null) || (!params[2].isEmpty())) {
      newTargetYaw = Integer.parseInt(params[2]);

    } else {
      newTargetYaw = 0; // Don't turn if there's no input

    }

    myNavigation.setTargetValues(targetValue);

  }

  public boolean done() {
    if (myNavigation.areWeThereYet() == true) {
      myRobotDrive.drive(0, 0);
      return true;
    }

    else {
      return false;
    }

  }
  
  public double getError()
  {
    double error;
    double currentYaw = Math.abs(myNavigation.getYaw());
    
    error = targetValue - currentYaw;
    Logger.Log("turn left : TURN ERROR = " + error);
    
    return error;
  }

}