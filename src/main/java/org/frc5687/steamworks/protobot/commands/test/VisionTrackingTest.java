package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.utils.PiTrackerProxy;
import org.frc5687.steamworks.protobot.utils.TonyPose;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Created by Ben Bernard on 4/6/2017.
 */
public class VisionTrackingTest extends Command {
    double _previousOffsetAngle;

    public VisionTrackingTest() {
    }

    @Override
    protected void initialize() {
        _previousOffsetAngle = imu.getYaw();
    }

    @Override
    protected void execute() {

        PiTrackerProxy.Frame frame = piTrackerProxy.getLatestFrame();
        if (frame!=null && frame.isSighted() && Math.abs(frame.getOffsetAngle()-_previousOffsetAngle) > Constants.Auto.Drive.AnglePID.TOLERANCE) {
            _previousOffsetAngle = frame.getOffsetAngle();
            TonyPose pose = (TonyPose)poseTracker.get(frame.getMillis());
            if (pose!=null) {
                double poseAngle = pose.getAngle();
                SmartDashboard.putNumber("VisionTrackingTest/poseAngle", poseAngle);
                SmartDashboard.putNumber("VisionTrackingTest/offsetAngle", _previousOffsetAngle);

                double targetAngle = poseAngle + _previousOffsetAngle;
                SmartDashboard.putNumber("VisionTrackingTest/targetAngle", targetAngle);

                if (targetAngle > 180) { targetAngle-=360; }
                else if (targetAngle < -180) { targetAngle+=360; }
            }
        }
        SmartDashboard.putNumber("VisionTrackingTest/imu", imu.getYaw());
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
