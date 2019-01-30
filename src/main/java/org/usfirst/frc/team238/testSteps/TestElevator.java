/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.testSteps;

import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.core.TestStep;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.TestSweet238;
import org.usfirst.frc.team238.core.DashBoard238;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class TestElevator implements TestStep {

  String[] parameters;
  TestSweet238 theSuiteOfTests;
  boolean done = false;
  double elevatorSetpointOne;
  int counter = 0;
  Robot myRobot;
  double setPoint;
  double elevatorCurrentHeight;

  public TestElevator(Robot robot)
  {
  
    this.myRobot = robot;
}

  @Override
  public void prepare() {

  }

  @Override
  public void init() {

  }

  // used when autonomous is interrupted
  @Override
  public void reset() {

  }

  @Override
  public void init(String params[], TestSweet238 theController) {
    theSuiteOfTests = theController;
    parameters = params;
    // DashboardValues elevatorSetpoints;
    elevatorSetpointOne = SmartDashboard.getNumber("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE);
    Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointOne = " + elevatorSetpointOne);

  }

  @Override
  public boolean process() {
    Logger.Log("TestElevator.process() ");

    double elevatorSetpointDifference;
    if (counter == 0) {
      // set elevator dashboard objects to not working

      myRobot.myDashBoard238.setTestElevatorIndicators(false);
      Logger.Log("TestElevator.process(): start of elevator test");

      myRobot.myElevator.setSetpoint(setPoint);
      // Give the elevator a new setpoint = distance (inches)
      counter++;
    }

    elevatorCurrentHeight = myRobot.myElevator.getHeight();
    Logger.Log("TestElevator.process(): elevatorCurrentHeight = " + elevatorCurrentHeight);
    Logger.Log("TestElevator.process(): targetSetPoint = " + setPoint);
    elevatorSetpointDifference = Math.abs(elevatorCurrentHeight - setPoint);
    if (elevatorSetpointDifference <= CrusaderCommon.ELEVATOR_SETPOINT_TOLERANCE) {

      myRobot.myDashBoard238.putTestElevatorTestOne(elevatorCurrentHeight);

      Logger.Log("TestElevator.process(): TargetSetpoint: elevatorHeight = " + elevatorCurrentHeight);
      // puts in RIOlog that we have run this code and the elevator's current height

      done = true;

      counter = 0;
      // Resets counter for easy testing ie no restarting code

    }

    Logger.Log("TestElevator.process(): done = " + done);
    return done;

  }

  @Override
  public boolean done() {
    Logger.Log("TestElevator.done() ");
    return done;
  }

  @Override
  public void showParams() {

    // RM SmartDashboard.putString("Param 1 - targetValue", "0");
    // RM SmartDashboard.putString("Param 2 - motorSpeed", "0");
    // RM SmartDashboard.putString("Param 3 - targetYaw", "0");
    // RM SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
    // RM SmartDashboard.putString("Param 5 - collisionToggle", "0");
  }

  @Override
  public void updateParams() {

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
