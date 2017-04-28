package org.frc5687.steamworks.protobot.commands.actions.mandibles;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Created by Ben Bernard on 2/27/2017.
 */
public class OpenMandibles extends Command {
    private long minMillis;
    private long endMillis;

    public OpenMandibles() {
        requires(mandibles);
    }

    @Override
    protected void initialize() {
        ledStrip.setMandiblesOpen(true);
        minMillis = System.currentTimeMillis() + Constants.Mandibles.MIN_OPEN_TIME;
        endMillis = System.currentTimeMillis() + Constants.Mandibles.MAX_OPEN_TIME;
    }

    @Override
    protected void execute() {
        mandibles.open();
    }

    @Override
    protected boolean isFinished() {
        return (System.currentTimeMillis() > endMillis || (System.currentTimeMillis() > minMillis && pdp.getMandiblesAmps() > Constants.Mandibles.THRESHOLD_OPEN_AMPS));
    }

    @Override
    protected void end() {
        mandibles.setSpeed(Constants.pickConstant(-Constants.Mandibles.CLAMP_SPEED_TONY, -Constants.Mandibles.CLAMP_SPEED_RHODY));
    }

    @Override
    protected void interrupted() {
        end();
    }

}
