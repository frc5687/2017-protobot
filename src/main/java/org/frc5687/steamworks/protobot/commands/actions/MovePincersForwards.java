package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Command for moving the dustpan slowly forwards until the button is released
 */
public class MovePincersForwards extends Command {

    public MovePincersForwards() {
        requires(dustpan);
    }

    @Override
    protected void execute() {
        dustpan.setLifterSpeed(Constants.Dustpan.FORWARDS_SPEED);
    }

    @Override
    protected void end() {
        dustpan.setLifterSpeed(0);
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
