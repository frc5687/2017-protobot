package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

/**
 * Created by Baxter on 3/23/2017.
 */
public class AutoTraverseNeutralZoneLeftFromCenter extends CommandGroup {

        public AutoTraverseNeutralZoneLeftFromCenter() {
            addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_ANGLE_LEFT, Constants.Auto.Align.SPEED));
            addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_DISTANCE, Constants.Auto.Drive.SPEED));
            addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE, Constants.Auto.Align.SPEED));
            addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_CENTER_DISTANCE, Constants.Auto.Drive.SPEED));
        }

}
