package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.pdp;
import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Lower the pincers to the hardstop (by time or amps).
 */
public class LowerPincers extends Command {
    private long endMillis;

    public LowerPincers() {
        requires(pincers);
    }

    @Override
    protected void initialize() {
        endMillis = System.currentTimeMillis() + Constants.Pincers.LOWER_TIME;
    }

    @Override
    protected void execute() {
        pincers.setPincerSpeed(Constants.Pincers.LOWER_SPEED);
    }

    @Override
    protected void end() {
        pincers.setPincerSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > endMillis || pdp.getPincersAmps() > Constants.Pincers.HARDSTOP_AMPS;
    }

}
