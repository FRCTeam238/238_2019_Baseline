package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Ramp
{

	DoubleSolenoid solenoid;

    public Ramp()
    {
        
    }
    
    public void init()
    {
    	solenoid = new DoubleSolenoid(CrusaderCommon.RAMP_SOL_1, CrusaderCommon.RAMP_SOL_2);
    	solenoid.set(DoubleSolenoid.Value.kReverse);
    	
    }
    
    //release the hounds
    public void deployRamp()
    {
    	solenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    
    //if we need this we are well and truly screwed 
    public void retractRamp()
    {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
}
