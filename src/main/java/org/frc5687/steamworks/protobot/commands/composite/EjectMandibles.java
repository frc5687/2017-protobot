package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.HoldMandiblesOpen;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.OpenMandibles;

public class EjectMandibles extends CommandGroup {

    public EjectMandibles() {
        addSequential(new OpenMandibles());
        addSequential(new HoldMandiblesOpen(250));
    }
}
