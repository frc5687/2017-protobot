package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.HoldDustpanUp;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.RaiseDustpan;
import org.frc5687.steamworks.protobot.commands.composite.StowDustpan;
import org.frc5687.steamworks.protobot.subsystems.Shifter;

public class SteamworksBaseCommandGroup extends CommandGroup {

    public SteamworksBaseCommandGroup() {
        addParallel(new EnableRingLight());
        addParallel(new ReleaseFunnel());
        addParallel(new StowDustpan());
        addParallel(new ReceiveMandibles());
        addSequential(new Shift(Shifter.Gear.LOW, true));
    }

}
