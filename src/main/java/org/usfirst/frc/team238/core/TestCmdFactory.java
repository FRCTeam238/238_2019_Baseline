package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.testSteps.TestElevator;
import org.usfirst.frc.team238.testSteps.TestShoulder;
import org.usfirst.frc.team238.testSteps.TestStaticDriveTrainEncoders;
import org.usfirst.frc.team238.testSteps.TestStepDriveTrainEncoders;



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
        TestCommands.put("Elevator Test2", cmd);
        TestCommands.put("Elevator Test3", cmd);
        
        cmd = new TestShoulder(theRobot);
        TestCommands.put("Shoulder Test", cmd);

        cmd = new TestStepDriveTrainEncoders(theRobot);
        TestCommands.put("Drivetrain Test", cmd);

        cmd = new TestStaticDriveTrainEncoders(theRobot);
        TestCommands.put("Encoder Test", cmd);

        return TestCommands;
	}
	
	public TestStep getTestStep(String cmdName)
	{
		return TestCommands.get(cmdName);
	}

	
}
