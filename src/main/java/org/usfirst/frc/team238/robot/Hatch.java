package org.usfirst.frc.team238.robot;

import org.usfirst.frc.team238.core.DashBoard238;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Hatch
{

    DashBoard238 dashboard = DashBoard238.getInstance();

    DoubleSolenoid solenoid;
    
   
    public Hatch()
    {
         solenoid = new DoubleSolenoid(2, 7);
        setHatch(false);
    }
   
    
    public void init()
    {
        
        
    }

    public void setHatch(boolean position) {
        if (!position) {
            dashboard.setHatch(false);
            extendHatch();
            
        } 
        else {
            dashboard.setHatch(true);
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
