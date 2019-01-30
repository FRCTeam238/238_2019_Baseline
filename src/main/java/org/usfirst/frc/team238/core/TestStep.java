package org.usfirst.frc.team238.core;

import org.usfirst.frc.team238.robot.TestSweet238;

public interface TestStep {
	
	public void init();
	public void prepare();
	public void init(String params[], TestSweet238 theController);
	public boolean process();
	public boolean done();
	public void reset();
	public void showParams();
	public void updateParams();
	public String getParam(int value);
	
}
