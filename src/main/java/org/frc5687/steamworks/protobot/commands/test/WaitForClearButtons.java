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
        DriverStation.reportError("Waiting for buttons to clear.", false);
    }

    @Override
    protected void end() {
        DriverStation.reportError("Buttons clear.", false);
    }

    @Override
    protected boolean isFinished() {
        boolean y = oi.isYesPressed();
        boolean n = oi.isNoPressed();

        DriverStation.reportError("Y=" + y + ", no=" + n, false);
        return !(y || n);
    }
}
