package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;

public class AutoDepositGear extends CommandGroup {

    public AutoDepositGear() {
        super();
        addSequential(new AutoDrive(12, .5, false, false));
        addSequential(new AutoDrive(18, 1, false, false));
        addSequential(new AutoApproachTarget(0.7));
        addSequential(new EjectMandibles());
        addSequential(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.PAUSE_AT_SPRING_TIME));
        addParallel(new HoldMandiblesOpen(Constants.Auto.AnglesAndDistances.MANDIBLE_HOLD_TIME));
        addSequential(new AutoDrive(-48, Constants.Auto.Drive.SPEED, false, true));
    }

}