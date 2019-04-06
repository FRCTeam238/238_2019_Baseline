/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;

/**
 * Add your docs here.
 */
public class CommandSetTankDriveSpeedAdjustment extends AbstractCommand {
    private CommandTankDrive tankDriveCommand;
    private double speedAdjustment;
    private double defaultSpeedAdjustment;

    public CommandSetTankDriveSpeedAdjustment(CommandTankDrive tankDriveCommand, double speedAdjustment) {
        this.tankDriveCommand = tankDriveCommand;
        this.speedAdjustment = speedAdjustment;
        this.defaultSpeedAdjustment = tankDriveCommand.getSpeedAdjustment();
    }
    
    @Override
    public void execute() {
        this.tankDriveCommand.setSpeedAdjustment(this.speedAdjustment);
    }

    @Override
    public void stop() {
        this.tankDriveCommand.setSpeedAdjustment(this.defaultSpeedAdjustment);
    }
}
