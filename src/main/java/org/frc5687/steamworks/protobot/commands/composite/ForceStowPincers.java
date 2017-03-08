package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.HoldPincers;
import org.frc5687.steamworks.protobot.commands.actions.OpenPincers;
import org.frc5687.steamworks.protobot.commands.actions.OverdrivePincers;
import org.frc5687.steamworks.protobot.commands.actions.RaisePincers;

public class ForceStowPincers extends CommandGroup {

    public ForceStowPincers() {
        addSequential(new OverdrivePincers());
        addSequential(new RaisePincers());
        addSequential(new HoldPincers());
    }

}
