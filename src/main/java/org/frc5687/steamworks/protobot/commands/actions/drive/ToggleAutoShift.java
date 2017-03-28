package org.frc5687.steamworks.protobot.commands.actions.drive;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.shifter;

/**
 * Created by Ben Bernard on 3/27/2017.
 */
public class ToggleAutoShift extends Command {
    public ToggleAutoShift() {
        requires(shifter);
    }

    @Override
    protected void initialize() {
        shifter.setAutShiftEnabled(!shifter.isAutShiftEnabled());
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

}
