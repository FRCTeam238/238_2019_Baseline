package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.DashBoard238;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.core.XBoxValues;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.ScoringPosition;
import org.usfirst.frc.team238.robot.Shoulder;
import org.usfirst.frc.team238.robot.Wrist;

public class CommandScoringPosition extends AbstractCommand {

    Elevator theElevator;
    Shoulder theShoulder;
    Wrist theWrist;
    DashBoard238 dashboard;
    boolean done = false;

    protected double height = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ZERO_VALUE;// 77;
    double angle = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ZERO_VALUE;
    boolean extended = CrusaderCommon.ScoringPositions.WRIST_FALSE;

    public CommandScoringPosition(Robot theRobot) {
        this.theElevator = theRobot.myElevator;
        this.theShoulder = theRobot.myShoulder;
        this.theWrist = theRobot.myWrist;
        dashboard = DashBoard238.getInstance();
    }

    @Override
    public void execute() {
        Logger.Log("CommandScoringPosition.execute(): This isn't the execute you're looking for.");
    }

    @Override
    public void execute(int btnPressed) {
        // execute();

        switch (btnPressed) {
        case XBoxValues.Y: // 4, Cargo Level Three
            // setArmPositions(CrusaderCommon.ROCKET_CARGO_LEVEL_THREE_SHOULDER,
            // CrusaderCommon.ROCKET_CARGO_LEVEL_THREE_ELEVATOR,
            // CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_WRIST);
            setArmPositions(CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_THREE);
            break;

        case XBoxValues.B: // 2, Cargo Level Two
            // setArmPositions(CrusaderCommon.ROCKET_CARGO_LEVEL_TWO_SHOULDER,
            // CrusaderCommon.ROCKET_CARGO_LEVEL_TWO_ELEVATOR,
            // CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_WRIST);
            setArmPositions(CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_TWO);
            break;

        case XBoxValues.A: // 1, Cargo Level One
            // setArmPositions(CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_SHOULDER,
            // CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_ELEVATOR, CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_WRIST);
            setArmPositions(CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ONE);
            break;

        case XBoxValues.DPadUp: // 26, Hatch Level Three
            // setArmPositions(CrusaderCommon.ROCKET_HATCH_LEVEL_THREE_SHOULDER,
            // CrusaderCommon.ROCKET_HATCH_LEVEL_THREE_ELEVATOR, CrusaderCommon.HATCH_LEVEL_ONE_WRIST);
            setArmPositions(CrusaderCommon.ScoringPositions.ROCKET_HATCH_LEVEL_THREE);
            break;

        case XBoxValues.DPadRight: // 25, Hatch Level Two
            // setArmPositions(CrusaderCommon.ROCKET_HATCH_LEVEL_TWO_SHOULDER,
            // CrusaderCommon.ROCKET_HATCH_LEVEL_TWO_ELEVATOR, CrusaderCommon.ROCKET_HATCH_LEVEL_TWO_WRIST);
            setArmPositions(CrusaderCommon.ScoringPositions.ROCKET_HATCH_LEVEL_TWO);
            break;

        case XBoxValues.DPadDown: // 24, Hatch Level One
            // setArmPositions(CrusaderCommon.HATCH_LEVEL_ONE_SHOULDER, CrusaderCommon.HATCH_LEVEL_ONE_ELEVATOR,
            //         CrusaderCommon.HATCH_LEVEL_ONE_WRIST);
            setArmPositions(CrusaderCommon.ScoringPositions.ROCKET_HATCH_LEVEL_ONE);
            break;

        case XBoxValues.X: // 3, Cargo Ship Cargo
            setArmPositions(CrusaderCommon.ScoringPositions.CARGO_SHIP_CARGO);
            // setArmPositions(CrusaderCommon.CARGO_SHIP_CARGO_SHOULDER,
            // CrusaderCommon.CARGO_SHIP_CARGO_ELEVATOR,
            // CrusaderCommon.CARGO_SHIP_CARGO_WRIST);
            break;

        case XBoxValues.Back: // back Tuning
            //            ScoringPosition scoringPosition = DashBoard238.getInstance().getTargetValues();
            //            setArmPositions(scoringPosition);
            setArmPositionsAuto(CrusaderCommon.ScoringPositions.SAFE_DRIVING_MODE);
            break;

        case XBoxValues.Start: // 8 Tuning
            theElevator.resetEncoders();
            break;
        // case XBoxValues.DPadDownLeft:
        //     setArmPositions(CrusaderCommon.ScoringPositions.FLOOR_PICKUP);
        //     break;
        case 42:
            setArmPositionsAuto(angle, height, CrusaderCommon.ScoringPositions.WRIST_FALSE);
            break;

        }
    }

    private void setArmPositions(ScoringPosition scoringPosition) {
        setArmPositions(scoringPosition.getShoulderAngle(), scoringPosition.getElevatorHeight(),
                scoringPosition.getWristExtended());
    }

    private void setArmPositions(double shoulderPos, double elevatorPos, boolean wristExtended) {
        theShoulder.setshoulder(shoulderPos);
        theElevator.setSetpoint(elevatorPos);
        theWrist.setWrist(wristExtended);
        done = true;
    }

    private void setArmPositionsAuto(ScoringPosition scoringPosition) {
        setArmPositionsAuto(scoringPosition.getShoulderAngle(), scoringPosition.getElevatorHeight(),
                scoringPosition.getWristExtended());
    }

    private void setArmPositionsAuto(double shoulderPos, double elevatorPos, boolean wristExtended) {
        double currentDelta = Math.abs(theElevator.getHeight() - elevatorPos);
        // wait to set shoulder until elevator is relatively close
        if (currentDelta <= 5) {
            theShoulder.setshoulder(shoulderPos);
            done = true;
        }
        theElevator.setSetpoint(elevatorPos);
        theWrist.setWrist(wristExtended);
    }

    @Override
    public void prepare() {
    }

    public void setParams(String params[]) {
        ScoringPosition defaultPostion = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ONE;
        if ((params[0] != null) || (!params[0].isEmpty())) {
            height = Double.parseDouble(params[0]);
        } else {
            height = defaultPostion.getElevatorHeight();
        }

        if ((params[1] != null) || (!params[1].isEmpty())) {
            angle = Double.parseDouble(params[1]);
        } else {
            angle = defaultPostion.getShoulderAngle();
        }

        if ((params[2] != null) || (!params[2].isEmpty())) {
            extended = Boolean.parseBoolean(params[2]);
        } else {
            extended = defaultPostion.getWristExtended();
        }
        Logger.Log("CommandScoringPosition():setParams(): Hight =  " + height + " Angle = " + angle);
    }

    @Override
    public boolean done() {
        return done;
    }
}
