package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.Auto.Align;
import org.frc5687.steamworks.protobot.utils.PiTrackerProxy;
import org.frc5687.steamworks.protobot.utils.TonyPose;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Autonomous command to turn to specified angle
 */
public class AutoAlignVision extends Command implements PIDOutput {

    private PIDController controller;
    private long endTime;
    private double speed;
    private double _previousOffsetAngle;
    private double pidOut;

    public AutoAlignVision(double speed) {
        requires(driveTrain);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        endTime = System.currentTimeMillis() + 3000;
        _previousOffsetAngle = imu.getYaw();
        controller = new PIDController(Align.kP, Align.kI, Align.kD, imu, this);
        controller.setInputRange(Constants.Auto.MIN_IMU_ANGLE, Constants.Auto.MAX_IMU_ANGLE);
        controller.setOutputRange(-speed, speed);
        controller.setAbsoluteTolerance(Align.TOLERANCE);
        controller.setContinuous();
        controller.setSetpoint(_previousOffsetAngle);
        controller.enable();
        DriverStation.reportError("AutoAlign initialized", false);
    }

    @Override
    protected void execute() {

        PiTrackerProxy.Frame frame = piTrackerProxy.getLatestFrame();
        if (frame!=null && frame.isSighted() && Math.abs(frame.getOffsetAngle()-_previousOffsetAngle) > Constants.Auto.Drive.AnglePID.TOLERANCE) {
            _previousOffsetAngle = frame.getOffsetAngle();
            TonyPose pose = (TonyPose)poseTracker.get(frame.getMillis());
            if (pose!=null) {
                double poseAngle = pose.getAngle();
                SmartDashboard.putNumber("AutoAlignVision/poseAngle", poseAngle);
                SmartDashboard.putNumber("AutoAlignVision/offsetAngle", _previousOffsetAngle);

                double targetAngle = poseAngle + _previousOffsetAngle;
                SmartDashboard.putNumber("AutoAlignVision/targetAngle", targetAngle);

                if (targetAngle > 180) { targetAngle-=360; }
                else if (targetAngle < -180) { targetAngle+=360; }
                synchronized (controller) {
                    DriverStation.reportError("AutoAlignVision retargeting angle " + targetAngle, false);
                    controller.reset();
                    controller.setSetpoint(targetAngle);
                    controller.enable();
                }
            }
        }


        // if(!controller.onTarget()) endTime = System.currentTimeMillis() + Align.STEADY_TIME;
        // DriverStation.reportError("Align: " + pidOut + "," + -pidOut, false);
        driveTrain.tankDrive(pidOut, -pidOut); // positive output is counterclockwise
        SmartDashboard.putBoolean("AutoAlignVision/onTarget", controller.onTarget());
        SmartDashboard.putNumber("AutoAlignVision/imu", imu.getYaw());
        SmartDashboard.putNumber("AutoAlignVision/pidOut", pidOut);
        SmartDashboard.putData("VisionPid", controller);
    }

    @Override
    protected boolean isFinished() {
        return false;// System.currentTimeMillis() > endTime && controller.onTarget();
    }

    @Override
    protected void end() {
        DriverStation.reportError("AutoAlign finished (" + imu.getYaw() + ")", false);
        controller.disable();
        driveTrain.tankDrive(0,0);
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            pidOut = output;
        }
    }

}