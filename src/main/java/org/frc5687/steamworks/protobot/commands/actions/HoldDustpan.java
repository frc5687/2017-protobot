package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class HoldDustpan extends Command {

    public HoldDustpan() {
        requires(dustpan);
    }

    @Override
    protected void execute() {
        dustpan.setLifterSpeed(Constants.Dustpan.LIFTER_HOLD_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        dustpan.setLifterSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
