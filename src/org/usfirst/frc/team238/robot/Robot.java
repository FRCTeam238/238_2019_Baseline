/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team238.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.ControlMode;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.HashMap;

import org.usfirst.frc.team238.core.AutonomousController;
import org.usfirst.frc.team238.core.AutonomousDataHandler;
import org.usfirst.frc.team238.core.AutonomousPlay;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Drivetrain;
import RealBot.TrajectoryIntepreter;
import RealBot.TrajectoryFactory;
import RealBot.Trajectory;
import org.usfirst.frc.team238.robot.Elevator;
import org.usfirst.frc.team238.robot.IntakeWrist;

import org.usfirst.frc.team238.lalaPaths.goStraight;
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
	double[] dataFromVision;
		
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
	DifferentialDrive myRobotDrive;
	Navigation myNavigation;
	Drivetrain myDriveTrain;
	DriverStation myDriverstation;
	Logger myLogger;
	DriverStation myDriverStation;
	TrajectoryIntepreter theTrajectoryIntepreter;
	Elevator theElevator;
	IntakeWrist theIntake;
	Ramp theRamp;
	
	//There shouldn't be two of these
	Alliance myAllianceTeam;
		
		// Autonomous Mode Support
	String autoMode;
	/*AutonomousDrive autonomousDrive;*/
	private AutonomousDataHandler myAutonomousDataHandler;
		//private TargetingDataHandler myTargetingData;
	private AutonomousController theMACP;
	
	SendableChooser<String> autonomousSaveChooser;
	//SendableChooser<String> targetingStateParamsUpdate;
	//SendableChooser<String> targetingSaveChooser;
	SendableChooser<String> aModeSelector;
	SendableChooser<String> autonomousStateParamsUpdate;
	
	public void disabledInit() 
	{
		try 
		{
			// only use checkForSmartDashboardChanges function in init methods
			// or you will smoke the roborio into a useless pile of silicon
			//checkForSmartDashboardChanges(CrusaderCommon.PREFVALUE_OP_AUTO, CrusaderCommon.PREFVALUE_OP_AUTO_DEFAULT);
			
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
				theElevator.getEncoderTicks();
				
				
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
			System.out.println("RobotInit()");
			
			//RM SmartDashboard.putBoolean("Output Log to File", true);
		   
		  	//RM SmartDashboard.putBoolean("Debug", true);
			
			myRobot = Robot.this; 
		
			initSmartDashboardObjects();
			
			initTalons();
			initRobotObjects();
			initCoreObjects();

			Logger.Log("Robot(): robotInit(): Fully Initialized");
			
			//RM SmartDashboard.putBoolean("Output Log to File", false);
		   
			//RM SmartDashboard.putBoolean("Debug", false);
		} 
		catch (Exception ex) 
		{
		  ex.printStackTrace();
			Logger.Log("Robot(): robotInit() Exception : "+ex);
		}
	}
	public void initOldStyleAutoModeDashboard() {
	    SmartDashboard.putNumber("Chosen Auto Mode", 0);
	  //Create a new SendableChooser for the save function
        autonomousSaveChooser = new SendableChooser<String>();
        autonomousSaveChooser.addDefault("do nothing", "0");
        autonomousSaveChooser.addObject("drive ten feet", "1");
        autonomousSaveChooser.addObject("Primary", "2");
        autonomousSaveChooser.addObject("Switch", "3");
        SmartDashboard.putData("AutoModes", autonomousSaveChooser);
	    
      //RM SmartDashboard.putData("Edit State Params", autonomousStateParamsUpdate);
        //RM SmartDashboard.putData("Save Changes", autonomousSaveChooser);     
        //RM SmartDashboard.putBoolean("Update Params", false);
        //RM SmartDashboard.putBoolean("Save to Amode238", false);
        //RM SmartDashboard.putBoolean("Read Amode238", false);
        
        SmartDashboard.putNumber("TICKS PER INCH", 1627);

        //Sendable Chooser for the state update function
        autonomousStateParamsUpdate = new SendableChooser<String>();
        autonomousStateParamsUpdate.addDefault("As Received", "0");
        autonomousStateParamsUpdate.addObject("UPDATE", "1");
    
	}
	public void initSmartDashboardObjects()
	{
		  
		  SmartDashboard.putBoolean(CrusaderCommon.AUTO_PLAY_BOOK, true);
		  SmartDashboard.putString(CrusaderCommon.AUTO_ROBOT_POSITION,  "C");
		  SmartDashboard.putString("P or S", "nothing");
		  
		  aModeSelector = new SendableChooser<String>();
	
		  //Sendable Chooser for the state update function
		  autonomousStateParamsUpdate = new SendableChooser<String>();
		  autonomousStateParamsUpdate.addDefault("As Received", "0");
		  autonomousStateParamsUpdate.addObject("UPDATE", "1");
	  
	}

	/*
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
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
		
		//RM SmartDashboard.putNumber("Left Encoder", leftMasterDrive.getSelectedSensorPosition(0));
        //RM SmartDashboard.putNumber("Right Encoder", rightFrontDrive.getSelectedSensorPosition(0));   
	    //System.out.println("ELEVATOR TICKS:" +theElevator.getEncoderTicks());
		//int currentYaw = (int) myNavigation.getYaw();           
        //RM SmartDashboard.putNumber("AutonomousPeriodic: The CurrentYaw ", currentYaw);   
	    
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
		
		SmartDashboard.putNumber("Left Encoder", theElevator.getEncoderTicks());        
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
		
		//System.out.println("ANGLE:" + myNavigation.getYaw());
        /*
        leftMasterDrive.set(ControlMode.PercentOutput, -0.5); 
        leftDriveFollower1.set(ControlMode.PercentOutput, -0.5);; 
        rightMasterDrive.set(ControlMode.PercentOutput, -0.5);; 
        rightDriveFollower1.set(ControlMode.PercentOutput, -0.5);; 
        */
		//SmartDashboard.putNumber("Left Encoder", leftMasterDrive.getSelectedSensorPosition(0));
        //theElevator.getEncoderTicks();
        //myDriveTrain.getEncoderTicks();
        //System.out.println("Speed: " + speedLeft + " " + speedRight);

			
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
		
		theIntake = new IntakeWrist();
		theIntake.init();
		
		theElevator = new Elevator();
		theElevator.init();
		
		theRamp = new Ramp();
		theRamp.init();
		
		myDriveTrain.resetEncoders();
		
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
		theMCP.init(myDriveTrain, myNavigation, myRobot, theTrajectoryIntepreter, theElevator, theIntake, theRamp);
		
		
		//The handler that handles everything JSON related 
		myAutonomousDataHandler = new AutonomousDataHandler();
		
	  //Takes the CommandController in order to create AutonomousStates that work with the control scheme
		myAutonomousDataHandler.init(theMCP);
		
		//Controller Object for autonomous
		theMACP = new AutonomousController(); 
		
		//Gives the newly read JSON data to the AutonomousController for processing
		theMACP.setAutonomousControllerData(myAutonomousDataHandler);
		
		Logger.Log("iniCoreObjects Is Sucessful!");	
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
