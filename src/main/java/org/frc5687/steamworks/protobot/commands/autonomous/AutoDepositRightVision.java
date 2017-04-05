package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoVisualApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.HoldMandiblesOpen;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;

/**
 * Created by Baxter on 2/25/2017.
 */
public class AutoDepositRightVision extends CommandGroup {

    public AutoDepositRightVision() {
        super();
        // addParallel(new ReceiveMandibles());
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FAR_INITIAL_DISTANCE, Constants.Auto.Drive.SPEED));
        addSequential(new AutoAlign(-Constants.Auto.AnglesAndDistances.DEPOSIT_GEAR_FAR_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new AutoVisualApproachTarget(0.7));
        addSequential(new EjectMandibles());
        addSequential(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.PAUSE_AT_SPRING_TIME));
        addParallel(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.MANDIBLE_HOLD_TIME));
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, Constants.Auto.Drive.SPEED, false, true, 5000));
    }

}
