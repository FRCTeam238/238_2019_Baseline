package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.TestCoreObject;

public class CommandStopEverything implements Command {

    /**
   * THIS IS THE DEFAULT STOP EVERYTHING ON OPERATOR CONTROLLS
   */
    TestCoreObject myTestCoreObject;
    
    public CommandStopEverything(TestCoreObject testCoreObject) {
   
      this.myTestCoreObject = testCoreObject; 
    // TODO Auto-generated constructor stub
  
  }

  public CommandStopEverything() {
	}

@Override
  public void execute() {
    myTestCoreObject.turnOffChannelTwo();
  }

  @Override
  public void prepare() {
    // TODO Auto-generated method stub

  }

  @Override
  public void setParams() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean done() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void execute(int btnPressed) {
    // TODO Auto-generated method stub
    
  }

}
