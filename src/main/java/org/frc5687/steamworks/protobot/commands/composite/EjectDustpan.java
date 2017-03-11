package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.EjectDust;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.MovePincersForwards;

/**
 * Command group for opening the dustpan while upright, releasing the gear onto the peg.
 */
public class EjectDustpan extends CommandGroup {

    public EjectDustpan() {
        addSequential(new EjectDust());
        addSequential(new SetLEDStrip(LEDColors.DUSTPAN_EJECT));
        addSequential(new MovePincersForwards());
    }

}
