package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command that holds the pincers in their current state until a specified button is released
 */
public class WaitForButtonRelease extends Command {

    private int ocButton;
    private int gpButton;

    public WaitForButtonRelease(Subsystem subsystem, int ocButton, int gpButton) {
        requires(subsystem);
        this.ocButton = ocButton;
        this.gpButton = gpButton;
    }

    @Override
    protected boolean isFinished() {
        return !oi.isgpButtonPressed(gpButton) && !oi.isocButtonPressed(ocButton);
    }
}
