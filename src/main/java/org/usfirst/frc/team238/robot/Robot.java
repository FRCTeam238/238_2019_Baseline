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

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;

import java.util.HashMap;

import org.usfirst.frc.team238.core.AutonomousController;
import org.usfirst.frc.team238.core.AutonomousController2019;
import org.usfirst.frc.team238.core.AutonomousDataHandler;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.core.TestCmdFactory;
import org.usfirst.frc.team238.core.TestController;
import org.usfirst.frc.team238.core.TestStep;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.testSteps.TestElevator;
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
public class Robot extends TimedRobot {

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
    public CommandController theMCP;
    public Navigation myNavigation;
    public Drivetrain myDriveTrain;
    DriverStation myDriverstation;
    public TrajectoryIntepreter theTrajectoryIntepreter;
    public DashBoard238 myDashBoard238;
    public Elevator myElevator;
    public Shoulder myShoulder;
    public Wrist myWrist;
    public Hatch myHatch;
    static String previousTestStep;
    Compressor myCompressor;

    // Testing vars
    TestController myTestController;

    // There shouldn't be two of these
    Alliance myAllianceTeam;

    // Autonomous Mode Support
    String autoMode;

    // private AutonomousDataHandler myAutonomousDataHandler;
    // private AutonomousController theMACP;

    SendableChooser<String> autonomousSaveChooser;
    SendableChooser<String> aModeSelector;
    SendableChooser<String> autonomousStateParamsUpdate;

    String robotPosition;

    private TestCmdFactory myTestCmdFactory;

    Boolean FailedInitilization = false;
    Boolean loadedAmodeSteps = false;
    public AutonomousController2019 theMACP2019;
    private String currentTest;

    public void disabledInit() {
        try {

            Logger.Log("Robot(): disabledInit:");

        } catch (Exception e) {
            e.printStackTrace();
            Logger.Log("Robot(): disabledInit Exception: " + e);
        }
    }

    public void disabledPeriodic() {
        if (FailedInitilization) {
            return;
        }

        try {
            if (count > 150) {
                count = 0;
                myDriveTrain.resetEncoders();

                String selectedTest = myDashBoard238.getSelectedAutonomousMode();

                // build a Dropdown with the individual steps
                if (!selectedTest.equals(currentTest)) {
                    theMACP2019.populateAutoModeSteps(selectedTest);
                    // build another dropdown with the params for the selected step
                    theMACP2019.populateAutoModeStepsParams();
                }
               
                // populate the param field with the selcted param
                theMACP2019.populateAutoModeStepsParamsValue();
                // see if we need to update

                currentTest = selectedTest;

                myDashBoard238.setTargetValues();

                double s_height = myShoulder.getAngle();
                double e_height = myElevator.getHeight();
             
                myDashBoard238.reflectPosition( s_height, e_height);
            }

            count++;

        } catch (Exception e) {
            e.printStackTrace();
            Logger.Log("Robot(): disabledPeriodic(): disabledPriodic exception: " + e);
        }

    }

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {

        try {
            Logger.Log("Starting RobotInit()");

            myRobot = Robot.this;

            initSmartDashboardObjects();
            // if (robotPosition.equals("ProgrammingStation"))
            // {
            // initTalonsProgrammingStation();
            // }
            // else
            // {
            initTalons();
            // }

            initRobotObjects();
            initCoreObjects();
            long startProcessingTime = System.currentTimeMillis();
            Logger.Log("Robot(): robotInit(): Fully Initialized" + startProcessingTime);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.Log("Robot(): robotInit() Exception : " + ex);

        }
    }

    public void initSmartDashboardObjects() {

        //myDashBoard238 = new DashBoard238(this);
        //myDashBoard238.init();
        myDashBoard238 = DashBoard238.getInstance();
        aModeSelector = myDashBoard238.getAutonomusModeSelector();
        //robotPosition = myDashBoard238.getRobotPosition();

    }

