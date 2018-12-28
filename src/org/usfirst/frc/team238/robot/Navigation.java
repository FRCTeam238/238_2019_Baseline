package org.usfirst.frc.team238.robot;

import org.usfirst.frc.team238.core.Logger;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;


public class Navigation {
	
	AHRS ahrs;
	double currentYaw;
	double currentRoll;
	double targetYaw;
	double ultrasonicDistance;
	DigitalInput lineSensorInput;
	
	//Ultrasonic myUltrasonic;
	
	final static double kCollisionThreshold_DeltaG = 0.75f;
  double last_world_linear_accel_x = 0;
  double last_world_linear_accel_y = 0;
  
  int count = 0;
  double start = 0;
  double current = 0;
  double elapsed = 0;
	
	public void init()
	{
		lineSensorInput = new DigitalInput(CrusaderCommon.LINE_SENSOR);
		
	    
		ahrs = new AHRS(SPI.Port.kMXP);
		currentYaw = ahrs.getYaw();
	
		currentRoll = ahrs.getRoll();
//		myUltrasonic = new Ultrasonic(CrusaderCommon.SONIC_OUTPUT_PORT,CrusaderCommon.SONIC_INPUT_PORT);
//		myUltrasonic.setEnabled(true);
//		myUltrasonic.setAutomaticMode(true);
		
		count = 0;
		start = 0;
		current = 0;
		elapsed = 0;
	}
	
	
	
	public boolean getLineSensor()
	{
	    return lineSensorInput.get();
	}
    
        
   


    public void test()
	{
	  
	  if(!weAreConntected())
	  {
	   
	    Logger.Log("Navigation(): test(): The NavX board isn't connected!!!");
	    
	  }
	  
	}
	
	
	/**
	 * Checks if the NavX Board is actually connected
	 * @return
	 */
	public boolean weAreConntected(){
    
    return ahrs.isConnected();
    
	}
	
	
//	public double getDistanceFromUltrasonic()
//	{
//		
//		ultrasonicDistance = myUltrasonic.getRangeInches();
//		SmartDashboard.putNumber("Ultrasonic Distance", ultrasonicDistance);
//		
//		return ultrasonicDistance;
//	
//	}
	
	public void resetNAVX(){
		
		ahrs.reset();
		
	}
	
	public void zeroYaw()
	{
	  
	  ahrs.zeroYaw();
	  
	}
	
	public double getRoll()
	{
		currentRoll = ahrs.getRoll();
		
		return currentRoll;
	}
	public double getYaw()
	{
	    
		currentYaw = ((ahrs.getYaw() % 360) + 360) % 360;
		
		return currentYaw;
	}
	
	//NOTE: make a set target for yaw and roll	
	public void setTargetValues(double targetValue)
	{
		
		targetYaw = Math.abs(targetValue);
		
	}
	
	//Values being read from the NavX board
	public void navxValues()
	{
		Timer.delay(0.020);
		
		/*SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
      SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
        
		  SmartDashboard.putNumber("Gyro_X", ahrs.getRawGyroX());
		  SmartDashboard.putNumber("Gyro_Y", ahrs.getRawGyroY());
		  SmartDashboard.putNumber("Gyro_Z", ahrs.getRawGyroZ());
		
		  SmartDashboard.putNumber("Accel_X", ahrs.getRawAccelX());
		  SmartDashboard.putNumber("Accel_Y", ahrs.getRawAccelY());
		  SmartDashboard.putNumber("Accel_Z", ahrs.getRawAccelZ());*/
		
      //RM SmartDashboard.putNumber("IMU_Yaw", ahrs.getYaw());
      //RM SmartDashboard.putNumber("IMU_Pitch", ahrs.getPitch());
      //RM SmartDashboard.putNumber("IMU_Roll", ahrs.getRoll());
        
      //RM SmartDashboard.putNumber("Refresh Rate", ahrs.getActualUpdateRate());
   // haveWeCollided();
   //SmartDashboard.putBoolean("Are We Moving?", ahrs.isMoving());
	}
	//Tells us if we are at our target yaw
	public boolean areWeThereYet()
	{
		currentYaw = ahrs.getYaw();
		currentYaw = Math.abs(currentYaw);
		
		Logger.Log("Navigation(): areWeThereYet(): Current Yaw is : "+ currentYaw);
		Logger.Log("Navigation(): areWeThereYet(): Target is : "+ targetYaw);
		
		if((currentYaw >= (targetYaw - CrusaderCommon.NAVIGATION_TURNING_DEADZONE))) 
		{
			return true;
		}
		else
		{

			return false;
		}
	}
	
	
	/**
	 * What is this here for? It doesnt get called anywhere
	 * @param targetYaw
	 * @param currentYaw
	 * @param motorValue
	 * @return
	 */

	
	/**
	 * This function tells us if we are moving by using the NavX board .isMoving() function
	 * @return
	 */
	public boolean haveWeCollided()
	{
	  
	  boolean collisionDetected = false;

	  
    double curr_world_linear_accel_x = ahrs.getWorldLinearAccelX();
    last_world_linear_accel_x = curr_world_linear_accel_x;
    double currentJerkX = curr_world_linear_accel_x - last_world_linear_accel_x;
     
    
    double curr_world_linear_accel_y = ahrs.getWorldLinearAccelY();
    last_world_linear_accel_y = curr_world_linear_accel_y;
    double currentJerkY = curr_world_linear_accel_y - last_world_linear_accel_y;
    
    
    if ( ( Math.abs(currentJerkX) > kCollisionThreshold_DeltaG ) ||
         ( Math.abs(currentJerkY) > kCollisionThreshold_DeltaG) ) {
      
        collisionDetected = true;
        
        Logger.Log("Navigation(): haveWeCollided(): CollisionDetected =" + collisionDetected);
    }
   // SmartDashboard.putNumber("cOLLISIONvaLUE x: ", currentJerkX);
   // SmartDashboard.putNumber("cOLLISIONvaLUE y: ", currentJerkY);
    
   // Logger.Log("Navigation(): haveWeCollided(): Collision X :" + currentJerkX);
   // Logger.Log("Navigation(): haveWeCollided(): Collision Y :" + currentJerkY);
    
   // SmartDashboard.putBoolean(  "CollisionDetected", collisionDetected);
   
    return collisionDetected;
	  
	}
	
	 
  public double getCollisionDelay()
  {
    if(count == 0)
    {
      
      start = System.currentTimeMillis();
      count++;
      
    }
    else
    {
      current = System.currentTimeMillis();
    }
    
    elapsed = current - start;
    
    return elapsed;
  }
	
	
}
