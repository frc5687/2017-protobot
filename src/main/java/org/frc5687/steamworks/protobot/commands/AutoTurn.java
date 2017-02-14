package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;

/**
 * Command to autonomously turn the robot in place to a specified angle relative to its initial position
 */
public class AutoTurn extends Command implements PIDOutput {

    private PIDController controller;
    private double target;

    public AutoTurn(double angle) {
        this.target = angle;
    }

    @Override
    protected void initialize() {
        controller = new PIDController(Constants.Drive.PID.AutoTurn.kP, Constants.Drive.PID.AutoTurn.kI, Constants.Drive.PID.AutoTurn.kD, driveTrain, this);
        controller.setInputRange(Constants.Drive.PID.AutoTurn.MIN_INPUT, Constants.Drive.PID.AutoTurn.MAX_INPUT);
        controller.setOutputRange(Constants.Drive.AUTONOMOUS_BACKWARDS_SPEED, Constants.Drive.AUTONOMOUS_FORWARDS_SPEED);
        controller.setAbsoluteTolerance(Constants.Drive.PID.AutoTurn.TOLERANCE);
        controller.setSetpoint(target);
        controller.setContinuous();
        controller.enable();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected void end() {
        driveTrain.stop();
        controller.disable();
    }

    @Override
    protected boolean isFinished() {
        return controller.onTarget();
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            driveTrain.tankDrive(-output, output); // positive output = counter clockwise
        }
    }
}
