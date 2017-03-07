package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;

/**
 * Created by Baxter on 2/25/2017.
 */
public class AutoDepositLeftFromFarLeft extends SteamworksBaseCommandGroup {

    public AutoDepositLeftFromFarLeft() {
        super();
        addParallel(new ReceiveMandibles());
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FAR_INITIAL_DISTANCE, Constants.Auto.Drive.SPEED));
        addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FAR_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new AutoApproachTarget(Constants.Auto.Drive.SPEED));
        addSequential(new EjectMandibles());
        addSequential(new HoldMandiblesOpen(500));
        addParallel(new HoldMandiblesOpen(5000));
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, Constants.Auto.Drive.SPEED));
    }

}
