package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlignAwayFromBoiler;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.drive.Shift;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.ReceiveMandibles;
import org.frc5687.steamworks.protobot.subsystems.Shifter;

/**
 * Created by Baxter on 3/25/2017.
 */
public class AutoTraverseNeutralZoneAwayFromBoiler extends CommandGroup {

    public AutoTraverseNeutralZoneAwayFromBoiler() {
        addSequential(new AutoAlignAwayFromBoiler(Constants.Auto.Align.SPEED));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.ESCAPE_CENTER_DISTANCE, Constants.Auto.Drive.SPEED, 2000, "Escape from Center"));
        addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new Shift(Shifter.Gear.HIGH, false));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_CENTER_DISTANCE, Constants.Auto.Drive.SPEED, true, true, 5000, "Traverse from Center"));
        addSequential(new Shift(Shifter.Gear.LOW, false));
        addParallel(new ReceiveMandibles());
    }

}
