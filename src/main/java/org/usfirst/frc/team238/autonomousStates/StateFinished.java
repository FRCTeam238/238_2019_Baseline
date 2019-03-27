package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;

public class StateFinished implements AutonomousState {

    String[] parameters;
    int stopCount = 0;

    @Override
    public void prepare() {

    }

    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    // used when autonomous is interrupted
    @Override
    public void reset() {

    }

    @Override
    public void init(String params[], CommandController theMcp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void process() {
        // Logger.Log("AutonomousMode Done ");
        stopCount++;
    }

    @Override
    public boolean done() {
        boolean done = false;
        
        if (stopCount > 1) {
            done = true;
        }
        
        return done;
    }

    @Override
    public void showParams() {
        // TODO Auto-generated method stub
        // RM SmartDashboard.putString("Param 1 - targetValue", "0");
        // RM SmartDashboard.putString("Param 2 - motorSpeed", "0");
        // RM SmartDashboard.putString("Param 3 - targetYaw", "0");
        // RM SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
        // RM SmartDashboard.putString("Param 5 - collisionToggle", "0");
    }

    @Override
    public void updateParams() {
        // TODO Auto-generated method stub

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

    @Override
    public String[] getParams() {

        return parameters;
    }
}
