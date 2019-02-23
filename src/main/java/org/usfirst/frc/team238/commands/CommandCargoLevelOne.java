package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.DashBoard238;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.robot.Shoulder;

public class CommandCargoLevelOne extends AbstractCommand
{

    Elevator theElevator;
    Shoulder theShoulder;
    double height = CrusaderCommon.ELEVATOR_LEVEL_ONE_HEIGHT;//77;
    double angle = CrusaderCommon.SHOULDER_LEVEL_ONE_ANGLE;
    
    public CommandCargoLevelOne(Robot theRobot) {
        this.theElevator = theRobot.myElevator;
        this.theShoulder = theRobot.myShoulder;
    }
        
    @Override
    public void execute()
    {
        theShoulder.setshoulder(angle);
        theElevator.setSetpoint(height);
    }

    @Override
    public void execute(int btnPressed)
    {
        // TODO Auto-generated method stub

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
            height = 77;
          }

    }

    @Override
    public boolean done()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
