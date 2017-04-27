package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.ReceiveMandibles;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;

/**
 * Created by Baxter on 2/25/2017.
 */
public class AutoDepositCenterFromLeft extends CommandGroup {

    public AutoDepositCenterFromLeft() {
        super();
        addParallel(new ReceiveMandibles());
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_NEAR_INITIAL_DISTANCE, Constants.Auto.Drive.SPEED, ""));
        addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_NEAR_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_NEAR_DIAGONAL_DISTANCE, Constants.Auto.Drive.SPEED, ""));
        addSequential(new AutoAlign(0, Constants.Auto.Align.SPEED));
        addSequential(new AutoApproachTarget(Constants.Auto.Drive.SPEED));
        addSequential(new EjectMandibles());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, Constants.Auto.Drive.SPEED, ""));
        // addParallel(new ReceiveMandibles());
    }

}
