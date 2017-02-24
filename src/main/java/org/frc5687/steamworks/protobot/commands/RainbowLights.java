package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;

/**
 * Created by Baxter on 2/24/2017.
 */
public class RainbowLights extends Command {

    private Color initialColor;
    private double hue;

    public RainbowLights() {
        requires(ledStrip);
    }

    @Override
    protected void initialize() {
        initialColor = ledStrip.getStripColor();
    }

    @Override
    protected void execute() {
        super.execute();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        super.end();
    }

    @Override
    protected void interrupted() {
        end();
    }

}
