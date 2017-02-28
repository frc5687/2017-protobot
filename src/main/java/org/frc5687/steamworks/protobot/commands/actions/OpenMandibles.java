package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;

import java.util.Date;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Created by Ben Bernard on 2/27/2017.
 */
public class OpenMandibles extends Command {
    private long endMillis;

    public OpenMandibles() {
        requires(mandibles);
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Ejecting gear", false);
        ledStrip.setStripColor(LEDColors.MANDIBLES_OPEN);
        endMillis = System.currentTimeMillis() + Constants.Mandibles.OPEN_TIME;
    }

    @Override
    protected void execute() {
        mandibles.open();
    }

    @Override
    protected boolean isFinished() {
        return (System.currentTimeMillis() > endMillis || pdp.getMandiblesAmps() > Constants.Mandibles.THRESHOLD_OPEN_AMPS);
    }

    @Override
    protected void end() {
        mandibles.setSpeed(-Constants.Mandibles.CLAMP_SPEED);
    }

    @Override
    protected void interrupted() {
        end();
    }

}
