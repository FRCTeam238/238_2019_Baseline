package org.usfirst.frc.team238.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Wrist
{

    DoubleSolenoid solenoid;
    
   
    public Wrist()
    {
         solenoid = new DoubleSolenoid(1, 6);

    }
   
    
    public void init()
    {
        
        
    }

    public void setWrist(boolean position) {
        if (position) {

            extendWrist();
        } 
        else {
            retractWrist();
        }
        

    }
   
    public void extendWrist() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

  
    public void retractWrist() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    
    public void stop()
    {
        
    }
    
}
