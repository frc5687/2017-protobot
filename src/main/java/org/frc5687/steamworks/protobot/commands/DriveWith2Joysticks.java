package org.frc5687.steamworks.protobot.commands;

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

    protected void initialize() {
        driveTrain.resetDriveEncoders();
    }

    protected void execute() {
        if(oi.isLeftTriggerPressed()) driveTrain.tankDrive(Constants.Drive.FULL_FORWARDS_SPEED);
        else if (oi.isRightTriggerPressed()) driveTrain.tankDrive(Constants.Drive.FULL_BACKWARDS_SPEED);
        else driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

}
