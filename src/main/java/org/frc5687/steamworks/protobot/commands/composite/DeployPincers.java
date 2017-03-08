package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.OI;
import org.frc5687.steamworks.protobot.commands.actions.*;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Command group for lowering and opening the pincers to intake a gear
 */
public class DeployPincers extends CommandGroup {

    public DeployPincers() {
        addSequential(new LowerPincers());
        addSequential(new OpenPincers());
        addSequential(new SetLEDStrip(LEDColors.PINCERS_DEPLOYED));
        addSequential(new HoldPincersDown());
    }

}