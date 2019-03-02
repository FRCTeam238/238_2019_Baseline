package org.usfirst.frc.team238.robot;

//import java.util.HashMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CrusaderCommon {

	// All button inputs for the operator
	public static final int OPERATOR_TRIGGER = 1;
	// MAKE THESE INT
	public static final int stopEverythingInput = 0;

	/* DO NOT NUKE */
	// two types of command lists
	public static final int OPR_CMD_LIST = 0;
    public static final int DT_CMD_LIST = 1;
    
    //only used if we go back to 2 regular JoyStick
    public static final int LEFTDRIVER_CMD_LIST = 1;
	public static final int RIGHTDRIVER_CMD_LIST = 2;

	/* DO NOT NUKE */
	public static final int INPUT_DRIVER_LEFT_JS = 2;
	public static final int INPUT_DRIVER_RIGHT_JS = 3;

	// this element in the commandValue array is only
	// used by the AutoMode1Impl class. CommandController
	// is unaware of it

	/* DO NOT NUKE */
	public static final boolean SHIFTER_HIGH_GEAR = true;
	public static final boolean SHIFTER_LOW_GEAR = false;

	public static final double DRIVETRAIN_MAX_RPM = 1500;
	public static final double SHOOTER_MAX_RPM = SmartDashboard.getNumber("SHOOTER RPM", 0);// 1500;

	public static final double AUTO_JSON_CREATOR_PARAM_LIMIT = 4;

	/*
	 * The AUTO_DRIVE_* constants are the joystick positions when moving in the
	 * expected direction. These values are passed to the RobotDrive.tankDrive
	 * method.
	 */
	/* DO NOT NUKE */
	public static final double AUTO_DRIVE_FORWARD = 0.75;
	public static final double AUTO_DRIVE_BACKWARD = -0.75;
	public static final double AUTO_DRIVE_IDLE = 0.0;

	/*
	 * Auto selection constants
	 */
	public static final String AUTO_PLAY_BOOK = "PlayBook";
	public static final String AUTO_ROBOT_POSITION = "Robot Position";

	/* DO NOT NUKE */
	// public static final HashMap<Integer, Boolean> DRIVE_TRAIN_CMD_IDX = new
	// HashMap<Integer, Boolean>() {{put(0,true);}};
	public static final Integer[] DRIVE_TRAIN_CMD_IDX = new Integer[0];

	/* DO NOT NUKE */
	public static final int AUTO_DRIVE_LIMIT = 15000;

	// Intake Motor Values
	public final static double SERIALIZER_MOTOR_ON = 0.77;// 0.77;
	public final static double SERIALIZER_MOTOR_OFF = 0;
	public final static double SERIALIZER_MOTOR_ON_REVERSE = -0.77;

	public final static double INTAKE_MOTOR_ROTATE_IN = -1.0;
	public final static double INTAKE_MOTOR_ROTATE_OUT = 1.0;
	public final static double INTAKE_MOTOR_OFF = 0;

	public final static int VISION_ANGLE_SLOT = 0;
	public final static int VISION_DISTANCE_SLOT = 1;

	public final static double TRACKING_MOTOR_VALUE = 1;

	public final static double TALON_F_VALUE_LEFT = 0.00455;//0.0725 old autonomous
	public final static double TALON_F_VALUE_HIGH = 0.002;//0.0725 old autonomous

	public final static double TALON_F_VALUE_RIGHT = 0.00455;//0.0735 old autonomous
	public final static double TALON_P_VALUE = 0.2;//0.5
	public final static double TALON_D_VALUE = 0;
	public final static int TALON_NO_VALUE = 0;

	/* THESE ARE THE ALL THE TALON IDS FOR THE 2018 ROBOT */

	public static final int DRIVE_TRAIN_RIGHT_MASTER_DRIVER_STATION = 0;
	public static final int DRIVE_TRAIN_LEFT_MASTER_DRIVER_STATION = 15;

	public static final int DRIVE_TRAIN_RIGHT_MASTER = 0;
	public static final int DRIVE_TRAIN_RIGHT_SLAVE1 = 1;
	public static final int DRIVE_TRAIN_RIGHT_SLAVE2 = 2;

	public static final int DRIVE_TRAIN_LEFT_MASTER = 15;
	public static final int DRIVE_TRAIN_LEFT_SLAVE1 = 14;
	public static final int DRIVE_TRAIN_LEFT_SLAVE2 = 13;

	public static final double ELEVATOR_KP = 0.067;
	public static final double ELEVATOR_KD = 0.037;
	public static final double ELEVATOR_TICK_TO_IN = 2205.0;//7414
	public static final int CW_ELEVATOR_MASTER = 8; 
	public static final int CW_ELEVAOR_SLAVE_SRX = 11;
    public static final int CW_ELEVATOR_SLAVE_SPX = 10;
    public static final int ELEVATOR_MASTER = 11; 
	//public static final int ELEVAOR_SLAVE_SRX = 11;
	//public static final int ELEVATOR_SLAVE_SPX = 10;

	public static final int INTAKE_MASTER_SRX = 4;
	public final static int INTAKE_SHOULDER = 7;
    public final static double INTAKE_TICK_TO_DEGREE = 45;//9.55;
	public final static double INTAKE_KP = 0.025;

	public static final double ELEVATOR_CUBE_SPEED = 0.8;
	public static final double ELEVATOR_CLIMB_SPEED = 0.5;
	public static final double ELEVATOR_BOTTOM_SOFT_STOP = 6000;
	public static final double ELEVATOR_TOP_SOFT_STOP = 600000;
	public static final double ELEVATOR_FEED_FORWARD = 0.1;

	public static final double INTAKE_SHOULDER_SPEED = 0.5;
	public static final double INTAKE_SPEED = 0.5;//0.8;
	public static final double INTAKE_SPEED_FAST = 0.95;
    public static final double INTAKE_SPEED_SLOW = 0.3;

    // public static final double SHOULDER_LEVEL_ONE_ANGLE = 0;
    // public static final double SHOULDER_LEVEL_TWO_ANGLE = 10;
    // public static final double SHOULDER_LEVEL_THREE_ANGLE = 20;

     public static final double ELEVATOR_LEVEL_ONE_HEIGHT = 1;
     public static final double ELEVATOR_LEVEL_TWO_HEIGHT = 10;
     public static final double ELEVATOR_LEVEL_THREE_HEIGHT = 30;

    //numbers are the buttons
    public static final int CARGO_LEVEL_ONE = 1;
    public static final int CARGO_LEVEL_TWO = 2;
    public static final int CARGO_LEVEL_THREE = 4;

    public static final int HATCH_LEVEL_ONE = 24;
    public static final int HATCH_LEVEL_TWO = 25;
    public static final int HATCH_LEVEL_THREE = 26;

    public static final int CARGO_SHIP_CARGO = 3;

    public static final int SAFE_DRIVING_MODE = 8;

    //scores cargo( ball) in Cargo ship
    public static final double CARGO_SHIP_LEVEL_ONE_ELEVATOR = 0;
    public static final double CARGO_SHIP_LEVEL_ONE_SHOULDER = 80;
   

    //scores cargo (ball  in Rocket ship)
    public static final double ROCKET_CARGO_LEVEL_ONE_ELEVATOR = 0;
    public static final double ROCKET_CARGO_LEVEL_ONE_SHOULDER = 60;
    public static final boolean ROCKET_CARGO_LEVEL_ONE_WRIST = true; //true means wrist is extended

    public static final double ROCKET_CARGO_LEVEL_TWO_ELEVATOR = 10;
    public static final double ROCKET_CARGO_LEVEL_TWO_SHOULDER = 100;
    public static final boolean ROCKET_CARGO_LEVEL_TWO_WRIST = true;

    public static final double ROCKET_CARGO_LEVEL_THREE_ELEVATOR = 30;
    public static final double ROCKET_CARGO_LEVEL_THREE_SHOULDER = 100;
    public static final boolean ROCKET_CARGO_LEVEL_THREE_WRIST = true;

    public static final double ROCKET_CARGO_LEVEL_ZERO_VALUE = 0;


    //Scores hatch on Rocket Ship level one  will also do cargo ship
    public static final double HATCH_LEVEL_ONE_ELEVATOR = 0;
    public static final double HATCH_LEVEL_ONE_SHOULDER = 0;
    public static final boolean HATCH_LEVEL_ONE_WRIST = false; //true means wrist is extended

    public static final double ROCKET_HATCH_LEVEL_TWO_ELEVATOR = 0; 
    public static final double ROCKET_HATCH_LEVEL_TWO_SHOULDER = 85; 
    public static final boolean ROCKET_HATCH_LEVEL_TWO_WRIST = false;

    public static final double ROCKET_HATCH_LEVEL_THREE_ELEVATOR = 20;
    public static final double ROCKET_HATCH_LEVEL_THREE_SHOULDER = 100;
    public static final boolean ROCKET_HATCH_LEVEL_THREE_WRIST = false;

    public static final double ROCKET_HATCH_LEVEL_ZERO_VALUE = 0;
    public static final boolean WRIST_FALSE = false;
    public static final boolean WRIST_TRUE = true;

    // hatch level 2 = 50

    public static final double CARGO_SHIP_CARGO_ELEVATOR = 5;
    public static final double CARGO_SHIP_CARGO_SHOULDER = 3;
    public static final boolean CARGO_SHIP_CARGO_WRIST = true;

    public static final double SAFE_DRIVING_MODE_ELEVATOR = 5;
    public static final double SAFE_DRIVING_MODE_SHOULDER = 3;
    public static final boolean SAFE_DRIVING_MODE_WRIST = false;

	// CurrentDraw variable
	public final static double CURRENT_DRAW_LIMIT = 20.0;

	// PID VALS FOR NAVIGATION
	public final static double NAVIGATION_P_VALUE = 0.1;
	public final static double NAVIGATION_MAX_MOTOR_INCREMENT = 0.2;
	public final static double NAVIGATION_TURNING_DEADZONE = 1.5;

	public final static double DRIVE_FORWARD_P_VALUE = 0.015;
	public final static double DRIVE_FORWARD_I_VALUE = 0;
	public final static double DRIVE_FORWARD_MAX_YAW_PERCENT = 0.1;

	// DETERMINE THIS EXACT NUMBER FOR NEW ROBOT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	// //325 = 1 inch 3900 = 1 foot
    public final static double DRIVE_FORWARD_ENCODER_TICKS_PER_INCH = 194; //1626.0;//252;//323;// 4983;//3900//1750;

	// TURNING AND DRIVING PID VALUES
	public final static double TURN_P_VALUE = 0.0007;// 0.003;//SmartDashboard.getNumber("Turn
														// P
														// Value",0.005);//0.005;
	public final static double TURN_I_VALUE = 0.00120;// 0.00125;//0.003;//FIND
														// A VALUE FOR THIS
	public final static double TURN_DEAD_STOP = 0.175;// 0.25;//SmartDashboard.getNumber("Turn
														// Dead Stop",
														// 0.42);//0.42;
	public final static double TURN_DEAD_STOP_RIGHT = 0.118;
	public final static double TURN_MAX_ERROR = 80;// SmartDashboard.getNumber("Turn
													// Max Error",45);//45;
	public final static double TURN_MAX_MOTOR_VALUE = .7;// SmartDashboard.getNumber("Turn
															// Max
															// Error",45);//45;

	public final static double STRAIGHT_P_VALUE = 8.5E-6;//0.000055;// 0.00003512;
	public final static double STRAIGHT_I_VALUE = 0.0000009;
	public final static double STRAIGHT_DEAD_STOP = 0.1;// 0.05;
	public final static double STRAIGHT_MAX_ERROR = 40000;//15000;// 9966;
	public final static double STRAIGHT_MAX_MOTOR_VALUE = 0.5;// SmartDashboard.getNumber("Turn
																// Max
																// Error",45);//45;

	public static final int AUTONOMOUS_READ_FILE = 2;
	public static final int AUTONOMOUS_SAVE = 1;
	public static final int AUTONOMOUS_UPDATE = 1;

	public final static int CURL_START = 1;
	public final static int CURL_TURN = 2;
	public final static int CURL_FINISH_TURN = 3;

	public final static int COLLISION_DELAY_IN_MILLIS = 375;

	public final static int RAMP_SOL_1 = 2;
	public final static int RAMP_SOL_2 = 3;

	public static final int LINE_SENSOR = 0;
	public static final int Additional_Button_Mapppings = 21;

	// constants for test functions
	public static final int TEST_STEPS_START_POSITION = 0;
	public static final int TEST_DRIVE_COUNTER = 100;
	public static final int TEST_DRIVE_TICKS = 4000;

	//Motors are at half speed
	public static final double TEST_LEFT_MOTOR_VALUE = 0.5;
	public static final double TEST_RIGHT_MOTOR_VALUE = 0.5;

	public static final double TEST_DRIVETRAIN_BASELINE = 100000; //expected encoder values
	public static final double TEST_DRIVETRAIN_TOLERANCE = 0.25 * TEST_DRIVETRAIN_BASELINE; //limit between expected and actual encoder values

	public static final double TEST_ELEVATOR_COUNTER = 8000;
	public static final double TEST_ELEVATOR_TOLERANCE = 100;

	public static final double ELEVATOR_SETPOINT_ONE = 5;
	public static final double ELEVATOR_SETPOINT_TWO = 16;
	public static final double ELEVATOR_SETPOINT_THREE = 0;
    public static final double ELEVATOR_SETPOINT_TOLERANCE = 1.5;
    
    public static final double SHOULDER_TARGET_TOLERANCE = 5;
}
