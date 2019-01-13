package RealBot;

import java.util.ArrayList;

public class TrajectoryFactory
{
    public static Trajectory getTrajectory(Object[][] moments)
    {
        ArrayList<Moment>momentArraylist = new ArrayList<>();
        int i=0;
        for(Object[] moment : moments)
        {
            System.out.println(i++);
            double timestamp = (double) moment[0];
            double leftVelocity = (double) moment[1];
            double rightVelocity = (double) moment[2];
            double gyro = (double) moment[3];
            Moment m = new Moment("", (double) moment[0], 0.0, 0.0, 
                    gyro,
                    leftVelocity, 
                    rightVelocity);
            momentArraylist.add(m);
        }
        
        
        Trajectory t = new Trajectory(){

            @Override
            public ArrayList<Moment> getMoments()
            {
                return momentArraylist;
            }

            //dont worry about this quite yet
            @Override
            public double getTotalTime()
            {
         
                return 0;
            }
            
        };
        return t;
    }
}
