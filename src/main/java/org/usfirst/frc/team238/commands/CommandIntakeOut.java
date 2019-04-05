package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Shoulder;

public class CommandIntakeOut extends AbstractCommand
{
    Shoulder theIntake;
    double targetValue;
    
   
    public CommandIntakeOut(Shoulder myIntake)
    {
        this.theIntake = myIntake;
    }
    
    @Override
    public void execute()
    {
        if(targetValue == 1){
        	theIntake.intakeOutSlow();
        }
        else
        {
        	theIntake.intakeOut();
        }
    }

    @Override
    public void execute(int btnPressed)
    {
        theIntake.intakeOut();
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
            targetValue = Double.parseDouble(params[0]);
        } else {
            targetValue = 0;
        }

    }
    
    @Override
    public void stop() {
        theIntake.stop();
    }

    @Override
    public boolean done()
    {
        // TODO Auto-generated method stub
        return false;
    }

}
