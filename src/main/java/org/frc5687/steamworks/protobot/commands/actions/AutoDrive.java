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
        DriverStation.reportError("Turn PID Max Output: " + speed, false);
        angleController.setOutputRange(-maxSpeed, maxSpeed);
        angleController.setContinuous();
        angleController.setSetpoint(imu.getAngle());
        angleController.enable();

        DriverStation.reportError("Auto Drive initialized", false);
    }

    @Override
    protected void execute() {
        if(!distanceController.onTarget()) endTime = System.currentTimeMillis() + Drive.STEADY_TIME;
        driveTrain.tankDrive(distancePID.get() + anglePID.get(), distancePID.get() - anglePID.get());

        SmartDashboard.putBoolean("AutoDrive/onTarget", distanceController.onTarget());
        SmartDashboard.putNumber("AutoDrive/imu", imu.getAngle());
        SmartDashboard.putNumber("AutoDrive/distance", driveTrain.pidGet());
        SmartDashboard.putNumber("AutoDrive/turnPID", anglePID.get());
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() >= endTime;
    }

    @Override
    protected void end() {
        DriverStation.reportError("AutoDrive Finished (" + driveTrain.getDistance() + ")", false);
        angleController.disable();
        driveTrain.tankDrive(0, 0);
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
