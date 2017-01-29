package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command for manually driving the robot
 */
public class DriveWith2Joysticks extends Command {

    /*
     * Constructor
     */
    public DriveWith2Joysticks() {
        requires(driveTrain);
    }

    /*
     * Sets up the command
     * Called just before this Command runs the first time(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#initialize()
     */
    protected void initialize() {
        driveTrain.resetDriveEncoders();
    }

    /*
     * Executes the command
     * Called repeatedly when this Command is scheduled to run(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#execute()
     */
    protected void execute() {
        if(oi.isLeftTriggerPressed()) driveTrain.tankDrive(Constants.Drive.FULL_FORWARDS_SPEED);
        else if (oi.isRightTriggerPressed()) driveTrain.tankDrive(Constants.Drive.FULL_BACKWARDS_SPEED);
        else driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
    }

    /*
     * Check if this command is finished running
     * Make this return true when this Command no longer needs to run execute()(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#isFinished()
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /*
     * Command execution clean-up
     * Called once after isFinished returns true(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#end()
     */
    protected void end() {
    }

    /*
     * Handler for when command is interrupted
     * Called when another command which requires one or more of the same(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#interrupted()
     */
    protected void interrupted() {
    }

}
