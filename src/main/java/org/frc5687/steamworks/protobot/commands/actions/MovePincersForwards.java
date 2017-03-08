package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Command for moving the pincers slowly forwards until the button is released
 */
public class MovePincersForwards extends Command {

    public MovePincersForwards() {
        requires(pincers);
    }

    @Override
    protected void execute() {
        pincers.setPincerSpeed(Constants.Pincers.FORWARDS_SPEED);
    }

    @Override
    protected void end() {
        pincers.setPincerSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return !oi.isReleasePincersPressed();
    }
}
