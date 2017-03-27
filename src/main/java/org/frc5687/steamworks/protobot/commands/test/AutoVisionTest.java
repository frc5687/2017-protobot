package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.lights.DisableRingLight;
import org.frc5687.steamworks.protobot.commands.actions.lights.EnableRingLight;

/**
 * Created by Ben Bernard on 3/12/2017.
 */
public class AutoVisionTest extends CommandGroup {
    public AutoVisionTest() {
        addSequential(new EnableRingLight());
        addSequential(new AutoAlignVision(.7));
        // addSequential(new AutoVisualApproachTarget(.5));
        addSequential(new DisableRingLight());
    }
}
