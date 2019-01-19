/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.robot;
import org.usfirst.frc.team238.robot.Drivetrain;

/**
 * Add your docs here.
 */
public class TestSweet238 
{

    private Robot myRobot;
    int counter = 0;
    public TestSweet238(Robot myRobot) {

        this.myRobot = myRobot;   

    }

    public void testDriveTrainEncoders() {
        //Reseting the Encoders to start from a known position
        myRobot.myDriveTrain.resetEncoders();
       
        //run for amount of runs specified in the TEST_DRIVE_COUNTER

        if (counter < CrusaderCommon.TEST_DRIVE_COUNTER) {
        
           myRobot.myDriveTrain.drive(CrusaderCommon.TEST_LEFT_MOTOR_VALUE, CrusaderCommon.TEST_RIGHT_MOTOR_VALUE); 

        }
        else{
            
            myRobot.myDriveTrain.drive(CrusaderCommon.AUTO_DRIVE_IDLE, CrusaderCommon.AUTO_DRIVE_IDLE); 
            myRobot.myDriveTrain.getEncoderTicks2();
            
        }
 
        counter++;
        //get encoder value for Right and Left
        //Check with Base Line
        //Difference is in tolerance 
        //set indicator true if in tolerance(green) else set indicator to false(red)
    }   

}
