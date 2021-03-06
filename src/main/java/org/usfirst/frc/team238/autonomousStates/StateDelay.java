package org.usfirst.frc.team238.autonomousStates;

import org.usfirst.frc.team238.commands.CommandDelay;
import org.usfirst.frc.team238.core.AutonomousState;
import org.usfirst.frc.team238.core.CommandController;
import org.usfirst.frc.team238.core.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StateDelay implements AutonomousState {

	CommandDelay delay;
	String       parameters[];
	int count;
	int targetValue;

	@Override
	public void prepare() {

		delay.setParams(Integer.parseInt(parameters[0]));
		delay.prepare();
	}

	//called when this state object is created during reading the json file 
	public void init(String params[], CommandController theMcp) {

		delay = (CommandDelay) theMcp.getAutoCmd("CommandDelay");
		parameters = params;

	}


	public void process() {
		delay.execute();
		Logger.Log("StateDelay.Process()");
	}

	public boolean done() {

		//	if (delay.done() == true) {
		//      return true;
		//    } else {
		//      return false;
		//    }

		return delay.done();
	}

	@Override
	public void init() {
	
	}

	@Override
	public void reset() {
	
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
    
    @Override
	public String[] getParams() {
		
		return parameters;
	}

}
