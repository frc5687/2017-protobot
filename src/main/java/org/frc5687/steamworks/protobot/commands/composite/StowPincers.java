package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.ClosePincers;
import org.frc5687.steamworks.protobot.commands.actions.HoldPincers;
import org.frc5687.steamworks.protobot.commands.actions.RaisePincers;
import org.frc5687.steamworks.protobot.commands.actions.Rest;

import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Command Group for stowing the pincers in the upright closed position. This is the default command for the Pincers subsystem.
 */
public class StowPincers extends CommandGroup {

    public StowPincers() {
        addSequential(new ClosePincers());
        addSequential(new RaisePincers());
        addSequential(new HoldPincers());
    }

}
