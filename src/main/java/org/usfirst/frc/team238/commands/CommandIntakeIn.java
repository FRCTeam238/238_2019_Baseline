package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;

import org.usfirst.frc.team238.robot.IntakeWrist;
import org.usfirst.frc.team238.robot.Shoulder;

public class CommandIntakeIn extends AbstractCommand {
	
	
	Shoulder intake;
	public CommandIntakeIn(Shoulder myintake)
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
