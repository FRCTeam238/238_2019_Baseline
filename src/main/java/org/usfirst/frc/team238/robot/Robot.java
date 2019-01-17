/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.robot;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;


import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.HashMap;

import org.usfirst.frc.team238.core.AutonomousController;
import org.usfirst.frc.team238.core.AutonomousDataHandler;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Drivetrain;
import RealBot.TrajectoryIntepreter;
import RealBot.TrajectoryFactory;
import RealBot.Trajectory;
import org.usfirst.frc.team238.core.DashBoard238;
import org.usfirst.frc.team238.lalaPaths.leftSwitch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot 
{


	private static int count = 1;
		
	TalonSRX leftMasterDrive; 
	VictorSPX leftDriveFollower1;
	VictorSPX leftDriveFollower2;
	
	TalonSRX rightMasterDrive; 
	VictorSPX rightDriveFollower1;
	VictorSPX rightDriveFollower2;
	
	Robot myRobot;
	Preferences myPreferences;
	ControlBoard myControlBoard;
	CommandController theMCP;
	public Navigation myNavigation;
	public Drivetrain myDriveTrain;
	DriverStation myDriverstation;
	Logger myLogger;
	public TrajectoryIntepreter theTrajectoryIntepreter;
	DashBoard238 myDashBoard238;
	public TestCoreObject myTestCoreObject;

	//There shouldn't be two of these
	Alliance myAllianceTeam;
		
	// Autonomous Mode Support
	String autoMode;
	
	private AutonomousDataHandler myAutonomousDataHandler;
	private AutonomousController theMACP;

	SendableChooser<String> autonomousSaveChooser;
	SendableChooser<String> aModeSelector;
	SendableChooser<String> autonomousStateParamsUpdate;
	
	String robotPosition;

	public void disabledInit() 
	{
		try 
		{
			
			Logger.Log("Robot(): disabledInit:");
		
		} catch (Exception e) 
		{
		  e.printStackTrace();
		  Logger.Log("Robot(): disabledInit Exception: "+e);
		}
	}
	
	public void disabledPeriodic() 
	{
		//boolean debug;
		try 
		{
			if (count > 150) 
			{

			    boolean playBook = SmartDashboard.getBoolean("PlayBook", true);
			    if (playBook == true) {
			        SmartDashboard.putString("P or S", "Primary");
			    }
			    else {
			        SmartDashboard.putString("P or S", "Secondary");
			    }
				count = 0;

				myDriveTrain.resetEncoders();
				
				
				//int automousModeFromDS =  myAutonomousDataHandler.getModeSelectionFromDashboard(); 
	            //Logger.Log("Robot(): DisabledPeriodic(): The chosen One =  " + String.valueOf(automousModeFromDS));
	            //theMACP.pickAMode(automousModeFromDS);
				//myAutonomousDataHandler.dump();
				theMACP.dumpPlays();
				//autoModeUpdateAndRead();
				//SmartDashboard.putNumber("DisPer Amode", automousModeFromDS);
				
				 String robotPosition = SmartDashboard.getString(CrusaderCommon.AUTO_ROBOT_POSITION,  "X");
		         Logger.Log("AUtoMOde Selection: = "+ robotPosition);
			}
			
			count++;
			
		} catch (Exception e) 
		{
		  e.printStackTrace();
		  Logger.Log("Robot(): disabledPeriodic(): disabledPriodic exception: " + e);
		}
		
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
	  
		try 
		{
			Logger.Log("Starting RobotInit()");
			
			myRobot = Robot.this; 
			
			initSmartDashboardObjects();
			if (robotPosition.equals("C"))
			{
			    initTalonsProgrammingStation();			  
			}
			else 
			{
			    initTalons();
			}
			
			initTalons();
			initRobotObjects();
			initCoreObjects();

			Logger.Log("Robot(): robotInit(): Fully Initialized");
			
		} 
		catch (Exception ex) 
		{
		  ex.printStackTrace();
			Logger.Log("Robot(): robotInit() Exception : "+ex);
		}
	}
	
	public void initSmartDashboardObjects()
	{
		 
		myDashBoard238 = new DashBoard238(this);
		aModeSelector = myDashBoard238.getAutonomusModeSelector();
		robotPosition = myDashBoard238.getRobotPosition(); 
	  
	}

	public void initTalonsProgrammingStation()
    {
        leftMasterDrive = new TalonSRX(CrusaderCommon.DRIVE_TRAIN_LEFT_MASTER_DRIVER_STATION);  
        leftMasterDrive.setInverted(true);
        leftMasterDrive.setNeutralMode(NeutralMode.Brake);
     
        rightMasterDrive = new TalonSRX(CrusaderCommon.DRIVE_TRAIN_RIGHT_MASTER_DRIVER_STATION);       
        rightMasterDrive.setNeutralMode(NeutralMode.Brake);
       
        rightMasterDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 0);
        leftMasterDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 0);
        Logger.Log("initTalons Is Sucessful!");
    }
	/**
	 * Initializes Talons
	 */
	public void initTalons()
	{
		leftMasterDrive = new TalonSRX(15);  
		leftDriveFollower1 = new VictorSPX(14);  
		leftDriveFollower2 = new VictorSPX(13);
		
		leftMasterDrive.setInverted(true);
        leftDriveFollower1.setInverted(true);
        leftDriveFollower2.setInverted(true);
		
		
		leftDriveFollower1.follow(leftMasterDrive); 
        leftDriveFollower2.follow(leftMasterDrive);        
       
        leftMasterDrive.setNeutralMode(NeutralMode.Brake);
        leftDriveFollower1.setNeutralMode(NeutralMode.Brake);
        leftDriveFollower2.setNeutralMode(NeutralMode.Brake);
        
		rightMasterDrive = new TalonSRX(0);  
		rightDriveFollower1 = new VictorSPX(1);
		rightDriveFollower2 = new VictorSPX(2);		
		
		rightDriveFollower1.follow(rightMasterDrive); 
		rightDriveFollower2.follow(rightMasterDrive); 
		
		rightMasterDrive.setNeutralMode(NeutralMode.Brake);
		rightDriveFollower1.setNeutralMode(NeutralMode.Brake);
        rightDriveFollower2.setNeutralMode(NeutralMode.Brake);
        
        rightMasterDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 0);
        leftMasterDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 0);
		Logger.Log("initTalons Is Sucessful!");
	}
	
	/**
	 * Initializes everything in the Robot package
	 */
	public void initRobotObjects()
	{
		myNavigation = new Navigation();
		myNavigation.init();
		
		myDriveTrain = new Drivetrain(myControlBoard);
		myDriveTrain.init(leftMasterDrive, rightMasterDrive);
		
		myControlBoard = new ControlBoard();
		myControlBoard.controlBoardInit();
		
		myDriveTrain.resetEncoders();

		myTestCoreObject = new TestCoreObject();
		myTestCoreObject.initTestCoreObject();
		
		Logger.Log("initRobotObjects Is Sucessful!");	
	}
	
	/**
	 * Initializes everything in the Core package
	 */
	public void initCoreObjects()
	{
		myLogger = new Logger();
		theMCP = new CommandController();
		
		ArrayList<Trajectory> trajectories = new ArrayList<>();
		trajectories.add(TrajectoryFactory.getTrajectory(leftSwitch.objects));
		HashMap<String, Runnable> markers = new HashMap<>();
		theTrajectoryIntepreter = new TrajectoryIntepreter(myDriveTrain, myNavigation, trajectories, markers);
		theMCP.init(myRobot);
		
		
		//The handler that handles everything JSON related 
		myAutonomousDataHandler = new AutonomousDataHandler();
		
	  //Takes the CommandController in order to create AutonomousStates that work with the control scheme
		myAutonomousDataHandler.init(theMCP);
		
		//Controller Object for autonomous
		theMACP = new AutonomousController(); 
		
		//Gives the newly read JSON data to the AutonomousController for processing
		theMACP.setAutonomousControllerData(myAutonomousDataHandler);
		
		Logger.Log("initCoreObjects Is Sucessful!");	
	}
	
	@Override
	public void autonomousInit() 
	{
	    myNavigation.resetNAVX();
	    myDriveTrain.shiftLow();
	    StringBuilder autoSelectionKey = null;
	    boolean failSafe = false;
	    
	    try 
	    {
	        Logger.Log("Robot(): AutononousInit()");

	        autoSelectionKey = new StringBuilder();

	        boolean autoPlaybook = SmartDashboard.getBoolean(CrusaderCommon.AUTO_PLAY_BOOK, true);
	        if(autoPlaybook )
	        {
	            autoSelectionKey.append("primary_");
	        }
	        else
	        {
	            autoSelectionKey.append("secondary_");
	        }

	        String robotPosition = SmartDashboard.getString(CrusaderCommon.AUTO_ROBOT_POSITION,  "C");
	        autoSelectionKey.append(robotPosition + "_");

	        String gameData;
	        gameData = DriverStation.getInstance().getGameSpecificMessage();
	        SmartDashboard.putString("GameData", gameData);
	        if(gameData != null && gameData.length() > 0)
	        {
	            autoSelectionKey.append(gameData, 0, 2);
	        }
	        else
	        {
	            failSafe = true;
	        }
	        Logger.Log("Robot(): AutonomousInit(): The 2018 chosen One =  " + autoSelectionKey.toString());
	        
	        SmartDashboard.putString("Auto 2018", autoSelectionKey.toString());
	        /*
	         * AT THIS POINT WE SHOULD HAVE SOMETHING LIKE 
	         * 
	         * Primary_L_LL as the lookup key
	         * 
	         * where 
	         * Primary is the Play book
	         * L is Field Position 
	         * LL = Field Disposition
	         */
	        
	    }
	    catch (Exception ex) 
	    {
	        ex.printStackTrace();
	        Logger.Log("Robot(): AutononousInit() Exception: "+ex);
	    }
	    


	    try { 
	        
	        myDriveTrain.resetEncoders();
	        
	        if(!failSafe) {
	            theMACP.pickAMode2018(autoSelectionKey.toString());
	        }
	        else {
	            theMACP.pickAModeFaileSafe(); 
	        }
	       // theMACP.dumpLoadedStates(aModeSelector);

	        myDriveTrain.getEncoderTicks();
	        
	        //old style auto chooser
	        //int automousModeFromDS =  myAutonomousDataHandler.getModeSelectionFromDashboard(); 
            //Logger.Log("Robot(): AutonomousInit(): The chosen One =  " + String.valueOf(automousModeFromDS));
            //theMACP.pickAMode(automousModeFromDS);
            
	    }
	    catch (Exception ex) 
	    {
	        ex.printStackTrace();
	        Logger.Log("Robot(): AutononousInit() Exception: "+ex);
	    }
	    

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() 
	{
			
		try 
		{
			theMACP.process();
			myNavigation.navxValues();

		} 
		catch (Exception ex) 
		{
		  ex.printStackTrace();
			Logger.Log("Robot(): autonomousPeriodic() Exception: "+ex);
		}
		
	}
	
	public void teleopInit() 
	{
		try 
		{
			Logger.Log("Robot(): TeleopInit()");
		
		} 
		catch (Exception e) 
		{
		  e.printStackTrace();
			Logger.Log("Robot(): TeleopInit() Exception: "+ e);
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() 
	{
	    
		HashMap<Integer,Integer[]> commandValues;	
		
		      
		SmartDashboard.putNumber("Right Encoder", rightMasterDrive.getSelectedSensorPosition(0));
		
		int speedLeft = leftMasterDrive.getSelectedSensorVelocity(0);
		int speedRight = rightMasterDrive.getSelectedSensorVelocity(0);
				
		SmartDashboard.putNumber("Left Speed", speedLeft);
		SmartDashboard.putNumber("Right Speed", speedRight);
		
		try 
		{
			//get the buttons that were pressed on the joySticks/controlBoard
			commandValues = myControlBoard.getControllerInputs();
			
			//pass the buttonsPressed into the commandController for command execution
			theMCP.joyStickCommandExecution(commandValues);

		} 
		catch (Exception e) 
		{
		  e.printStackTrace();
			Logger.Log("Robot(): teleopPeriodic() Exception: "+ e);
		}
		
	}

	/**
	 * This function is called periodically during test mode.
	 */
	double i=0;
	@Override
	public void testPeriodic() 
	{
	    myDriveTrain.shiftHigh();
	    if(i<1) {
	        i+=0.001;
	        
	        myDriveTrain.drive(-i, -i);
	        System.out.println("VOLTAGE: " + i + "   SPEED:" + myDriveTrain.getLeftVelocity());
	    }
		
	}
	
	
	
	
	
	public void autoModeUpdateAndRead()
	{
		theMACP.setAutonomousControllerData(myAutonomousDataHandler);
		
		int autoModeSelection = myAutonomousDataHandler.getModeSelectionFromDashboard();
		Logger.Log("Robot(): disabledPeriodic(): The chosen AutoMode =  " + String.valueOf(autoModeSelection));
	
		//backups  in case sendable chooser disappear
		boolean updateBackup_BecauseTheSendableChooserSucks = false;
		boolean saveBackup_BecauseTheSendableChooserSucks = false;
		boolean  readAmode238DotTxt = false;

		//see if we need to modify the params on a state
		String updateParams = (String) autonomousStateParamsUpdate.getSelected();
		int update = Integer.parseInt(updateParams);
		
		if(updateParams == null)
		{
		  updateBackup_BecauseTheSendableChooserSucks = SmartDashboard.getBoolean("Update Params", false);
		  
		  if(updateBackup_BecauseTheSendableChooserSucks)
		  {
		    update = 1;
		  }
		}
		
		String saveParam = (String) autonomousSaveChooser.getSelected();
		int save = 0;
		if(saveParam == null)
		{
			saveBackup_BecauseTheSendableChooserSucks = SmartDashboard.getBoolean("Save to Amode238", false);
        
			if(saveBackup_BecauseTheSendableChooserSucks)
	        {
	          save = CrusaderCommon.AUTONOMOUS_SAVE;            
	        }
  
			readAmode238DotTxt = SmartDashboard.getBoolean("Read Amode238", false);
			if(readAmode238DotTxt)
			{
			  save = CrusaderCommon.AUTONOMOUS_READ_FILE;
			}
		}
		
		else
		{
		  save = Integer.parseInt(saveParam); 
		}
		 
		Logger.Log("Robot:DisablePeriodic: save = " + save);
		
		theMACP.pickAMode(autoModeSelection);		
		
		if(update == CrusaderCommon.AUTONOMOUS_UPDATE)
		{
			theMACP.updateStateParameters(autoModeSelection);
		}
		
		if(save == CrusaderCommon.AUTONOMOUS_SAVE) 
		{
		  myAutonomousDataHandler.save();	
		}	
		
		if(save == CrusaderCommon.AUTONOMOUS_READ_FILE)
		{
		  myAutonomousDataHandler.readJson(theMCP);
		}
		
		myAutonomousDataHandler.dump();
		
		//RM SmartDashboard.putString("Last Modified : ", myAutonomousDataHandler.getModificationDate());
	}
}
