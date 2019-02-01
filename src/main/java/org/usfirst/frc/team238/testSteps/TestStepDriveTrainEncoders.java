package org.usfirst.frc.team238.testSteps;

import org.usfirst.frc.team238.core.TestStep;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.TestSweet238;
import org.usfirst.frc.team238.core.EncoderValues;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestStepDriveTrainEncoders implements TestStep {

  
  String            parameters[];
  TestSweet238 theSuiteOfTests;
  int count;
    boolean done = false;
    private Robot myRobot;
    private long startTime;
    private int counter;

    public TestStepDriveTrainEncoders(Robot robot){

        this.myRobot = robot;

  }




  @Override
  public void prepare() {
    theSuiteOfTests.myRobot.myDriveTrain.resetEncoders();

  }

  @Override
  public void init(String params[]) {

    //theSuiteOfTests = theController;
    parameters = params;
        Logger.Log("TestStepDriveTrainEncoders.init() parameters = " + parameters[0]);
  }


  @Override
    public boolean process() {

        //Logger.Log("TestStateDriveTrainEncoders.Process() " + count);
        // count++;
        // done = theSuiteOfTests.testDriveTrainEncoders();
        //     return done;

        boolean returnValue = false;

        //Reseting the Encoders to start from a known position

        if (counter == 0) {
            Logger.Log("TestStepDriveTrainEncoders.process(): start of drivetrain test");
            startTime = System.currentTimeMillis();
            myRobot.myDriveTrain.drive(CrusaderCommon.TEST_LEFT_MOTOR_VALUE, CrusaderCommon.TEST_RIGHT_MOTOR_VALUE);
        }
        //Every first call of the method sets things to starting values

        //run for amount of runs specified in the TEST_DRIVE_COUNTER
        //increases counter by one every time method is called
        if (CrusaderCommon.TEST_DRIVE_COUNTER == counter++) {

            myRobot.myDriveTrain.drive(CrusaderCommon.AUTO_DRIVE_IDLE, CrusaderCommon.AUTO_DRIVE_IDLE);

            double elapsedTime = System.currentTimeMillis() - startTime;

            EncoderValues currentEncoderValues = myRobot.myDriveTrain.getEncoderTicks2();

            double currentLeftEncoder = currentEncoderValues.getLeftEncoder();
            double currentRightEncoder = currentEncoderValues.getRightEncoder();

            double leftEncoderDifference = currentLeftEncoder - CrusaderCommon.TEST_DRIVETRAIN_BASELINE;
            double rightEncoderDifference = currentRightEncoder - CrusaderCommon.TEST_DRIVETRAIN_BASELINE;

            boolean leftEncoderTolerance;
            boolean rightEncoderTolerance;

            boolean encoderDifferenceTolerance;
            double encoderDifferenceBetween = Math.abs(leftEncoderDifference - rightEncoderDifference);

            if (Math.abs(leftEncoderDifference) < CrusaderCommon.TEST_DRIVETRAIN_TOLERANCE) {

                leftEncoderTolerance = true;

            } else {

                leftEncoderTolerance = false;
            }

            if (Math.abs(rightEncoderDifference) < CrusaderCommon.TEST_DRIVETRAIN_TOLERANCE) {

                rightEncoderTolerance = true;

            } else {

                rightEncoderTolerance = false;
            }

            if (rightEncoderTolerance && leftEncoderTolerance
                    && encoderDifferenceBetween < CrusaderCommon.TEST_DRIVETRAIN_TOLERANCE) {
                encoderDifferenceTolerance = true;
            } else {
                encoderDifferenceTolerance = false;
            }

            myRobot.myDashBoard238.setTestDrivetrainEncodersIndicators(currentLeftEncoder, currentRightEncoder,
                    leftEncoderTolerance, rightEncoderTolerance, encoderDifferenceTolerance, elapsedTime);

            Logger.Log("TestStepDriveTrainEncoders.process(): elapsedTime = " + elapsedTime);
            //puts in RIOlog that we have run this code and the elapsed time from start of test

            returnValue = true;
            //tells the test controller that the test has been completed,
            //prevents motors from running infinitely

            counter = 0;
            //Resets counter for easy testing ie no restarting code

            myRobot.myDriveTrain.resetEncoders();

        }

        //----get encoder value for Right and Left----
        //----Check with Base Line----
        //----Difference is in tolerance----
        //----set indicator true if in tolerance(green) else set indicator to false(red)----

        return returnValue;
    }

    
  

  @Override
  public boolean done() {

        if (done) {
            count = 0;
            return true;
        }
    return false;
  }

  @Override
  public void reset() {

    // turnRightCommand.reset();

  }

  @Override
  public void init() {
    // TODO Auto-generated method stub

  }

  @Override
  public void showParams() {
    // TODO Auto-generated method stub
    //RM SmartDashboard.putString("Param 1 - targetValue", parameters[0]);
    //RM SmartDashboard.putString("Param 2 - motorSpeed", parameters[1]);
    //RM SmartDashboard.putString("Param 3 - newTargetYaw", parameters[2]);
    //RM SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
    //RM SmartDashboard.putString("Param 5 - collisionToggle", "0");
  }

  @Override
  public void updateParams() {
    // TODO Auto-generated method stub
    String param1;
    String param2;
    String param3;
    String param4;
    String param5;

    param1 = SmartDashboard.getString("Param 1 - targetValue", "");
    parameters[0] = param1;
    param2 = SmartDashboard.getString("Param 2 - motorSpeed", "");
    parameters[1] = param2;
    param3 = SmartDashboard.getString("Param 3 - targetYaw", "");
    parameters[2] = param3;
    param4 = SmartDashboard.getString("Param 4 - ultrasonicTarget", "");
    parameters[3] = param4;
    param5 = SmartDashboard.getString("Param 5 - collisionToggle", "");
    parameters[4] = param5;
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