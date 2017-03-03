package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Baxter on 2/28/2017.
 */
public class HoldMandiblesOpen extends Command {

    public HoldMandiblesOpen() {
        requires(mandibles);
    }

    @Override
    protected void execute() {
        mandibles.setSpeed(Constants.Mandibles.HOLD_OPEN_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return !oi.isEjectGearPressed();
    }
}
