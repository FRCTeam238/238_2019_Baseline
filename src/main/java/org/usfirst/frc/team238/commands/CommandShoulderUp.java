package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;

import org.usfirst.frc.team238.robot.Shoulder;

public class CommandShoulderUp extends AbstractCommand {
	
	Shoulder theShoulder; 
	public CommandShoulderUp(Shoulder myShoulder)
	{
		this.theShoulder = myShoulder;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
        theShoulder.shoulderUp();
        //theShoulder.shoulderUpManual();


	}

	@Override
	public void execute(int btnPressed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParams() {
		// TODO Auto-generated method stub

	}
	
	public void stop() {
	    theShoulder.stop();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
