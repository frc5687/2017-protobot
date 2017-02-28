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
        DriverStation.reportError("Catching gear", false);
    }

    private class WaitToClose extends Command {

        @Override
        protected void initialize() {
            DriverStation.reportError("Waiting to close", false);
        }

        @Override
        protected boolean isFinished() {
            if (pincers.hasGear()) DriverStation.reportError("Got a gear", false);
            if (!oi.isDeployPincersPressed()) DriverStation.reportError("Button released", false);
            return !oi.isDeployPincersPressed() || pincers.hasGear();
        }

    }

}
