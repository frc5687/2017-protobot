package org.frc5687.steamworks.protobot.commands.actions.drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.Auto.Drive;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.imu;

public class AutoDrive extends Command {

    private double distance;
    private double speed;
    private PIDController distanceController;
    private PIDController angleController;
    private PIDListener distancePID;
    private PIDListener anglePID;
    private double endMillis;
    private boolean usePID;
    private boolean stopOnFinish;

    public AutoDrive(double distance, double speed) {
        this(distance, speed, false, true, 0);
    }

    /***
     * Drives for a set distance at a set speed.
     * @param distance Distance to drive
     * @param speed Speed to drive
     * @param usePID Whether to use pid or not
     * @param stopOnFinish Whether to stop the motors when we are done
     */
    public AutoDrive(double distance, double speed, boolean usePID, boolean stopOnFinish, long maxMillis) {
        requires(driveTrain);
        this.speed = speed;
        this.distance = distance;
        this.usePID = usePID;
        this.stopOnFinish = stopOnFinish;
        this.endMillis = maxMillis == 0 ? Long.MAX_VALUE : System.currentTimeMillis() + maxMillis;
    }

    @Override
    protected void initialize() {
        driveTrain.resetDriveEncoders();
        if (usePID) {
            distancePID = new PIDListener();
            SmartDashboard.putNumber("AutoDrive/kP", Drive.EncoderPID.kP);
            SmartDashboard.putNumber("AutoDrive/kI", Drive.EncoderPID.kI);
            SmartDashboard.putNumber("AutoDrive/kD", Drive.EncoderPID.kD);
            SmartDashboard.putNumber("AutoDrive/kT", Drive.EncoderPID.TOLERANCE);

            distanceController = new PIDController(Drive.EncoderPID.kP, Drive.EncoderPID.kI, Drive.EncoderPID.kD, driveTrain, distancePID);
        //        distanceController.setPID(SmartDashboard.getNumber("DB/Slider 0", 0), SmartDashboard.getNumber("DB/Slider 1", 0), SmartDashboard.getNumber("DB/Slider 2", 0));
            distanceController.setAbsoluteTolerance(Drive.EncoderPID.TOLERANCE);
            distanceController.setOutputRange(-speed, speed);
            distanceController.setSetpoint(distance);
            distanceController.enable();
        }

        anglePID = new PIDListener();
        angleController = new PIDController(Drive.AnglePID.kP, Drive.AnglePID.kI, Drive.AnglePID.kD, imu, anglePID);
//        angleController.setPID(SmartDashboard.getNumber("DB/Slider 0", 0), SmartDashboard.getNumber("DB/Slider 1", 0), SmartDashboard.getNumber("DB/Slider 2", 0));
        angleController.setInputRange(Constants.Auto.MIN_IMU_ANGLE, Constants.Auto.MAX_IMU_ANGLE);
        double maxSpeed = speed * Drive.AnglePID.MAX_DIFFERENCE;
        SmartDashboard.putNumber("AutoDrive/angleMaxSpeed", maxSpeed);
        SmartDashboard.putNumber("AutoDrive/setPoint", imu.getYaw());
        angleController.setOutputRange(-maxSpeed, maxSpeed);
        angleController.setContinuous();
        angleController.setSetpoint(imu.getYaw());
        angleController.enable();

        DriverStation.reportError("Auto Drive initialized", false);
    }

    @Override
    protected void execute() {
        double distanceFactor = 0;
        double angleFactor = 0;
        if (usePID) {
            synchronized (distancePID) {
                distanceFactor = distancePID.get();
            }
        } else {
            distanceFactor = distance > 0 ? speed : -speed;
        }

        synchronized (anglePID) {
            angleFactor = anglePID.get();
        }
        SmartDashboard.putNumber("AutoDrive/distanceFactor", distanceFactor);
        SmartDashboard.putNumber("AutoDrive/angleFactor", angleFactor);

        driveTrain.tankDrive(distanceFactor + angleFactor, distanceFactor - angleFactor, true);

        SmartDashboard.putBoolean("AutoDrive/onTarget", distanceController == null ? false : distanceController.onTarget());
        SmartDashboard.putNumber("AutoDrive/imu", imu.getYaw());
        SmartDashboard.putNumber("AutoDrive/distance", driveTrain.pidGet());
        SmartDashboard.putNumber("AutoDrive/turnPID", anglePID.get());
    }

    @Override
    protected boolean isFinished() {
        if (System.currentTimeMillis() > endMillis) { return true; }
        if (usePID) {
            return distanceController.onTarget();
        } else {

            return distance == 0 ? true : distance < 0 ? (driveTrain.getDistance() < distance) : (driveTrain.getDistance() >  distance);
        }
    }

    @Override
    protected void end() {
        DriverStation.reportError("AutoDrive Finished (" + driveTrain.getDistance() + ", " + (imu.getYaw() - angleController.getSetpoint()) + ")", false);
        angleController.disable();
        if (stopOnFinish) {
            driveTrain.tankDrive(0, 0);
        }
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
