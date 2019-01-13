package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandRunnable implements Runnable {
    
    double angle, topSpeed, distance;
    double rotateOutput;
    
    final double acceleration = 150; //  in/sec^2
    final double ANGLE_KP = 3;
    
    boolean stop = false;
   
    Navigation navigation;
    Drivetrain driveTrain;
    PIDController pID;
    
    //EVERTHING IS IN INCHES
    public CommandRunnable(Robot myRobot, String[] params){ 
    	
    	if ((params[0] != null) || (!params[0].isEmpty())) {
    		this.distance = Double.parseDouble(params[0]) ;
    	} else {
    		this.distance = 0;
    	}

    	if ((params[1] != null) || (!params[1].isEmpty())) {
    		this.topSpeed = Double.parseDouble(params[1]);
    	} else {
    		this.topSpeed = 1;
    	}
    	       
        this.driveTrain = myRobot.myDriveTrain;
        this.navigation = myRobot.myNavigation;
        this.angle = this.navigation.getYaw();
        pID = new PIDController(ANGLE_KP, ANGLE_KP, ANGLE_KP, null, null);
        
    }

    public static final double delT = 50.0;

    public void run(){
        boolean deAccelerate = false;   
        double initialPosL = driveTrain.leftDistanceTravelled();
        double initialPosR = driveTrain.rightDistanceTravelled();
        double distanceTravelled =0;
       
        System.out.println("INITIAL POSL:" + initialPosL + "  INITIALPOSR:" + initialPosR + "DISTANCE" + distance);
        
        boolean backwards = distance<0;
        
        double currentVelocity = 0;
        double lastVelocity =0;
        
        //This is where the magic happens
        while(	!stop && 
        		DriverStation.getInstance().isAutonomous() && 
        		DriverStation.getInstance().isEnabled())
        {
            long startProcessingTime = System.currentTimeMillis();
            double timeToStop = Math.abs(currentVelocity/acceleration);
            double distanceNeededToStop = (Math.abs(currentVelocity)/2) * timeToStop;
            
            distanceTravelled=Math.abs(driveTrain.leftDistanceTravelled() - initialPosL); 
            //+         driveTrain.rightDistanceTravelled() - initialPosR) / 2;
            System.out.println("DISTANCETRAVELLED:" + distanceTravelled);
            //remainingdistance
            if(Math.abs(distance) - distanceTravelled<=distanceNeededToStop){
                deAccelerate=true;
            }

            double currentAccel;
            if(deAccelerate ? !backwards : backwards){
                currentVelocity -= (delT/1000) * acceleration;
                currentAccel = -acceleration;
            }else{
                currentVelocity += (delT/1000) * acceleration;
                currentAccel=acceleration;
            }
            currentVelocity = Math.max(Math.min(topSpeed, currentVelocity), -topSpeed);
           // System.out.println("currentV:" + currentVelocity);
            System.out.println(deAccelerate);
            //System.out.println(backwards);
            
            double angleError = angle - navigation.getYaw() ;
            if(Math.abs(angleError) > (360.0 - 0.0)/2.0D) {
                angleError = angleError>0.0D ? angleError- 360.0+ 0.0 : angleError + 360.0 -0.0; 
            }
 
            double angleVelocityAddend = angleError * ANGLE_KP;
            angleVelocityAddend = Math.min(50, Math.max(angleVelocityAddend, -50));

            System.out.println("ANGLEADDEND:" + angleVelocityAddend);
            if(Math.abs(topSpeed - Math.abs(currentVelocity))<0.5) {
                currentAccel=0;
            }
            System.out.println("TIME:" + System.currentTimeMillis());
            driveTrain.driveSpeedAccel(currentVelocity + angleVelocityAddend, currentVelocity - angleVelocityAddend,currentAccel,currentAccel);
            
            if(backwards){
                if(currentVelocity>=0 && lastVelocity<0){
                    stop=true;
                }
            }else{
                if(currentVelocity<=0 && lastVelocity>0){
                    stop=true;
                }
            }
            lastVelocity = currentVelocity;
            
            try
            {
                long endProcessingTime = System.currentTimeMillis();
                Thread.sleep((long) delT - (endProcessingTime - startProcessingTime));
                
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        driveTrain.drive(0, 0);


    }
    
    public boolean done() {
        return stop;
    }
    
   

    
    
}