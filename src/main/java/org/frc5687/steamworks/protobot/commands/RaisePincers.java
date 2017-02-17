package org.frc5687.steamworks.protobot.commands;

import org.frc5687.steamworks.protobot.Constants;

/**
 * Created by Ben Bernard on 2/17/2017.
 */
public class RaisePincers extends MovePincers {
    public RaisePincers() {
        super(Constants.Pincers.potentiometerLifted, Constants.Pincers.raiseSpeed);
    }
}
