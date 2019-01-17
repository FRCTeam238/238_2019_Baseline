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
        
        SmartDashboard.putBoolean(CrusaderCommon.AUTO_PLAY_BOOK, true);
        SmartDashboard.putString(CrusaderCommon.AUTO_ROBOT_POSITION,  "C");
        SmartDashboard.putString("P or S", "nothing");
        
        aModeSelector = new SendableChooser<String>();
  
        //Send able Chooser for the state update function
        autonomousStateParamsUpdate = new SendableChooser<String>();
        autonomousStateParamsUpdate.addDefault("As Received", "0");
        autonomousStateParamsUpdate.addObject("UPDATE", "1");
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
 
}
