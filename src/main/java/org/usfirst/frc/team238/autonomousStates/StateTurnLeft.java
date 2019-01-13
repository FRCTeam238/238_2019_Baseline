package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandTurnLeft;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateTurnLeft implements AutonomousState {

  CommandTurnLeft   turnLeftCommand;
  CommandController theMCP;
  String            parameters[];

  int count;
  // double newTargetYaw = -45; //For turning Left however many degrees uses
  // negatives

  @Override
  public void prepare() {
    turnLeftCommand.setParams(parameters);
    turnLeftCommand.prepare();

  }

  @Override
  public void init(String params[], CommandController theMcp) {

    turnLeftCommand = (CommandTurnLeft) theMcp.getAutoCmd("CommandTurnLeft");
    parameters = params;

  }

  @Override
  public void process() {

    Logger.Log("StateTurnLeft.Process() "+ count);
    count++;
    turnLeftCommand.execute();

  }

  @Override
  public boolean done() {

    if (turnLeftCommand.done()) {
      count = 0;
      return true;

    }

    return false;

  }

  @Override
  public void reset() {

    // turnLeftCommand.reset();

  }

  @Override
  public void init() {
    // TODO Auto-generated method stub

  }

  @Override
  public void showParams() {
    // TODO Auto-generated method stub
    //RM SmartDashboard.putString("Param 1 - targetValue", parameters[0]);
    //RM SmartDashboard.putString("Param 2 - motorSpeed", parameters[1]);
    //RM SmartDashboard.putString("Param 3 - newTargetYaw", parameters[2]);
    //RM SmartDashboard.putString("Param 4 - ultrasonicTarget", "0");
    //RM SmartDashboard.putString("Param 5 - collisionToggle", "0");
  }

  @Override
  public void updateParams() {
    // TODO Auto-generated method stub
    String param1;
    String param2;
    String param3;
    String param4;
    String param5;

    param1 = SmartDashboard.getString("Param 1 - targetValue", "");
    parameters[0] = param1;
    param2 = SmartDashboard.getString("Param 2 - motorSpeed", "");
    parameters[1] = param2;
    param3 = SmartDashboard.getString("Param 3 - targetYaw", "");
    parameters[2] = param3;
    param4 = SmartDashboard.getString("Param 4 - ultrasonicTarget", "");
    parameters[3] = param4;
    param5 = SmartDashboard.getString("Param 5 - collisionToggle", "");
    parameters[4] = param5;
  }

  @Override
  public String getParam(int value) {
    String output = "";
    if (parameters == null || parameters.length - 1 < value) {
      output = "";
    } else {
      output = parameters[value];
    }
    return output;
  }

}
