package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.Shift;
import org.frc5687.steamworks.protobot.commands.actions.ReleaseFunnel;

/**
 * Created by Ben Bernard on 2/20/2017.
 */
public class SteamworksBaseCommandGroup extends CommandGroup {

    public SteamworksBaseCommandGroup() {
        addParallel(new ReleaseFunnel());
    }
}
