package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Baxter on 2/15/2017.
 */
public class AutoDrive extends Command {

    double distance;

    public AutoDrive(double distance) {
        this.distance = distance;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
