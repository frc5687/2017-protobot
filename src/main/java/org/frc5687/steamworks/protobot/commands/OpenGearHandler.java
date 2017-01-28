package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.subsystems.GearHandler;
import static org.frc5687.steamworks.protobot.Robot.gearHandler;

/**
 * Created by Caleb on 1/21/2017.
 */
public class OpenGearHandler extends MoveGearHandler {

    public  OpenGearHandler() {
        super(Constants.GearHandler.PID.MAX_INPUT);
    }
}
