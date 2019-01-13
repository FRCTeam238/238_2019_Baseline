package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Robot;

public class CommandShiftHigh extends AbstractCommand {

  Drivetrain myDrivetrain;

  public CommandShiftHigh(Robot myRobot) {
    this.myDrivetrain = myRobot.myDriveTrain;
  }

  public void prepare() {

  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    myDrivetrain.shiftHigh();
  }

  // @Override
  public void execute(double overRideValue) {

    // TODO Auto-generated method stub
  }

  public void setParams() {

  }

  public boolean complete() {
    return myDrivetrain.complete();
  }

}
