package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandRunTrajectory;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateScoreSwitchEnd implements AutonomousState {

  CommandRunTrajectory leftSwitch;
  CommandRunTrajectory rightSwitch;
  
  String       parameters[];
  boolean 		rightside;
  public void init(String params[], CommandController theMcp) {
	  
      leftSwitch = (CommandRunTrajectory) theMcp.getAutoCmd("CommandRunSwitchEndLeftTrajectory");
      rightSwitch = (CommandRunTrajectory) theMcp.getAutoCmd("CommandRunSwitchEndRightTrajectory");
      parameters = params;
      
  }

  int count;
  int targetValue;

  public void process() {
      
      Logger.Log("StateScoreSwitchEnd.Process()");
      
	  if(rightside)
	  {
		  rightSwitch.execute();  
	  }
	  else
	  {
	      Logger.Log("StateScoreSwitchEnd.Process()LeftSide");
	      leftSwitch.execute();
	  }
	  
      
  }

  public boolean done() {
    
	  
	  if(rightside)
	  {
		  if (rightSwitch.done() == true) 
		    {
		      return true;
		    } 
		    else 
		    {
		      return false;
		    } 
	  }
	  else
	  {
		  if (leftSwitch.done() == true) 
		    {
		      return true;
		    } 
		    else 
		    {
		      return false;
		    }
	  }
	  
	

  }

  @Override
  public void init() {
    // TODO Auto-generated method stub

  }

  @Override
  public void prepare() {
	  
	  String gameData;
	  gameData = DriverStation.getInstance().getGameSpecificMessage();
      if(gameData.length() > 0)
      {
		    if( gameData.startsWith("L"))
		    {
		    	rightside = false;
		    	leftSwitch.setParams(parameters);
		        leftSwitch.prepare();
		    }
		    else
		    {
		    	rightside = true;
		    	rightSwitch.setParams(parameters);
		        rightSwitch.prepare();
		    }
		    
      }
      
      
  }

  @Override
  public void reset() {
    // TODO Auto-generated method stub

  }

  @Override
  public void showParams() {

    //RM SmartDashboard.putString("Param 1 - targetValue", parameters[0]);
    //RM SmartDashboard.putString("Param 2 - motorSpeed", "0");
    //RM SmartDashboard.putString("Param 3 - targetYaw", "0");
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
  param3 = SmartDashboard.getString("Param 3 - newTargetYaw", "");
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
