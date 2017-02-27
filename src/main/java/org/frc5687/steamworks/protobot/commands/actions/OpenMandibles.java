package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;

import java.util.Date;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class OpenMandibles extends Command {

    private long endTime;

    public OpenMandibles() {
        requires(mandibles);
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Ejecting gear", false);
        ledStrip.setStripColor(LEDColors.TELEOP);
        endTime = new Date().getTime() + Constants.Mandibles.OPEN_TIME;
    }

    @Override
    protected void execute() {
        if (new Date().getTime() < endTime) mandibles.open();
        else mandibles.stop();
    }

    @Override
    protected boolean isFinished() {
        return (new Date().getTime() > endTime) && (!oi.isOpenMandiblesPressed());
    }

    @Override
    protected void end() {
        mandibles.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
