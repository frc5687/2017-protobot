package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.EjectDust;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.MoveDustpanForwards;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.TimeoutDustpanRollers;

import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command group for opening the dustpan while upright, releasing the gear onto the peg.
 */
public class EjectDustpan extends CommandGroup {

    public EjectDustpan() {
        addParallel(new MoveDustpanForwards());
        addSequential(new TimeoutDustpanRollers(Constants.Dustpan.EJECT_ROLLERS_DELAY));
        addParallel(new EjectDust());
    }

    @Override
    protected boolean isFinished() {
        return !oi.isReleasePincersPressed();
    }
}
