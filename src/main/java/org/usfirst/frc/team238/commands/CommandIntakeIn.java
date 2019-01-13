package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;

import org.usfirst.frc.team238.robot.IntakeWrist;

public class CommandIntakeIn extends AbstractCommand {
	
	
	IntakeWrist intake;
	public CommandIntakeIn(IntakeWrist myintake)
	{
		this.intake = myintake;
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		intake.intakeIn();
		
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

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}

}
