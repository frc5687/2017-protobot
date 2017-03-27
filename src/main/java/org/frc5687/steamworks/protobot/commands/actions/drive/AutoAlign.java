package org.frc5687.steamworks.protobot.commands.actions.drive;

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
        double kP = Align.kP; // Double.parseDouble(SmartDashboard.getString("DB/String 0", ".04"));
        double kI = Align.kI; // Double.parseDouble(SmartDashboard.getString("DB/String 1", ".006"));
        double kD = Align.kD; //Double.parseDouble(SmartDashboard.getString("DB/String 2", ".09"));

        controller = new PIDController(kP, kI, kD, imu, this);
        controller.setInputRange(Constants.Auto.MIN_IMU_ANGLE, Constants.Auto.MAX_IMU_ANGLE);
        controller.setOutputRange(-speed, speed);
        controller.setAbsoluteTolerance(Align.TOLERANCE);
        controller.setContinuous();
        controller.setSetpoint(angle);
        controller.enable();
        DriverStation.reportError("AutoAlign initialized to " + angle + " at " + speed, false);
        DriverStation.reportError("kP="+kP+" , kI="+kI+", kD="+kD + ",T="+ Align.TOLERANCE, false);
        startTimeMillis = System.currentTimeMillis();
    }

    @Override
    protected void execute() {
        // if(!controller.onTarget()) endTime = System.currentTimeMillis() + Align.STEADY_TIME;
//        DriverStation.reportError("Align: " + pidOut + "," + -pidOut, false);
        driveTrain.tankDrive(pidOut, -pidOut); // positive output is counterclockwise
        SmartDashboard.putBoolean("AutoAlign/onTarget", controller.onTarget());
        SmartDashboard.putNumber("AutoAlign/imu", imu.getYaw());
        SmartDashboard.putData("AutoAlign/pid", controller);
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
            SmartDashboard.putNumber("AutoAlign/pidOut", pidOut);
        }
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}