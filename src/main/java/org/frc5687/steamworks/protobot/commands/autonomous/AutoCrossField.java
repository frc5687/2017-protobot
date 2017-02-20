package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Baxter on 2/17/2017.
 */
public class AutoCrossField extends SteamworksBaseCommandGroup {

    public AutoCrossField() {
        super();
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.CROSS_FIELD_DISTANCE, Constants.Auto.Drive.SPEED));
    }

}
