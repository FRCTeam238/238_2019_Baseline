/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.core;

/**
 * Add your docs here.
 */
public class TargetValues {

    double shoulderAngle;
    double elevatorHeights;
    Boolean wristPosition; //True = extended, false = retracted
    
    public TargetValues(double shoulderAngle, double elevatorHeights, Boolean wrist) {
        this.shoulderAngle = shoulderAngle;
        this.elevatorHeights = elevatorHeights;
        this.wristPosition = wrist;

    }
    public double getShoulderAngle (){
        return shoulderAngle;

    }
    public double getElevatorHeights (){
        return elevatorHeights;

    }
    public Boolean getWristPopsition(){
        return wristPosition;

    }
}

