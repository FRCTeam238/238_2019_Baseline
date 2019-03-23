/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.robot;

/**
 * Add your docs here.
 */
public class ScoringPositionBuilder {
    private ScoringPosition scoringPosition = new ScoringPosition();

    public ScoringPositionBuilder elevator(double height) {
        scoringPosition.setElevatorHeight(height);
        return this;
    }

    public ScoringPositionBuilder shoulder(double angle) {
        scoringPosition.setShoulderAngle(angle);
        return this;
    }

    public ScoringPositionBuilder wrist(boolean extended) {
        scoringPosition.setWristExtended(extended);
        return this;
    }

    public ScoringPosition toScoringPosition() {
        return scoringPosition;
    }
}
