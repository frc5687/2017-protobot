package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.Drive.PID.PIDDrive;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;

/**
 * Command to autonomously drive the robot a specified distance, in feet
 */
public class AutoDrive extends Command implements PIDOutput {

    private PIDController controller;
    private double target;

    public AutoDrive(double distance) {
        target = distance;
    }

    @Override
    protected void initialize() {
        controller = new PIDController(PIDDrive.kP, PIDDrive.kI, PIDDrive.kD, driveTrain, this);
        controller.setInputRange(PIDDrive.MIN_INPUT, PIDDrive.MAX_INPUT);
        controller.setOutputRange(Constants.Drive.AUTONOMOUS_BACKWARDS_SPEED, Constants.Drive.AUTONOMOUS_FORWARDS_SPEED);
        controller.setAbsoluteTolerance(PIDDrive.TOLERANCE);
        controller.setSetpoint(target);
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
