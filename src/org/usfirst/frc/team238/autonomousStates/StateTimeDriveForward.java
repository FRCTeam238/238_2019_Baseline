package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandTimeDriveFwd;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateTimeDriveForward implements AutonomousState {

 CommandTimeDriveFwd driveForAWhile;
  int                   count = 0;

  String parameters[];

  @Override
  public void prepare() {
	  driveForAWhile.setParams(parameters);
	  driveForAWhile.prepare();

  }

  @Override
  public void init(String params[], CommandController theMcp) {

	driveForAWhile = (CommandTimeDriveFwd) theMcp.getAutoCmd("CommandTimeDriveFwd");
    parameters = params;

  }

  @Override
  public void process() {
    Logger.Log("StateDriveBackwards.Process()  "+ count);
    count++;
    driveForAWhile.execute();
  }

  @Override
  public boolean done() {
    if (driveForAWhile.done()) {
      count = 0;
      return true;
    }

    return false;
  }

  // used when autonomous is interrupted
  @Override
  public void reset() {

  }

  @Override
  public void init() {
    // TODO Auto-generated method stub

  }

  @Override
  public void showParams() {

    //RM SmartDashboard.putString("Param 1 - targetValue", parameters[0]);
    //RM SmartDashboard.putString("Param 2 - motorSpeed", parameters[1]);
   // SmartDashboard.putString("Param 3 - targetYaw", parameters[2]);
   // SmartDashboard.putString("Param 4 - ultrasonicTarget", parameters[3]);

  }

  @Override
  public void updateParams() {

    String param1;
    String param2;
    String param3;
    String param4;

    param1 = SmartDashboard.getString("Param 1 - targetValue", "");
    parameters[0] = param1;
    param2 = SmartDashboard.getString("Param 2 - motorSpeed", "");
    parameters[1] = param2;
//    param3 = SmartDashboard.getString("Param 3 - targetYaw");
//    parameters[2] = param3;
//    param4 = SmartDashboard.getString("Param 4 - ultrasonicTarget");
//    parameters[3] = param4;
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
