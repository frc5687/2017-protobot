package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.OpenMandibles;
import org.frc5687.steamworks.protobot.commands.actions.AutoApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

public class AutoDepositGear extends SteamworksBaseCommandGroup {

    public AutoDepositGear() {
        super();
        addSequential(new AutoApproachTarget(Constants.Auto.Drive.SPEED));
        addSequential(new OpenMandibles());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, Constants.Auto.Drive.SPEED));
    }

}