package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;

/**
 * Created by Baxter on 2/17/2017.
 */
public class AutoReverseToBaseline extends AutoDrive {

    public AutoReverseToBaseline() {
        super(-Constants.Auto.AnglesAndDistances.CROSS_BASELINE_DISTANCE, -Constants.Auto.Drive.SPEED);
    }

}
