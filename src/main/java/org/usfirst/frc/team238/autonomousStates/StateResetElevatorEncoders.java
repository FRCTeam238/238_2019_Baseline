package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team238.commands.CommandResetElevatorEncoders;

public class StateResetElevatorEncoders implements AutonomousState
{
    CommandResetElevatorEncoders resetElevatorEncodersCommand;
    String parameters[];
    
    boolean done = true;

    @Override
    public void init()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void prepare()
    {
        // TODO Auto-generated method stub
        resetElevatorEncodersCommand.prepare();
        resetElevatorEncodersCommand.setParams();
    }

    @Override
    public void init(String[] params, CommandController theMcp)
    {
        // TODO Auto-generated method stub
        resetElevatorEncodersCommand = (CommandResetElevatorEncoders) theMcp.getAutoCmd("CommandResetElevatorEncoders");

        parameters = params;
    }

    @Override
    public void process()
    {
      resetElevatorEncodersCommand.execute();
    }

    @Override
    public boolean done()
    {
        // TODO Auto-generated method stub

        return done;
    }

    @Override
    public void reset()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void showParams()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateParams()
    {
        // TODO Auto-generated method stub
        String param1;
        String param2;
        String param3;
        String param4;
        //String param5;

        param1 = SmartDashboard.getString("Param 1 - targetValue", "");
        parameters[0] = param1;
        param2 = SmartDashboard.getString("Param 2 - motorSpeed", "");
        parameters[1] = param2;
        param3 = SmartDashboard.getString("Param 3 - targetYaw", "");
        parameters[2] = param3;
        param4 = SmartDashboard.getString("Param 4 - ultrasonicTarget", "");
        parameters[3] = param4;
        //param5 = SmartDashboard.getString("Param 5 - collisionToggle", "");
        // parameters[4] = param5;
    }

    @Override
    public String getParam(int value)
    {
        String output = "";
        if (parameters == null || parameters.length - 1 < value)
        {
            output = "";
        }
        else
        {
            output = parameters[value];
        }
        return output;
    }

    @Override
	public String[] getParams() {
		
		return parameters;
	}

}
