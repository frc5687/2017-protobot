package org.frc5687.steamworks.protobot.commands.actions.mandibles;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class RunMandiblesManually extends Command {

    public RunMandiblesManually() {
        requires(mandibles);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        if (oi.isGearInPressed()) {
            mandibles.close();
        } else if (oi.isGearOutPressed()) {
            mandibles.open();
        } else {
            mandibles.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}