package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.pincers;

public class OverdrivePincers extends Command {

    private long endTime;

    @Override
    protected void initialize() {
        endTime = System.currentTimeMillis() + Constants.Pincers.OVERDRIVE_TIME;
    }

    @Override
    protected void execute() {
        pincers.setPincerSpeed(Constants.Pincers.OVERDRIVE_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > endTime;
    }

}
