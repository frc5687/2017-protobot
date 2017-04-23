package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;

public class AutoCrossBaseline extends SteamworksBaseCommandGroup {

    public AutoCrossBaseline() {
        super();
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.CROSS_BASELINE_DISTANCE, Constants.Auto.Drive.SPEED, 5000, "Cross Baseline"));
    }

}
