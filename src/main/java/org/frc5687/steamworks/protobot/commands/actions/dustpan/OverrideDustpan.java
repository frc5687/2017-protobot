package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class OverrideDustpan extends Command {

    private final double speed;

    public OverrideDustpan(double speed) {
        this.speed = speed;
        requires(dustpan.lifter);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        dustpan.lifter.set(speed);
    }

    @Override
    protected void end() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
