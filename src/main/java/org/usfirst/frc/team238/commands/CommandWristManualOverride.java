package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Shoulder;

public class CommandWristManualOverride extends AbstractCommand {

    Shoulder wrist;

  public CommandWristManualOverride(Shoulder wrist) {
    this.wrist = wrist;
  }

  public void prepare() {

  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    wrist.manualOverride(false);
    System.out.println("execute");
  }

  @Override
  public void execute(int overRideValue) {
      System.out.println("execute(index)" + String.valueOf(overRideValue));
      
   if(overRideValue == 8) {
       wrist.manualOverride(true);
   }else
   {
       wrist.manualOverride(false);
   }
  }

  public void setParams() {

  }

  public boolean complete() {
    return true;
  }

}
