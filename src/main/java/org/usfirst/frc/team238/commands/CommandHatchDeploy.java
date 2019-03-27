package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Hatch;
import org.usfirst.frc.team238.robot.Robot;

public class CommandHatchDeploy extends AbstractCommand {

  Hatch theHatch;

  public CommandHatchDeploy(Robot myRobot) {
    this.theHatch= myRobot.myHatch;
  }

  public void prepare() {

  }

  @Override
  public void execute() {
        Logger.Log("CommandHatchDeploy.execute()");
        theHatch.setHatch(true);
        
  }

  // @Override
  public void execute(double overRideValue) {

    // TODO Auto-generated method stub
  }

  public void setParams() {

  }

  public boolean complete() {
        return true;
  }

}