    public void initTalonsProgrammingStation() {
        leftMasterDrive = new TalonSRX(CrusaderCommon.DRIVE_TRAIN_LEFT_MASTER_DRIVER_STATION);
        leftMasterDrive.setInverted(true);
        leftMasterDrive.setNeutralMode(NeutralMode.Brake);

        rightMasterDrive = new TalonSRX(CrusaderCommon.DRIVE_TRAIN_RIGHT_MASTER_DRIVER_STATION);
        rightMasterDrive.setNeutralMode(NeutralMode.Brake);

        rightMasterDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 0);
        leftMasterDrive.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 0);
        Logger.Log("Programming initTalons Is Sucessful!");
    }

    /**
     * Initializes Talons
     */
    public void initTalons() {
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
    public void initRobotObjects() {
        myNavigation = new Navigation();
        myNavigation.init();

        myDriveTrain = new Drivetrain(myControlBoard);
        myDriveTrain.init(leftMasterDrive, rightMasterDrive);

        myControlBoard = new ControlBoard();
        myControlBoard.controlBoardInit();

        myDriveTrain.resetEncoders();

        myElevator = new Elevator();
        myElevator.init();

        myShoulder = new Shoulder();
        myShoulder.init();

        myWrist = new Wrist();

        myHatch = new Hatch();

        try {
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setFPS(20);
        camera.setResolution(320, 240);
        } catch (Exception ex){
            Logger.Log("initRobotObjects: No Camera");
        }
        // myCompressor = new Compressor();
        // myCompressor.setClosedLoopControl(false);
        // myTestCoreObject = new TestCoreObject();
        // myTestCoreObject.initTestCoreObject();
        Logger.Log("initRobotObjects Is Sucessful!");
    }

    /**
     * Initializes everything in the Core package
     */
    public void initCoreObjects() {
        theMCP = new CommandController();

        // ArrayList<Trajectory> trajectories = new ArrayList<>();
        // trajectories.add(TrajectoryFactory.getTrajectory(leftSwitch.objects));
        // HashMap<String, Runnable> markers = new HashMap<>();
        // theTrajectoryIntepreter = new TrajectoryIntepreter(myDriveTrain,
        // myNavigation, trajectories, markers);

        theMCP.init(myRobot);

        // The handler that handles everything JSON related
        // myAutonomousDataHandler = new AutonomousDataHandler();

        // Takes the CommandController in order to create AutonomousStates that work
        // with the control scheme
        // myAutonomousDataHandler.init(theMCP,
        // myDashBoard238.getAutonomusModeSelector());

        // Controller Object for autonomous
        // theMACP = new AutonomousController();
        theMACP2019 = new AutonomousController2019(this);

        // Gives the newly read JSON data to the AutonomousController for processing
        // theMACP.setAutonomousControllerData(myAutonomousDataHandler);

        Logger.Log("initCoreObjects Is Sucessful!");
    }

    @Override
    public void autonomousInit() {
        myNavigation.resetNAVX();
        // myDriveTrain.shiftLow();
        String autoSelectionKey = myDashBoard238.getSelectedAutonomousMode();
        boolean failSafe = false;

        try {

            myDriveTrain.resetEncoders();

            if (!failSafe) {
                // theMACP.pickAMode2018(autoSelectionKey);
                theMACP2019.pickAMode(autoSelectionKey);
            } else {
                // theMACP.pickAModeFaileSafe();
                theMACP2019.pickAModeFaileSafe();
            }
            // //theMACP.dumpLoadedStates(aModeSelector);

            myDriveTrain.getEncoderTicks();

            // old style auto chooser
            // int automousModeFromDS =
            // myAutonomousDataHandler.getModeSelectionFromDashboard();
            // Logger.Log("Robot(): AutonomousInit(): The chosen One = " +
            // String.valueOf(automousModeFromDS));
            // theMACP.pickAMode(automousModeFromDS);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.Log("Robot(): AutononousInit() Exception: " + ex);
        }

    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {

        try {
            // theMACP.process();
            theMACP2019.process();
            myNavigation.navxValues();

            double s_height = myShoulder.getAngle();
            double e_height = myElevator.getHeight();
         
            myDashBoard238.reflectPosition(s_height, e_height);

        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.Log("Robot(): autonomousPeriodic() Exception: " + ex);
        }

    }

    public void teleopInit() {
        try {
            Logger.Log("Robot(): TeleopInit()");

        } catch (Exception e) {
            e.printStackTrace();
            Logger.Log("Robot(): TeleopInit() Exception: " + e);
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {

        HashMap<Integer, Integer[]> commandValues;

        myDashBoard238.putDrivetrainEncoders(myDriveTrain.getEncoderTicks2());
        int speedLeft = leftMasterDrive.getSelectedSensorVelocity(0);
        int speedRight = rightMasterDrive.getSelectedSensorVelocity(0);
        
        try {
            // get the buttons that were pressed on the joySticks/controlBoard
            commandValues = myControlBoard.getControllerInputs();

            // pass the buttonsPressed into the commandController for command execution
            theMCP.joyStickCommandExecution(commandValues);

            double s_height = myShoulder.getAngle();
            double e_height = myElevator.getHeight();
             
            myDashBoard238.reflectPosition( s_height, e_height);
           

        } catch (Exception e) {
            e.printStackTrace();
            Logger.Log("Robot(): teleopPeriodic() Exception: " + e);
        }

    }

    @Override
    public void testInit() {

        // myTestController = new TestController();

        // myTestController.init(null, myTestSweet238);

        myTestCmdFactory = new TestCmdFactory();

        myTestCmdFactory.init();

        myTestCmdFactory.createTestCommands(this);

    }

    /**
     * This function is called periodically during test mode. read switch toggles
     * from smartdashboard put it in parameters variable
     * 
     */
    double i = 0;

    @Override
    public void testPeriodic() {

        String selectedTest = myDashBoard238.getSelectedTest();
        String[] parameters = new String[1];
        Logger.Log("Selected Test = " + selectedTest);

        parameters[0] = selectedTest;

        TestStep selectedTestStep = myTestCmdFactory.getTestStep(selectedTest);

        if (selectedTest != previousTestStep) {
            selectedTestStep.reset();
        }
        // do we have a test
        //
        // if the test is not done...

        if (selectedTestStep == null) {

            Logger.Log("Robot.testPeriodic(): Test Does Not Exist");
        } else if (!selectedTestStep.done()) {
            selectedTestStep.init(parameters);
            selectedTestStep.process();
        } else {

        }

        previousTestStep = selectedTest;

    }

    public void autoModeUpdateAndRead() {

        String autoModeSelection = myDashBoard238.getSelectedAutonomousMode();
        Logger.Log("Robot(): disabledPeriodic(): The chosen AutoMode =  " + autoModeSelection);

        // backups in case sendable chooser disappear
        boolean updateBackup_BecauseTheSendableChooserSucks = false;
        boolean saveBackup_BecauseTheSendableChooserSucks = false;
        boolean readAmode238DotTxt = false;

        // see if we need to modify the params on a state
        String updateParams = (String) autonomousStateParamsUpdate.getSelected();
        int update = Integer.parseInt(updateParams);

        if (updateParams == null) {
            updateBackup_BecauseTheSendableChooserSucks = SmartDashboard.getBoolean("Update Params", false);

            if (updateBackup_BecauseTheSendableChooserSucks) {
                update = 1;
            }
        }

        String saveParam = (String) autonomousSaveChooser.getSelected();
        int save = 0;
        if (saveParam == null) {
            saveBackup_BecauseTheSendableChooserSucks = SmartDashboard.getBoolean("Save to Amode238", false);

            if (saveBackup_BecauseTheSendableChooserSucks) {
                save = CrusaderCommon.AUTONOMOUS_SAVE;
            }

            readAmode238DotTxt = SmartDashboard.getBoolean("Read Amode238", false);
            if (readAmode238DotTxt) {
                save = CrusaderCommon.AUTONOMOUS_READ_FILE;
            }
        }

        else {
            save = Integer.parseInt(saveParam);
        }

        Logger.Log("Robot:DisablePeriodic: save = " + save);

        theMACP2019.pickAMode(autoModeSelection);

        if (update == CrusaderCommon.AUTONOMOUS_UPDATE) {
            theMACP2019.updateStateParameters(autoModeSelection);
        }

        // if (save == CrusaderCommon.AUTONOMOUS_SAVE) {
        // myAutonomousDataHandler.save();
        // }

        // if (save == CrusaderCommon.AUTONOMOUS_READ_FILE) {
        // myAutonomousDataHandler.readJson(theMCP);
        // }

        // myAutonomousDataHandler.dump();

        // RM SmartDashboard.putString("Last Modified : ",
        // myAutonomousDataHandler.getModificationDate());
    }
}
