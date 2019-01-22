package org.usfirst.frc.team238.testSteps;

import org.usfirst.frc.team238.core.TestStep;
import org.usfirst.frc.team238.robot.TestSweet238;

import org.usfirst.frc.team238.core.Logger;

public class TestStepFinished implements TestStep {

  String[] parameters;

  @Override
  public void prepare() {

  }

  @Override
  public void init() {
    // TODO Auto-generated method stub

  }

  // used when autonomous is interrupted
  @Override
  public void reset() {

  }

  @Override
  public void init(String params[], TestSweet238 theControllerp) {
    // TODO Auto-generated method stub

  }

  @Override
  public void process() {
    //Logger.Log("AutonomousMode Done ");

  }

  @Override
  public boolean done() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void showParams() {
    // TODO Auto-generated method stub
    //RM SmartDashboard.putString("Param 1 - targetValue", "0");
    //RM SmartDashboard.putString("Param 2 - motorSpeed", "0");
    //RM SmartDashboard.putString("Param 3 - targetYaw", "0");
    //RM SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
    //RM SmartDashboard.putString("Param 5 - collisionToggle", "0");
  }

  @Override
  public void updateParams() {
    // TODO Auto-generated method stub

  }

  @Override
  public String getParam(int value) {
    String output = "";
    if (parameters == null || parameters.length - 1 < value) {
      output = "";
    } else {
      output = parameters[value];
    }
    return output;
  }

}
