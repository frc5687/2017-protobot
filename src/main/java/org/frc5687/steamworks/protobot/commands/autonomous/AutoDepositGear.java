package org.frc5687.steamworks.protobot.commands.autonomous;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.HoldMandiblesOpen;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;
import org.frc5687.steamworks.protobot.commands.actions.AutoApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.AutoDrive;

public class AutoDepositGear extends SteamworksBaseCommandGroup {

    public AutoDepositGear() {
        super();
        // addSequential(new AutoDrive(30, 1, false));
        addSequential(new AutoApproachTarget(0.8));
        addSequential(new EjectMandibles());
        addSequential(new HoldMandiblesOpen(500));
        addParallel(new HoldMandiblesOpen(5000));
        addSequential(new AutoDrive(-Constants.Auto.AnglesAndDistances.RETREAT_DISTANCE, Constants.Auto.Drive.SPEED, false));
    }

}