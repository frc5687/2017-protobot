package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.HoldDustpanUp;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.RaiseDustpan;

/**
 * Created by Baxter on 3/11/2017.
 */
public class StowDustpan extends CommandGroup {

    public StowDustpan() {
        addSequential(new RaiseDustpan());
        addSequential(new HoldDustpanUp());
    }

}
