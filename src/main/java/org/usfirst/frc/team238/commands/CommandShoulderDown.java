package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Shoulder;

public class CommandShoulderDown extends AbstractCommand
{
    Shoulder theShoulder;
    
    public CommandShoulderDown(Shoulder myShoulder)
    {
       this.theShoulder = myShoulder;
        
    }

    @Override
    public void execute()
    {
        theShoulder.shoulderDown();
        
        //theShoulder.shoulderDownManual();

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
    public void execute(int btnPressed)
    {
    
    }
    
    @Override
    public void stop()
    {
        theShoulder.stop();
    }
}
