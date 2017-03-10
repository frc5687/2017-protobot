package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Rest extends Command {

    public Rest(Subsystem subsystem) {
        requires(subsystem);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
