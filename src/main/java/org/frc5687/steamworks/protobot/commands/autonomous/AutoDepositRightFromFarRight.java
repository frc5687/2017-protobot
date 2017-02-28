package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.ReceiveMandibles;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;
import org.frc5687.steamworks.protobot.commands.actions.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.AutoApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Baxter on 2/25/2017.
 */
public class AutoDepositRightFromFarRight extends SteamworksBaseCommandGroup {

    public AutoDepositRightFromFarRight() {
        super();
        addParallel(new ReceiveMandibles());
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FAR_INITIAL_DISTANCE, Constants.Auto.Drive.SPEED));
        addSequential(new AutoAlign(-Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FAR_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new AutoApproachTarget(Constants.Auto.Drive.SPEED));
        addSequential(new EjectMandibles());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, Constants.Auto.Drive.SPEED));
    }

}
