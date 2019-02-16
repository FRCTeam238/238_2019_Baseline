package org.usfirst.frc.team238.testSteps;

import org.usfirst.frc.team238.core.TestStep;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.core.EncoderValues;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestStaticDriveTrainEncoders implements TestStep {

    String parameters[];
    boolean done = false;
    private Robot myRobot;
    private long startTime;
    private int counter = 0;

    public TestStaticDriveTrainEncoders(Robot robot) {

        this.myRobot = robot;

    }

    @Override
    public void prepare() {
        myRobot.myDriveTrain.resetEncoders();

    }

    @Override
    public void init(String params[]) {

        // theSuiteOfTests = theController;
        parameters = params;
        Logger.Log("TestStepDriveTrainEncoders.init() parameters = " + parameters[0]);
    }

    @Override
    public boolean process() {

        //Logger.Log("TestStateDriveTrainEncoders.Process() start: counter = " + counter);

        boolean returnValue = false;

        Logger.Log("TestStaticDriveTrainEncoders.process(): start of drivetrain test");

        EncoderValues currentEncoderValues = myRobot.myDriveTrain.getEncoderTicks2();

        double currentLeftEncoder = currentEncoderValues.getLeftEncoder();
        double currentRightEncoder = currentEncoderValues.getRightEncoder();

        myRobot.myDashBoard238.setTestDrivetrainEncodersIndicators(currentLeftEncoder, currentRightEncoder);

        return returnValue;
    }

    @Override
    public boolean done() {

        if (done) {
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
        // RM SmartDashboard.putString("Param 1 - targetValue", parameters[0]);
        // RM SmartDashboard.putString("Param 2 - motorSpeed", parameters[1]);
        // RM SmartDashboard.putString("Param 3 - newTargetYaw", parameters[2]);
        // RM SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
        // RM SmartDashboard.putString("Param 5 - collisionToggle", "0");
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