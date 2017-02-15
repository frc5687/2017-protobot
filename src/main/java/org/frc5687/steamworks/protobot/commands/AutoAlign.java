package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Baxter on 2/15/2017.
 */
public class AutoAlign extends Command {

    double target;

    public AutoAlign(double angle) {
        this.target = angle;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
