package org.usfirst.frc.team238.commands;
/*
import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.DashBoard238;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.ScoringPosition;
import org.usfirst.frc.team238.robot.Shoulder;
import org.usfirst.frc.team238.robot.Wrist;

public class CommandExitSafeMode extends AbstractCommand {

    // go up one inch, move shoulder out
    // move to cargo level one
    //
    //
    //
    Elevator theElevator;
    Shoulder theShoulder;
    Wrist theWrist;
    DashBoard238 dashboard;

    int count = 0;
    double height = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ZERO_VALUE;// 77;
    double angle = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ZERO_VALUE;
    boolean extended = CrusaderCommon.ScoringPositions.WRIST_FALSE;
    int currentStep = 0;

    public CommandExitSafeMode(Robot theRobot) {
        this.theElevator = theRobot.myElevator;
        this.theShoulder = theRobot.myShoulder;
        this.theWrist = theRobot.myWrist;
        dashboard = DashBoard238.getInstance();
    }

    @Override
    public void execute() {
        ScoringPosition values = dashboard.getTargetValues();
        height = values.getElevatorHeight();
        angle = values.getShoulderAngle();
        extended = values.getWristExtended();

        theShoulder.setshoulder(angle);
        theElevator.setSetpoint(height);
        theWrist.setWrist(extended);
        Logger.Log("CommandExitSafeMode.execute(): height = " + height + "\n angle = " + angle + "\n extended = "
                + extended);

        if (currentStep == 0) {
            setArmPositions(0, 2, false);
            if (theElevator.getHeight() > 1.5) {
                currentStep = 1;
            }
        } else if (currentStep == 1) {
            setArmPositions(20, -17, CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ONE.getWristExtended());
        }
    }

    @Override
    public void execute(int btnPressed) {
        // setArmPositions(CrusaderCommon.SAFE_DRIVING_MODE_SHOULDER,
        // CrusaderCommon.SAFE_DRIVING_MODE_ELEVATOR,
        // CrusaderCommon.SAFE_DRIVING_MODE_WRIST);
        setArmPositions(CrusaderCommon.ScoringPositions.SAFE_DRIVING_MODE.getShoulderAngle(),
                CrusaderCommon.ScoringPositions.SAFE_DRIVING_MODE.getElevatorHeight(),
                CrusaderCommon.ScoringPositions.SAFE_DRIVING_MODE.getWristExtended());
    }

    private void setArmPositions(double shoulderPos, double elevatorPos, boolean wristExtended) {
        theShoulder.setshoulder(shoulderPos);
        theElevator.setSetpoint(elevatorPos);
        theWrist.setWrist(wristExtended);
    }

    @Override
    public void prepare() {
        // TODO Auto-generated method stub

    }

    public void setParams(String params[]) {
        if ((params[0] != null) || (!params[0].isEmpty())) {
            height = Double.parseDouble(params[0]);
        } else {
            height = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ONE.getElevatorHeight();
        }

        if ((params[1] != null) || (!params[1].isEmpty())) {
            angle = Double.parseDouble(params[1]);
        } else {
            angle = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ONE.getShoulderAngle();
        }

        if ((params[0] != null) || (!params[0].isEmpty())) {
            extended = Boolean.parseBoolean(params[0]);
        } else {
            extended = CrusaderCommon.ScoringPositions.ROCKET_CARGO_LEVEL_ONE.getWristExtended();
        }

        // switch (whatLevel) {
        // case CrusaderCommon.CARGO_LEVEL_ONE:
        // //get target values fro dashboard

        // //assign to local variables
        // break;
        // case CrusaderCommon.CARGO_LEVEL_TWO:

        // }

    }

    @Override
    public boolean done() {
        // TODO Auto-generated method stub
        return false;
    }

}
*/