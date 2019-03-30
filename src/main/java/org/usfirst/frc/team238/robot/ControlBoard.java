package org.usfirst.frc.team238.robot;

import java.util.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

import org.usfirst.frc.team238.core.DriverInput;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.core.XBoxValues;

public class ControlBoard { 

	//static Joystick manualOverrideJs; 	// operator manual overide
	static Joystick operatorJs;  	// operator control board
	static Joystick driverJS;
	private static Joystick driverLeftJs; 	// driveTrain left
	private static Joystick driverRightJs; 	// driveTrain right
    private DriverStation driverStation;
	
    HashMap<Integer, Integer[]> controllers; //Contains each joystick value and their button inputs
	public void controlBoardInit()
	{
		try
		{
			operatorJs = new Joystick(CrusaderCommon.OPR_CMD_LIST);
            driverJS = new Joystick(CrusaderCommon.DT_CMD_LIST);
            
			driverLeftJs = new Joystick(CrusaderCommon.LEFTDRIVER_CMD_LIST);
            driverRightJs = new Joystick(CrusaderCommon.RIGHTDRIVER_CMD_LIST);

			controllers = new HashMap<Integer,Integer[]>();
			driverStation = DriverStation.getInstance();
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
		buttonsPressed = new Integer[joyStickButtonCount + CrusaderCommon.Additional_Button_Mapppings];
        int arrayIterator = 0;
        
        boolean hasController = findController(theJoyStick);
        if (hasController) {

            //interator = 11 and buttons do not count from zero
            for (int i = 1; i <= joyStickButtonCount; i++) {

                jsButtonValue = theJoyStick.getRawButton(i);

                //If a button is detected
                if (jsButtonValue) {

                    //Add that button to the collection of buttons pressed
                    //buttonsPressed.put(i, jsButtonValue); 
                    buttonsPressed[arrayIterator] = i;
                    arrayIterator++;

                }

            }
            double rawAxis = theJoyStick.getRawAxis(1);
            //Logger.Log("ControlBoard.getOperatorJoystickInputs.rawAxis = " + rawAxis);
            //elevator 21 is up, 20 is down lefT JS
            if (rawAxis > 0.65) {
                buttonsPressed[arrayIterator++] = XBoxValues.LeftJoystickUp; //20;
            } else if (rawAxis < -0.65) {
                buttonsPressed[arrayIterator++] = XBoxValues.LeftJoystickDown; //21;

            }

            rawAxis = theJoyStick.getRawAxis(5);
            //shoulder 23 is up 22 is down right JS
            if (rawAxis > 0.65) {
                buttonsPressed[arrayIterator++] = XBoxValues.RightJoystickUp; //22;
            } else if (rawAxis < -0.65) {
                buttonsPressed[arrayIterator++] = XBoxValues.RightJoystickDown; //23;
            }

            //Wrist Down
            if (theJoyStick.getRawAxis(2) > 0.65) {
                buttonsPressed[arrayIterator++] = XBoxValues.TriggerLeft; // 27;
            }

            //Wrist Up
            if (theJoyStick.getRawAxis(3) > 0.65) {
                buttonsPressed[arrayIterator++] = XBoxValues.TriggerRight; //28;
            }

            //DPAD 180(down) down 24, 90(to the right) switch 25, 0(up) scale 26

            switch (theJoyStick.getPOV()) {
            case 0:
                buttonsPressed[arrayIterator++] = XBoxValues.DPadUp;
                break;
            case 90:
                buttonsPressed[arrayIterator++] = XBoxValues.DPadRight;//25;
                break;
            case 180:
                buttonsPressed[arrayIterator++] = XBoxValues.DPadDown;
                break;
            case 270:
                //Deploying hatch
                buttonsPressed[arrayIterator++] = XBoxValues.DPadLeft; //29;
                break;
            }
        }
        // if(theJoyStick.getPOV() == 90) {
        //     buttonsPressed[arrayIterator++] = 25;     
        // }else if(theJoyStick.getPOV() == 0) {
        //     buttonsPressed[arrayIterator++] = 26;     
                
        // } else if (theJoyStick.getPOV() == 180) {
        //     buttonsPressed[arrayIterator++] = 24;

        // } else if (theJoyStick.getPOV() == 270) {
        //     buttonsPressed[arrayIterator++] = 29; 
        // }
        
       // DashBoard238.getInstance().addOrUpdateElement("Elevator", "Buttons Pressed", buttonsPressed);
    	return buttonsPressed;
	}
	
	/**
	 * Loops through all buttons on the joystick. Keeping track of every button pressed.
	 * @param theJoyStick
	 * @return
	 */
	public Integer[] getDriverJoystickInput(Joystick theJoyStick){
	  
		boolean jsButtonValue = false;
		int joyStickButtonCount = theJoyStick.getButtonCount();
		Integer[] buttonsPressed;
		buttonsPressed = new Integer[joyStickButtonCount];
		int arrayIterator = 0;
	    
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
    
    return buttonsPressed;
	}
	
	/**
	 * Grabs button/analog inputs from the controllers
	 * @return
	 */
	public HashMap<Integer, Integer[]> getControllerInputs(){
        
        controllers.put(CrusaderCommon.OPR_CMD_LIST, getOperatorJoystickInputs(operatorJs));
        controllers.put(CrusaderCommon.DT_CMD_LIST, CrusaderCommon.DRIVE_TRAIN_CMD_IDX);

        controllers.put(CrusaderCommon.LEFTDRIVER_CMD_LIST, getDriverJoystickInput(driverLeftJs));
        controllers.put(CrusaderCommon.RIGHTDRIVER_CMD_LIST, getDriverJoystickInput(driverRightJs));

	  //ManualOverride Input
		//controllers.put(0, getOperatorJoystickInputs(manualOverrideJs));
		//Operator Input
		
		//Left Driver Joystick Input
		//controllers.put(CrusaderCommon.INPUT_DRIVER_LEFT_JS, getDriverJoystickInput(getDriverLeftJs()));
		//Right Driver Joystick Input
		//controllers.put(CrusaderCommon.INPUT_DRIVER_RIGHT_JS, getDriverJoystickInput(getDriverRightJs()));
		//Driver Joystick y Values (For robot movement)
		
		return controllers;
		
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

    public static DriverInput getDriverInput(Boolean xBox){
        
        DriverInput driverJoySticks;
        
        if( xBox){
            driverJoySticks= new DriverInput(driverJS.getRawAxis(1),  driverJS.getRawAxis(3));
        }else{
            driverJoySticks= new DriverInput(driverLeftJs.getY(),  driverRightJs.getY());
        }
        
        return driverJoySticks;
    }
	
	public static Joystick getOperatorRightJs() {
		return operatorJs;
    }
    
    public boolean findController(Joystick theController) {
        return driverStation.getJoystickIsXbox(theController.getPort());
    }
	
	// public static double getHangerRightSide() {
		
	// 	double value = 0;
		
	// 	if(operatorJs.getRawButton(5))
	// 	{
	// 		value = operatorJs.getY();
	// 	}
			
	// 	return value;
	// }
	
	// public static double getHangerLeftSide() {
		
	// 	double value = 0;
		
	// 	if(operatorJs.getRawButton(4))
	// 	{
	// 		value = operatorJs.getY();
	// 	}
			
	// 	return value;
	// }
	
}
