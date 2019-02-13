package org.usfirst.frc.team238.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController2019 implements AutonomousState{

	//Current state being processed
	private AutonomousState currentState;
	
	//Index for going through the autonomousModeList
    private int index = 0;
    
	//The states that it's currently processing through
	ArrayList<AutonomousState> steps;
	
	//Holds all autocommands in order 
	HashMap<String, ArrayList<AutonomousState>> autonomousModeList;
    private AutonomousDataHandler myAutonomousDataHandler;

    //Constructor
    public AutonomousController2019(Robot myRobot){
        
        myAutonomousDataHandler = new AutonomousDataHandler(myRobot);
        autonomousModeList = myAutonomousDataHandler.getAutonomousModeCommandList2019();
        myAutonomousDataHandler.dump();
    }

	/**
	 * Allows the JSONHandler to pass it's data to the autonomousController for processing
	 * @param autonomousModeCommandList
	 */
	// public void setAutonomousControllerData(AutonomousDataHandler myJsonHandler){
	// 	autonomousModeList = myJsonHandler.getAutonomousModeCommandList2019();
	// }
	
	/**
	 * Sets the array of steps to be processed during AutonomousMode
	 * @param mode
	 */
    Iterator<AutonomousState> aModeIterator;
	
	public void pickAMode(String mode){
        steps = autonomousModeList.get(mode);
       
        if( steps != null){
            aModeIterator = steps.iterator();
            AutonomousState thisState = aModeIterator.next();
            setState(thisState);
        }
        else
        {
            Logger.Log("AutonomousController(): pickAmode: no auto mode");
        }
        
	}
	
	
	
	public void pickAModeFaileSafe() {
	    
        steps = autonomousModeList.get("Overline");
        setState(steps.get(0));
        index = 0;
	}
	
	
	/**
	 * Sets the current state to the given state parameter
	 * (Also calls the prepare method of the given state)
	 * @param state
	 */
	public void setState(AutonomousState state){
		this.currentState = state;
		state.prepare();
        Logger.Log("AutonomousController(): State: " + currentState);
        
	}
	
	/**
	 * Process the currentState and then continues onto the next state when done
	 */
	public void process() { 
		
        if(this.currentState != null){
            
            this.currentState.process();
            
            if(this.currentState.done() == true){
                
                setState(getNextState());
            }

            Logger.Log("AutonomousController2019.process(): State: " + currentState);
        }
        else{

            Logger.Log("AutonomousController2019.process():no matching auto mode to selction");
        }
	}

	/**
	 * Gets the next state in the sequence of steps
	 * @return
	 */
	private AutonomousState getNextState(){
	
        AutonomousState nextState = aModeIterator.next();
		Logger.Log("AutonomousController2019.getNextState():  " + nextState.getClass().getName() );
    
        return(nextState);
	}
	//Gets the selected automode and prints out the states in it
	public void dumpLoadedStates(SendableChooser<String>aModeSelector )
	{
		Iterator<AutonomousState> aModeIterator = steps.iterator();
		Integer count = 0;
		
		
		//Iterates through the mode's states until it finds the selected state
		while(aModeIterator.hasNext()){
			
			AutonomousState thisState = aModeIterator.next();
			
			Logger.Log("AutonomousController2019.Dump Loaded States():" + thisState.getClass().getName());
			
			//aModeSelector.addObject(thisState.getClass().getName(), count.toString());
			count++;	  
		}
		
		//SmartDashboard.putData("Sel Auto States", aModeSelector);
	}
	/**
	 * This cycles through every state until it gets to the selected state to be changed.
	 * Then applies the changes to the state object.
	 * THIS ISN'T THE SAME AS SAVING! THIS JUST UPDATES THE STATES WITH NEW PARAMS!
	 */
	public void updateStateParameters(String selectedMode)
	{
		int base = 0;
		//Get the index of the state that's being modified
		int index = (int) SmartDashboard.getNumber("Select Auto State", base);
		int count = 0;

       
        steps = autonomousModeList.get(selectedMode);
		Iterator<AutonomousState> aModeIterator = steps.iterator();
		
		//Iterates through the mode's states until it finds the selected state
		while(aModeIterator.hasNext()){
			
			AutonomousState thisState = aModeIterator.next();
			Logger.Log("AutonomousController(): updateStateParameters(): State Updated! :" + thisState.getClass().getName());
			if ( count == index)
			{
				//Updates it (It grabs the data from the SmartDashboard and applies it)
				thisState.updateParams();
				
			}
			
			//Increment counter as it goes through each states
			count++;
		}
	}

    public void displayAutoModes(){

        myAutonomousDataHandler.dump();
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
	public void init(String[] params, CommandController theMcp) {
		 
		
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

}