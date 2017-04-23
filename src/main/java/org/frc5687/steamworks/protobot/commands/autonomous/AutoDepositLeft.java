package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoVisualApproachTarget;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;

public class AutoDepositLeft extends CommandGroup {

    public AutoDepositLeft() {
        super();
        addSequential(new AutoDrive(91.5, Constants.Auto.Drive.SPEED, "Initial Left"));
        addSequential(new AutoAlign(60, 0.1));
        // addSequential(new AutoDrive(48, Constants.Auto.Drive.SPEED));

        addSequential(new AutoVisualApproachTarget(Constants.Auto.Drive.SPEED, 60));
        addSequential(new EjectMandibles());
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, -Constants.Auto.Drive.SPEED, false, true, 5000, "Retreat Left"));
    }

}
