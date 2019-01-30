package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.testSteps.TestElevator;



public class TestCmdFactory {

	HashMap<String, TestStep> TestCommands;
	
	
	public void init()
	{
	
		TestCommands = new HashMap<String, TestStep>(50);
	  
		
	
	}
	
	/**
	 * Test controls get binded here. Assigning a series of buttons and commands to a HashMap
	 * 
	 * @param theRobot
	 * @return
	 */
	public HashMap<String, TestStep> createTestCommands(Robot theRobot)
	{
		TestStep cmd;

		cmd = new TestElevator(theRobot);
		TestCommands.put("Elevator Test", cmd);

		return TestCommands;

	}
	
	public TestStep getTestStep(String cmdName)
	{
		return TestCommands.get(cmdName);
	}

	
}
