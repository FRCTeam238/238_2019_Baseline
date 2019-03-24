/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Robot;

public class CommandTankDriveTurbo extends AbstractCommand {

    Drivetrain myDrivetrain;
    double leftPowerPct;
    double rightPwerPc;
  
    public CommandTankDriveTurbo(Robot myRobot, double leftPowerPct, double rightPwerPct) {
        this.myDrivetrain = myRobot.myDriveTrain;
        this.leftPowerPct = leftPowerPct;
        this.rightPwerPc = rightPwerPct;
    }
  
    public void prepare() {
    }
  
    @Override
    public void execute() {
        myDrivetrain.drive(leftPowerPct, rightPwerPc);
    }
  
  }
  
