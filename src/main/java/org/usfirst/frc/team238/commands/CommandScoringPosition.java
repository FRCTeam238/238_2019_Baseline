package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.DashBoard238;
import org.usfirst.frc.team238.core.DashboardValues;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.core.TargetValues;
import org.usfirst.frc.team238.core.XBoxValues;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.Shoulder;
import org.usfirst.frc.team238.robot.Wrist;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandScoringPosition extends AbstractCommand
{

    Elevator theElevator;
    Shoulder theShoulder;
    Wrist theWrist;
    DashBoard238 dashboard;
    boolean done = false;
    
    protected double height = CrusaderCommon.ROCKET_CARGO_LEVEL_ZERO_VALUE;//77;
    double angle = CrusaderCommon.ROCKET_CARGO_LEVEL_ZERO_VALUE;
    boolean extended = CrusaderCommon.WRIST_FALSE;

    public CommandScoringPosition(Robot theRobot) {
        this.theElevator = theRobot.myElevator;
        this.theShoulder = theRobot.myShoulder;
        this.theWrist = theRobot.myWrist;
        dashboard = DashBoard238.getInstance();

    }
        
    @Override
    public void execute()
    {
        //TargetValues values = dashboard.getTargetValues();
        //height = values.getElevatorHeight();
        //angle = values.getShoulderAngle();
        //extended = values.getWristPosition();

        //theShoulder.setshoulder(angle);
        //theElevator.setSetpoint(height);
        //theWrist.setWrist(extended);
        Logger.Log("CommandScoringPosition.execute(): This isn't the execute you're looking for." ) ;
    }

    @Override
    public void execute(int btnPressed)
    {
        //execute();

        switch(btnPressed)
        {
            case XBoxValues.Y:   //4,   Cargo Level Three
                setArmPositions(CrusaderCommon.ROCKET_CARGO_LEVEL_THREE_SHOULDER, CrusaderCommon.ROCKET_CARGO_LEVEL_THREE_ELEVATOR, CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_WRIST);
                break;
        
            case XBoxValues.B:   //2,  Cargo Level Two
                setArmPositions(CrusaderCommon.ROCKET_CARGO_LEVEL_TWO_SHOULDER, CrusaderCommon.ROCKET_CARGO_LEVEL_TWO_ELEVATOR, CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_WRIST);
                break;
        
            case XBoxValues.A:   //1,  Cargo Level One
                setArmPositions(CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_SHOULDER, CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_ELEVATOR, CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_WRIST);
                break;
            
            case XBoxValues.DPadDown:   //26, Hatch Level Three
                setArmPositions(CrusaderCommon.ROCKET_HATCH_LEVEL_THREE_SHOULDER,
                CrusaderCommon.ROCKET_HATCH_LEVEL_THREE_ELEVATOR, 
                CrusaderCommon.HATCH_LEVEL_ONE_WRIST);
                break;
        
            case XBoxValues.DPadRight:   //25, Hatch Level Two
                setArmPositions(CrusaderCommon.ROCKET_HATCH_LEVEL_TWO_SHOULDER,
                CrusaderCommon.ROCKET_HATCH_LEVEL_TWO_ELEVATOR, CrusaderCommon.ROCKET_HATCH_LEVEL_TWO_WRIST);
                break;
        
            case XBoxValues.DPadUp:   //24, Hatch Level One
            setArmPositions(CrusaderCommon.HATCH_LEVEL_ONE_SHOULDER, 
                 CrusaderCommon.HATCH_LEVEL_ONE_ELEVATOR, CrusaderCommon.HATCH_LEVEL_ONE_WRIST);
                break;
                
            case XBoxValues.X:   //3, Cargo Ship Cargo
                setArmPositions(CrusaderCommon.CARGO_SHIP_CARGO_SHOULDER, CrusaderCommon.CARGO_SHIP_CARGO_ELEVATOR, CrusaderCommon.CARGO_SHIP_CARGO_WRIST);
                break;
                
            case XBoxValues.Back: //back
                setArmPositions(CrusaderCommon.CARGO_SHIP_CARGO_SHOULDER, CrusaderCommon.CARGO_SHIP_CARGO_ELEVATOR, CrusaderCommon.CARGO_SHIP_CARGO_WRIST);
                break;
                
            case XBoxValues.Start: //8 Tuning
                // TargetValues targetValues = DashBoard238.getInstance().getTargetValues();
                // setArmPositions(targetValues.getShoulderAngle(), targetValues.getElevatorHeight(), targetValues.getWristPosition());
                //targetValues.getElevatorHeights is singular
                // setArmPositions(CrusaderCommon.SAFE_DRIVING_MODE_SHOULDER, CrusaderCommon.SAFE_DRIVING_MODE_ELEVATOR, 
            // CrusaderCommon.SAFE_DRIVING_MODE_WRIST);
                theElevator.resetEncoders();
                break;
                
            case 42:
                setArmPositionsAuto(angle, height, CrusaderCommon.WRIST_FALSE);
                break;

        }
    }


    private void setArmPositions(double shoulderPos, double elevatorPos, boolean wristExtended) {
        
        theShoulder.setshoulder(shoulderPos);
        theElevator.setSetpoint(elevatorPos);
        theWrist.setWrist(wristExtended);
        done = true;

    } 

    private void setArmPositionsAuto(double shoulderPos, double elevatorPos, boolean wristExtended) {
        double currentDelta = Math.abs(theElevator.getHeight() - elevatorPos);
        if (currentDelta <= 5) {
            theShoulder.setshoulder(shoulderPos);
            done = true;
        }
        theElevator.setSetpoint(elevatorPos);
        theWrist.setWrist(wristExtended);

    } 
    @Override
    public void prepare()
    {
        // TODO Auto-generated method stub
       
    }

    public void setParams(String params[])
    {
        if ((params[0] != null) || (!params[0].isEmpty())) {
            height = Double.parseDouble(params[0]);
        } else {
            height = CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_ELEVATOR;
        }
        
        if ((params[1] != null) || (!params[1].isEmpty())) {
            angle = Double.parseDouble(params[1]);
        } else {
            angle = CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_SHOULDER;
        }

        if ((params[2] != null) || (!params[2].isEmpty())) {
            extended = Boolean.parseBoolean(params[2]);
        } else {
            extended = CrusaderCommon.ROCKET_CARGO_LEVEL_ONE_WRIST;
        }
        Logger.Log("CommandScoringPosition():setParams(): Hight =  " + height + " Angle = " + angle );
        // switch (whatLevel) {
        // case CrusaderCommon.CARGO_LEVEL_ONE:
        //     //get target values fro dashboard

           
        //     //assign to local variables
        //     break;
        // case CrusaderCommon.CARGO_LEVEL_TWO:
            
        // }

    }

    @Override
    public boolean done()
    {
        // TODO Auto-generated method stub
        return done;
    }


}
