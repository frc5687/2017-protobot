package org.frc5687.steamworks.protobot.commands.actions;

import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.OI;

import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Command for moving the pincers slowly forwards until the button is released
 */
public class MovePincersForwards extends WaitForButtonRelease {

    public MovePincersForwards() {
        super(pincers, OI.OC_RELEASE_PINCERS, OI.GP_RELEASE_PINCERS);
    }

    @Override
    protected void execute() {
        pincers.setPincerSpeed(Constants.Pincers.FORWARDS_SPEED);
    }

}
