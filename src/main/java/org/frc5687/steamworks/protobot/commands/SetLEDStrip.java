package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
/**
 * Created by Ben Bernard on 2/16/2017.
 */
public class SetLEDStrip extends Command {

    private Color _color;


    public SetLEDStrip(Color color) {
        requires(ledStrip);
        _color=color;
    }

    @Override
    protected void initialize() {
        ledStrip.setStripColor(_color);
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