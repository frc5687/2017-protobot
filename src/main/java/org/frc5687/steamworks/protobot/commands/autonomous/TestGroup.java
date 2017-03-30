package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.drive.DriveArc;

/**
 * Created by Ben Bernard on 3/25/2017.
 */
public class TestGroup extends SteamworksBaseCommandGroup {

    public TestGroup() {
        super();
        addSequential(new DriveArc(-.35, -1.0, 60.0, 500, false));
        addSequential(new AutoDrive(48.0, 1.0, true, true, 60.0));
        // addSequential(new AutoDepositGear());
        // addSequential(new AutoTraverseNeutralZoneLeftFromCenter());
    }
}
