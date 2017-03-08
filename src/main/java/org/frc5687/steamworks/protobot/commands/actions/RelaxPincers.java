package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Command to relax the pincers piston
 */
public class RelaxPincers extends Command {

    public RelaxPincers() {
        requires(pincers);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        pincers.relax();
    }

    @Override
    protected boolean isFinished() {
        return pincers.isRelaxed();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
