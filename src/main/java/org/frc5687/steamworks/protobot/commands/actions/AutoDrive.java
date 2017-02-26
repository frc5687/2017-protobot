package org.frc5687.steamworks.protobot.commands.actions;

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
    private double endTime;

    public AutoDrive(double distance, double speed) {
        requires(driveTrain);
        this.speed = speed;
        this.distance = distance;
    }

    @Override
    protected void initialize() {
        driveTrain.resetDriveEncoders();
        distancePID = new PIDListener();
        SmartDashboard.putNumber("AutoDrive/kP", Drive.DistancePID.kP);
        SmartDashboard.putNumber("AutoDrive/kI", Drive.DistancePID.kI);
        SmartDashboard.putNumber("AutoDrive/kD", Drive.DistancePID.kD);
        SmartDashboard.putNumber("AutoDrive/kT", Drive.DistancePID.TOLERANCE);

        distanceController = new PIDController(Drive.DistancePID.kP, Drive.DistancePID.kI, Drive.DistancePID.kD, driveTrain, distancePID);
//        distanceController.setPID(SmartDashboard.getNumber("DB/Slider 0", 0), SmartDashboard.getNumber("DB/Slider 1", 0), SmartDashboard.getNumber("DB/Slider 2", 0));
        distanceController.setAbsoluteTolerance(Drive.DistancePID.TOLERANCE);
        distanceController.setOutputRange(-speed, speed);
        distanceController.setSetpoint(distance);
        distanceController.enable();

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
        synchronized (distancePID) {
            distanceFactor = distancePID.get();
        }

        synchronized (anglePID) {
            angleFactor = anglePID.get();
        }
        SmartDashboard.putNumber("AutoDrive/distanceFactor", distanceFactor);
        SmartDashboard.putNumber("AutoDrive/angleFactor", angleFactor);

        if(!distanceController.onTarget()) endTime = System.currentTimeMillis() + Drive.STEADY_TIME;
        driveTrain.tankDrive(distanceFactor + angleFactor, distanceFactor - angleFactor, true);

        SmartDashboard.putBoolean("AutoDrive/onTarget", distanceController.onTarget());
        SmartDashboard.putNumber("AutoDrive/imu", imu.getYaw());
        SmartDashboard.putNumber("AutoDrive/distance", driveTrain.pidGet());
        SmartDashboard.putNumber("AutoDrive/turnPID", anglePID.get());
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() >= endTime;
    }

    @Override
    protected void end() {
        DriverStation.reportError("AutoDrive Finished (" + driveTrain.getDistance() + ", " + (imu.getYaw() - angleController.getSetpoint()) + ")", false);
        angleController.disable();
        // driveTrain.tankDrive(0, 0);
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
