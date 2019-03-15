package org.usfirst.frc.team238.commands;
import org.usfirst.frc.team238.robot.Robot;

public class CommandWaitForScoringPosition extends CommandScoringPosition
{
    public CommandWaitForScoringPosition(Robot theRobot) {
        super (theRobot);

    }

    public boolean done() {
        
        double currentHeight = theElevator.getHeight();
        if (currentHeight <= height || theElevator.isBottomReached()) {

            return true;
        }

        return false;
    }

}
