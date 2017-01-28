package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.climber;

/**
 * Created by Baxter on 1/28/2017.
 */
public class DescendClimber extends Command {

    public DescendClimber() {
        requires(climber);
    }

    public void initalize() {
        climber.setSpeed(Constants.Climber.ASCEND_SPEED);
    }

    public void execute() {
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        climber.stop();
    }

    protected void interrupted() {
        end();
    }

}
