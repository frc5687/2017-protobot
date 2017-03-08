package org.frc5687.steamworks.protobot.utils;

/**
 * Created by Ben Bernard on 6/20/2016.
 */
public class TonyPose extends Pose {

    private double _angle;
    private double _rightEncoder;
    private double _leftEncoder;
    private double _distance;

    public TonyPose(double angle, double leftEncoder, double rightEncoder, double distance) {
        super();
        _angle = angle;
        _leftEncoder = leftEncoder;
        _rightEncoder = rightEncoder;
        _distance = distance;
    }

    public double getAngle() {
        return _angle;
    }

    public double getDistance() {
        return _distance;
    }

}
