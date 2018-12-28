package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandAutonLine;
import org.usfirst.frc.team238.commands.CommandDriveForward;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateNewDriveForward implements AutonomousState {

    CommandAutonLine DriveForwardCommand;
  int                 count = 0;
  String              parameters[];

  @Override
  public void prepare() {
    DriveForwardCommand.setParams(parameters);
    DriveForwardCommand.prepare();
    // Logger.logString(parameters);

  }

  @Override
  public void init(String params[], CommandController theMcp) {

    DriveForwardCommand = (CommandAutonLine) theMcp.getAutoCmd("CommandAutonLine");
    
    parameters = params;

  }

  @Override
  public void process() {
    Logger.Log("StateDriveForward.Process()  "+ count);
    count++;
    DriveForwardCommand.execute();
  }

  @Override
  public boolean done() {
    return DriveForwardCommand.done();

  }

  @Override
  public void reset() {

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
    //RM SmartDashboard.putString("Param 3 - targetYaw", parameters[2]);
    //RM SmartDashboard.putString("Param 4 - ultrasonicTarget", parameters[3]);
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
    //parameters[4] = param5;
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
