package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Ben Bernard on 2/23/2017.
 */
public class GimmeGearLight extends Command {

    Color previousColor;

    protected void initialize() {
        previousColor = ledStrip.getStripColor();
    }

    @Override
    protected void execute() {
        ledStrip.setStripColor(LEDColors.GIMME_GEAR);
    }

    protected boolean isFinished() {
        return oi.isGimmeGearPressed();
    }

    @Override
    protected void end() {
        ledStrip.setStripColor(previousColor);
    }
}

