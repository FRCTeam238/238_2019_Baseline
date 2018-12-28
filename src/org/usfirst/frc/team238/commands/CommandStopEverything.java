package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.IntakeWrist;

public class CommandStopEverything implements Command {

  IntakeWrist theIntake;
  Elevator theElevator;
  /**
   * THIS IS THE DEFAULT STOP EVERYTHING ON OPERATOR CONTROLLS
   */
  public CommandStopEverything(IntakeWrist myIntake, Elevator myElevator) {
    // TODO Auto-generated constructor stub
    this.theIntake = myIntake;
    this.theElevator = myElevator;
    
  }

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    theIntake.stop();
    //theElevator.stop();
    theElevator.elevatorShiftCube();
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
