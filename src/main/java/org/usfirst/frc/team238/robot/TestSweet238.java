/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.robot;

import org.usfirst.frc.team238.core.EncoderValues;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Drivetrain;

/**
 * Add your docs here.
 */
public class TestSweet238 
{
    static double startTime;
    public Robot myRobot;
    int counter = 0;
    public TestSweet238(Robot myRobot) {

        this.myRobot = myRobot;   

    }

    public boolean testDriveTrainEncoders() {
        
        boolean returnValue = false;

        //Reseting the Encoders to start from a known position
       
        if (counter == 0) {
            Logger.Log("TestSweet38.testDriveTrainEncoders(): start of drivetrain test");
            startTime = System.currentTimeMillis();
            myRobot.myDriveTrain.drive(CrusaderCommon.TEST_LEFT_MOTOR_VALUE, CrusaderCommon.TEST_RIGHT_MOTOR_VALUE);
        }
        //Every first call of the method sets things to starting values


        //run for amount of runs specified in the TEST_DRIVE_COUNTER
        //increases counter by one every time method is called
        if(CrusaderCommon.TEST_DRIVE_COUNTER == counter++){

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


            if (rightEncoderTolerance 
            && leftEncoderTolerance 
            && encoderDifferenceBetween < CrusaderCommon.TEST_DRIVETRAIN_TOLERANCE) {
                encoderDifferenceTolerance = true;
            }
            else {
                encoderDifferenceTolerance = false;
            }

            myRobot.myDashBoard238.setTestDrivetrainEncodersIndicators(currentLeftEncoder, currentRightEncoder,
                    leftEncoderTolerance, rightEncoderTolerance, 
                    encoderDifferenceTolerance, elapsedTime);
                     
            Logger.Log("TestSweet238.testDriveTrainEncoders(): elapsedTime = " + elapsedTime);
            //puts in RIOlog that we have run this code and the elapsed time from start of test
            
            returnValue = true;
            /*tells the test controller that the test has been completed,
            prevents motors from running infinitely
            */
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

  public boolean testElevator(){

        //set elevator dashboard objects to not working
        myRobot.myDashBoard238.setTestElevatorIndicators(false);
        
        return true;
  }
}
