package org.frc5687.steamworks.protobot.commands.actions.drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.imu;

/**
 * Created by Ben Bernard on 3/25/2017.
 */
public class DriveArc extends Command {

    private double _leftSpeed;
    private double _rightSpeed;
    private long _millis = 0;
    private double _angle = 1000;
    private long _endMillis;
    private boolean _stopOnFinish = false;
    private boolean _clockwise;

    private DriveArc(double leftSpeed, double rightSpeed, boolean stopOnFinish) {
        _leftSpeed = leftSpeed;
        _rightSpeed = rightSpeed;
        _stopOnFinish = stopOnFinish;
        _clockwise = _leftSpeed > _rightSpeed;
    }

    public DriveArc(double leftSpeed, double rightSpeed, long millis, boolean stopOnFinish) {
        this(leftSpeed, rightSpeed, stopOnFinish);
        _millis = millis;
    }

    public DriveArc(double leftSpeed, double rightSpeed, double angle, boolean stopOnFinish) {
        this(leftSpeed, rightSpeed, stopOnFinish);
        _angle = angle;
    }

    public DriveArc(double leftSpeed, double rightSpeed, double angle, long millis, boolean stopOnFinish) {
        this(leftSpeed, rightSpeed, angle, stopOnFinish);
        _millis = millis;
    }

    @Override
    protected void initialize() {
        if (_millis > 0) {
            DriverStation.reportError("DriveArc started at " + _leftSpeed + ", " + _rightSpeed + " for " + _millis + " millis.", false);
            _endMillis = System.currentTimeMillis() + _millis;
        }
        if (_angle!=1000) {
            DriverStation.reportError("DriveArc started at " + _leftSpeed + ", " + _rightSpeed + " from " + imu.getYaw() + " to " + _angle, false);
        }
    }

    @Override
    protected void execute() {
        driveTrain.tankDrive(_leftSpeed, _rightSpeed);
    }

    @Override
    protected void end() {
        if (_stopOnFinish) {
            driveTrain.tankDrive(0,0);
        }
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        if (_millis>0 && System.currentTimeMillis() > _endMillis) {
            DriverStation.reportError("DriveArc timed out at " + _millis, false);
            return true;
        }
        if (_angle != 1000) {
            double yaw = imu.getYaw();
            if (_clockwise) {
                if (yaw >= _angle) {
                    DriverStation.reportError("DriveArc passed angle " + _angle + " to" + yaw, false);
                    return true;
                }
                DriverStation.reportError("Clockwise at " + yaw, false);
            } else {
                if (yaw <= _angle) {
                    DriverStation.reportError("DriveArc passed angle " + _angle + " to" + yaw, false);
                    return true;
                }
                DriverStation.reportError("Counterclockwise at " + yaw, false);
            }
        }
        return false;
    }
}
