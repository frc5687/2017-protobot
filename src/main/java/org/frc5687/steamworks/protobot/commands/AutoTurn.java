package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;

/**
 * Command to autonomously turn the robot in place to a specified angle relative to its initial position
 */
public class AutoTurn extends Command implements PIDOutput {

    private PIDController controller;
    private double target;

    public AutoTurn(double angle) {
        target = angle;
    }

    @Override
    protected void initialize() {
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
            driveTrain.tankDrive(output, -output);
        }
    }
}
