package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.HoldDust;
import org.frc5687.steamworks.protobot.commands.actions.HoldDustpan;
import org.frc5687.steamworks.protobot.commands.actions.RaiseDustpan;

/**
 * Command Group for stowing the dustpan in the upright closed position. This is the default command for the Pincers subsystem.
 */
public class StowDustpan extends CommandGroup {

    public StowDustpan() {
        addSequential(new HoldDust());
        addSequential(new RaiseDustpan());
        addSequential(new HoldDustpan());
    }

}
