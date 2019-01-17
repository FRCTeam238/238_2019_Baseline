package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.TestCoreObject;

public class CommandTestShiftHigh extends AbstractCommand {

  TestCoreObject myTestCoreObject;

  public CommandTestShiftHigh(TestCoreObject testCoreObject) {
    this.myTestCoreObject = testCoreObject;
  }

  public void prepare() {

  }

  @Override
  public void execute() {
      Logger.Log("CommandTestShiftHigh.execute()");
      myTestCoreObject.turnOnChannelTwo();
  }

  @Override
  public void execute(int overRideValue) {
      Logger.Log("CommandTestShiftHigh.execute(index)");
   
      myTestCoreObject.turnOnChannelTwo();
  }

  public void setParams() {

  }

  public boolean complete() {
    return myTestCoreObject.complete();
  }

}
