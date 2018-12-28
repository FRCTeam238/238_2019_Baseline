package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandTurn extends AbstractCommand {

  Drivetrain myRobotDrive;
  Navigation myNavigation;

  double motorValue;
  double finalMotorValue;
  double targetValue;
  double currentYaw;
  int    count;
  
  
  long startTime ;
  double runTime;
  
  public CommandTurn(Drivetrain robotDrive, Navigation myNavigationForTarget) {

    this.myRobotDrive = robotDrive;
    this.myNavigation = myNavigationForTarget;
    count = 0;
  }

  public void prepare() {

    //myNavigation.zeroYaw();
    resetVals();
    startTime = System.currentTimeMillis();
  }

  
  public final double TURN_KP = 0.033;
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
        calculatedValue+=0.09;
    }else {
        calculatedValue-=0.09;
    }
    
    calculatedValue = Math.min(motorValue, Math.max(calculatedValue, -motorValue));
    myRobotDrive.turnRight(calculatedValue, calculatedValue);
    System.out.println("CVALUE:" + calculatedValue);
    myNavigation.navxValues();
    
    Logger.Log("CommandTurnRight(): Our yaw = "+yaw);
    Logger.Log("CommandTurnRight(): Our Target yaw is = "+ targetValue);
    Logger.Log("CALCULATED VALUE = " + calculatedValue);
    
  }

  public void setParams(String params[]) {

    if ((params[0] != null) || (!params[0].isEmpty())) {
      targetValue = ((Double.parseDouble(params[0]) % 360) + 360) % 360;
    } else {
      targetValue = 0;
    }

    if ((params[1] != null) || (!params[1].isEmpty())) {
      motorValue = Double.parseDouble(params[1]);
    } else {
      motorValue = 1;
    }
    
    
    if ((params[2] != null) || (!params[2].isEmpty())) {
        runTime = Double.parseDouble(params[2]);
      } else {
        runTime = 1;
      }


    myNavigation.setTargetValues(targetValue);

  }

  public boolean done() {

   
    if(System.currentTimeMillis() - startTime <runTime) {
        
        return false;
    }else {
        
        if(Math.abs(pastError) > 20.0)
        {
            return false;
        }
        else {
            myRobotDrive.drive(0, 0);
            return true;
        }
        
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
