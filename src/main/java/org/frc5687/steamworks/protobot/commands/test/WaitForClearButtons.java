package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command to prompt the operator to confirm a result using the gamepad buttons.
 */
public class WaitForClearButtons extends Command {

    public WaitForClearButtons() {
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected boolean isFinished() {
        return !(oi.isYesPressed() || oi.isNoPressed());
    }
}
