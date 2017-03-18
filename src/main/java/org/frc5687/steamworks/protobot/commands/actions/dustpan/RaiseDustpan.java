package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Raise the dustpan to the hardstop (by time or amps).
 */
public class RaiseDustpan extends MoveDustpan {

    public RaiseDustpan() {
        super(Constants.Dustpan.RAISE_TIME, Constants.Dustpan.RAISE_SPEED);
    }

    @Override
    protected void initialize() {
        super.initialize();
        ledStrip.setDustpanDeployed(false);
    }
}
