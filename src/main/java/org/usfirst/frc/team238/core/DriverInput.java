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
public class DriverInput {

    public double leftSide;
    public double rightSide;
    
    public DriverInput(double leftSide, double rightSide) {
        this.leftSide = leftSide;
        this.rightSide = rightSide;


    }
    public double getLeftEncoder (){
        return leftSide;

    }
    public double getRightEncoder (){
        return rightSide;

    }
}

