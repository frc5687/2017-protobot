package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.OI;
import org.frc5687.steamworks.protobot.commands.actions.WaitForButtonRelease;
import org.frc5687.steamworks.protobot.commands.actions.LowerPincers;
import org.frc5687.steamworks.protobot.commands.actions.OpenPincers;
import org.frc5687.steamworks.protobot.commands.actions.SetLEDStrip;

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
        addSequential(new WaitForButtonRelease(pincers, OI.OC_DEPLOY_PINCERS, OI.GP_DEPLOY_PINCERS));
    }

}