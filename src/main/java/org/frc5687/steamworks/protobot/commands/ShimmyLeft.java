package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;

/**
 * Created by Caleb on 2/11/2017.
 */
public class ShimmyLeft extends Command {
    public ShimmyLeft() {
        requires(driveTrain);
    }

    private long endBackLeft;
    private long endBackRight;
    private long endFrontLeft;
    private long endFrontRight;


    protected void initialize() {
        endBackLeft = System.currentTimeMillis() + Constants.Drive.SHIMMY_TIME;
        endBackRight = System.currentTimeMillis() + 2*(Constants.Drive.SHIMMY_TIME);
        endFrontLeft = System.currentTimeMillis() + 3*(Constants.Drive.SHIMMY_TIME);
        endFrontRight = System.currentTimeMillis() + 4*(Constants.Drive.SHIMMY_TIME);
    }

    protected void execute(){
        driveTrain.tankDrive(Constants.Drive.SHIMMY_SPEED,0);
    }

    protected boolean isFinished() {
        return false;
    }
}