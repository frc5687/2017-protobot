package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.*;

/**
 * Command group for opening the dustpan while upright, releasing the gear onto the peg.
 */
public class ReleasePincers extends CommandGroup {

    public ReleasePincers() {
        addSequential(new CollectDust());
        addSequential(new SetLEDStrip(LEDColors.DUSTPAN_EJECT));
        addSequential(new MovePincersForwards());
    }

}
