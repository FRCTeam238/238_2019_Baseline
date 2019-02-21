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

    private static final double MIN_HEIGHT = -80.0;
    private static final double MAX_HEIGHT = 83; // 86;

    private double zeroHeight = 0;
    private double setpoint = 0;
    private double currentError = 0;

    // Really only a P loop
    public boolean PIDEnabled = true;

    TalonSRX elevatorMasterTalon;
    // TalonSRX elevatorSlaveTalon;
    // VictorSPX elevatorSlaveVictor;

    DoubleSolenoid solenoid;

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
        // elevatorMasterTalon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
        // LimitSwitchNormal.NormallyOpen, 0);

        elevatorMasterTalon.setSensorPhase(true);
        elevatorMasterTalon.configOpenloopRamp(0.2, 0);
        elevatorMasterTalon.config_kP(0, 0.004, 0);
        elevatorMasterTalon.setInverted(false);

        solenoid = new DoubleSolenoid(6, 7);

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

        liftEncoder = elevatorMasterTalon.getSelectedSensorPosition(0);

    }

    /**
     * Sends the elevator up at the speed used for cubes
     */

    public void elevatorUp() {

        // get encoder ticks
        PIDEnabled = false;
        int whereAmI = getEncoderTicks();
        elevatorMasterTalon.set(ControlMode.PercentOutput, CrusaderCommon.ELEVATOR_CUBE_SPEED);
        Logger.Log("Elevator.elevatorUp()");
    }

    /**
     * Sends the elevator down at the speed used for cubes
     */
    public void elevatorDown() {
        {
            PIDEnabled = false;
            // get encoder ticks
            int whereAmI = getEncoderTicks();
            elevatorMasterTalon.set(ControlMode.PercentOutput, -CrusaderCommon.ELEVATOR_CUBE_SPEED);
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

    /**
     * Sends the elevator up at the speed used for climbing
     */
    public void elevatorClimbUp() {

        elevatorMasterTalon.set(ControlMode.PercentOutput, CrusaderCommon.ELEVATOR_CLIMB_SPEED);

    }

    /**
     * ,. Sends the elevator down at the speed used for climbing
     */

    public void setElevatorHeight(double height) {
        PIDEnabled = true;
        elevatorMasterTalon.set(ControlMode.Position, height * ((double) CrusaderCommon.ELEVATOR_TICK_TO_IN));
        // Logger.log("ELEVATOR ERROR:" + elevatorMasterTalon.getClosedLoopError(0));
    }

    public void elevatorClimbDown() {

        elevatorMasterTalon.set(ControlMode.PercentOutput, -CrusaderCommon.ELEVATOR_CLIMB_SPEED);

    }

    public void stop() {
        DashBoard238.getInstance().addOrUpdateElement("Elevator", "elevatorStop", "Stop called at " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        elevatorMasterTalon.set(ControlMode.PercentOutput, 0);
    }

    /**
     * Shift into cube manipulation speed
     */
    public void elevatorShiftCube() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        climbMode = false;
    }

    /**
     * Shift into climb speed
     */
    public void elevatorShiftClimb() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        climbMode = true;
    }

    public void enablePID() {
        PIDEnabled = true;
    }

    public void disablePID() {
        PIDEnabled = false;
    }

    // set height of robot
    public void setSetpoint(double height) {
        this.setpoint = Math.min(Math.max(MIN_HEIGHT, height), MAX_HEIGHT);
        DashBoard238.getInstance().addOrUpdateElement("Elevator", "currentSetPoint", this.setpoint);
    }

    public void tilt(double heightTilt) {
        DashBoard238.getInstance().addOrUpdateElement("Elevator", "tilt", heightTilt);
        setpoint += heightTilt;
        this.setpoint = Math.min(Math.max(MIN_HEIGHT, setpoint), MAX_HEIGHT);
        //Logger.Log("Elevator.tilt() : setpoint = " + setpoint);
    }

    private double prevError;

    public void mainLoop() {
        // nominal voltage <-1,1> outpu for elevator based in P gain
        if (PIDEnabled) {

            double height = getHeight();
            // setting the value for the lowest allowable elevator position
            if (elevatorMasterTalon.getSensorCollection().isRevLimitSwitchClosed() && setpoint < 3) {
                zeroHeight += height;
            }

            currentError = setpoint - height;
            double dVal = (currentError - prevError) * CrusaderCommon.ELEVATOR_KD;

            double outputWanted = currentError * CrusaderCommon.ELEVATOR_KP + dVal
                    + CrusaderCommon.ELEVATOR_FEED_FORWARD;
            Logger.Log("Elevator.mainloop() outputWanted = " + outputWanted);
            outputWanted = Math.min(Math.max(MIN_OUT, outputWanted), MAX_OUT);

            if (height < 15 && setpoint < 4) {
                elevatorMasterTalon.set(ControlMode.PercentOutput, outputWanted * 0.35);
            } else {
                elevatorMasterTalon.set(ControlMode.PercentOutput, outputWanted);
            }

            if( prevError != currentError){
                Logger.Log("Elevator.mainloop() setPoint = " + setpoint + "  Height = " + height + "  currentError = " + currentError + "  Kd = " + dVal + "output wanted = " + outputWanted);
            }

            DashBoard238 dashboard = DashBoard238.getInstance();
            dashboard.addOrUpdateElement("Elevator", "currentHeight", height);
            dashboard.addOrUpdateElement("Elevator", "currentError", currentError);
            dashboard.addOrUpdateElement("Elevator", "outputWanted", outputWanted);
            dashboard.addOrUpdateElement("Elevator", "motorOutputPercent", elevatorMasterTalon.getMotorOutputPercent());
            dashboard.addOrUpdateElement("Elevator", "encoderTicks", elevatorMasterTalon.getSelectedSensorPosition(0));
            dashboard.update();
            prevError = currentError;
        }
    }

    // getHeight() gets the sensor position, converts it from ticks to inches, then
    // subtracts the zeroHeight
    private double prevElevatorSensorPosition;

    public double getHeight() {

        double elevatorSensorPosition = elevatorMasterTalon.getSelectedSensorPosition(0);
        double elevatorSensorInches = elevatorSensorPosition / CrusaderCommon.ELEVATOR_TICK_TO_IN;
        
        if( prevElevatorSensorPosition != elevatorSensorPosition)
        {
            Logger.Log(
                "Elevator.getHeight() sensorPos = " + elevatorSensorPosition + " sensorInches = " + elevatorSensorInches
                        + "  zeroHeight = " + zeroHeight + "  SETPOINT = " + setpoint + "    ERROR:" + currentError);
        }
        
        

        elevatorSensorInches = elevatorSensorInches - zeroHeight;

        prevElevatorSensorPosition = elevatorSensorPosition;
        return elevatorSensorInches;
        // -elevatorMasterTalon.getSelectedSensorPosition(0) /
        // CrusaderCommon.ELEVATOR_TICK_TO_IN - zeroHeight;

    }
}
