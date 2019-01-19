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
public class EncoderValues {

    double leftEncoder;
    double rightEncoder;
    
    public EncoderValues(double leftEncoder, double rightEncoder) {
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;


    }
    public double getLeftEncoder (){
        return leftEncoder;

    }
    public double getRightEncoder (){
        return rightEncoder;

    }
}

