package org.usfirst.frc.team238.robot;

import org.usfirst.frc.team238.core.DashBoard238;
import org.usfirst.frc.team238.core.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

//import sun.rmi.runtime.Log;

public class Elevator {

    private static final double MAX_OUT = 0.95;
    private static final double MIN_OUT = -0.95;

    private static final double MIN_HEIGHT = -31.0;
    private static final double MAX_HEIGHT = 31; // 86;

    private double zeroHeight = 0;
    private double setpoint = 0;
    private double currentError = 0;

    // Really only a P loop
    private boolean PIDEnabled = true;

    TalonSRX elevatorMasterTalon;
    // TalonSRX elevatorSlaveTalon;
    // VictorSPX elevatorSlaveVictor;

    //DoubleSolenoid solenoid;

    int liftEncoder;

    public boolean climbMode;

    /**
     * Constructor
     */
    public Elevator() {

    }

    /**
     * Initializes everything!
     */
    public void init() {

        elevatorMasterTalon = new TalonSRX(CrusaderCommon.ELEVATOR_MASTER);
        // elevatorSlaveTalon = new TalonSRX(CrusaderCommon.ELEVAOR_SLAVE_SRX);
        // elevatorSlaveVictor = new VictorSPX(CrusaderCommon.ELEVATOR_SLAVE_SPX);

        // elevatorSlaveTalon.set(ControlMode.Follower,
        // CrusaderCommon.ELEVATOR_MASTER);//follow(elevatorMasterTalon);
        // elevatorSlaveVictor.follow(elevatorMasterTalon);

        elevatorMasterTalon.setNeutralMode(NeutralMode.Brake);
        // elevatorSlaveTalon.setNeutralMode(NeutralMode.Brake);
        // elevatorSlaveVictor.setNeutralMode(NeutralMode.Brake);

        elevatorMasterTalon.set(ControlMode.PercentOutput, 0);
        elevatorMasterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        elevatorMasterTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, 0);

        elevatorMasterTalon.setSensorPhase(true);
        elevatorMasterTalon.configOpenloopRamp(0.2, 0);
        elevatorMasterTalon.config_kP(0, 0.004, 0);
        elevatorMasterTalon.setInverted(false);

        //solenoid = new DoubleSolenoid(6, 7);

        climbMode = false;

        resetEncoders();

        PIDEnabled = true;
        Runnable loop = () -> {
            while (true) {
                mainLoop();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        };
        new Thread(loop).start();
    }

    public int getEncoderTicks() {
        liftEncoder = elevatorMasterTalon.getSelectedSensorPosition(0);

        liftEncoder = Math.abs(liftEncoder);

        //SmartDashboard.putNumber("Lift Encoder", liftEncoder);
        return liftEncoder;
    }

    public void resetEncoders() {

        elevatorMasterTalon.setSelectedSensorPosition(0, 0, 0);
        setSetpoint(0);
    }
    /**
     * Sends the elevator up at the speed used for cubes
     */

    public void elevatorUp() {

       
        //setpoint = getHeight();
        //elevatorUpPID();
        //Without this line, setpoint goes far out of bounds, messing up the teleop manual commands
        //elevatorDownPID();

        double newSetPoint =  getHeight() + 3;
        

        setSetpoint(newSetPoint);
       // elevatorMasterTalon.set(ControlMode.PercentOutput, CrusaderCommon.ELEVATOR_CUBE_SPEED);
        Logger.Log("Elevator.elevatorUp()");
    }

    /**
     * Sends the elevator down at the speed used for cubes
     */
    public void elevatorDown() {
        {
            //PIDEnabled = false;
            // get encoder ticks
            //int whereAmI = getEncoderTicks();
            //elevatorMasterTalon.set(ControlMode.PercentOutput, -CrusaderCommon.ELEVATOR_CUBE_SPEED);
            double newSetPoint =  getHeight() - 3;
            //Without this line, setpoint goes far out of bounds, messing up the teleop manual commands
            //elevatorDownPID();

            setSetpoint(newSetPoint);
            Logger.Log("Elevator.elevatorDown()");
        }

    }

    public void elevatorUpPID() {
        PIDEnabled = true;
        tilt(1);
    }

    public void elevatorDownPID() {
        PIDEnabled = true;
        tilt(-1);
    }

    public void stop() {
        if (!PIDEnabled) {
            elevatorMasterTalon.set(ControlMode.PercentOutput, 0);
        }
    }

    public void enablePID() {
        PIDEnabled = true;
    }

    public void disablePID() {
        PIDEnabled = false;
    }

