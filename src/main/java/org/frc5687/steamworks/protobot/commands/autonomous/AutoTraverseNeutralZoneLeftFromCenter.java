package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;

/**
 * Created by Baxter on 3/23/2017.
 */
public class AutoTraverseNeutralZoneLeftFromCenter extends CommandGroup {

        public AutoTraverseNeutralZoneLeftFromCenter() {
            addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_ANGLE_LEFT, Constants.Auto.Align.SPEED));
            addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_DISTANCE, 1.0, true, true, 6000));
            addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE, Constants.Auto.Align.SPEED));
            addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_CENTER_DISTANCE, 1.0, true, true, 5000));
        }

}
