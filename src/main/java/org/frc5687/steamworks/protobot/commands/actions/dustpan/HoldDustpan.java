package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class HoldDustpan extends Command {

    public final double speed;

    public HoldDustpan(double speed) {
        requires(dustpan.lifter);
        this.speed = speed;
    }

    @Override
    protected void initialize() {

        DriverStation.reportError("HoldDustpan at " + speed, false);
    }

    @Override
    protected void execute() {
        dustpan.lifter.set(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        dustpan.lifter.set(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
