package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Elevator;

public class CommandElevatorSwitchHeight extends AbstractCommand
{

    Elevator theElevator;
    double height = 12;//40;
    
    public CommandElevatorSwitchHeight(Elevator myElevator) {
        Logger.Log("CommandElevatorSwitchHeight()");
        
        this.theElevator = myElevator;
        Logger.Log("CommandElevatorSwitchHeight().elevator " + theElevator);
        
    }
        
    @Override
    public void execute()
    {
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
            height = 40;
          }

    }

    @Override
    public boolean done()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
