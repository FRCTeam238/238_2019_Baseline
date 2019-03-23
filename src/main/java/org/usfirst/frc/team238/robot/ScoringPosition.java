/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.robot;

public class ScoringPosition {
    private double elevatorHeight;
    private double shoulderAngle;
    private boolean wristExtended;

    public double getElevatorHeight() {
        return elevatorHeight;
    }

    public void setElevatorHeight(double height) {
        elevatorHeight = height;
    }

    public double getShoulderAngle() {
        return shoulderAngle;
    }

    public void setShoulderAngle(double angle) {
        shoulderAngle = angle;
    }

    public boolean getWristExtended() {
        return wristExtended;
    }

    public void setWristExtended(boolean extended) {
        wristExtended = extended;
    }

    protected ScoringPosition() {
    }

}
 

