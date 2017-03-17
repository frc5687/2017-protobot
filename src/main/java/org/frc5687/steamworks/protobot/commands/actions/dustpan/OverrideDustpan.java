package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class OverrideDustpan extends Command {

    private long endTime;
    private final double speed;

    public OverrideDustpan(double speed) {
        this.speed = speed;
        requires(dustpan.lifter);
    }

    @Override
    protected void initialize() {
        endTime = System.currentTimeMillis() + Constants.Dustpan.OVERRIDE_TIME;
    }

    @Override
    protected void execute() {
        dustpan.lifter.set(speed);
    }

    @Override
    protected void end() {
        dustpan.lifter.set(speed);
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > endTime;
    }
}
