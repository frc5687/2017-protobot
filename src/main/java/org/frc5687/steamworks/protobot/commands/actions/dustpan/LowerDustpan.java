package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.pdp;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Lower the dustpan to the hardstop (by time or amps).
 */
public class LowerDustpan extends Command {
    private long endMillis;

    public LowerDustpan() {
        requires(dustpan);
    }

    @Override
    protected void initialize() {
        endMillis = System.currentTimeMillis() + Constants.Dustpan.LOWER_TIME;
    }

    @Override
    protected void execute() {
        dustpan.setLifterSpeed(Constants.Dustpan.LOWER_SPEED);
    }

    @Override
    protected void end() {
        dustpan.setLifterSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > endMillis || pdp.getDustpanLifterAmps() > Constants.Dustpan.HARDSTOP_AMPS;
    }

}
