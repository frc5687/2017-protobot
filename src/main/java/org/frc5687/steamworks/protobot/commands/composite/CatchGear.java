package org.frc5687.steamworks.protobot.commands.composite;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.*;

import static org.frc5687.steamworks.protobot.Robot.pincers;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class CatchGear extends CommandGroup {

    public CatchGear() {
        addSequential(new LowerPincers());
        addSequential(new OpenPincers());
        addSequential(new SetLEDStrip(LEDColors.PINCERS_DEPLOYED));
        addSequential(new WaitToClose());
        addSequential(new ClosePincers());
        addSequential(new RaisePincers());
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
            return !oi.isDeployPincersPressed() || pincers.hasGear();
        }

    }

}
