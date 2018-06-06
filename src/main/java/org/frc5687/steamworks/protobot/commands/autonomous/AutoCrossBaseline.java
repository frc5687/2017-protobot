package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.HoldMandiblesOpen;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;

public class AutoCrossBaseline extends SteamworksBaseCommandGroup {

    public AutoCrossBaseline() {
        super();
        addSequential(new AutoDrive(72, .7, false, false, 5000, "Initial Center"));
        addSequential(new AutoDrive(58, .4, false, true, 5000, "Approach"));
        // addSequential(new AutoDrive(-48, Constants.Auto.Drive.SPEED, false, true, 5000, "Retreat Center"));
        // addParallel(new ReceiveMandibles());
    }

}
