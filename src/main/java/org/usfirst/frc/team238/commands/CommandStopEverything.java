package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.TestCoreObject;

public class CommandStopEverything extends AbstractCommand {

    /**
    * THIS IS THE DEFAULT STOP EVERYTHING ON OPERATOR CONTROLLS
    */
  
    Robot theRobot;

    TestCoreObject myTestCoreObject;
    
    public CommandStopEverything(Robot myRobot) {
   
      this.theRobot = myRobot; 
     
  
  }

  public CommandStopEverything() {
	}

@Override
  public void execute() {
        theRobot.myShoulder.stop();
        theRobot.myElevator.stop();
  }

  @Override
  public void prepare() {
       

  }

  @Override
  public void setParams() {
       

  }

  @Override
  public boolean done() {
       
    return false;
  }

  @Override
  public void execute(int btnPressed) {
       
    
  }

}
