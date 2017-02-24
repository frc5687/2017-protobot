package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.pincers;

public class RestPincers extends Command {

    public RestPincers() {
        requires(pincers);
    }

    @Override
    protected void execute() {
        pincers.rest();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
