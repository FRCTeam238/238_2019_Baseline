package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.core.AbstractCommand;
import org.usfirst.frc.team238.core.Command;
import org.usfirst.frc.team238.robot.CrusaderCommon;
import org.usfirst.frc.team238.robot.IntakeWrist;

import edu.wpi.first.wpilibj.DriverStation;

public class CommandIntakeInAdv extends AbstractCommand {
	
	
	IntakeWrist intake;
	double delay, time, speed;
	
	public CommandIntakeInAdv(IntakeWrist myintake)
	{
		this.intake = myintake;
		
	}

	boolean executed = false;
	@Override
	public void execute() {
		// TODO Auto-generated method stub
	    System.out.println("EXECUTING");
	    
		if(!executed) {
		    executed=true;
		    Runnable run = ()->{
	            try
	            {
	                Thread.sleep((long) delay);
	            }
	            catch (InterruptedException e)
	            {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            long startTime  = System.currentTimeMillis();
	            while((System.currentTimeMillis() - startTime ) < time && DriverStation.getInstance().isAutonomous()) {
	                intake.intakeIn();
	                System.out.println("INTAKING: " + time);
	                try
	                {
	                    Thread.sleep(50);
	                }
	                catch (InterruptedException e)
	                {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                };
	                
	            }
	            intake.stop();
	        };
	        new Thread(run).start();    
		}
		
	}

	@Override
	public void execute(int btnPressed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepare() {
		// TODO Auto-generated method stub
	    executed=false;

	}

	
	public void setParams(String params[]) {
		// TODO Auto-generated method stub
	    if ((params[0] != null) || (!params[0].isEmpty())) {
	        delay = Double.parseDouble(params[0]);//CrusaderCommon.DRIVE_FORWARD_ENCODER_TICKS_PER_INCH; //4560;
	      } else {
	        delay = 0;
	      }

	      if ((params[1] != null) || (!params[1].isEmpty())) {
	        speed = Double.parseDouble(params[1]);
	      } else {
	        speed = 1;
	      }

	      if ((params[2] != null) || (!params[2].isEmpty())) {
	        time = Double.parseDouble(params[2]);
	      } else {
	        time = 0;
	      }
	    
	    
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return true;
	}

}
