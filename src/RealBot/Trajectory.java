package RealBot;

import java.util.ArrayList;

/**
 * Created by raque on 8/24/2017.
 */
public interface Trajectory {

    public static final double refreshRate = 100;

    public ArrayList<Moment> getMoments();
    public double getTotalTime();

}
