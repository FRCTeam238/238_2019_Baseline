/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.testSteps;

import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.core.TestStep;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.Wrist;

/**
 * Add your docs here.
 */
public class TestWrist implements TestStep {

    Robot myRobot;
    String parameters[];
    int counter;
   
    boolean done = false;
    Wrist theWrist;

    public TestWrist(Robot robot) {

        this.theWrist = robot.myWrist;
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
        Logger.Log("TestWrist.init() parameters = " + parameters);
    }

    @Override
    public boolean process() {
       
        if (counter == 0) {
            Logger.Log("TestWrist.process():  Test has started");
            theWrist.extendWrist();
            counter++;
        }

       

        return false;
    }

    @Override
    public boolean done() {
        Logger.Log("TestWrist.done() ");
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
