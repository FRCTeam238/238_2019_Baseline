package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class TestCoreObject
{
    DoubleSolenoid theShiftSolenoid;

    boolean channelTwoPower;
    
    
    public void initTestCoreObject()
    {
        
        theShiftSolenoid = new DoubleSolenoid(2,3);
        
        
        
    }
    
    public boolean complete()
    {
        // TODO Auto-generated method stub
        return false;
    }
    
    public void turnOnChannelTwo()
    {
     
        channelTwoPower = true;
        
        theShiftSolenoid.set(DoubleSolenoid.Value.kReverse);
        
       
    }
    
    public void turnOffChannelTwo()
    {
        
        channelTwoPower = false;
        
        theShiftSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    
    
    
}