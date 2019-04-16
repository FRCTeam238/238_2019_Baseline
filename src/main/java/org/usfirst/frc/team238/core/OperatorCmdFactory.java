package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;

import org.usfirst.frc.team238.commands.CommandScoringPosition;
import org.usfirst.frc.team238.commands.CommandElevatorDown;
import org.usfirst.frc.team238.commands.CommandElevatorUp;
import org.usfirst.frc.team238.commands.CommandHatchDeploy;
import org.usfirst.frc.team238.commands.CommandIntakeIn;
import org.usfirst.frc.team238.commands.CommandIntakeOut;
import org.usfirst.frc.team238.commands.CommandShoulderDown;
import org.usfirst.frc.team238.commands.CommandShoulderUp;
import org.usfirst.frc.team238.commands.CommandStopEverything;
import org.usfirst.frc.team238.commands.CommandWristDown;
import org.usfirst.frc.team238.commands.CommandWristUp;

public class OperatorCmdFactory {

    HashMap<Integer, Command> operatorCommands;

    public void init() {
        operatorCommands = new HashMap<Integer, Command>(50);
    }

    /**
     * Operator controls get binded here. Assigning a series of buttons and commands
     * to a HashMap
     * 
     * @param theRobot
     * @return
     */
    public HashMap<Integer, Command> createOperatorCommands(Robot theRobot) {
        AbstractCommand cmd;

        // Create command objects, passing objects into each of them

        cmd = new CommandStopEverything(theRobot);
        operatorCommands.put(CrusaderCommon.stopEverythingInput, cmd);

        cmd = new CommandIntakeIn(theRobot.myShoulder);
        operatorCommands.put(XBoxValues.LeftBumper, cmd); // (5, cmd);

        cmd = new CommandIntakeOut(theRobot.myShoulder);
        operatorCommands.put(XBoxValues.RightBumper, cmd); // (6, cmd);

        // these next two are the manual shoulder
        cmd = new CommandShoulderUp(theRobot.myShoulder);
        operatorCommands.put(XBoxValues.LeftJoystickUp, cmd); // (22, cmd);

        cmd = new CommandShoulderDown(theRobot.myShoulder);
        operatorCommands.put(XBoxValues.LeftJoystickDown, cmd); // (23, cmd);

        cmd = new CommandElevatorUp(theRobot.myElevator);
        operatorCommands.put(XBoxValues.RightJoystickDown, cmd); // (21, cmd);

        cmd = new CommandElevatorDown(theRobot.myElevator);
        operatorCommands.put(XBoxValues.RightJoystickUp, cmd); // (20, cmd);

        cmd = new CommandScoringPosition(theRobot);
        operatorCommands.put(XBoxValues.A, cmd); // 1, cmd);
        operatorCommands.put(XBoxValues.B, cmd); // 2, cmd);
        operatorCommands.put(XBoxValues.Y, cmd); // 4, cmd);
        operatorCommands.put(XBoxValues.X, cmd); // 3, cmd);
        operatorCommands.put(XBoxValues.Start, cmd); // 8, cmd);
        operatorCommands.put(XBoxValues.DPadUp, cmd); // 24, cmd);
        operatorCommands.put(XBoxValues.DPadRight, cmd); // 25, cmd);
        operatorCommands.put(XBoxValues.DPadDown, cmd); // 26, cmd);
        //operatorCommands.put(XBoxValues.DPadDownLeft, cmd); // 30, cmd);
        operatorCommands.put(XBoxValues.Back, cmd); // 7, cmd);

        cmd = new CommandWristUp(theRobot);
        operatorCommands.put(XBoxValues.TriggerLeft, cmd); // (28, cmd);

        cmd = new CommandWristDown(theRobot);
        operatorCommands.put(XBoxValues.TriggerRight, cmd); // (27, cmd);

        // cmd = new CommandHatchRetract(theRobot);
        // operatorCommands.put(9, cmd);

        cmd = new CommandHatchDeploy(theRobot);
        operatorCommands.put(XBoxValues.DPadLeft, cmd); // (29, cmd);
        //mapped DPadLeft, DPadDownLeft, DPadUpLeft all to DPadLeft
        //operatorCommands.put(XBoxValues.DPadDownLeft, cmd); // (29, cmd);
        //operatorCommands.put(XBoxValues.DPadUpLeft, cmd); // (29, cmd);

        return operatorCommands;
    }
}
