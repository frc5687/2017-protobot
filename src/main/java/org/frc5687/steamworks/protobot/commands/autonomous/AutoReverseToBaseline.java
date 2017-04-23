package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;

public class AutoReverseToBaseline extends SteamworksBaseCommandGroup {

    public AutoReverseToBaseline() {
        super();
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.CROSS_BASELINE_DISTANCE, -Constants.Auto.Drive.SPEED, "Reverse to Baseline"));
    }

}
