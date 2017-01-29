package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;

/**
 * Command for closing the gear handler to the home position
 */
public class CloseGearHandler extends Command {

    public  CloseGearHandler() {
        requires(gearHandler);
    }

    protected void initialize(){

    }
    protected void execute() {
        gearHandler.close();
    }

    protected boolean isFinished(){
        return gearHandler.isAtMinHall();
    }

    protected void end() {
        gearHandler.stop();
    }

    protected void interrupted() {
        end();
    }
}
