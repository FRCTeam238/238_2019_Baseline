package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Wrist;
import org.usfirst.frc.team238.robot.Robot;

public class CommandWristUp extends AbstractCommand {

  Wrist theWrist;

  public CommandWristUp(Robot myRobot) {
    this.theWrist= myRobot.myWrist;
  }

  public void prepare() {

  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    theWrist.extendWrist();
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
