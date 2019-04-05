package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Elevator;

public class CommandElevatorDown extends AbstractCommand
{

    Elevator theElevator;
    public CommandElevatorDown(Elevator myElevator) {
        this.theElevator = myElevator;
    }
    
    @Override
    public void execute()
    {
        theElevator.elevatorDown();
/*      if(theElevator.climbMode) {
            theElevator.elevatorClimbDown();
        }
        else
        {
        }*/

    }

    @Override
    public void execute(int btnPressed)
    {

    }

    @Override
    public void prepare()
    {

    }

    @Override
    public void setParams()
    {
 

    }

    @Override
    public boolean done()
    {
        return false;
    }

    @Override
    public void stop()
    {
        theElevator.stop();
    }
}
