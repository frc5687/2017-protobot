package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command for controlling the drivetrain manually
 */
public class DriveWith2Joysticks extends Command {

    public DriveWith2Joysticks() {
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
        driveTrain.resetDriveEncoders();
    }

    @Override
    protected void execute() {
        driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
