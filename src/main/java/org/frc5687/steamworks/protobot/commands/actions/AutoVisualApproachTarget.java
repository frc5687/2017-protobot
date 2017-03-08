package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.utils.PiTrackerProxy;
import org.frc5687.steamworks.protobot.utils.TonyPose;

import static org.frc5687.steamworks.protobot.Robot.*;

public class AutoVisualApproachTarget extends Command {

    private double speed;
    private PIDController distanceController;
    private PIDController angleController;
    private PIDListener distancePID;
    private PIDListener anglePID;

    private double _previousOffsetAngle = -1000;
//    private double endTime;

    public AutoVisualApproachTarget(double speed) {
        requires(driveTrain);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        lights.turnRingLightOn();
        distancePID = new PIDListener();
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/kP", Constants.Auto.Drive.IRPID.kP);
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/kI", Constants.Auto.Drive.IRPID.kI);
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/kD", Constants.Auto.Drive.IRPID.kD);
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/kT", Constants.Auto.Drive.IRPID.TOLERANCE);
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/SetPoint", Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_IR_VOLTAGE);

        driveTrain.resetDriveEncoders();

        distanceController = new PIDController(Constants.Auto.Drive.IRPID.kP, Constants.Auto.Drive.IRPID.kI, Constants.Auto.Drive.IRPID.kD, driveTrain.getIrSensor(), distancePID);
        distanceController.setAbsoluteTolerance(Constants.Auto.Drive.IRPID.TOLERANCE);
        distanceController.setInputRange(0, 5.5);
        distanceController.setOutputRange(-speed, speed);
        distanceController.setSetpoint(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_IR_VOLTAGE);
        distanceController.enable();

        SmartDashboard.putNumber("AutoApproachTarget/AnglePID/kP", Constants.Auto.Drive.AnglePID.kP);
        SmartDashboard.putNumber("AutoApproachTarget/AnglePID/kI", Constants.Auto.Drive.AnglePID.kI);
        SmartDashboard.putNumber("AutoApproachTarget/AnglePID/kD", Constants.Auto.Drive.AnglePID.kD);

        anglePID = new PIDListener();
        angleController = new PIDController(Constants.Auto.Drive.AnglePID.kP, Constants.Auto.Drive.AnglePID.kI, Constants.Auto.Drive.AnglePID.kD, imu, anglePID);
        angleController.setInputRange(Constants.Auto.MIN_IMU_ANGLE, Constants.Auto.MAX_IMU_ANGLE);
        double maxSpeed = speed * Constants.Auto.Drive.AnglePID.MAX_DIFFERENCE;
        SmartDashboard.putNumber("AutoApproachTarget/AnglePID/RAISE_SPEED", maxSpeed);
        DriverStation.reportError("Turn PID Max Output: " + speed, false);
        angleController.setOutputRange(-maxSpeed, maxSpeed);
        angleController.setContinuous();
        angleController.setSetpoint(imu.getYaw());
        angleController.enable();

        DriverStation.reportError("AutoApproach initialized", false);
    }

    @Override
    protected void execute() {
        // See what we can find out from the piTracker...
        PiTrackerProxy.Frame frame = piTrackerProxy.getLatestFrame();
        if (frame!=null & frame.isSighted() && frame.getOffsetAngle()!=_previousOffsetAngle) {
            _previousOffsetAngle = frame.getOffsetAngle();
            TonyPose pose = (TonyPose)poseTracker.get(frame.getMillis());
            if (pose!=null) {
                double targetAngle = pose.getAngle() + _previousOffsetAngle;
                if (targetAngle > 180) { targetAngle-=360; }
                else if (targetAngle < -180) { targetAngle+=360; }
                synchronized (angleController) {
                    angleController.setSetpoint(targetAngle);
                    angleController.enable();
                }
            }
        }


        double distanceFactor = 0;
        double angleFactor = 0;
        synchronized (distancePID) {
            distanceFactor = distancePID.get();
        }

        synchronized (anglePID) {
            angleFactor = anglePID.get();
        }
        SmartDashboard.putNumber("AutoApproachTarget/distanceFactor", distanceFactor);
        SmartDashboard.putNumber("AutoApproachTarget/angleFactor", angleFactor);

        driveTrain.tankDrive(distanceFactor + angleFactor, distanceFactor - angleFactor);

        SmartDashboard.putBoolean("AutoApproachTarget/IRPID/onTarget", distanceController.onTarget());
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/voltage", driveTrain.getIrSensor().pidGet());
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/value", driveTrain.getIrSensor().getValue());
        SmartDashboard.putNumber("AutoApproachTarget/IRPID/error", distanceController.getError());
        SmartDashboard.putNumber("AutoApproachTarget/AnglePID/yaw", imu.getYaw());
        SmartDashboard.putNumber("AutoApproachTarget/AnglePID/value", anglePID.get());
    }

    @Override
    protected boolean isFinished() {
        return distanceController.onTarget() || driveTrain.getIrSensor().pidGet() >= Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_IR_VOLTAGE;
    }

    @Override
    protected void end() {
        DriverStation.reportError("AutoApproach Finished (" + driveTrain.getDistance() + ")", false);
        angleController.disable();
        driveTrain.tankDrive(0, 0);
        lights.turnRingLightOff();
    }

    private class PIDListener implements PIDOutput {

        private double value;

        public double get() {
            return value;
        }

        @Override
        public void pidWrite(double output) {
            synchronized (this) {
                value = output;
            }
        }

    }

}
