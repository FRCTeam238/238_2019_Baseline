package org.usfirst.frc.team238.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashBoard238
{
    Robot myRobot;
    private SendableChooser<String> aModeSelector;
    private SendableChooser<String> testSelector;
    private String robotPosition;
    ShuffleboardTab testTab;
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
    
   
    HashMap<String,NetworkTableEntry> testSweetEntries;

    public DashBoard238(Robot myRobot)
    {
        this.myRobot = myRobot;
        
    }
    
    public void init()
    {
        testSweetEntries = new  HashMap<String,NetworkTableEntry>();
        
        SmartDashboard.putString(CrusaderCommon.AUTO_ROBOT_POSITION, "C");

        //autonmous mode selector ie dropdown selection
        aModeSelector = new SendableChooser<String>();
        aModeSelector.addOption("CargoShip 1", "CargoShip 1");
        aModeSelector.addOption("CargoShip 2", "CargoShip 1");
        aModeSelector.addOption("CargoShip 3", "CargoShip 2");
        aModeSelector.addOption("CargoShip 4", "CargoShip 3");
        aModeSelector.addOption("CargoShip 5", "CargoShip 4");
        aModeSelector.addOption("CargoShip 6", "CargoShip 5");
        SmartDashboard.putData("AuTo", aModeSelector);

        robotPosition = SmartDashboard.getString(CrusaderCommon.AUTO_ROBOT_POSITION, "C");

        testSelector = new SendableChooser<String>();

        //Send able Chooser for the state update function
        testSelector.addOption("Stop Test", "Stop Test");
        testSelector.addOption("Drivetrain Test", "Drivetrain Test");
        testSelector.addOption("Elevator Test", "Elevator Test");
        testSelector.addOption("Elevator Test2", "Elevator Test2");
        testSelector.addOption("Elevator Test3", "Elevator Test3");
        testSelector.addOption("Wrist Test", "Wrist Test");
        testSelector.addOption("Climber Test", "Climber Test");

        Shuffleboard.selectTab("TestSweet");
        testSelector.setName("Test Section");
        testTab = Shuffleboard.getTab("TestSweet");
        testTab.add(testSelector);

         //we "add" something to the test tab
         //then we call "getEntry" to get the networktables Entry for that element 
         //and then "put" it in teh testSweetEntries Hashmap for future use
        /* if the code was expanded it would look like this
        *   SimpleWidget theWidget = testTab.add(X,x);
        *   NetworkTabelEntry NTE = theWidget.getENtry(); 
        *   testSweetEntries.put(X,NTE);
        */
        
        //DriveTrain test elements on the TestSweet tab in Shuffleboard
        testSweetEntries.put(leftDriveTrainEncoder, testTab.add(leftDriveTrainEncoder, 0).getEntry());
        testSweetEntries.put(rightDriveTrainEncoder, testTab.add(rightDriveTrainEncoder, 0).getEntry());
        testSweetEntries.put(leftDriveTrainTolerance, testTab.add(leftDriveTrainTolerance, false).getEntry());
        testSweetEntries.put(rightDriveTrainTolerance, testTab.add(rightDriveTrainTolerance, false).getEntry());
        testSweetEntries.put(encoderDiffTolerance , testTab.add(encoderDiffTolerance, false).getEntry());
        testSweetEntries.put(elapsedTime, testTab.add(elapsedTime, 0).getEntry());
        testSweetEntries.put(driveTrainDone, testTab.add(driveTrainDone, false).getEntry());
        
        //elevator test elements on TestSweet tab in Shuffleboard
        testSweetEntries.put(elevatorHeight,testTab.add(elevatorHeight, 0).getEntry());
        testSweetEntries.put(elevatorTolerance,testTab.add(elevatorTolerance, false).getEntry());
        testSweetEntries.put(elevatorDone,testTab.add(elevatorDone, false).getEntry());
        
        //shoulder test elements on TestSweet tab in Shuffleboard
        testSweetEntries.put(shoulderTarget,testTab.add(shoulderTarget, 0).getEntry());
        testSweetEntries.put(shoulderHeight, testTab.add(shoulderHeight, 0).getEntry());
        
        elevatorTestInfo1 = testTab.add("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE);
        elevatorTestInfo2 = testTab.add("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        elevatorTestInfo3 = testTab.add("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE);
       
    }
    
    public String getSelectedTest() {

       return testSelector.getSelected();

    }

    public String getRobotPosition()
    {

        return robotPosition;

    }
    
    
    public SendableChooser<String> getAutonomusModeSelector()
    {

        return aModeSelector;
    }
    
    public SendableChooser<String> getTestSelector()
    {
      
        return testSelector;
    }

    public void setTestDrivetrainEncodersIndicators(double leftDrivetrainEncoderValue,
            double rightDrivetrainEncoderValue, boolean leftDrivetrainToleranceValue, 
    boolean rightDrivetrainToleranceValue, boolean encoderDifferenceToleranceValue, double elapsedTimeValue) {
        
        testSweetEntries.get(leftDriveTrainEncoder).setNumber(leftDrivetrainEncoderValue);
        testSweetEntries.get(rightDriveTrainEncoder).setNumber(rightDrivetrainEncoderValue);
        testSweetEntries.get(leftDriveTrainTolerance).setBoolean(leftDrivetrainToleranceValue);
        testSweetEntries.get(rightDriveTrainTolerance).setBoolean(rightDrivetrainToleranceValue);
        testSweetEntries.get(encoderDiffTolerance).setBoolean(encoderDifferenceToleranceValue);
        testSweetEntries.get(elapsedTime).setNumber(elapsedTimeValue);
        testSweetEntries.get(driveTrainDone).setBoolean(true);
    }
    
    public void setTestElevatorIndicators(boolean value) {
        SmartDashboard.putBoolean("EL.Enc", value);
    }

    public DashboardValues getTestElevatorHeights() {

        
        double elevatorSetpointOne = testTab.add("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE).getEntry().getDouble(CrusaderCommon.ELEVATOR_SETPOINT_ONE);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointOne = " + elevatorSetpointOne);
        
        double elevatorSetpointTwo = testTab.add("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO).getEntry().getDouble(CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointTwo = " + elevatorSetpointTwo);
       
        double elevatorSetpointThree = testTab.add("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE).getEntry().getDouble(CrusaderCommon.ELEVATOR_SETPOINT_THREE);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointThree = " + elevatorSetpointThree);
       
        DashboardValues testElevatorHeights = new DashboardValues(elevatorSetpointOne, elevatorSetpointTwo,
                elevatorSetpointThree);
        
                return testElevatorHeights;
    }

    public void putTestElevatorTestOne(double elevatorSetpointOneTest) {

        SmartDashboard.putNumber("ELEV_TEST_ONE", elevatorSetpointOneTest);

    }

    public void putElevatorData(double elevatorHeight, boolean elevatorHeightTolerance) {
        
     
        testSweetEntries.get(elevatorTolerance).setBoolean(elevatorHeightTolerance);
        testSweetEntries.get(elevatorDone).setBoolean(true);
     
    }
    
    public double getShoulderData() {

        return  testSweetEntries.get(shoulderTarget).getDouble(0);
    }
    
    public void putShoulderData(double shoulderAngle) {

        testSweetEntries.get(shoulderTarget).setDouble(shoulderAngle);
        
        
        //testSweetEntries.get(shoulderHeight).setNumber();
        
    }
    
 
}
