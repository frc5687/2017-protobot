package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class EjectGear extends CommandGroup {

    public EjectGear() {
        addParallel(new EjectMandibles());
        addParallel(new EjectDustpan());
    }

}
