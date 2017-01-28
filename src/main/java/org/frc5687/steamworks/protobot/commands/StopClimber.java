package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.climber;

/**
 * Created by Baxter on 1/28/2017.
 */
public class StopClimber extends Command{

    public StopClimber() {
        requires(climber);
    }

    public void initalize() {
        climber.stop();
    }

    public void execute() {
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {
    }

}
