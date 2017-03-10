package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class HoldDust extends Command {

    private long endMillis;
    public HoldDust() {
        requires(dustpan);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        dustpan.hold();
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
