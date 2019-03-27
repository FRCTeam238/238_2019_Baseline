package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.Robot;

public class CommandResetElevatorEncoders extends AbstractCommand
{
    Elevator theElevator;
    public CommandResetElevatorEncoders(Robot myRobot) {
        this.theElevator = myRobot.myElevator;
    }
    
    @Override
    public void execute()
    {
        theElevator.resetEncoders();

        // TODO Auto-generated method stub
/*        if(theElevator.climbMode) {
            theElevator.elevatorClimbDown();
        }
        else
        {
        }*/

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

    @Override
    public void setParams()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean done()
    {
        // TODO Auto-generated method stub
        return true;
    }

}
