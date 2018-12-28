package org.usfirst.frc.team238.robot;

import java.util.*;
import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team238.core.Logger;

public class ControlBoard { 

	static Joystick manualOverrideJs; 	// operator manual overide
	static Joystick operatorJs;  	// operator control board
	
	private static Joystick driverLeftJs; 	// driveTrain left
	private static Joystick driverRightJs; 	// driveTrain right
	
	static Joystick xboxController;
	
	HashMap<Integer, Integer[]> controllers; //Contains each joystick value and their button inputs
	
	boolean isXBoxController;
	
	static Integer[] xBoxToJsCmdMapping= { 0, 11, 10, 2, 3, 7, 6};
	
	public void controlBoardInit()
	{
		try
		{
			manualOverrideJs = new Joystick(0);
			operatorJs = new Joystick(1);
			setDriverLeftJs(new Joystick(2));
			setDriverRightJs(new Joystick(3));
		
			controllers = new HashMap<Integer,Integer[]>();
			
		}
		
		catch(Exception ex)
		{
			Logger.Log("ControlBoard(): controlBoardInit() Failed!");
		}
	}
	
	/**
	 * Loops through all buttons on the joystick. Keeping track of every button pressed.
	 * @return command value
	 */
	public Integer[] getOperatorJoystickInputs(Joystick theJoyStick)
	{
		
		boolean jsButtonValue = false;
		int joyStickButtonCount = theJoyStick.getButtonCount();
		Integer[] buttonsPressed;
		buttonsPressed = new Integer[joyStickButtonCount +7];
		int arrayIterator = 0;
		
		//interator = 11 and buttons do not count from zero
		for(int i = 1; i <= joyStickButtonCount; i++) 
		{
		  
			jsButtonValue = theJoyStick.getRawButton(i);
			
			//If a button is detected
			if(jsButtonValue) 
			{
			  
			  //Add that button to the collection of buttons pressed
			  //buttonsPressed.put(i, jsButtonValue); 
			  buttonsPressed[arrayIterator] = i;
			  arrayIterator++;
			  
			}
			
		}
		
		//elevator 21 is up, 20 is down lefT JS
		if( theJoyStick.getRawAxis(1) > 0.65) {
            buttonsPressed[arrayIterator++] = 20;
        }else if( theJoyStick.getRawAxis(1) < -0.65) {
            buttonsPressed[arrayIterator++] = 21;
        }
        
        //wrist 23 is up 22 is down right JS
        if( theJoyStick.getRawAxis(5) > 0.65) {
            buttonsPressed[arrayIterator++] = 22;
        }else if( theJoyStick.getRawAxis(5) < -0.65) {
            buttonsPressed[arrayIterator++] = 23;
        }
        

        //elevator shift
        if( theJoyStick.getRawAxis(2) > 0.65) {
            buttonsPressed[arrayIterator++] = 27;
        }
        
        //intake out fast
        if( theJoyStick.getRawAxis(3) > 0.65) {
            buttonsPressed[arrayIterator++] = 28;
        }
        
        //DPAD 180(down) down 24, 90(to the right) switch 25, 0(up) scale 26
        
        if(theJoyStick.getPOV() ==90) {
            buttonsPressed[arrayIterator++] = 25;     
        }else if(theJoyStick.getPOV() ==0) {
            buttonsPressed[arrayIterator++] = 26;     
                
        }else if(theJoyStick.getPOV() ==180) {
            buttonsPressed[arrayIterator++] = 24;     
                
        }
            
           
        
		
		        
		return buttonsPressed;
	}
	
	/**
	 * Loops through all buttons on the joystick. Keeping track of every button pressed.
	 * @param theJoyStick
	 * @return
	 */
	public Integer[] getDriverJoystickInput(Joystick theJoyStick){
	  
	  /*!!!!!!!!!!!!!!THIS NEEDS TO BE REWORKED SO IT IS NOT HARDCODED IN!!!!!!!!!!!!!!!!!!*/
		Integer[] buttonsPressed = new Integer[5];
	  
		if(theJoyStick.getRawButton(1))
		{
		  buttonsPressed[1] = 1;
		}
		else if (theJoyStick.getRawButton(2))
		{
		  buttonsPressed[2] = 2;
		}
		else if(theJoyStick.getRawButton(3))
		{
		  buttonsPressed[3] = 3;
		}
		else if(theJoyStick.getRawButton(4))
		{
		  buttonsPressed[4] = 4;
		}
    
    return buttonsPressed;
	}
	
	/**
	 * Grabs button/analog inputs from the controllers
	 * @return
	 */
	public HashMap<Integer, Integer[]> getControllerInputs(){
		
	  //ManualOverride Input
		controllers.put(0, getOperatorJoystickInputs(manualOverrideJs));
		//Operator Input
		controllers.put(CrusaderCommon.OPR_CMD_LIST, getOperatorJoystickInputs(operatorJs));
		//Left Driver Joystick Input
		controllers.put(CrusaderCommon.INPUT_DRIVER_LEFT_JS, getDriverJoystickInput(getDriverLeftJs()));
		//Right Driver Joystick Input
		controllers.put(CrusaderCommon.INPUT_DRIVER_RIGHT_JS, getDriverJoystickInput(getDriverRightJs()));
		//Driver Joystick y Values (For robot movement)
		controllers.put(CrusaderCommon.DT_CMD_LIST, CrusaderCommon.DRIVE_TRAIN_CMD_IDX);
		
		return controllers;
		
	}
	
	//gets the y value of the manual overide joy stick to feed to the command controller
	public static double getManualCommandValue()
	{
		return manualOverrideJs.getY();
	}
	
	public static boolean canWeReleaseTheHounds()
	{
		boolean  secondButton = manualOverrideJs.getRawButton(1); //this button may need to change
		return secondButton;
	}
	
	public static boolean resetEncoderValue()
	{
		boolean resetEncoderValue = operatorJs.getRawButton(8);
		Logger.Log("ControlBoard(): resetEncoderValue(): Reset Encoder = " + resetEncoderValue);
		return resetEncoderValue;
	}
	
	public boolean overRide(){
		boolean overRide = operatorJs.getRawButton(10);
		
		return overRide;
					
	}

	public static Joystick getDriverLeftJs() {
		return driverLeftJs;
	}
	
	public static double getDriverLeftYAxis() {
		return driverLeftJs.getY();
	}

	public static void setDriverLeftJs(Joystick driverLeftJs) {
		ControlBoard.driverLeftJs = driverLeftJs;
	}


	public static Joystick getDriverRightJs() {
		return driverRightJs;
	}
	
	public static double getDriverRightYAxis() {
		return driverLeftJs.getY();
	}

	public static void setDriverRightJs(Joystick driverRightJs) {
		ControlBoard.driverRightJs = driverRightJs;
	}
	
	public void checkXboxController()
	{
		//isXBoxController = operatorJs.getIsXbox();
	}

	public static Joystick getOperatorLeftJs() {
		return manualOverrideJs;
	}
	
	public static Joystick getOperatorRightJs() {
		return operatorJs;
	}
	
	public static double getHangerRightSide() {
		
		double value = 0;
		
		if(operatorJs.getRawButton(5))
		{
			value = operatorJs.getY();
		}
			
		return value;
	}
	
	public static double getHangerLeftSide() {
		
		double value = 0;
		
		if(operatorJs.getRawButton(4))
		{
			value = operatorJs.getY();
		}
			
		return value;
	}
	
}
