package org.usfirst.frc.team238.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;

import RealBot.Trajectory;
import RealBot.TrajectoryFactory;
import RealBot.TrajectoryIntepreter;

public class CommandRunTrajectory extends AbstractCommand
{
    
    TrajectoryIntepreter trajectoryInterpreter;
    
    public CommandRunTrajectory(Drivetrain myDriveTrain, Navigation navigation, Object[][] moments)
    {
       
        ArrayList<Trajectory> trajectories = new ArrayList<>();
        trajectories.add(TrajectoryFactory.getTrajectory(moments));
        
        HashMap<String, Runnable> markers = new HashMap<>();
        this.trajectoryInterpreter = new TrajectoryIntepreter(myDriveTrain, navigation, trajectories, markers);
  
       
    }
    
    @Override
    public void execute()
    {
        // TODO Auto-generated method stub
        trajectoryInterpreter.run();
    }

    @Override 
    public void prepare()
    {
        // TODO Auto-generated method stub

    }

    public void setParams(String params[]) {

        
      }

    @Override
    public boolean done()
    {
        // TODO Auto-generated method stub
        return trajectoryInterpreter.isDone();
    }

}
