package org.usfirst.frc.team238.core;

import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashBoard238
{
    Robot myRobot;
    private SendableChooser<String> aModeSelector;
    private SendableChooser<String> autonomousStateParamsUpdate;
    private String robotPosition;
    
    public DashBoard238(Robot myRobot)
    {
        this.myRobot = myRobot;
        
    }
    
    public void init()
    {
        
       // SmartDashboard.putBoolean(CrusaderCommon.AUTO_PLAY_BOOK, true);
        SmartDashboard.putString(CrusaderCommon.AUTO_ROBOT_POSITION,  "C");
       // SmartDashboard.putString("P or S", "nothing");
        
        aModeSelector = new SendableChooser<String>();
        
        //Send able Chooser for the state update function
        autonomousStateParamsUpdate = new SendableChooser<String>();
        autonomousStateParamsUpdate.addOption("CargoShip 1", "0");
        autonomousStateParamsUpdate.addOption("CargoShip 2", "1");
        autonomousStateParamsUpdate.addOption("CargoShip 3", "2");
        autonomousStateParamsUpdate.addOption("CargoShip 4", "3");
        autonomousStateParamsUpdate.addOption("CargoShip 5", "4");
        autonomousStateParamsUpdate.addOption("CargoShip 6", "5");
        SmartDashboard.putData("AuTo", autonomousStateParamsUpdate);
        robotPosition = SmartDashboard.getString(CrusaderCommon.AUTO_ROBOT_POSITION,  "C");
        //SmartDashboard.putNumber("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE);
        //SmartDashboard.putNumber("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        //SmartDashboard.putNumber("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE);
    }
    
    public String getRobotPosition()
    {
        
        return robotPosition;
        
    }
    
    public SendableChooser<String> getAutonomusModeSelector()
    {
        return aModeSelector;
    }

    public void setTestDrivetrainEncodersIndicators(double leftDrivetrainEncoderValue,
            double rightDrivetrainEncoderValue, boolean leftDrivetrainTolerance, 
    boolean rightDrivetrainTolerance, boolean encoderDifferenceTolerance, double elapsedTime) {
        
        SmartDashboard.putNumber("L_DT_ENC", leftDrivetrainEncoderValue);
        SmartDashboard.putNumber("R_DT_ENC", rightDrivetrainEncoderValue);
        SmartDashboard.putBoolean("L_DT_TOL", leftDrivetrainTolerance);
        SmartDashboard.putBoolean("R_DT_TOL", rightDrivetrainTolerance);
        SmartDashboard.putBoolean("ENC_DIFF_TOL", encoderDifferenceTolerance);
        SmartDashboard.putNumber("ELAPS_TIME", elapsedTime);

    }
    
    public void setTestElevatorIndicators(boolean value) {
        SmartDashboard.putBoolean("EL.Enc", value);
    }

    public DashboardValues getTestElevatorHeights() {

        
        double elevatorSetpointOne = SmartDashboard.getNumber("ELEV_SETPT_1", CrusaderCommon.ELEVATOR_SETPOINT_ONE);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointOne = " + elevatorSetpointOne);
        double elevatorSetpointTwo = SmartDashboard.getNumber("ELEV_SETPT_2", CrusaderCommon.ELEVATOR_SETPOINT_TWO);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointTwo = " + elevatorSetpointTwo);
        double elevatorSetpointThree = SmartDashboard.getNumber("ELEV_SETPT_3", CrusaderCommon.ELEVATOR_SETPOINT_THREE);
        Logger.Log("DashboardValues getTestElevatorHeights: elevatorSetpointThree = " + elevatorSetpointThree);
        DashboardValues testElevatorHeights = new DashboardValues(elevatorSetpointOne, elevatorSetpointTwo,
                elevatorSetpointThree);
        return testElevatorHeights;
    }

    public void putTestElevatorTestOne(double elevatorSetpointOneTest) {

        SmartDashboard.putNumber("ELEV_TEST_ONE", elevatorSetpointOneTest);

    }
    
 
}
