package RealBot;

/**
 * Created by raque on 8/24/2017.
 */
public class Moment {
    public double x,y, angle;
    public double lVel, rVel;
    public double timeStamp;
    public String marker;


    public Moment(String marker, double timeStamp, double x, double y, double angle, double lVel, double rVel) {
        this.timeStamp=timeStamp;
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.lVel=lVel;
        this.rVel=rVel;
        this.marker=marker;

    }



}