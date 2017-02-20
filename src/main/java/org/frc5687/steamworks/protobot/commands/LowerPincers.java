package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Created by Ben Bernard on 2/17/2017.
 */
public class LowerPincers extends Command {
    public LowerPincers() {
        requires(pincers);
    }

    protected void initialize() {
        pincers.lower();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
