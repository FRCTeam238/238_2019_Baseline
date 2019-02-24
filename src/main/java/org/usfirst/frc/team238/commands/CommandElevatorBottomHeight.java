package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Elevator;

public class CommandElevatorBottomHeight extends AbstractCommand
{

    Elevator theElevator;
    public CommandElevatorBottomHeight(Elevator myElevator) {
        this.theElevator = myElevator;
    }
        
    @Override
    public void execute()
    {
        theElevator.setSetpoint(1.0);
        
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

}
