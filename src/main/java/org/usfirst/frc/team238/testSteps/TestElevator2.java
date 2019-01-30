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
import org.usfirst.frc.team238.robot.TestSweet238;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class TestElevator2 implements TestStep{

    String[] parameters;
    TestSweet238 theSuiteOfTests;
    boolean done = false;
  double elevatorSetpointTwo;
    
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
        elevatorSetpointTwo = SmartDashboard.getNumber("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointTwo = " + elevatorSetpointTwo);
  
    }
  
    @Override
    public boolean process() {
      Logger.Log("TestElevator.process() ");
    done = theSuiteOfTests.testElevator(elevatorSetpointTwo);
    return done;
    }
  
    @Override
    public boolean done() {
      Logger.Log("TestElevator.done() ");
      return done;
    }
  
    @Override
    public void showParams() {
       
      //RM SmartDashboard.putString("Param 1 - targetValue", "0");
      //RM SmartDashboard.putString("Param 2 - motorSpeed", "0");
      //RM SmartDashboard.putString("Param 3 - targetYaw", "0");
      //RM SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
      //RM SmartDashboard.putString("Param 5 - collisionToggle", "0");
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
