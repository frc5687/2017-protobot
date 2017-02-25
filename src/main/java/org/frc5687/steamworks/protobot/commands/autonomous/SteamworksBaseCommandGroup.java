package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.RaisePincers;
import org.frc5687.steamworks.protobot.commands.Shift;
import org.frc5687.steamworks.protobot.commands.actions.ReleaseFunnel;

public class SteamworksBaseCommandGroup extends CommandGroup {

    public SteamworksBaseCommandGroup() {
        addParallel(new ReleaseFunnel());
        addParallel(new RaisePincers());
        addSequential(new Shift(DoubleSolenoid.Value.kReverse));
    }

}
