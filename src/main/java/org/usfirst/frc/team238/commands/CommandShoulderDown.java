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
