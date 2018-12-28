package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandTurnRight extends AbstractCommand {

  Drivetrain myRobotDrive;
  Navigation myNavigation;

  double motorValue;
  double finalMotorValue;
  double targetValue;
  double newTargetYaw;
  double currentYaw;
  int    count;

  public CommandTurnRight(Drivetrain robotDrive, Navigation myNavigationForTarget) {

    this.myRobotDrive = robotDrive;
    this.myNavigation = myNavigationForTarget;
    count = 0;
  }

  public void prepare() {

   // myNavigation.zeroYaw();
    resetVals();

  }

  
  public final double TURN_KP = 0.028;
  public final double TURN_KD = 0.0033;
  
  private double pastError =0;
  public void execute() {
    
    double yaw = myNavigation.getYaw();
    double calculatedValue;
    
    double error = 0; //FIX THIS
    /*calculatedValue = pidCalc(  CrusaderCommon.TURN_P_VALUE, 
                                CrusaderCommon.TURN_DEAD_STOP_RIGHT,
                                error,
                                CrusaderCommon.TURN_MAX_ERROR,
                                CrusaderCommon.TURN_MAX_MOTOR_VALUE,
                                CrusaderCommon.TURN_I_VALUE);
    */
    
    currentYaw = myNavigation.getYaw();
    double angleError = (targetValue - currentYaw);
    if(Math.abs(angleError) > (360.0 - 0.0)/2.0D) {
        angleError = angleError>0.0D ? angleError- 360.0+ 0.0 : angleError + 360.0 -0.0; 
    }
    calculatedValue = TURN_KP * angleError ;
    double dContribution = TURN_KD *( angleError - pastError) *(1.0/0.05);
    calculatedValue += dContribution;
    
    pastError = angleError;
    if(calculatedValue>0) {
        calculatedValue+=0.05;
    }else {
        calculatedValue-=0.05;
    }
    
    Math.min(0.7, Math.max(calculatedValue, -0.7));
    myRobotDrive.turnRight(calculatedValue, calculatedValue);
    System.out.println("CVALUE:" + calculatedValue);
    myNavigation.navxValues();
    
    Logger.Log("CommandTurnRight(): Our yaw = "+yaw);
    Logger.Log("CommandTurnRight(): Our Target yaw is = "+ targetValue);
    Logger.Log("CALCULATED VALUE = " + calculatedValue);
    
  }

  public void setParams(String params[]) {

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
    Logger.Log("TURN ERROR = " + error);
    
    return error;
  }
  
}
