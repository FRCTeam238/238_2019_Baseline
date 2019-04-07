package org.usfirst.frc.team238.commands;

import org.usfirst.frc.team238.robot.Drivetrain;
import org.usfirst.frc.team238.robot.Navigation;
import org.usfirst.frc.team238.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team238.core.DashBoard238;
import org.usfirst.frc.team238.core.Logger;

public class AutonLineRunnable implements Runnable {
    
    double angle, topSpeed, distance;
    String navigationDevice;
    double rotateOutput;
    
    final double acceleration = 150; //  150 in/sec^2
    final double ANGLE_KP = 3;
    
    boolean stop = false;
   
    Robot myRobot;
    Navigation navigation;
    Drivetrain driveTrain;
    DashBoard238 theDashboard;
    
    //EVERHING IS IN INCHESSSSSSS
    public AutonLineRunnable(Robot myRobot, double distance, double topSpeed, String nav){

    	this.driveTrain = myRobot.myDriveTrain;
        this.navigation = myRobot.myNavigation;
        this.theDashboard = myRobot.myDashBoard238;
        
        //this.navigation.resetNAVX();
        //this.angle = this.navigation.getYaw();
        this.topSpeed=topSpeed;
        this.distance=distance;
        this.navigationDevice = nav;
        
       
    }

    // private double getYaw(){
    //     double yaw;
        
    //     if( navigationDevice.equalsIgnoreCase("NAVBOARD")) {
    //         yaw = navigation.getYaw();
    //     }
    //     else{
    //         TapeStatus theTape = theDashboard.getTapeStatus();
    //         if(theTape.detected == true){
    //             yaw = theTape.yaw;
    //         }
    //         else{
    //             yaw = navigation.getYaw();
    //         }
           

    //     }
    //     return yaw;
    // }


    public static final double delT = 50.0;
    public void run(){

        boolean deAccelerate = false;
        driveTrain.resetEncoders();
        double initialPosL = driveTrain.leftDistanceTravelled();
        double initialPosR = driveTrain.rightDistanceTravelled();
        double distanceTravelled =0;
       
        Logger.Log("INITIAL POSL:" + initialPosL + "  INITIALPOSR:" + initialPosR + "DISTANCE" + distance);
        
        boolean backwards = distance < 0;
        
        double currentVelocity = 0;
        double lastVelocity = 0;
        
        this.navigation.resetNAVX();
        this.angle = this.navigation.getYaw();

        while(	!stop && 
        		DriverStation.getInstance().isAutonomous() && 
        		DriverStation.getInstance().isEnabled())
        {
            //long startProcessingTime = System.currentTimeMillis();
            double timeToStop = Math.abs(currentVelocity/acceleration);
            double distanceNeededToStop = (Math.abs(currentVelocity)/2) * timeToStop;
            
            double leftEncoderPos = driveTrain.leftDistanceTravelled();
            double rightEncoderPos = driveTrain.rightDistanceTravelled();

            // if (leftEncoderPos == 0){
            //     if(rightEncoderPos  != 0){
            //         distanceTravelled=Math.abs( rightEncoderPos - initialPosR);    
            //     }
            //     else{
            //         stop = true;
            //     }
            // }else if (rightEncoderPos == 0){
            //     if(leftEncoderPos != 0){
            //         distanceTravelled=Math.abs( leftEncoderPos - initialPosL);    
            //     }
            //     else{
            //         stop = true;
            //     }
            // }else{
            
                distanceTravelled=Math.abs( leftEncoderPos - initialPosL +  rightEncoderPos - initialPosR) / 2;
            //}
           // distanceTravelled=Math.abs(driveTrain.leftDistanceTravelled() - initialPosL +  driveTrain.rightDistanceTravelled() - initialPosR) / 2;
            //Logger.Log("DISTANCETRAVELLED:" + distanceTravelled);
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
                currentAccel = acceleration;
            }
            currentVelocity = Math.max(Math.min(topSpeed, currentVelocity), -topSpeed);
           // Logger.Log("currentV:" + currentVelocity);
           // Logger.Log("deAccelerate: " + deAccelerate);
            //Logger.Log(backwards);
            
            double yaw = navigation.getYaw();
            double angleError = angle - yaw;
           // Logger.Log("AutonlineForward.Run() Angle = " + angle + " :  Yaw Value =  " + yaw);
            if(Math.abs(angleError) > (360.0 - 0.0)/2.0D) {
                angleError = angleError>0.0D ? angleError- 360.0+ 0.0 : angleError + 360.0 -0.0; 
            }
            //Logger.Log("angleError = " + angleError);

            double angleVelocityAddend = angleError * ANGLE_KP;
            angleVelocityAddend = Math.min(50, Math.max(angleVelocityAddend, -50));

           // Logger.Log("ANGLEADDEND:" + angleVelocityAddend);
            if(Math.abs(topSpeed - Math.abs(currentVelocity))<0.5) {
                currentAccel=0;
            }
            Logger.Log("TIME:" + System.currentTimeMillis());
            Logger.Log("AutonlineForward.Run() " + 
                        "   Distance =" + distanceTravelled + 
                        "   Time Need to stop = " + timeToStop +
                        "   Dist Need to stop = " + distanceNeededToStop +
                        "   DeAccelerate = " + deAccelerate + 
                        "   Angle = " + angle + 
                        "   Yaw Value =  " + yaw +
                        "   AngleError = " + angleError +
                        "   AngleVelocityAdded = " + angleVelocityAddend + 
                        "   CurrentVelocity = " + currentVelocity + 
                        "   CurrentAccel = " + currentAccel);

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
           
            double currentDistance = driveTrain.leftDistanceTravelled();
            if (Math.abs(currentDistance) > Math.abs(distance))
            {
                stop = true;
                Logger.Log("Stopping: " + currentDistance);
                Logger.Log("Stopping: " + distance);
            } 
            try
            {
                //long endProcessingTime = System.currentTimeMillis();
                //Thread.sleep((long) delT - (endProcessingTime - startProcessingTime));
                if(!deAccelerate )
                {
                    Thread.sleep(5);
                }
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