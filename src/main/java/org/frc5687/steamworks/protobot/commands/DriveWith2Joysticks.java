package org.frc5687.steamworks.protobot.commands;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.OI;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Ben Bernard on 1/13/2017.
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
        if(oi.isLeftTriggerPressed()) driveTrain.tankDrive(Constants.Drive.FULL_SPEED);
        else if (oi.isRightTriggerPressed()) driveTrain.tankDrive(-Constants.Drive.FULL_SPEED);
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
