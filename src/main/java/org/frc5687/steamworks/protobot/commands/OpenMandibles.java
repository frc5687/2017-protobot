package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;

import java.util.Date;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.mandibles;

public class OpenMandibles extends Command {

    private long endTime;

    public OpenMandibles() {
        requires(mandibles);
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Ejecting gear", false);
        ledStrip.setStripColor(LEDColors.TELEOP);
        endTime = new Date().getTime() + Constants.GearHandler.OPEN_TIME;
    }

    @Override
    protected void execute() {
        mandibles.open();
    }

    @Override
    protected boolean isFinished() {
        return new Date().getTime() > endTime;
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
