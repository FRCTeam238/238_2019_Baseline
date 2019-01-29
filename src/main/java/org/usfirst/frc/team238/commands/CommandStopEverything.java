package org.usfirst.frc.team238.commands;
\
import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.TestCoreObject;

public class CommandStopEverything extends AbstractCommand {

    /**
   * THIS IS THE DEFAULT STOP EVERYTHING ON OPERATOR CONTROLLS
   */
    TestCoreObject myTestCoreObject;
    
    public CommandStopEverything(TestCoreObject testCoreObject) {
   
      this.myTestCoreObject = testCoreObject; 
     
  
  }

  public CommandStopEverything() {
	}

@Override
  public void execute() {
    myTestCoreObject.turnOffChannelTwo();
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