    // set height of robot
    public void setSetpoint(double height) {
        PIDEnabled = true;
        
        // this.setpoint = Math.min(Math.max(MIN_HEIGHT, height), MAX_HEIGHT);
        double newSetpoint = Math.max(MIN_HEIGHT, height);
        
        //makes sure setpoint is above min height, if not, sets to min height
        this.setpoint = Math.min(MAX_HEIGHT, newSetpoint);
        
        //makes sure setpoint is below max height, if not, sets to max height
        DashBoard238.getInstance().addOrUpdateElement("Elevator", "currentSetPoint", this.setpoint);
    }

    private void tilt(double heightTilt) {
        DashBoard238.getInstance().addOrUpdateElement("Elevator", "tilt", heightTilt);
        double newSetpoint = setpoint + heightTilt;
        setSetpoint(newSetpoint);
        //Logger.Log("Elevator.tilt() : setpoint = " + setpoint);
    }

    private double prevError;
    int count = 0;
    private double prevHeight;

    private void mainLoop() {
        // nominal voltage <-1,1> outpu for elevator based in P gain
        if (PIDEnabled) {

            double height = getHeight();
            // setting the value for the lowest allowable elevator position
            if (elevatorMasterTalon.getSensorCollection().isRevLimitSwitchClosed() && setpoint < 3) {
                zeroHeight += height;
            }
            
            double KP_VALUE;
            if( prevHeight < height){
                //going down
                KP_VALUE = CrusaderCommon.ELEVATOR_KP_DOWN;
            }
            else{
                //going up
                KP_VALUE =  CrusaderCommon.ELEVATOR_KP_UP;
            }

            currentError = setpoint - height;
            double dVal = (currentError - prevError) * CrusaderCommon.ELEVATOR_KD; //used to slow down the motor as it reaches

            double outputWanted = currentError * KP_VALUE + dVal
                    + CrusaderCommon.ELEVATOR_FEED_FORWARD;
            
                    //Logger.Log("Elevator.mainloop() outputWanted = " + outputWanted);
            double origOutputWaned = outputWanted;
            outputWanted = Math.max(MIN_OUT, outputWanted);
            String log1 = "Elevator.mainLoop() Math.max(" + MIN_OUT + ", " + origOutputWaned + ") = " + outputWanted;
            origOutputWaned = outputWanted;
            outputWanted = Math.min(outputWanted, MAX_OUT);
            String log2 = "Elevator.mainLoop() Math.min(" + origOutputWaned + ", " + MAX_OUT + ") = " + outputWanted;

            if( count > 100){
               // Logger.Log(log1);
                //Logger.Log(log2);
                count = 0;
            }
            count++;
            
            //height:15 setpoint:4
            //starting point in auto mode is 15 inches high on elevator
            // setpoint and height are negative only at beginning of game, need to use math.abs to get abs value
            //Math.abs is when we go negative at the start point
            if (Math.abs(height) < 5 && Math.abs(setpoint) < 2) {
                elevatorMasterTalon.set(ControlMode.PercentOutput, outputWanted * 0.35);
            } else {
                elevatorMasterTalon.set(ControlMode.PercentOutput, outputWanted);
            }

            // if( prevError != currentError){
            //     Logger.Log("Elevator.mainloop() setPoint = " + setpoint + "  Height = " + height + "  currentError = " + currentError + "  Kd = " + dVal + "output wanted = " + outputWanted);
            // }
            
            prevHeight = height;
            prevError = currentError;
        }
    }

    // getHeight() gets the sensor position, converts it from ticks to inches, then
    // subtracts the zeroHeight
    
    public double getHeight() {

        double elevatorSensorPosition = elevatorMasterTalon.getSelectedSensorPosition(0);
        double elevatorSensorInches = elevatorSensorPosition / CrusaderCommon.ELEVATOR_TICK_TO_IN;
        
        // if( prevElevatorSensorPosition != elevatorSensorPosition)
        // {
        //     Logger.Log(
        //         "Elevator.getHeight() sensorPos = " + elevatorSensorPosition + " sensorInches = " + elevatorSensorInches
        //                 + "  zeroHeight = " + zeroHeight + "  SETPOINT = " + setpoint + "    error:" + currentError);
        // }
        
        

        elevatorSensorInches = elevatorSensorInches - zeroHeight;

       
        return elevatorSensorInches;
        // -elevatorMasterTalon.getSelectedSensorPosition(0) /
        // CrusaderCommon.ELEVATOR_TICK_TO_IN - zeroHeight;

    }
}
