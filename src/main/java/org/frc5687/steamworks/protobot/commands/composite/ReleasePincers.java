package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.OI;
import org.frc5687.steamworks.protobot.commands.actions.*;

import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Command group for opening the pincers while upright, releasing the gear onto the peg.
 */
public class ReleasePincers extends CommandGroup {

    public ReleasePincers() {
        addSequential(new OpenPincers());
        addSequential(new SetLEDStrip(LEDColors.PINCERS_OPEN));
        addSequential(new MovePincersForwards());
    }

}
