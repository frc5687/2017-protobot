package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.CollectDust;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.HoldDustpanDown;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.LowerDustpan;

/**
 * Command group for lowering and opening the dustpan to intake a gear
 */
public class DeployDustpan extends CommandGroup {

    public DeployDustpan() {
        addSequential(new LowerDustpan());
        addSequential(new CollectDust());
        addSequential(new SetLEDStrip(LEDColors.DUSTPAN_DEPLOYED));
        addSequential(new HoldDustpanDown());
    }

}