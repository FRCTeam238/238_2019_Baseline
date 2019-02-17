package org.usfirst.frc.team238.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashBoard238 {
    Robot myRobot;
    private SendableChooser<String> aModeSelector;
    private SendableChooser<String> testSelector;
    private SendableChooser<String> positionSelector;
    private SendableChooser<String> stepSelector;

    public SimpleWidget robotPosition;
    ShuffleboardTab testTab;
    ShuffleboardTab chickenTab;
    public SimpleWidget elevatorTestInfo1;
    public SimpleWidget elevatorTestInfo2;
    public SimpleWidget elevatorTestInfo3;
    public boolean drivetrainDone;

    String leftDriveTrainEncoder = "L_DT_ENC";
    String rightDriveTrainEncoder = "R_DT_ENC";
    String leftDriveTrainTolerance = "L_DT_TOL";
    String rightDriveTrainTolerance = "R_DT_TOL";
    String encoderDiffTolerance = "ENC_DIFF_TOL";
    String elapsedTime = "ELAPS_TIME";
    String driveTrainDone = "Drivetrain Done";

    String elevatorHeight = "Elevator Height";
    String elevatorTolerance = "Elevator Tolerance";
    String elevatorDone = "Elevator Done";
    String shoulderTarget = "Shoulder Target";
    String shoulderHeight = "Shoulder Height";

    HashMap<String, NetworkTableEntry> testSweetEntries;

    public DashBoard238(Robot myRobot) {
        this.myRobot = myRobot;

    }

    public void init() {
        Logger.Log("DashBoard238.init() Start");
        stepSelector = new SendableChooser<String>();
        // for (int i = 0; i < 10; i++) {
        //     stepSelector.addOption("x"+i, "x"+i);
        // }
        SmartDashboard.putData("Steps", stepSelector);
        
        testSweetEntries = new HashMap<String, NetworkTableEntry>();

        positionSelector = new SendableChooser<String>();
        positionSelector.setDefaultOption("ProgrammingStation", "ProgrammingStation");
        positionSelector.addOption("Left", "Left");
        positionSelector.addOption("Center", "Center");
        positionSelector.addOption("Right", "Right");
        SmartDashboard.putData("Robot Position", positionSelector);

        aModeSelector = new SendableChooser<String>();
        SmartDashboard.putData("AuTo", aModeSelector);
        Logger.Log("DashBoard238.init() After aMode");

        testSelector = new SendableChooser<String>();

        //Send able Chooser for the state update function
        testSelector.setDefaultOption("Stop Test", "Stop Test");
        testSelector.addOption("Encoder Test", "Encoder Test");
        testSelector.addOption("Drivetrain Test", "Drivetrain Test");
        testSelector.addOption("Elevator Test", "Elevator Test");
        testSelector.addOption("Elevator Test2", "Elevator Test2");
        testSelector.addOption("Elevator Test3", "Elevator Test3");
        testSelector.addOption("Shoulder Test", "Shoulder Test");
        testSelector.addOption("Climber Test", "Climber Test");

        Shuffleboard.selectTab("TestSweet");
        testSelector.setName("Test Section");
        testTab = Shuffleboard.getTab("TestSweet");
        testTab.add(testSelector).withSize(1, 1).withPosition(0, 0);

        //we "add" something to the test tab
        //then we call "getEntry" to get the networktables Entry for that element 
        //and then "put" it in the testSweetEntries Hashmap for future use
        /* if the code was expanded it would look like this
        *   SimpleWidget theWidget = testTab.add(X,x);
        *   NetworkTabelEntry NTE = theWidget.getENtry(); 
        *   testSweetEntries.put(X,NTE);
        */

        buildElement(leftDriveTrainEncoder, 0, 1, 1, 1, 0);
        buildElement(rightDriveTrainEncoder, 0, 1, 1, 1, 1);
        buildElement(leftDriveTrainTolerance, false, 1, 1, 2, 0);
        buildElement(rightDriveTrainTolerance, false, 1, 1, 2, 1);
        buildElement(encoderDiffTolerance, false, 1, 1, 3, 1);
        //buildElement(elapsedTime, false, 1, 1, 8, 0);
        buildElement(driveTrainDone, false, 1, 1, 3, 0);

        //elevator test elements on TestSweet tab in Shuffleboard
        buildElement(elevatorHeight, 0, 1, 1, 1, 2);
        buildElement(elevatorTolerance, false, 1, 1, 2, 2);
        buildElement(elevatorDone, false, 1, 1, 3, 2);

        //shoulder test elements on TestSweet tab in Shuffleboard
        buildElement(shoulderTarget, 0, 1, 1, 5, 0);
        buildElement(shoulderHeight, 0, 1, 1, 5, 1);

        //needs to be refactored to use  testsweetentries 
        buildElement("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE, 1, 1, 1, 3);
        buildElement("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO, 1, 1, 2, 3);
        buildElement("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE, 1, 1, 3, 3);

        chickenTab = Shuffleboard.getTab("ChickenVision");

        //chickenTab.buildInto(NetworkTableInstance.getDefault().getTable("ChickenVision"), NetworkTableInstance.getDefault().getTable("ChickenVision"));

        buildElement(chickenTab, "TapeBlur", 1, 1, 1, 6, 0);
        buildElement(chickenTab, "TapeLowerH", 55, 1, 1, 7, 0);
        buildElement(chickenTab, "TapeLowerS", 128, 1, 1, 6, 1);
        buildElement(chickenTab, "TapeLowerV", 133, 1, 1, 7, 1);
        buildElement(chickenTab, "TapeUpperH", 109, 1, 1, 6, 2);
        buildElement(chickenTab, "TapeUpperS", 255, 1, 1, 7, 2);
        buildElement(chickenTab, "TapeUpperV", 255, 1, 1, 6, 3);

        testSweetEntries.put("tapeYaw", NetworkTableInstance.getDefault().getTable("Shuffleboard")
                .getSubTable("ChickenVision").getEntry("tapeYaw"));

        testSweetEntries.put("tapeDetected", NetworkTableInstance.getDefault().getTable("Shuffleboard")
                .getSubTable("ChickenVision").getEntry("tapeDetected"));

        Logger.Log("DashBoard238.init() end");
    }

    void buildElement(String elementName, Boolean value, int sizeX, int sizeY, int posX, int posY) {
        SimpleWidget theWidget = testTab.add(elementName, value);
        testSweetEntries.put(elementName, theWidget.getEntry());
        theWidget.withSize(sizeX, sizeY).withPosition(posX, posY);
      
    }

    void buildElement(String elementName, Double value, int sizeX, int sizeY, int posX, int posY) {
        SimpleWidget theWidget = testTab.add(elementName, value);
        testSweetEntries.put(elementName, theWidget.getEntry());
        theWidget.withSize(sizeX, sizeY).withPosition(posX, posY);
    }

    void buildElement(String elementName, int value, int sizeX, int sizeY, int posX, int posY) {
        buildElement(testTab, elementName, value, sizeX, sizeY, posX, posY);
    }

    void buildElement(ShuffleboardTab tab, String elementName, int value, int sizeX, int sizeY, int posX, int posY) {
        SimpleWidget theWidget = tab.add(elementName, value);
        testSweetEntries.put(elementName, theWidget.getEntry());
        theWidget.withSize(sizeX, sizeY).withPosition(posX, posY);
    }

    public String getSelectedTest() {

        return testSelector.getSelected();

    }

    public String getRobotPosition() {

        return positionSelector.getSelected();
    }

    public SendableChooser<String> getAutonomusModeSelector() {

        return aModeSelector;
    }

    public String getSelectedAutonomousMode() {
        String selectedAutoMode = aModeSelector.getSelected();
        Logger.Log("DashBoard238.getSelectedAutonomousMode(): Automode = " + selectedAutoMode);
        return selectedAutoMode;
    }

    public SendableChooser<String> getTestSelector() {

        return testSelector;
    }

    public void putAutoMode() {

    }

    public void setTestDrivetrainEncodersIndicators(double leftDrivetrainEncoderValue,
            double rightDrivetrainEncoderValue) {

        testSweetEntries.get(leftDriveTrainEncoder).setNumber(leftDrivetrainEncoderValue);
        testSweetEntries.get(rightDriveTrainEncoder).setNumber(rightDrivetrainEncoderValue);

    }

    public void setTestDrivetrainEncodersIndicators(double leftDrivetrainEncoderValue,
            double rightDrivetrainEncoderValue, boolean leftDrivetrainToleranceValue,
            boolean rightDrivetrainToleranceValue, boolean encoderDifferenceToleranceValue, double elapsedTimeValue) {

        testSweetEntries.get(leftDriveTrainEncoder).setNumber(leftDrivetrainEncoderValue);
        testSweetEntries.get(rightDriveTrainEncoder).setNumber(rightDrivetrainEncoderValue);
        testSweetEntries.get(leftDriveTrainTolerance).setBoolean(leftDrivetrainToleranceValue);
        testSweetEntries.get(rightDriveTrainTolerance).setBoolean(rightDrivetrainToleranceValue);
        testSweetEntries.get(encoderDiffTolerance).setBoolean(encoderDifferenceToleranceValue);
        //testSweetEntries.get(elapsedTime).setNumber(elapsedTimeValue);
        testSweetEntries.get(driveTrainDone).setBoolean(true);

    }

    public void setTestElevatorIndicators(boolean value) {
        SmartDashboard.putBoolean("EL.Enc", value);
    }

    public DashboardValues getTestElevatorHeights() {

        double elevatorSetpointOne = testTab.add("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE).getEntry()
                .getDouble(CrusaderCommon.ELEVATOR_SETPOINT_ONE);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointOne = " + elevatorSetpointOne);

        double elevatorSetpointTwo = testTab.add("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO).getEntry()
                .getDouble(CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointTwo = " + elevatorSetpointTwo);

        double elevatorSetpointThree = testTab.add("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE).getEntry()
                .getDouble(CrusaderCommon.ELEVATOR_SETPOINT_THREE);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointThree = " + elevatorSetpointThree);

        DashboardValues testElevatorHeights = new DashboardValues(elevatorSetpointOne, elevatorSetpointTwo,
                elevatorSetpointThree);

        return testElevatorHeights;
    }

    public void putTestElevatorTestOne(double elevatorSetpointOneTest) {

        SmartDashboard.putNumber("ELEV_TEST_ONE", elevatorSetpointOneTest);

    }

    public void putElevatorData(double elevatorHeightValue, boolean elevatorHeightToleranceValue) {

        testSweetEntries.get(elevatorTolerance).setBoolean(elevatorHeightToleranceValue);
        testSweetEntries.get(elevatorDone).setBoolean(true);
        testSweetEntries.get(elevatorHeight).setDouble(elevatorHeightValue);

    }

    public double getShoulderData() {

        return testSweetEntries.get(shoulderTarget).getDouble(0);
    }

    public void putShoulderData(double shoulderAngle) {

        testSweetEntries.get(shoulderHeight).setDouble(shoulderAngle);

        //testSweetEntries.get(shoulderHeight).setNumber();

    }

    public void addAModeEntry(String name, String object) {

        aModeSelector.addOption(name, object);

    }

    public void addDefaultAModeEntry(String name, String object) {

        aModeSelector.setDefaultOption(name, object);

    }

    public void addAModeStep(String name, Boolean defaultOption) {

        if( defaultOption){
            stepSelector.setDefaultOption(name, name);
        }
        else{
            stepSelector.addOption(name, name);
        }
       

    }

    public void addAModeStepClear() {

        
            stepSelector.close();
            stepSelector = new SendableChooser<String>();
           
            SmartDashboard.putData("Steps", stepSelector);
       

    }


    public TapeStatus getTapeStatus() {

        double yaw = testSweetEntries.get("tapeYaw").getDouble(0);
        boolean detected = testSweetEntries.get("tapeDetected").getBoolean(false);

        return new TapeStatus(yaw, detected);
    }
 // aModeSelector.setDefaultOption("Cargo Center Left", "Cargo Center Left");
        // aModeSelector.addOption("Cargo Center Right", "Cargo Center Right");
        // aModeSelector.addOption("Cargo Left Front", "Cargo Left Front");
        // aModeSelector.addOption("Cargo Left Middle", "Cargo Left Middle");
        // aModeSelector.addOption("Cargo Left Back", "Cargo Left Back");
        // aModeSelector.addOption("Cargo Right Front", "Cargo Right Front");
        // aModeSelector.addOption("Cargo Right Middle", "Cargo Right Middle");
        // aModeSelector.addOption("Cargo Right Back", "Cargo Right Back");
        // aModeSelector.addOption("Left Rocket Front", "Left Rocket Front");
        // aModeSelector.addOption("Left Rocket Middle", "Left Rocket Middle");
        // aModeSelector.addOption("Left Rocket Back", "Left Rocket Back");
        // aModeSelector.addOption("Right Rocket Front", "Right Rocket Front");
        // aModeSelector.addOption("Right Rocket Middle", "Right Rocket Middle");
        // aModeSelector.addOption("Right Rocket Back", "Right Rocket Back");
   
}

