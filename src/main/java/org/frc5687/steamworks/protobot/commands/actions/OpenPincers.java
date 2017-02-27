package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.pincers;

public class OpenPincers extends Command {

    public OpenPincers() {
        requires(pincers);
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Opening Pincers", false);
    }

    @Override
    protected void execute() {
        pincers.open();
    }

    @Override
    protected boolean isFinished() {
        return pincers.isOpen();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
