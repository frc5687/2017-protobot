package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.OI;

import static org.frc5687.steamworks.protobot.Robot.pincers;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class RunPincersManually extends Command {

    public RunPincersManually() {
        requires(pincers);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double speed = oi.getPincerSpeed() / 2;
        pincers.setPincerSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        pincers.setPincerSpeed(0);
    }

}
