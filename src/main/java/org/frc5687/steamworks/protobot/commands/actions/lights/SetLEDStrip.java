package org.frc5687.steamworks.protobot.commands.actions.lights;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;

public class SetLEDStrip extends Command {

    private Color color;

    public SetLEDStrip(Color color) {
        requires(ledStrip);
        this.color = color;
    }

    @Override
    protected void initialize() {
        ledStrip.setStripColor(color, true);
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }

}