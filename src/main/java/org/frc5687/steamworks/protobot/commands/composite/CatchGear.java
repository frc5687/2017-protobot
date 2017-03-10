package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.*;

import static org.frc5687.steamworks.protobot.Robot.dustpan;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class CatchGear extends CommandGroup {

    public CatchGear() {
        addSequential(new LowerDustpan());
        addSequential(new CollectDust());
        addSequential(new SetLEDStrip(LEDColors.DUSTPAN_DEPLOYED));
        addSequential(new WaitToClose());
        addSequential(new HoldDust());
        addSequential(new RaiseDustpan());
    }

    @Override
    protected void initialize() {
    }

    private class WaitToClose extends Command {

        @Override
        protected void initialize() {
        }

        @Override
        protected boolean isFinished() {
            return !oi.isDeployPincersPressed() || dustpan.hasGear();
        }

    }

}
