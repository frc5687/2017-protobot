package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Lower the dustpan to the hardstop (by time or amps).
 */
public class LowerDustpan extends MoveDustpan {

    public LowerDustpan() {
        super(Constants.Dustpan.LOWER_TIME, Constants.Dustpan.LOWER_SPEED);
    }


    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void end() {
        super.end();
        ledStrip.setDustpanDeployed(true);
    }
}
