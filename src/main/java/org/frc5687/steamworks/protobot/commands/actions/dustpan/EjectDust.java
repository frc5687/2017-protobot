package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class EjectDust extends Command {

    public EjectDust() {
        requires(dustpan.roller);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        dustpan.eject();
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
