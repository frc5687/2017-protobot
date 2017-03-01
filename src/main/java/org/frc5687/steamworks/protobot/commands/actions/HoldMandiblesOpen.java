package org.frc5687.steamworks.protobot.commands.actions;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.OI;

import static org.frc5687.steamworks.protobot.Robot.mandibles;

/**
 * Created by Baxter on 2/28/2017.
 */
public class HoldMandiblesOpen extends WaitForButtonRelease {

    public HoldMandiblesOpen() {
        super(mandibles, OI.OC_EJECT_MANDIBLES, OI.GP_EJECT_MANDIBLES);
    }

    @Override
    protected void execute() {
        mandibles.setSpeed(Constants.Mandibles.HOLD_OPEN_SPEED);
    }
}
