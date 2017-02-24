package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.OpenMandibles;
import org.frc5687.steamworks.protobot.commands.actions.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Ben Bernard on 2/18/2017.
 */
public class AutoDepositLeft extends SteamworksBaseCommandGroup {

    public AutoDepositLeft () {
        super();
        addSequential(new AutoDrive(91.5, Constants.Auto.Drive.SPEED));
        addSequential(new AutoAlign(60));
        addSequential(new AutoDrive(48, Constants.Auto.Drive.SPEED));
        addSequential(new OpenMandibles());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, -Constants.Auto.Drive.SPEED));
    }

}
