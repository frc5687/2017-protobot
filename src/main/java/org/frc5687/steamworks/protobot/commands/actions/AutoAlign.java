package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.Auto.Align;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.imu;

/**
 * Autonomous command to turn to specified angle
 */
public class AutoAlign extends Command implements PIDOutput {

    private PIDController controller;
    private double endTime;
    private double angle;
    private double speed;

    private double pidOut;

    private long onTargetMillis;
    private long startTimeMillis;

    public AutoAlign(double angle, double speed) {
        requires(driveTrain);
        this.angle = angle;
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        controller = new PIDController(Align.kP, Align.kI, Align.kD, imu, this);
        controller.setPID(SmartDashboard.getNumber("DB/Slider 0", 0), SmartDashboard.getNumber("DB/Slider 1", 0), SmartDashboard.getNumber("DB/Slider 2", 0));
        controller.setInputRange(Constants.Auto.MIN_IMU_ANGLE, Constants.Auto.MAX_IMU_ANGLE);
        controller.setOutputRange(-speed, speed);
        controller.setAbsoluteTolerance(Align.TOLERANCE);
        controller.setAbsoluteTolerance(SmartDashboard.getNumber("DB/Slider 3", 180));
        controller.setContinuous();
        controller.setSetpoint(angle);
        controller.enable();
        DriverStation.reportError("AutoAlign initialized", false);
        startTimeMillis = System.currentTimeMillis();
    }

    @Override
    protected void execute() {
        // if(!controller.onTarget()) endTime = System.currentTimeMillis() + Align.STEADY_TIME;
//        DriverStation.reportError("Align: " + pidOut + "," + -pidOut, false);
        driveTrain.tankDrive(pidOut, -pidOut); // positive output is counterclockwise
        SmartDashboard.putBoolean("AutoAlign/onTarget", controller.onTarget());
        SmartDashboard.putNumber("AutoAlign/imu", imu.getYaw());
    }

    @Override
    protected boolean isFinished() {
        if (!controller.onTarget()) {
            onTargetMillis = 0;
            return false;
        }
        if (onTargetMillis == 0) {
            onTargetMillis = System.currentTimeMillis();
        }
        return System.currentTimeMillis() > onTargetMillis + Constants.Auto.Drive.ALIGN_STEADY_TIME;
    }

    @Override
    protected void end() {
        DriverStation.reportError("AutoAlign finished: angle = " + imu.getYaw() + ", time = " + (System.currentTimeMillis() - startTimeMillis), false);
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