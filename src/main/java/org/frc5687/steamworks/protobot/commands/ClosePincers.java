package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.pincers;

public class ClosePincers extends Command {

    public ClosePincers() {
        requires(pincers);
    }

    @Override
    protected void execute() {
        pincers.close();
    }

    @Override
    protected boolean isFinished() {
        return pincers.isClosed();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
