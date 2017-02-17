package org.frc5687.steamworks.protobot.commands;

import org.frc5687.steamworks.protobot.Constants;

/**
 * Created by Ben Bernard on 2/17/2017.
 */
public class LowerPincers extends MovePincers {
    public LowerPincers() {
        super(Constants.Pincers.potentiometerLowered, Constants.Pincers.lowerSpeed);
    }

}
