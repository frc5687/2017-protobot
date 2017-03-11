package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Raise the dustpan to the hardstop (by time or amps).
 */
public class RaiseDustpan extends Command {

    private long endMillis;

    public RaiseDustpan() {
        requires(dustpan);
    }

    @Override
    protected void initialize() {
        // dustpan.raise();  // was used for the PID approach
        endMillis = System.currentTimeMillis() + Constants.Dustpan.RAISE_TIME;
    }

    @Override
    protected void execute() {
        dustpan.setLifterSpeed(Constants.Dustpan.RAISE_SPEED);
    }

    @Override
    protected void end() {
        dustpan.setLifterSpeed(0);

        if (dustpan.hasGear()) {
            ledStrip.setStripColor(LEDColors.GEAR_IN_DUSTPAN);
        } else {
            ledStrip.setStripColor(LEDColors.TELEOP);
        }
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
