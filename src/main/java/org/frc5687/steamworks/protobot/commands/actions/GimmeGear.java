package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Ben Bernard on 2/23/2017.
 */
public class GimmeGear extends Command {

    Color previousColor;

    protected void initialize() {
        DriverStation.reportError("Gimme gear! started", false);
        previousColor = ledStrip.getStripColor();
    }

    @Override
    protected void execute() {
        ledStrip.setStripColor(LEDColors.GIMME_GEAR);
    }

    protected boolean isFinished() {
        return false;
        // return oi.isGimmeGearPressed();
    }

    @Override
    protected void end() {
        DriverStation.reportError("Gimme gear! done", false);
        ledStrip.setStripColor(previousColor);
    }
}