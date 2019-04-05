package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.robot.Shoulder;

public class CommandShoulderUp extends AbstractCommand {
	
	Shoulder theShoulder; 
	public CommandShoulderUp(Shoulder myShoulder)
	{
		this.theShoulder = myShoulder;
	}

	@Override
	public void execute() {
        theShoulder.shoulderUp();
        //theShoulder.shoulderUpManual();
	}

	@Override
	public void execute(int btnPressed) {

	}

	@Override
	public void prepare() {

	}

	@Override
    public void setParams() {

    }
    
    @Override
	public void stop() {
	    theShoulder.stop();
	}

	@Override
	public boolean done() {
		return false;
	}

}
