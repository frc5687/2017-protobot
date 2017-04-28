package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoApproachTarget;
import org.frc5687.steamworks.protobot.commands.actions.drive.AutoDrive;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.HoldMandiblesOpen;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.ReceiveMandibles;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;

public class AutoDepositCenterDeadReckoning extends CommandGroup {

    public AutoDepositCenterDeadReckoning() {
        super();
        addSequential(new AutoDrive(36, .7, false, false, 2000, "Initial Center"));
        addSequential(new AutoApproachTarget(0.7));
        addSequential(new EjectMandibles());
        addSequential(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.PAUSE_AT_SPRING_TIME));
        addParallel(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.MANDIBLE_HOLD_TIME));
        addSequential(new AutoDrive(-48, Constants.Auto.Drive.SPEED, false, true, 5000, "Retreat Center"));
        // addParallel(new ReceiveMandibles());
    }

}