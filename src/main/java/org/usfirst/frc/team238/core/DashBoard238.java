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
     
    }
    
    public String getRobotPosition()
    {
        
        return robotPosition;
        
    }
    
    public SendableChooser<String> getAutonomusModeSelector()
    {
        return aModeSelector;
    }

    public void setTestDrivetrainEncodersIndicators(double leftDrivetrainEncoderValue, double rightDrivetrainEncoderValue, boolean leftDrivetrainTolerance, boolean rightDrivetrainTolerance, boolean encoderDifferenceTolerance) {
        
        SmartDashboard.putNumber("L_DT_ENC", leftDrivetrainEncoderValue);
        SmartDashboard.putNumber("R_DT_ENC", rightDrivetrainEncoderValue);
        SmartDashboard.putBoolean("L_DT_TOL", leftDrivetrainTolerance);
        SmartDashboard.putBoolean("R_DT_TOL", rightDrivetrainTolerance);
        SmartDashboard.putBoolean("ENC_DIFF_TOL", encoderDifferenceTolerance);
    }
    
    public void setTestElevatorIndicators(boolean value){
        SmartDashboard.putBoolean("EL.Enc", value);
    }
 
}