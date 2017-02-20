package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Baxter on 2/17/2017.
 */
public class AutoReverseToBaseline extends SteamworksBaseCommandGroup {

    public AutoReverseToBaseline() {
        super();
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.CROSS_BASELINE_DISTANCE, -Constants.Auto.Drive.SPEED));
    }

}
