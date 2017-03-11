package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class CollectDust extends Command {

    public CollectDust() {
        requires(dustpan.roller);
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("CollectDust", false);
    }

    @Override
    protected void execute() {
        dustpan.collect();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
