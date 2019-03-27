package org.usfirst.frc.team238.core;

import java.util.ArrayList;
import java.util.Iterator;

import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.testSteps.TestStepFinished;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestController implements TestStep{

	//Current state being processed
	private   TestStep currentState;
	
	//Index for going through the  TestModeList
	private int index = 0;
	
	//The states that it's currently processing through
	ArrayList< TestStep> steps;
	
	// sets up the first step for the tests
	@Override
	public void init(String[] params) {

		steps = new ArrayList<TestStep>();
		TestStep xxx;
		//TestStep xxx = new TestStepDriveTrainEncoders();
		//xxx.init(params, theController);
        
    	//add it to the steps for this autonomous mode   					
        //steps.add(xxx);
		
		//xxx =  new TestElevator(theController.myRobot);
		//xxx.init(params, theController);
		
		//add it to the steps for this autonomous mode   					
        //steps.add(xxx);
		
		
		//finished is ALWAYS the last step
		xxx = new TestStepFinished();
		//add it to the steps for this autonomous mode   					
        steps.add(xxx);
		
		setState(steps.get(CrusaderCommon.TEST_STEPS_START_POSITION));
		
	}
	
	//Gets the selected automode and prints out the states in it
	public void dumpLoadedStates(SendableChooser<String>aModeSelector )
	{
		Iterator<  TestStep> aModeIterator = steps.iterator();
		Integer count = 0;
		
		
		//Iterates through the mode's states until it finds the selected state
		while(aModeIterator.hasNext()){
			
			  TestStep thisState = aModeIterator.next();
			
			Logger.Log(" TestController(): Dump Loaded States():" + thisState.getClass().getName());
			
			aModeSelector.addOption(thisState.getClass().getName(), count.toString());
			count++;	  
		}
		
		SmartDashboard.putData("Sel Auto States", aModeSelector);
	}
	/**
	 * Sets the current state to the given state parameter
	 * (Also calls the prepare method of the given state)
	 * @param state
	 */
	public void setState( TestStep state){
		this.currentState = state;
		state.prepare();
		Logger.Log("TestController(): Step: " + currentState);
	}
	
	/**
	 * Process the currentState and then continues onto the next state when done
	 */
	public boolean process() { 
		
		Logger.Log("TestController.Process() ");

		this.currentState.process();
		
		if(this.currentState.done() == true){
			
			setState(getNextState());
			return true;
		}
		return false;
	}

	/**
	 * Gets the next state in the sequence of steps
	 * @return
	 */
	private   TestStep getNextState(){
		Logger.Log(" TestController(): getNextState(): index = " + index);
		  TestStep nextState = steps.get(++index);
		
		return(nextState);
	}
	
	/**
	 * This cycles through every state until it gets to the selected state to be changed.
	 * Then applies the changes to the state object.
	 * THIS ISN'T THE SAME AS SAVING! THIS JUST UPDATES THE STATES WITH NEW PARAMS!
	 */
	public void updateStateParameters(int selectedMode)
	{
		int base = 0;
		//Get the index of the state that's being modified
		int index = (int) SmartDashboard.getNumber("Select Auto State", base);
		int count = 0;

		//mkae sure you are at the first element in the list
		setState(steps.get(CrusaderCommon.TEST_STEPS_START_POSITION));

		Iterator<  TestStep> aModeIterator = steps.iterator();
		
		//Iterates through the mode's states until it finds the selected state
		while(aModeIterator.hasNext()){
			
			  TestStep thisState = aModeIterator.next();
			Logger.Log(" TestController(): updateStateParameters(): State Updated! :" + thisState.getClass().getName());
			if ( count == index)
			{
				//Updates it (It grabs the data from the SmartDashboard and applies it)
				thisState.updateParams();
				
			}
			
			//Increment counter as it goes through each states
			count++;
		}
	}

  public void dumpPlays() {
     //  TestPlay.dump( TestPlayBooks);
  }
  @Override
  public void reset() {
     
    index = 0;
  }
  
	@Override
	public void init() {
		 
		
	}

	@Override
	public void prepare() {
		 
		
	}

	

	@Override
	public boolean done() {
		 
		return false;
	}

	@Override
	public void showParams() {
		 
		
	}

	@Override
	public void updateParams() {
		 
		
	}

	@Override
	public String getParam(int value) {
		 
		return null;
	}
	
	/**
	 * Sets the array of steps to be processed during  TestMode if there were more than ones setof tests left for future implementation
	 * @param mode
	 
	public void pickAMode(int mode){
		steps =  testModeList [mode];
		setState(steps.get(0));
		index = 0;
	}
	*/

}