package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.IntakeWrist;
import org.usfirst.frc.team238.robot.Shoulder;

public class CommandRetractWrist extends AbstractCommand
{
    Shoulder theIntake;
    
    public CommandRetractWrist(Shoulder myShoulder)
    {
       this.theIntake = myShoulder;
        
    }

    @Override
    public void execute()
    {
      theIntake.retractShoulderPID();

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
        return false;
    }

    @Override
    public void execute(int btnPressed)
    {
        // TODO Auto-generated method stub
        
    }

}
