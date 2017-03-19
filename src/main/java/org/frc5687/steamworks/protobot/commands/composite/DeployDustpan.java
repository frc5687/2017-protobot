package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.CollectDust;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.HoldDustpanDown;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.HoldDustpanUp;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.LowerDustpan;

import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command group for lowering and opening the dustpan to intake a gear
 */
public class DeployDustpan extends CommandGroup {

    public DeployDustpan() {
        addSequential(new LowerDustpan());
        addParallel(new CollectDust());
        addSequential(new HoldDustpanDown());
    }

    @Override
    protected boolean isFinished() {
        return !oi.isDeployPincersPressed();
    }
}