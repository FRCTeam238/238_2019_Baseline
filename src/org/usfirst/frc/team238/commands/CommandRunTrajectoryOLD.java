package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;

import RealBot.TrajectoryIntepreter;

public class CommandRunTrajectoryOLD extends AbstractCommand
{
    
    TrajectoryIntepreter trajectoryInterp;
    
    public CommandRunTrajectoryOLD(TrajectoryIntepreter theInterpreter)
    {
        this.trajectoryInterp = theInterpreter;
    }
    
    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        trajectoryInterp.run();
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
        return trajectoryInterp.isDone();
    }

}
