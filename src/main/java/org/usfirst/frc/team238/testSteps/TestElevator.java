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

/**
 * Add your docs here.
 */
public class TestElevator implements TestStep {

  String[] parameters;
  boolean done = false;
  double elevatorSetpoint;
  int counter = 0;
  Robot myRobot;
  double elevatorCurrentHeight;
  double elevatorHeightDifference;
  boolean elevatorHeightTolerance;

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
    done = false;
  }

  @Override
  public void init(String params[]) {
    //theSuiteOfTests = theController;
    parameters = params;
    // DashboardValues elevatorSetpoints;
    switch (parameters[0]) {
        case "Elevator Test":
            elevatorSetpoint = myRobot.myDashBoard238.getElevator1();//  .elevatorTestInfo1.getEntry().getDouble(CrusaderCommon.ELEVATOR_SETPOINT_ONE);
            break;
        
        case "Elevator Test2":
            elevatorSetpoint = myRobot.myDashBoard238.elevatorTestInfo2.getEntry().getDouble(CrusaderCommon.ELEVATOR_SETPOINT_TWO);
            break;
            
        case "Elevator Test3":
            elevatorSetpoint = myRobot.myDashBoard238.elevatorTestInfo3.getEntry().getDouble(CrusaderCommon.ELEVATOR_SETPOINT_THREE);
            break;
        
        default:
            elevatorSetpoint = 0;
            break;
    }
    
    Logger.Log("TestElevator.init() elevatorSetPoint = " + elevatorSetpoint + "  Test = " + parameters[0]);

  }

  @Override
  public boolean process() {
    Logger.Log("TestElevator.process() ");

    double elevatorSetpointDifference;
    if (counter == 0) {
      // set elevator dashboard objects to not working

      myRobot.myDashBoard238.setTestElevatorIndicators(false);
      Logger.Log("TestElevator.process(): start of elevator test");

      myRobot.myElevator.setSetpoint(elevatorSetpoint);
      // Give the elevator a new setpoint = distance (inches)
      counter++;
    }

    elevatorCurrentHeight = myRobot.myElevator.getHeight();
    Logger.Log("TestElevator.process(): elevatorCurrentHeight = " + elevatorCurrentHeight);
    Logger.Log("TestElevator.process(): targetSetPoint = " + elevatorSetpoint);
    elevatorSetpointDifference = Math.abs(elevatorCurrentHeight - elevatorSetpoint);
    if (elevatorSetpointDifference <= CrusaderCommon.ELEVATOR_SETPOINT_TOLERANCE) {

      myRobot.myDashBoard238.putTestElevatorTestOne(elevatorCurrentHeight);

      Logger.Log("TestElevator.process(): TargetSetpoint: elevatorHeight = " + elevatorCurrentHeight);
      // puts in RIOlog that we have run this code and the elevator's current height

            done = true;
            elevatorHeightDifference = elevatorCurrentHeight - elevatorSetpoint;
            //TODO: look at tolerance
            if (Math.abs(elevatorHeightDifference) < CrusaderCommon.TEST_ELEVATOR_TOLERANCE) {
                elevatorHeightTolerance = true; 
            } else {
                elevatorHeightTolerance = false;
            }
    
      myRobot.myDashBoard238.putElevatorData(elevatorCurrentHeight, elevatorHeightTolerance);

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
