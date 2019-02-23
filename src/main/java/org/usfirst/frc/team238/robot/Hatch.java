package org.usfirst.frc.team238.robot;

import org.usfirst.frc.team238.core.Logger;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Hatch
{

    DoubleSolenoid solenoid;
    
   
    public Hatch()
    {
         solenoid = new DoubleSolenoid(2, 3);

    }
   
    
    public void init()
    {
        
        
    }

    public void setHatch(boolean position) {
        if (position) {

            extendHatch();
        } 
        else {
            retractHatch();
        }
        

    }
   
    public void extendHatch() {
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

  
    public void retractHatch() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    
    public void stop()
    {
        
    }
    
}
