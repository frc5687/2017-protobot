package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.utils.PiTrackerProxy;
import org.frc5687.steamworks.protobot.utils.TonyPose;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Created by Ben Bernard on 3/12/2017.
 */
public class VisionTest extends Command {

    private State _state;
    private long _endMillis;
    private boolean _frameReceived;
    private boolean _targetSighted;
    private double _angle;

    @Override
    protected void initialize() {
        _state = State.FIND_TARGET;
        _endMillis = System.currentTimeMillis() + 10000;
        _targetSighted = false;
        _frameReceived = false;
        _angle = 1000;
        lights.turnRingLightOn();
    }

    @Override
    protected boolean isFinished() {
        return  _state == State.DONE;
    }

    @Override
    protected void end() {
        SmartDashboard.putBoolean("SelfTest/Vision/Frame Received", _frameReceived);
        SmartDashboard.putBoolean("SelfTest/Vision/Target Sighted", _targetSighted);
        SmartDashboard.putNumber("SelfTest/Vision/Target Angle", _angle);
        if (!_targetSighted) {
            DriverStation.reportError("Vision tracking failed locate target.", false);
            ledStrip.setStripColor(LEDColors.TEST_FAILED);
        } else {
            DriverStation.reportError("Vision tracking located target at " + _angle, false);
            ledStrip.setStripColor(LEDColors.TEST_PASSED);
        }
        lights.turnRingLightOff();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void execute() {
        PiTrackerProxy.Frame frame = piTrackerProxy.getLatestFrame();
        switch (_state) {
            case FIND_TARGET:
                if (frame!=null) {
                    _frameReceived = true;
                    if (frame.isSighted()) {
                        _targetSighted = true;
                        _angle = frame.getOffsetAngle();
                        _state = State.MONITOR;
                    }
                }
                if (System.currentTimeMillis() > _endMillis) {
                    _endMillis = System.currentTimeMillis() + 1000;
                    _state = State.DONE;
                }
                break;
            case MONITOR:
                if (frame!=null && frame.isSighted()) {
                    _angle = frame.getOffsetAngle();
                    TonyPose pose = (TonyPose)poseTracker.get(frame.getMillis());
                    if (pose !=null) {
                        SmartDashboard.putString("SelfTest/Vision/Frame/Millis", Long.toString(frame.getMillis()));
                        SmartDashboard.putString("SelfTest/Vision/Frame/Angle", Double.toString(frame.getOffsetAngle()));
                        SmartDashboard.putString("SelfTest/Vision/Pose/Millis", Long.toString(pose.getMillis()));
                        SmartDashboard.putString("SelfTest/Vision/Pose/Angle", Double.toString(pose.getAngle()));
                        SmartDashboard.putString("SelfTest/Vision/Pose/NetAngle", Double.toString(pose.getAngle() + frame.getOffsetAngle()));
                    }
                }
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.DONE;
                }
                break;
        }
    }


    private enum State {
        FIND_TARGET,
        MONITOR,
        DONE
    }
}