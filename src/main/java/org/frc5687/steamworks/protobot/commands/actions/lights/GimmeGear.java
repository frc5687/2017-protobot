package org.frc5687.steamworks.protobot.commands.actions.lights;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.mandibles;

/**
 * Created by Ben Bernard on 2/23/2017.
 */
public class GimmeGear extends Command {

    Color previousColor;

    protected void initialize() {
        previousColor = ledStrip.getStripColor();
    }

    @Override
    protected void execute() {
        ledStrip.setGimmeGear(true);
    }

    protected boolean isFinished() {
        return false;
        // return mandibles.gearPresent();
        // return oi.isGimmeGearPressed();
    }

    @Override
    protected void end() {
        ledStrip.setGimmeGear(false);
    }
}