package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.commands.actions.climber.ReleaseFunnel;
import org.frc5687.steamworks.protobot.commands.actions.drive.Shift;
import org.frc5687.steamworks.protobot.commands.actions.lights.EnableRingLight;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.ReceiveMandibles;
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
