package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoAlign;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.drive.DriveArc;
import org.frc5687.steamworks.protobot.commands.actions.drive.Shift;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.ReceiveMandibles;
import org.frc5687.steamworks.protobot.subsystems.Shifter;

/**
 * Command for traversing the neutral zone from a side airship peg. To be added to a command group after depositing a gear
 */
public class AutoTraverseNeutralZoneFromLeftSide extends CommandGroup {

    public AutoTraverseNeutralZoneFromLeftSide() {
        // addSequential(new DriveArc(0.367088608, 1.0, Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE, 2000, true));
        // addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_SIDE_DISTANCE, 0.7, true, true, Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE, 4000));

        addSequential(new AutoAlign(Constants.Auto.AnglesAndDistances.STRAIGHT_ANGLE, Constants.Auto.Align.SPEED));
        addSequential(new Shift(Shifter.Gear.HIGH, false));
        addSequential(new AutoDrive(Constants.Auto.AnglesAndDistances.TRAVERSE_NEUTRAL_ZONE_FROM_SIDE_DISTANCE, Constants.Auto.Drive.SPEED, 4000, "Traverse From Right"));
        addSequential(new Shift(Shifter.Gear.LOW, false));
        addParallel(new ReceiveMandibles());
    }

}
