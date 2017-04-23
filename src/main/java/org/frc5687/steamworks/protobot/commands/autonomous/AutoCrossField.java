package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;

public class AutoCrossField extends SteamworksBaseCommandGroup {

    public AutoCrossField() {
        super();
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_WALL_DISTANCE, Constants.Auto.Drive.SPEED, 5000, "Coss Field"));
    }

}
