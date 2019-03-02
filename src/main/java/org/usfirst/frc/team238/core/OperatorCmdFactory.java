package org.usfirst.frc.team238.core;

import java.util.HashMap;

import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.Robot;
import org.usfirst.frc.team238.commands.CommandScoringPosition;
import org.usfirst.frc.team238.commands.CommandElevatorBottomHeight;
import org.usfirst.frc.team238.commands.CommandElevatorDown;
import org.usfirst.frc.team238.commands.CommandElevatorScaleHeight;
import org.usfirst.frc.team238.commands.CommandElevatorSwitchHeight;
import org.usfirst.frc.team238.commands.CommandElevatorUp;
import org.usfirst.frc.team238.commands.CommandHatchDeploy;
import org.usfirst.frc.team238.commands.CommandHatchRetract;
import org.usfirst.frc.team238.commands.CommandIntakeIn;
import org.usfirst.frc.team238.commands.CommandIntakeOut;
//import org.usfirst.frc.team238.commands.CommandIntakeOutFast;
import org.usfirst.frc.team238.commands.CommandShoulderAngle;
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
        operatorCommands.put(5, cmd);

        cmd = new CommandIntakeOut(theRobot.myShoulder);
        operatorCommands.put(6, cmd);

        //these next two are the manual shoulder
        cmd = new CommandShoulderUp(theRobot.myShoulder);
        operatorCommands.put(22, cmd);

        cmd = new CommandShoulderDown(theRobot.myShoulder);
        operatorCommands.put(23, cmd);

        cmd = new CommandElevatorUp(theRobot.myElevator);
        operatorCommands.put(21, cmd);

        cmd = new CommandElevatorDown(theRobot.myElevator);
        operatorCommands.put(20, cmd);

        cmd = new CommandScoringPosition(theRobot);
        operatorCommands.put(1, cmd);
        operatorCommands.put(2, cmd);
        operatorCommands.put(4, cmd);
        operatorCommands.put(3, cmd);
        operatorCommands.put(8, cmd);
        operatorCommands.put(24, cmd);
        operatorCommands.put(25, cmd);
        operatorCommands.put(26, cmd);
        operatorCommands.put(7, cmd);
        
        cmd = new CommandWristUp(theRobot);
        operatorCommands.put(28, cmd);

        cmd = new CommandWristDown(theRobot);
        operatorCommands.put(27, cmd);

        // cmd = new CommandHatchRetract(theRobot);
        // operatorCommands.put(27, cmd);

        // cmd = new CommandHatchDeploy(theRobot);
        // operatorCommands.put(28, cmd);

        

        return operatorCommands;

    }

}
