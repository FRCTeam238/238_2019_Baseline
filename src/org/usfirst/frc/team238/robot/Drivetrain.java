package org.usfirst.frc.team238.robot;

import org.usfirst.frc.team238.core.Logger;
import org.usfirst.frc.team238.robot.ControlBoard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

/*import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;*/

public class Drivetrain
{

    Solenoid shifterSolenoid;
    Solenoid lowShiftSolenoid;
    DifferentialDrive robotMotors;

    boolean shiftedhigh = false;

    ControlBoard theControlBoard;

    DoubleSolenoid theShiftSolenoid;

    TalonSRX leftFrontDrive;
    TalonSRX rightFrontDrive;

    // Encoder tick variables
    int encoderLeft;
    int encoderRight;

    // A function that
    int counter;

    // Test function variables
    double protoCounter;
    int delayCounter;
    int lastBtnPressed;
    int scalefactor;
    int scalefactorOnePercent;
    int btncounter;
    int btncounterDec;

    public Drivetrain(ControlBoard theControls)
    {
        this.theControlBoard = theControls;
    }

    public void init(TalonSRX leftFrontDriveTalon, TalonSRX rightFrontDriveTalon)
    {

        leftFrontDrive = leftFrontDriveTalon;
        rightFrontDrive = rightFrontDriveTalon;
        // shifterSolenoid = new Solenoid (0);
        // lowShiftSolenoid = new Solenoid (1);
        theShiftSolenoid = new DoubleSolenoid(0, 1);

        leftFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_LEFT, 0);
        rightFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_RIGHT, 0);
        /* It Appears we cant do this anymore, we get what we get */
        // leftFrontDrive.configEncoderCodesPerRev(256);
        // rightFrontDrive.configEncoderCodesPerRev(256);

        /* IDK What this does but it might be important */
        // leftFrontDrive.setStatusFrameRateMs();
        // rightFrontDrive.setStatusFrameRateMs(StatusFrameRate.QuadEncoder,
        // 10);

        leftFrontDrive.configOpenloopRamp(0.1, 100);
        rightFrontDrive.configOpenloopRamp(0.1, 100);

        leftFrontDrive.configClosedloopRamp(0.1, 100);
        rightFrontDrive.configClosedloopRamp(0.1, 100);

        leftFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        rightFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

        configTalon(leftFrontDrive);
        configTalon(rightFrontDrive);

        btncounter = 0;
        btncounterDec = 0;
        lastBtnPressed = 0;
        protoCounter = 0;
        scalefactor = 5;
        scalefactorOnePercent = 1;
        counter = 0;

        shiftHigh();
    }

    /**
     * Gets the average number of encoder ticks and only returns one encoder if
     * one of them returns 0
     * 
     * @return
     */
    public int getEncoderTicks()
    {

        // What if we're going backwards?
        // What if an encoder is not 0 but hasn't changed?

        int encoderNumber = 0;
        int encoderAverage = 0;

        encoderLeft = leftFrontDrive.getSelectedSensorPosition(0);
        encoderRight = rightFrontDrive.getSelectedSensorPosition(0);

        encoderLeft = Math.abs(encoderLeft);
        encoderRight = Math.abs(encoderRight);

        Logger.Log("DriveTrain(): Left Encoder = " + encoderLeft);
        Logger.Log("DriveTrain(): Right Encoder = " + encoderRight);

        // RM SmartDashboard.putNumber("DriveTrain: EncoderAverage",
        // encoderRight);

        return encoderLeft;
    }
    //

    public void tankDrive(double leftVal, double rightVal)
    {
        // TOP SPEED IN HIGH IS 195 INCHES ON BLOCKS
        leftFrontDrive.set(ControlMode.PercentOutput, leftVal);
        rightFrontDrive.set(ControlMode.PercentOutput, -rightVal); // convert to
                                                                   // inches/second
        // System.out.println("RIGHT SPEED IS =" +
        // leftFrontDrive.getSelectedSensorVelocity(0));
    }

    /**
     * Resets the encoders by setting them to 0
     */
    public void resetEncoders()
    {

        leftFrontDrive.setSelectedSensorPosition(0, 0, 0);
        rightFrontDrive.setSelectedSensorPosition(0, 0, 0);

        encoderLeft = leftFrontDrive.getSelectedSensorPosition(0);
        encoderRight = rightFrontDrive.getSelectedSensorPosition(0);

    }

    public int getEncoderCount(int count)
    {
        counter++;
        return counter;

    }

    /**
     * Shifts solenoids into high gear
     */
    public void shiftHigh()
    {
        // Logger.Log("SHIFTHIGH ");
        shiftedhigh = true;
        theShiftSolenoid.set(DoubleSolenoid.Value.kReverse);

    }

    /**
     * Shifts solenoids into low gear
     */
    public void shiftLow()
    {
        shiftedhigh = false;
        // Logger.Log("SHIFTLOW ");
        theShiftSolenoid.set(DoubleSolenoid.Value.kForward);

    }

    /**
     * A drive function
     * 
     * @param leftMotorValue
     * @param rightMotorValue
     */
    public void drive(double leftMotorValue, double rightMotorValue)
    {

        /*
         * the joystick value is multiplied by a target RPM so the robot works
         * with the velocity tuning code
         */
        leftFrontDrive.set(ControlMode.PercentOutput, -leftMotorValue);
        rightFrontDrive.set(ControlMode.PercentOutput, -rightMotorValue);
        // Logger.Log("LEFT ENCODER === " +
        // leftFrontDrive.getSelectedSensorPosition(0));
        // Logger.Log("RIGHT ENCODER === " +
        // rightFrontDrive.getSelectedSensorPosition(0));
        // SmartDashboard.putNumber("ENCODER DISTANCE = ",
        // rightFrontDrive.getSelectedSensorPosition(0));
    }

    public void magicDrive(double target)
    {

        leftFrontDrive.set(ControlMode.MotionMagic, target);
        rightFrontDrive.set(ControlMode.MotionMagic, target);

    }

    // low gear real - 6.6ft/s high 17.5 ft/s
    public void driveSpeed(double leftSpeed, double rightSpeed)
    {

        /*
         * the joystick value is multiplied by a target RPM so the robot works
         * with the velocity tuning code
         */
        if (shiftedhigh)
        {
            leftFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_HIGH, 0);
            rightFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_HIGH, 0);

        }
        else
        {

            leftFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_LEFT, 0);
            rightFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_RIGHT, 0);

        }
        leftFrontDrive.set(ControlMode.Velocity, (leftSpeed) * CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH);
        rightFrontDrive.set(ControlMode.Velocity, (rightSpeed) * CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH);
        System.out.println("LEFT WANTED:" + leftSpeed);
        System.out.println("RIGHT WANTED:" + rightSpeed);
        // convert to inches/second
        Logger.Log("DriveTrain() : driveSpeed() : RIGHT SPEED IS ="
                + leftFrontDrive.getSelectedSensorVelocity(0) / CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH);
        Logger.Log("DriveTrain() : driveSpeed() : RIGHT ERROR IS =" + rightFrontDrive.getClosedLoopError(0));

    }

    //HIGH GEAR VALUES
    final double kV = 0.00434;// 0.00455
    public final double kA = 00.00434 * 0.15;
    public final double vSetpoint = 0.078;//0.078

    public void driveSpeedAccel(double leftSpeed, double rightSpeed, double leftAccel, double rightAccel)
    {

        /*
         * the joystick value is multiplied by a target RPM so the robot works
         * with the velocity tuning code
         */
        double leftWantedVoltage=0;
        leftWantedVoltage += kV * leftSpeed;
        leftWantedVoltage += kA * leftAccel;
        
        leftWantedVoltage += leftWantedVoltage>0? vSetpoint: -vSetpoint;
        
        
        
        double rightWantedVoltage=0;
        rightWantedVoltage += kV * rightSpeed;
        rightWantedVoltage += kA * rightAccel;
        rightWantedVoltage += rightWantedVoltage>0? vSetpoint: -vSetpoint;
         
        
        if (shiftedhigh)
        {
            double _leftSpeed = leftSpeed;
            double _rightSpeed = rightSpeed;
            if(Math.abs(leftSpeed)<0.01) {
                _leftSpeed = leftSpeed>0? 0.01:-0.01;
            }
            if(Math.abs(rightSpeed)<0.01) {
                _rightSpeed = rightSpeed>0? 0.01:-0.01;
            }
            
            
            double left_kF = (leftWantedVoltage/_leftSpeed);
            double right_kF = (rightWantedVoltage/_rightSpeed);
            leftFrontDrive.config_kF(0, left_kF*1023.0*10.0/CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH ,0);
            rightFrontDrive.config_kF(0, right_kF*1023.0*10.0/CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH, 0);
           System.out.println("LeftWanedVoltagE:" + leftWantedVoltage + "      left_KF:" + left_kF /*+"LEFT_VEL:" + leftFrontDrive.getSelectedSensorVelocity(0) +"   LEFT_TARGET:" + leftFrontDrive.getClosedLoopTarget(0)  + "    LEFT ERROR:" + leftFrontDrive.getClosedLoopError(0)*/);
            
        }
        else
        {

            leftFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_LEFT*10.0 , 0);
            rightFrontDrive.config_kF(0, CrusaderCommon.TALON_F_VALUE_RIGHT*10.0, 0);
            

        }
        leftFrontDrive.set(ControlMode.Velocity, (leftSpeed) * CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH/10.0);
        rightFrontDrive.set(ControlMode.Velocity, (rightSpeed) * CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH/10.0);
        System.out.println("Drive Accel LEFT WANTED:" + leftSpeed);
        System.out.println("Drive Accel RIGHT WANTED:" + rightSpeed);
        // convert to inches/second
       // Logger.Log("DriveTrain() : driveSpeed() : RIGHT SPEED IS ="
       //         + leftFrontDrive.getSelectedSensorVelocity(0) / CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH);
      //  Logger.Log("DriveTrain() : driveSpeed() : RIGHT ERROR IS =" + rightFrontDrive.getClosedLoopError(0));

    }
    
    /**
     * A drive backwards function used in Autonomous
     * 
     * @param leftMotorValue
     * @param rightMotorValue
     */
    public void driveBackwards(double leftMotorValue, double rightMotorValue)
    {

        // robotMotors.tankDrive(leftMotorValue, rightMotorValue);
        leftFrontDrive.set(ControlMode.PercentOutput, -leftMotorValue);
        rightFrontDrive.set(ControlMode.PercentOutput, -rightMotorValue);
    }

    /**
     * A turn left function used in Autonomous
     * 
     * @param leftJsValue
     * @param rightJsValue
     */
    public void turnLeft(double leftJsValue, double rightJsValue)
    {

        leftFrontDrive.set(ControlMode.PercentOutput, -leftJsValue);
        rightFrontDrive.set(ControlMode.PercentOutput, rightJsValue);

    }

    /**
     * A turn right function used in Autonomous
     * 
     * @param leftJsValue
     * @param rightJsValue
     */
    public void turnRight(double leftJsValue, double rightJsValue)
    {

        leftFrontDrive.set(ControlMode.PercentOutput, leftJsValue);
        rightFrontDrive.set(ControlMode.PercentOutput, -rightJsValue);

    }

    /**
     * configTalon is used to configure the master talons for velocity tuning so
     * they can be set to go to a specific velocity rather than just use a
     * voltage percentage This can be found in the CTRE Talon SRX Software
     * Reference Manual Section 12.4: Velocity Closed-Loop Walkthrough Java
     */
    public void configTalon(TalonSRX talon)
    {
        /*
         * This sets the voltage range the talon can use; should be set at
         * +12.0f and -12.0f
         */
        // talon.configNominalOutputVoltage(+0.0f, -0.0f);
        // talon.configPeakOutputVoltage(+12.0f, -12.0f);

        /*
         * This sets the FPID values to correct error in the motor's velocity
         */
        // talon.setProfile(CrusaderCommon.TALON_NO_VALUE);
        // .3113);
        talon.config_kP(0, CrusaderCommon.TALON_P_VALUE, 0); // .8);//064543);
        talon.config_kI(0, CrusaderCommon.TALON_NO_VALUE, 0);
        talon.config_kD(0, CrusaderCommon.TALON_D_VALUE, 0);

        talon.set(ControlMode.Velocity, 0);

    }

    // A complete function that only gets used in CommandShiftHigh and
    // CommandShiftLow
    public boolean complete()
    {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Motor Test function
     * 
     * @param currentBtn
     */
    public void incrementMotorValue(int currentBtn)
    {

        if (btncounter < 7)
        {
            btncounter++;
            return;
        }
        btncounter = 0;
        if ((lastBtnPressed == currentBtn)) // || (0 == currentBtn))
        {
            lastBtnPressed = currentBtn;
            // RM SmartDashboard.putNumber("current btn = ", lastBtnPressed);
            return;
        }
        else
        {
            lastBtnPressed = currentBtn;

            if (protoCounter < 1)
            {
                protoCounter = protoCounter * 100;
                protoCounter += scalefactor;
                protoCounter = protoCounter / 100;
                robotMotors.tankDrive(protoCounter, 0);
                lastBtnPressed = currentBtn;
                // RM SmartDashboard.putNumber("Motor vaslue = ", protoCounter);
            }
            Logger.Log("DriveTrain() : incrementMotorValue() : Increment" + protoCounter);
        }
    }

    /**
     * Motor Test function
     * 
     * @param currentBtn
     */
    public void decrementMotorValue(int currentBtn)
    {
        if (btncounterDec < 7)
        {
            btncounterDec++;
            return;
        }
        btncounterDec = 0;
        if ((lastBtnPressed == currentBtn))
        {
            lastBtnPressed = currentBtn;
            return;
        }
        else
        {
            lastBtnPressed = currentBtn;
            if (protoCounter > -1)
            {
                protoCounter = protoCounter * 100;
                protoCounter -= scalefactor;
                protoCounter = protoCounter / 100;
                robotMotors.tankDrive(protoCounter, 0);
                lastBtnPressed = currentBtn;
                // RM SmartDashboard.putNumber("Motor vaslue = ", protoCounter);
            }
            Logger.Log("DriveTrain() : decrementMotorValue() : decrenment " + protoCounter);
        }
    }

    /**
     * Motor Test function
     * 
     * @param currentBtn
     */
    public void incrementMotorValueOnePercent(int currentBtn)
    {

        if (btncounter < 7)
        {
            btncounter++;
            return;
        }
        btncounter = 0;
        if ((lastBtnPressed == currentBtn))
        {
            lastBtnPressed = currentBtn;
            // RM SmartDashboard.putNumber("current btn = ", lastBtnPressed);
            return;
        }
        else
        {
            lastBtnPressed = currentBtn;

            if (protoCounter < 1)
            {
                protoCounter = protoCounter * 100;
                protoCounter += scalefactorOnePercent;
                protoCounter = protoCounter / 100;
                robotMotors.tankDrive(protoCounter, 0);
                lastBtnPressed = currentBtn;
                // RM SmartDashboard.putNumber("Motor vaslue = ", protoCounter);
            }
            Logger.Log("DriveTrain() : incrementMotorValueOnePercent() : Increment " + protoCounter);
        }
    }

    /**
     * Motor Test function
     * 
     * @param currentBtn
     */
    public void decrementMotorValueOnePercent(int currentBtn)
    {
        if (btncounterDec < 7)
        {
            btncounterDec++;
            return;
        }
        btncounterDec = 0;
        if ((lastBtnPressed == currentBtn))
        {
            lastBtnPressed = currentBtn;
            return;
        }
        else
        {
            lastBtnPressed = currentBtn;
            if (protoCounter > -1)
            {
                protoCounter = protoCounter * 100;
                protoCounter -= scalefactorOnePercent;
                protoCounter = protoCounter / 100;
                robotMotors.tankDrive(protoCounter, 0);
                lastBtnPressed = currentBtn;
                // RM SmartDashboard.putNumber("Motor vaslue = ", protoCounter);
            }
            Logger.Log("DriveTrain() : incrementMotorValueOnePercent() : decrenment " + protoCounter);
        }
    }

    public double getLeftVelocity()
    {
        return 10.0 * leftFrontDrive.getSelectedSensorVelocity(0)/CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;
    }

    public double getRightVelocity()
    {
        return 10.0 * rightFrontDrive.getSelectedSensorVelocity(0)/CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;
        
    }

    /**
     * Motor Test function
     * 
     * @param currentBtn
     */
    public void resetMotorValue()
    {
        protoCounter = 0;
        lastBtnPressed = 1;
        robotMotors.tankDrive(0, 0);
        Logger.Log("DriveTrain() : resetMotorValue() : Reset Counter " + protoCounter);
    }

    /**
     * Motor Test function
     * 
     * @param currentBtn
     */
    public void nobtnPressed(boolean shifter)
    {
        lastBtnPressed = 1;
        if (shifter)
        {
            // Logger.Log("inside nobtnpressed");
            shiftLow();
        }

        // Logger.logDouble("Reset", lastBtnPressed);
    }

    // return distance travelled in inches
    public double leftDistanceTravelled()
    {
        System.out.println("LEFT TICKS: " + leftFrontDrive.getSelectedSensorPosition(0));
        return leftFrontDrive.getSelectedSensorPosition(0)  / CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;
    }

    // return distance travelled in inches
    public double rightDistanceTravelled()
    {
        System.out.println("RIGH TICKS: " + rightFrontDrive.getSelectedSensorPosition(0));
        return rightFrontDrive.getSelectedSensorPosition(0) / CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH;
    }

}
