package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

public class RunPincersManually extends Command {

    public RunPincersManually() {
        requires(dustpan);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        dustpan.setLifterSpeed(oi.getPincerSpeed());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        dustpan.setLifterSpeed(0);
    }

}
