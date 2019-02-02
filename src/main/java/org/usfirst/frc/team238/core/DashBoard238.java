package org.usfirst.frc.team238.core;

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
    private SendableChooser<String> autonomousStateParamsUpdate;
    private SendableChooser<String> testSelector;
    private String robotPosition;
    ShuffleboardTab testTab;
    public SimpleWidget elevatorTestInfo1;
    public SimpleWidget elevatorTestInfo2;
    public SimpleWidget elevatorTestInfo3;
    public boolean drivetrainDone;
    NetworkTableEntry testEntry0;
    NetworkTableEntry testEntry1;
    NetworkTableEntry testEntry2;
    NetworkTableEntry testEntry3;
    NetworkTableEntry testEntry4;
    NetworkTableEntry testEntry5;
    NetworkTableEntry testEntry6;
    NetworkTableEntry testEntry7;
    NetworkTableEntry testEntry8;
    NetworkTableEntry testEntry9;
    NetworkTableEntry testEntry10;
    NetworkTableEntry testEntry11;

    public DashBoard238(Robot myRobot)
    {
        this.myRobot = myRobot;
        
    }
    
    public void init()
    {

        // SmartDashboard.putBoolean(CrusaderCommon.AUTO_PLAY_BOOK, true);
        SmartDashboard.putString(CrusaderCommon.AUTO_ROBOT_POSITION, "C");
        // SmartDashboard.putString("P or S", "nothing");

        aModeSelector = new SendableChooser<String>();

        //Send able Chooser for the state update function
        autonomousStateParamsUpdate = new SendableChooser<String>();
        aModeSelector.addOption("CargoShip 1", "CargoShip 1");
        aModeSelector.addOption("CargoShip 2", "1");
        aModeSelector.addOption("CargoShip 3", "2");
        aModeSelector.addOption("CargoShip 4", "3");
        aModeSelector.addOption("CargoShip 5", "4");
        aModeSelector.addOption("CargoShip 6", "5");
        SmartDashboard.putData("AuTo", aModeSelector);
        robotPosition = SmartDashboard.getString(CrusaderCommon.AUTO_ROBOT_POSITION, "C");
        //SmartDashboard.putNumber("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE);
        //SmartDashboard.putNumber("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        //SmartDashboard.putNumber("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE);

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


        testEntry0 = testTab.add("L_DT_ENC", 0).getEntry();
        testEntry1 = testTab.add("R_DT_ENC", 0).getEntry();;
        testEntry2 = testTab.add("L_DT_TOL", false).getEntry();;
        testEntry3 = testTab.add("R_DT_TOL", false).getEntry();;
        testEntry4 = testTab.add("ENC_DIFF_TOL", false).getEntry();;
        testEntry5 = testTab.add("ELAPS_TIME", 0).getEntry();;
        testEntry6 = testTab.add("Drivetrain Done", false).getEntry();
        testEntry7 = testTab.add("Elevator Height", 0).getEntry();
        testEntry8 = testTab.add("Elevator Tolerance", false).getEntry();
        testEntry9 = testTab.add("Elevator Done", false).getEntry();
        testEntry10 = testTab.add("Shoulder Target", 0).getEntry();
        testEntry11 = testTab.add("Shoulder Height", 0).getEntry();
        
        elevatorTestInfo1 = testTab.add("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE);
        elevatorTestInfo2 = testTab.add("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        elevatorTestInfo3 = testTab.add("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE);


       // SmartDashboard.putData("TestSweet/Test Selection", testSelector);
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
            double rightDrivetrainEncoderValue, boolean leftDrivetrainTolerance, 
    boolean rightDrivetrainTolerance, boolean encoderDifferenceTolerance, double elapsedTime) {
        
        testEntry0.setNumber(leftDrivetrainEncoderValue);
        testEntry1.setNumber(rightDrivetrainEncoderValue);
        testEntry2.setBoolean(leftDrivetrainTolerance);
        testEntry3.setBoolean(rightDrivetrainTolerance);
        testEntry4.setBoolean(encoderDifferenceTolerance);
        testEntry5.setNumber(elapsedTime);
        testEntry6.setBoolean(true);


        // testTab.putNumber("L_DT_ENC", leftDrivetrainEncoderValue);
        // testTab.putNumber("R_DT_ENC", rightDrivetrainEncoderValue);
        // testTab.putBoolean("L_DT_TOL", leftDrivetrainTolerance);
        // testTab.putBoolean("R_DT_TOL", rightDrivetrainTolerance);
        // testTab.putBoolean("ENC_DIFF_TOL", encoderDifferenceTolerance);
        // testTab.putNumber("ELAPS_TIME", elapsedTime);
        
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

        testEntry7.setNumber(elevatorHeight);
        testEntry8.setBoolean(elevatorHeightTolerance);
        testEntry9.setBoolean(true);

    }
    
    public double getShoulderData() {

        return testEntry10.getDouble(0);
    }
    
    public void putShoulderData(double shoulderAngle) {

        testEntry11.setDouble(shoulderAngle);

    }
    
 
}
