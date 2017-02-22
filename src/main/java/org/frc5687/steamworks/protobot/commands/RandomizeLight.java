package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.subsystems.Lights;
import org.frc5687.steamworks.protobot.utils.LEDController;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.lights;

/**
 * Created by Caleb on 2/21/2017.
 */
public class RandomizeLight extends Command {
    public RandomizeLight() {
        requires(ledStrip);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        ledStrip.setStripColor((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
