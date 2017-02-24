package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

import static org.frc5687.steamworks.protobot.Robot.pincers;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class CatchGear extends CommandGroup {

    public CatchGear() {
        addSequential(new LowerPincers());
        addSequential(new OpenPincers());
        addSequential(new WaitToClose());
        addSequential(new ClosePincers());
        addSequential(new RaisePincers());
    }

    private class WaitToClose extends Command {

        @Override
        protected boolean isFinished() {
            return !oi.isCatchGearPressed() || pincers.hasGear();
        }

    }

}
