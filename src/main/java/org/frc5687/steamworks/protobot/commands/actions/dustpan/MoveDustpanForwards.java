package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Command for moving the dustpan slowly forwards until the button is released
 */
public class MoveDustpanForwards extends Command {

    public MoveDustpanForwards() {
        requires(dustpan.lifter);
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("MoveDustpanForwards", false);
    }

    @Override
    protected void execute() {
        dustpan.lifter.set(Constants.Dustpan.FORWARDS_SPEED);
    }

    @Override
    protected void end() {
        dustpan.lifter.set(0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        return !oi.isReleasePincersPressed();
    }

}
