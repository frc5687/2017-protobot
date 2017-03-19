package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class HoldDust extends Command {

    public HoldDust() {
        requires(dustpan.roller);
    }

    @Override
    protected void initialize() {

        DriverStation.reportError("HoldDust", false);
    }

    @Override
    protected void execute() {
        dustpan.hold();
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
