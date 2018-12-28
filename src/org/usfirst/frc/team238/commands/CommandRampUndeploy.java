package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Ramp;

public class CommandRampUndeploy extends AbstractCommand {

  Ramp myRamp;

  public CommandRampUndeploy(Ramp ramp) {
    this.myRamp = ramp;
  }

  public void prepare() {

  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    myRamp.retractRamp();
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
