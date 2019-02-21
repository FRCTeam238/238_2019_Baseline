package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Shoulder;;

public class CommandShoulderAngle extends AbstractCommand {
	
    
	Shoulder extend; 
	double angle;
	
	public CommandShoulderAngle(Shoulder myextend)
	{
		this.extend = myextend;
	}

	@Override
	public void execute() {
		
		Logger.Log("YUP = " + angle);
		extend.setshoulder(angle);

	}

	public void execute(boolean auto) {
        
        Logger.Log("YUP = " + angle);
        extend.setshoulder(angle, auto);

    }
	@Override
	public void execute(int btnPressed) {
		
	    //check button layou
	    Logger.Log("PRESSEDBUTTONDDDDDDDDDDDDDDDDDDDDDDDDDDD:" + btnPressed);
	    if(btnPressed ==1) {
	        extend.setshoulder(-80);
	    }else if(btnPressed ==2) {
            extend.setshoulder(-40);
        }else if(btnPressed ==4) {
            extend.setshoulder(-3);
        }
	}

	@Override
	public void prepare() {
		

	}

	
	public void setParams(String params[]) {
	    if ((params[0] != null) || (!params[0].isEmpty())) {
	        angle = Double.parseDouble(params[0]);
	      } else {
	        angle = 0;
	      }
		

	}
	
	public void stop() {
	    extend.stop();
	}

	@Override
	public boolean done() {
		
		return true;
	}

}
