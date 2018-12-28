package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;

import org.usfirst.frc.team238.robot.IntakeWrist;

public class CommandExtendWrist extends AbstractCommand {
	
	IntakeWrist extend; 
	public CommandExtendWrist(IntakeWrist myextend)
	{
		this.extend = myextend;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		extend.extendWristPID();

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
	    extend.stop();
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
