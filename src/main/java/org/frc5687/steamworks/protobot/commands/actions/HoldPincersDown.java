package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class HoldPincersDown extends Command {

    public HoldPincersDown() {
        requires(pincers);
    }

    @Override
    protected void execute() {
        pincers.setPincerSpeed(-Constants.Pincers.HOLD_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return !oi.isDeployPincersPressed();
    }

    @Override
    protected void end() {
        pincers.setPincerSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
