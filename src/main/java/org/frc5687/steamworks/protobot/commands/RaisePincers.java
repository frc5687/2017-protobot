package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import static org.frc5687.steamworks.protobot.Robot.pincers;

public class RaisePincers extends Command {

    public RaisePincers() {
        requires(pincers);
    }

    protected void initialize() {
        pincers.raise();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
