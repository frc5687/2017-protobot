package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoVisualApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.lights.DisableRingLight;
import org.frc5687.steamworks.protobot.commands.actions.lights.EnableRingLight;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.HoldMandiblesOpen;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;

/**
 * Created by Ben Bernard on 3/12/2017.
 */
public class AutoVisionTest extends CommandGroup {
    public AutoVisionTest() {
        addSequential(new EnableRingLight());
        addSequential(new AutoAlignVision(.7));
        // addSequential(new AutoVisualApproachTarget(.5, 1000));
        // addSequential(new EjectMandibles());
        // addSequential(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.PAUSE_AT_SPRING_TIME));
        // addParallel(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.MANDIBLE_HOLD_TIME));
        // addSequential(new AutoDrive(-48, Constants.Auto.Drive.SPEED, false, true, 5000));
        addSequential(new DisableRingLight());
    }
}
