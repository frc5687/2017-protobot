package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.DisableRingLight;
import org.frc5687.steamworks.protobot.commands.actions.EnableRingLight;
import org.frc5687.steamworks.protobot.commands.actions.VisionTest;

/**
 * Created by Ben Bernard on 3/12/2017.
 */
public class AutoVisionTest extends CommandGroup {
    public AutoVisionTest() {
        addSequential(new EnableRingLight());
        addSequential(new VisionTest());
        addSequential(new DisableRingLight());
    }
}
