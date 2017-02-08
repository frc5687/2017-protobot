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
public class AutoDriveToWall extends Command {


    public AutoDriveToWall() {
    requires(driveTrain);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
    driveTrain.tankDrive(Constants.Drive.AUTONOMOUS_FORWARDS_SPEED,Constants.Drive.AUTONOMOUS_FORWARDS_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return (driveTrain.getDistanceToWall() < Constants.Drive.DISTANCE_TO_WALL);
        // if IR sensoris used instead of ultrasonic make
    }

    @Override
    protected void end() {

    }
}
