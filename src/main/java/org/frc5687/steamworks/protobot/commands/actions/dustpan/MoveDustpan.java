package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.dustpan;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Baxter on 3/11/2017.
 */
public class MoveDustpan extends Command {

    private long clearMillis;
    private long endMillis;
    private final long time;
    private final double speed;

    public MoveDustpan(long time, double speed) {
        requires(dustpan.lifter);
        this.time = time;
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        clearMillis = System.currentTimeMillis() + 250;
        endMillis =  time==0 ? 0 : System.currentTimeMillis() + time;

        DriverStation.reportError("MoveDustpan at " + speed, false);
    }

    @Override
    protected void execute() {
        dustpan.lifter.set(speed);
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
        return (endMillis > 0 && System.currentTimeMillis() > endMillis) || pdp.getDustpanLifterAmps() > Constants.Dustpan.HARDSTOP_AMPS;
    }

}
