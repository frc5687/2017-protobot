package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import org.frc5687.steamworks.protobot.Constants.Drive.PID.PIDDriveToWall;

/**
 * Command to autonomously drive the robot a specified distance, in feet
 */
public class AutoDriveToWall extends Command implements PIDOutput {

    private PIDController controller;

    public AutoDriveToWall() {
    }

    @Override
    protected void initialize() {
        controller = new PIDController(PIDDriveToWall.kP, PIDDriveToWall.kI, PIDDriveToWall.kD, driveTrain.getDistanceSensor(), this);
        controller.setInputRange(PIDDriveToWall.MIN_INPUT, PIDDriveToWall.MAX_INPUT);
        controller.setOutputRange(Constants.Drive.AUTONOMOUS_BACKWARDS_SPEED, Constants.Drive.AUTONOMOUS_FORWARDS_SPEED);
        controller.setAbsoluteTolerance(PIDDriveToWall.TOLERANCE);
        controller.setSetpoint(PIDDriveToWall.TARGET);
        controller.enable();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return controller.onTarget();
    }

    @Override
    protected void end() {
        driveTrain.stop();
        controller.disable();
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            driveTrain.tankDrive(output, output);
        }
    }
}
