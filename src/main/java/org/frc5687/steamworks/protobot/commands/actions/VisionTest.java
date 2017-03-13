package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.utils.PiTrackerProxy;
import org.frc5687.steamworks.protobot.utils.TonyPose;

import static org.frc5687.steamworks.protobot.Robot.piTrackerProxy;
import static org.frc5687.steamworks.protobot.Robot.poseTracker;

/**
 * Created by Ben Bernard on 3/12/2017.
 */
public class VisionTest extends Command {

    @Override
    protected boolean isFinished() {
        PiTrackerProxy.Frame frame = piTrackerProxy.getLatestFrame();

        return frame!=null && frame.getDistance() < 12.0;
    }

    @Override
    protected void execute() {
        PiTrackerProxy.Frame frame = piTrackerProxy.getLatestFrame();
        if (frame==null) { return; }

        SmartDashboard.putString("VisionTest/Frame/Millis", Long.toString(frame.getMillis()));
        SmartDashboard.putNumber("VisionTest/Frame/Angle", frame.getOffsetAngle());

        TonyPose pose = (TonyPose)poseTracker.get(frame.getMillis());
        if (pose ==null) { return; }

        SmartDashboard.putString("VisionTest/Pose/Millis", Long.toString(pose.getMillis()));
        SmartDashboard.putNumber("VisionTest/Pose/Angle", pose.getAngle());
        SmartDashboard.putNumber("VisionTest/NetAngle", pose.getAngle() + frame.getOffsetAngle());

    }
}