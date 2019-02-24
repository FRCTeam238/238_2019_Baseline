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
    private static volatile DashBoard238 instance;

    private SendableChooser<String> aModeSelector;
    private SendableChooser<String> stepSelector;
    private SendableChooser<String> stepParamSelector;
    private SendableChooser<String> testSelector;
    private SendableChooser<String> positionSelector;

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

    private DashBoard238() {
    }

        // singleton pattern; we only ever want 1 dashboard instance
    public static DashBoard238 getInstance(){
        // check to see if the instance is already created
        if (instance == null){
            // not created, create a lock on the class in case another thread beat us here
            // and started the creation process already
            synchronized (DashBoard238 .class){
                // double check that the instance still doesn't exist
                // another instance could exist if another thread beat the current thread to here
                // in this case, the lock forces the current thread to wait until out of the
                // sync block
                // when this thread gets in here the instance is already created, so it skips 
                // initialization again
                if (instance == null){
                    instance = new DashBoard238();
                    instance.init();
                }
            }
        }

        //return the static instance for everone to use the same one
        return instance;
    }

    public void init() {
        Logger.Log("DashBoard238.init() Start");

        aModeSelector = new SendableChooser<String>();
        SmartDashboard.putData("AuTo", aModeSelector);
        // Logger.Log("DashBoard238.init() After aMode");

        stepSelector = new SendableChooser<String>();
        SmartDashboard.putData("Steps", stepSelector);

        stepParamSelector = new SendableChooser<String>();
        SmartDashboard.putData("Params", stepParamSelector);
        SmartDashboard.putString("ParamValue", "0");

        testSweetEntries = new HashMap<String, NetworkTableEntry>();

        positionSelector = new SendableChooser<String>();
        positionSelector.setDefaultOption("ProgrammingStation", "ProgrammingStation");
        positionSelector.addOption("Left", "Left");
        positionSelector.addOption("Center", "Center");
        positionSelector.addOption("Right", "Right");
        SmartDashboard.putData("Robot Position", positionSelector);

        testSelector = new SendableChooser<String>();
        

        // Send able Chooser for the state update function
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

        // we "add" something to the test tab
        // then we call "getEntry" to get the networktables Entry for that element
        // and then "put" it in the testSweetEntries Hashmap for future use
        /*
         * if the code was expanded it would look like this SimpleWidget theWidget =
         * testTab.add(X,x); NetworkTabelEntry NTE = theWidget.getENtry();
         * testSweetEntries.put(X,NTE);
         */

        buildElement(leftDriveTrainEncoder, 0, 1, 1, 1, 0);
        buildElement(rightDriveTrainEncoder, 0, 1, 1, 1, 1);
        buildElement(leftDriveTrainTolerance, false, 1, 1, 2, 0);
        buildElement(rightDriveTrainTolerance, false, 1, 1, 2, 1);
        buildElement(encoderDiffTolerance, false, 1, 1, 3, 1);
        // buildElement(elapsedTime, false, 1, 1, 8, 0);
        buildElement(driveTrainDone, false, 1, 1, 3, 0);

        // elevator test elements on TestSweet tab in Shuffleboard
        buildElement(elevatorHeight, 0, 1, 1, 1, 2);
        buildElement(elevatorTolerance, false, 1, 1, 2, 2);
        buildElement(elevatorDone, false, 1, 1, 3, 2);

        // shoulder test elements on TestSweet tab in Shuffleboard
        buildElement(shoulderTarget, 0, 1, 1, 5, 0);
        buildElement(shoulderHeight, 0, 1, 1, 5, 1);

        // needs to be refactored to use testsweetentries
        buildElement("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE, 1, 1, 1, 3);
        buildElement("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO, 1, 1, 2, 3);
        buildElement("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE, 1, 1, 3, 3);

        chickenTab = Shuffleboard.getTab("ChickenVision");

        // chickenTab.buildInto(NetworkTableInstance.getDefault().getTable("ChickenVision"),
        // NetworkTableInstance.getDefault().getTable("ChickenVision"));

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

        //for the logger 
        SmartDashboard.putBoolean("Debug", true);    
        SmartDashboard.putBoolean("Output Log to File", true);
        SmartDashboard.putNumber("Select Auto State", 0);

        initializeScoring();

        SmartDashboard.putBoolean("xBox", false);
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

    public void initializeScoring() {
        
        SmartDashboard.putNumber("ElevatorInput", CrusaderCommon.CARGO_LEVEL_ONE_ELEVATOR);
        SmartDashboard.putNumber("ElevatorOutput", CrusaderCommon.CARGO_LEVEL_ZERO_VALUE);
        SmartDashboard.putNumber("ShoulderInput", CrusaderCommon.CARGO_LEVEL_ONE_SHOULDER);
        SmartDashboard.putNumber("ShoulderOutput", CrusaderCommon.CARGO_LEVEL_ZERO_VALUE);
        SmartDashboard.putBoolean("WristInput", CrusaderCommon.CARGO_LEVEL_ONE_WRIST);
        SmartDashboard.putBoolean("WristOutput", CrusaderCommon.CARGO_LEVEL_WRIST_FALSE);

    }
    
    public void addOrUpdateElement(String tabName, String elementName, Object val){
        String key = tabName + "$" + "elementName";
        NetworkTableEntry entry;
        if (!testSweetEntries.containsKey(key)){
            ShuffleboardTab tab = Shuffleboard.getTab(tabName);
            entry = tab.add(elementName, val).getEntry();
            testSweetEntries.put(key, entry);
        } else {
            entry = testSweetEntries.get(key);
            entry.setValue(val);
        }
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

    public String getSelectedAutonomousModeStep() {
        String selectedAutoModeStep = stepSelector.getSelected();

        Logger.Log("DashBoard238.getSelectedAutonomousModeStep(): AutomodeStep = " + selectedAutoModeStep);

        return selectedAutoModeStep;
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

    public double getElevator1() {
        NetworkTableEntry value = testSweetEntries.get("ELEV_SETPT_1");
        return value.getDouble(2);
    }

    public double getElevator2() {
        NetworkTableEntry value = testSweetEntries.get("ELEV_SETPT_2");
        return value.getDouble(5);
    }
    
    public double getElevator3() {
        NetworkTableEntry value = testSweetEntries.get("ELEV_SETPT_3");
        return value.getDouble(0);
    }
    public void setTestDrivetrainEncodersIndicators(double leftDrivetrainEncoderValue,
            double rightDrivetrainEncoderValue, boolean leftDrivetrainToleranceValue,
            boolean rightDrivetrainToleranceValue, boolean encoderDifferenceToleranceValue, double elapsedTimeValue) {

        testSweetEntries.get(leftDriveTrainEncoder).setNumber(leftDrivetrainEncoderValue);
        testSweetEntries.get(rightDriveTrainEncoder).setNumber(rightDrivetrainEncoderValue);
        testSweetEntries.get(leftDriveTrainTolerance).setBoolean(leftDrivetrainToleranceValue);
        testSweetEntries.get(rightDriveTrainTolerance).setBoolean(rightDrivetrainToleranceValue);
        testSweetEntries.get(encoderDiffTolerance).setBoolean(encoderDifferenceToleranceValue);
        // testSweetEntries.get(elapsedTime).setNumber(elapsedTimeValue);
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
    

    public TargetValues getTargetValues() {

        double elevatorHeight = SmartDashboard.getNumber("ElevatorInput", CrusaderCommon.CARGO_LEVEL_ONE_ELEVATOR);
        double shoulderAngle = SmartDashboard.getNumber("ShoulderInput", CrusaderCommon.CARGO_LEVEL_ONE_SHOULDER);
        Boolean wristPosition = SmartDashboard.getBoolean("WristInput", CrusaderCommon.CARGO_LEVEL_ONE_WRIST);

        TargetValues values = new TargetValues(elevatorHeight, shoulderAngle, wristPosition);
        return values;
    }

    public void reflectPosition(double s_height, double e_height) {
        SmartDashboard.putNumber("E_HEIGHT", e_height);
        SmartDashboard.putNumber("S_ANGLE", s_height);
    }
    

    public void setTargetValues() {

        TargetValues selectedTargetValues = getTargetValues();
        SmartDashboard.putNumber("ElevatorOutput", selectedTargetValues.elevatorHeights);
        SmartDashboard.putNumber("ShoulderOutput", selectedTargetValues.shoulderAngle);
        SmartDashboard.putBoolean("WristOutput", selectedTargetValues.wristPosition);
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

        // testSweetEntries.get(shoulderHeight).setNumber();

    }

    public void addAModeEntry(String name, String object) {

        aModeSelector.addOption(name, object);

    }

    public void addDefaultAModeEntry(String name, String object) {

        aModeSelector.setDefaultOption(name, object);

    }

    public void addAModeStep(String name, Boolean defaultOption) {

        if (defaultOption) {
            stepSelector.setDefaultOption(name, name);
        } else {
            stepSelector.addOption(name, name);
        }

    }

    public void addAModeStepParams(String name, Boolean defaultOption) {

        if (defaultOption) {
            stepParamSelector.setDefaultOption(name, name);
        } else {
            stepParamSelector.addOption(name, name);
        }

    }

    public void addAModeStepClear() {

        stepSelector.close();
        stepSelector = new SendableChooser<String>();

        SmartDashboard.putData("Steps", stepSelector);

    }

    public void addAModeStepParamsClear() {

        stepParamSelector.close();
        stepParamSelector = new SendableChooser<String>();

        SmartDashboard.putData("Params", stepParamSelector);

    }

    public void setParamSelection(){

        String param = stepParamSelector.getSelected();

        SmartDashboard.putString("ParamValue", param);

    }

    public TapeStatus getTapeStatus() {

        double yaw = testSweetEntries.get("tapeYaw").getDouble(0);
        boolean detected = testSweetEntries.get("tapeDetected").getBoolean(false);

        return new TapeStatus(yaw, detected);
    }

 
    public void update() {
        Shuffleboard.update();
    }

}
