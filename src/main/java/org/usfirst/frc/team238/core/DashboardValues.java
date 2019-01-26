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
public class DashboardValues {

    double elevatorSetpointOne;
    double elevatorSetpointTwo;
    double elevatorSetpointThree;
    
    public DashboardValues(double elevatorSetpointOne, double elevatorSetpointTwo, double elevatorSetpointThree) {
        this.elevatorSetpointOne = elevatorSetpointOne;
        this.elevatorSetpointTwo = elevatorSetpointTwo;
        this.elevatorSetpointThree = elevatorSetpointThree;

    }
    public double getElevatorSetpointOne(){
        return elevatorSetpointOne;

    }

    public double getElevatorSetpointTwo() {
        return elevatorSetpointTwo;

    }

    public double getElevatorSetpointThree(){
        return elevatorSetpointThree;
    } 
    
}

