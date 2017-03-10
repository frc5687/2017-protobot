package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class HoldDustpanDown extends Command {

    public HoldDustpanDown() {
        requires(dustpan);
    }

    @Override
    protected void execute() {
        dustpan.setLifterSpeed(-Constants.Dustpan.LIFTER_HOLD_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return !oi.isDeployPincersPressed();
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
