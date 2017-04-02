package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;

/**
 * Command for traversing the neutral zone from a side airship peg. To be added to a command group after depositing a gear
 */
public class AutoTraverseNeutralZoneFromSide extends CommandGroup {

    public AutoTraverseNeutralZoneFromSide() {
        addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_SIDE_DISTANCE, Constants.Auto.Drive.SPEED, 4000));
    }

}
