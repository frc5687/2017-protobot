package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class HoldDustpanDown extends HoldDustpan {

    public HoldDustpanDown() {
        super(Constants.Dustpan.LIFTER_HOLD_SPEED);
    }

}
