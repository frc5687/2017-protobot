package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.subsystems.LEDStrip;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;
import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Raise the pincers to the hardstop (by time or amps).
 */
public class RaisePincers extends Command {

    private long endMillis;

    public RaisePincers() {
        requires(pincers);
    }

    @Override
    protected void initialize() {
        // pincers.raise();  // was used for the PID approach
        endMillis = System.currentTimeMillis() + Constants.Pincers.RAISE_TIME;
    }

    @Override
    protected void execute() {
        pincers.setPincerSpeed(Constants.Pincers.RAISE_SPEED);
    }

    @Override
    protected void end() {
        pincers.setPincerSpeed(0);

        if (pincers.hasGear()) {
            ledStrip.setStripColor(LEDColors.GEAR_IN_PINCERS);
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
        return System.currentTimeMillis() > endMillis || pdp.getPincersAmps() > Constants.Pincers.HARDSTOP_AMPS;
    }

}
