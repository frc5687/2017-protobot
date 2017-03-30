package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.drive.DriveArc;

import static org.frc5687.steamworks.protobot.Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_ANGLE_LEFT;
import static org.frc5687.steamworks.protobot.Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_ANGLE_RIGHT;
import static org.frc5687.steamworks.protobot.Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE;

/**
 * Created by Baxter on 3/23/2017.
 */
public class AutoTraverseNeutralZoneRightFromCenter extends CommandGroup {

    public AutoTraverseNeutralZoneRightFromCenter() {
        addSequential(new DriveArc(-.1, -0.5, 70, 2000, false));
        addSequential(new AutoDrive(72.0, 1.0, true, true, 70));
        addSequential(new DriveArc(.1, 0.5, STRAIGHT_ANGLE, 500, false));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_CENTER_DISTANCE, 1.0, true, true, STRAIGHT_ANGLE));
    }

}
