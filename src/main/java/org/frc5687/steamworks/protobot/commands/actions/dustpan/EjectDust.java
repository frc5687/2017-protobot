package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class EjectDust extends Command {

    public EjectDust() {
        requires(dustpan.roller);
    }

    @Override
    protected void initialize() {

        DriverStation.reportError("EjectDust", false);
    }

    @Override
    protected void execute() {
        dustpan.eject();
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
