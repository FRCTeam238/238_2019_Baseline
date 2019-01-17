/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.robot;

/**
 * Add your docs here.
 */
public class TestSweet238 
{

    private Robot myRobot;

    public TestSweet238(Robot myRobot) {

        this.myRobot = myRobot;   

    }

    public void testDriveTrainEncoders() {
        //Reseting the Encoders to start from a known position
        myRobot.myDriveTrain.resetEncoders();
        //run for 10 seconds
        myRobot.myDriveTrain.encoderRight;

        //get encoder value for Right and Left
        //Check with Base Line
        //Difference is in tolerance 
        //set indicator true if in tolerance(green) else set indicator to false(red)
    }   

}
