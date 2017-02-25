package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Robot;
import org.frc5687.steamworks.protobot.commands.CloseMandibles;
import org.frc5687.steamworks.protobot.commands.OpenMandibles;
import org.frc5687.steamworks.protobot.commands.actions.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.AutoApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Baxter on 2/25/2017.
 */
public class AutoDepositCenterFromLeft extends SteamworksBaseCommandGroup {

    public AutoDepositCenterFromLeft() {
        super();
        addParallel(new CloseMandibles());
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_NEAR_INITIAL_DISTANCE, Constants.Auto.Drive.SPEED));
        addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_NEAR_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_NEAR_DIAGONAL_DISTANCE, Constants.Auto.Drive.SPEED));
        addSequential(new AutoAlign(0, Constants.Auto.Align.SPEED));
        addSequential(new AutoApproachTarget(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_IR_DISTANCE, Constants.Auto.Drive.SPEED));
        addSequential(new OpenMandibles());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, Constants.Auto.Drive.SPEED));
    }

}
