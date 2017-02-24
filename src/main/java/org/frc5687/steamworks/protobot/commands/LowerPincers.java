package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.pincers;

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
