package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants.Drive.PID.PIDDrive;
import org.frc5687.steamworks.protobot.Robot;

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
        controller = new PIDController(PIDDrive.kP, PIDDrive.kI, PIDDrive.kD, Robot.driveTrain, this);
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    public void pidWrite(double output) {

    }
}
