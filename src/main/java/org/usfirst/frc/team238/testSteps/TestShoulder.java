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
public class TestShoulder implements TestStep {

    Robot myRobot;
    String parameters[];
    int counter;
    double shoulderTarget;
    double currentAngle;
    double angleDifference;
    boolean done = false;
    // Put shoulder to known starting position if not already at it
    // get target from smartdashboard
    // move up set number of degrees
    // record moved number of degrees
    // compare with target number of degrees
    // push to smartdashboard

    public TestShoulder(Robot robot) {

        this.myRobot = robot;
    }

    @Override
    public void prepare() {

    }

    @Override
    public void init() {

    }

    @Override
    public void init(String[] params) {

        parameters = params;
        Logger.Log("TestShoulder.init() parameters = " + parameters);
    }

    @Override
    public boolean process() {
        // Put shoulder to known starting position if not already at it
        // get target from smartdashboard X
        // move up set number of degrees X
        // record moved number of degrees X
        // compare with target number of degrees x
        // push to smartdashboard
        if (counter == 0) {
            Logger.Log("TestShoulder.process():  Test has started");
            shoulderTarget = myRobot.myDashBoard238.getShoulderData();
            myRobot.myShoulder.setshoulder(shoulderTarget);
            counter++;
        }

        currentAngle = myRobot.myShoulder.getAngle();
        angleDifference = currentAngle - shoulderTarget;
        if (Math.abs(angleDifference) < CrusaderCommon.SHOULDER_TARGET_TOLERANCE) {

            done = true;
            // This will be the shoulder on the Robot, but we are calling wrist to use its
            // methods

            myRobot.myDashBoard238.putShoulderData(currentAngle);

        }

        return false;
    }

    @Override
    public boolean done() {
        Logger.Log("TestShoulder.done() ");
        return done;
    }

    @Override
    public void reset() {
        done = false;
    }

    @Override
    public void showParams() {

    }

    @Override
    public void updateParams() {

    }

    @Override
    public String getParam(int value) {
        return null;
    }

}
