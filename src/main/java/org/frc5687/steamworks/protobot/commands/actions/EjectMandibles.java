package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;

import java.util.Date;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class EjectMandibles extends CommandGroup {

    public EjectMandibles() {
        addSequential(new OpenMandibles());
        // addSequential(new WaitForButtonRelease(mandibles, OI.ocEjectMandiblesButton, OI.gpEjectMandiblesButton));
    }
}
