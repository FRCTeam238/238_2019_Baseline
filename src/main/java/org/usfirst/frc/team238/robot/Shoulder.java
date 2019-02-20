package org.usfirst.frc.team238.robot;

import org.usfirst.frc.team238.core.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoulder
{

    
    private static final double MAX_OUT = 0.6;
    private static final double MIN_OUT = -0.6;
    
    private static final double AUTO_MAX_OUT = 0.3;
    private static final double AUTO_MIN_OUT = -0.3;
    
    private static final double MIN_ANGLE = -120;
    private static final double MAX_ANGLE = -3;
    
    
    private double setpoint = 0;
    private double currentError =0;
    
    private boolean PIDEnabled = true;
    private boolean inAutonomous = false;
    
    TalonSRX shoulderTalon;
    TalonSRX intakeMaster;
    VictorSPX intakeSlave;
    
    public Shoulder()
    {
        
    }
   
    
    public void init()
    {
        
        shoulderTalon = new TalonSRX(CrusaderCommon.INTAKE_SHOULDER);
        intakeMaster = new TalonSRX(CrusaderCommon.INTAKE_MASTER_SRX);
        intakeSlave = new VictorSPX(CrusaderCommon.INTAKE_SLAVE);
        
        intakeSlave.follow(intakeMaster);
        
        shoulderTalon.setNeutralMode(NeutralMode.Brake);
        intakeMaster.setNeutralMode(NeutralMode.Brake);
        intakeSlave.setNeutralMode(NeutralMode.Brake);
        
        shoulderTalon.set(ControlMode.PercentOutput, 0);
        intakeMaster.set(ControlMode.PercentOutput, 0);
        intakeSlave.setInverted(true);
        
        shoulderTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        shoulderTalon.setSensorPhase(true);
        shoulderTalon.config_kP(0, 0.005, 0);
        resetEncoders();
        
        PIDEnabled = true;
        SmartDashboard.putBoolean("PidEnabled", PIDEnabled);
        
        Runnable loop = ()->{
            while(true) {
                mainLoop();
                try
                {
                    Thread.sleep(30);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 
            }
           };
        new Thread(loop).start();
    }
    
    /**
     * Extends the shoulder out to manipulate cubes
     */
    
    // angle is 0 at top (starting configuraition) and then positive as i goes down.
    public void setshoulder(double angle) {
        
        setpoint = Math.min(Math.max(MIN_ANGLE, angle), MAX_ANGLE);
        inAutonomous = false;
    }
    
    public void setshoulder(double angle, boolean auto) {
        setpoint = Math.min(Math.max(MIN_ANGLE, angle), MAX_ANGLE);
        inAutonomous = auto;
    }
    
    public boolean usingshoulder = false;
    public void extendshoulder()
    {
        
        shoulderTalon.set(ControlMode.PercentOutput, CrusaderCommon.INTAKE_SHOULDER_SPEED);
        
    }
    
    /**
     * Retracts the shoulder out to hide our intake
     */
    public void retractshoulder()
    {
        
        shoulderTalon.set(ControlMode.PercentOutput, -CrusaderCommon.INTAKE_SHOULDER_SPEED);
        
    }
    
    /**
     * Suck a cube in
     */
    public void extendShoulderPID() {
        if(PIDEnabled) {
            tilt(1.0);
            Logger.Log("extendPidenabled"); 
        }else {
            double shoulderValue = ControlBoard.getOperatorRightJs().getRawAxis(5);
            double staticshoulderValue = 0.0;
            
            if (shoulderValue > 0.65)
            {
                staticshoulderValue = 0.1;
            }
//            else
//            {
//                staticshoulderValue = 0.0;
//            }

            shoulderTalon.set(ControlMode.PercentOutput, shoulderValue); 
           
            SmartDashboard.putNumber("Extending", shoulderValue);
            SmartDashboard.putNumber("ExtendingStatic", staticshoulderValue);
           
            //Logger.Log("extendPidNOTenabled"); 
            
        }
        
    }
    
    public void manualOverride(boolean val) {
        PIDEnabled = val;
        SmartDashboard.putBoolean("PidEnabled", PIDEnabled);
       
        Logger.Log("PidEnabled = ", String.valueOf(PIDEnabled));
        Logger.Log("manualOverride"); 
    }
    
    public void retractShoulderPID() {
        
        if(PIDEnabled) {
            tilt(-1.0);
            Logger.Log("RetractPidenabled"); 
        }else {
           double shoulderValue = ControlBoard.getOperatorRightJs().getRawAxis(5);
           double staticshoulderValue = 0.0;
           
            if (shoulderValue < -0.65)
            {
                staticshoulderValue = -0.1;
            }
//            else
//            {
//                staticshoulderValue = 0.0;
//            }
            shoulderTalon.set(ControlMode.PercentOutput, shoulderValue); 
            SmartDashboard.putNumber("Retracting", shoulderValue);
            SmartDashboard.putNumber("RetractingStatic", staticshoulderValue);
            
            //Logger.Log("RetractPidNOTenabled"); 
        }
    }
    
    public void tilt(double degrees) {
        setpoint+=degrees;
    }
    
    public void enablePID() {
        PIDEnabled=true;
    }
    
    public void disablePID() {
        PIDEnabled=false;
    }
    
    public void intakeIn()
    {
     
        intakeMaster.set(ControlMode.PercentOutput, -CrusaderCommon.INTAKE_SPEED);
    }
    
    /**
     * Spit a cube out
     */
    public void intakeOut()
    {
     
        intakeMaster.set(ControlMode.PercentOutput, CrusaderCommon.INTAKE_SPEED);
        
    }   
    
    public void intakeOutFast()
    {
     
        intakeMaster.set(ControlMode.PercentOutput, CrusaderCommon.INTAKE_SPEED_FAST);
        
    }
    public void intakeOutSlow()
    {
     
        intakeMaster.set(ControlMode.PercentOutput, CrusaderCommon.INTAKE_SPEED_SLOW);
        
    }  
    
    public void stop()
    {
        
        intakeMaster.set(ControlMode.PercentOutput, 0.0);    
        
        if(!PIDEnabled) {
            shoulderTalon.set(ControlMode.PercentOutput, 0.0);
            SmartDashboard.putNumber("Retracting", 0.0);
            SmartDashboard.putNumber("RetractingStatic", 0.0);
            SmartDashboard.putNumber("Extending", 0.0);
            SmartDashboard.putNumber("ExtendingStatic", 0.0);
        }
        
    }
    
    public void mainLoop() {
        if(PIDEnabled) {
            
            currentError = setpoint - getAngle();
            double outputWanted = currentError * CrusaderCommon.INTAKE_KP;
           
            outputWanted = Math.min(Math.max(MIN_OUT, outputWanted+0.085), MAX_OUT) ;
           
            shoulderTalon.set(ControlMode.PercentOutput, outputWanted);
            
        }  
        
        
    }
    
    public double getAngle() {
       
        double angle = -shoulderTalon.getSelectedSensorPosition(0)/ CrusaderCommon.INTAKE_TICK_TO_DEGREE;
        
        //Logger.Log("Intakeshoulder.getAngle: INTAKEANGLE = " + angle + "\n  SETPOINT = " + setpoint + "\n Error = " + currentError);
        
        return angle;
    }
    
    public void resetEncoders(){
        
        shoulderTalon.setSelectedSensorPosition(0,0,0);
      
      }
    
    
    
}
