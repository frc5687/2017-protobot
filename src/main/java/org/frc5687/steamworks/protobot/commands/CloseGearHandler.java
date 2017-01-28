package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;

/**
 * Created by Caleb on 1/21/2017.
 */
public class CloseGearHandler extends MoveGearHandler {

    public  CloseGearHandler() {
        super(Constants.GearHandler.PID.MIN_INPUT);
    }

}
