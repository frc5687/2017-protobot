package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;

/**
 * Created by Caleb on 1/21/2017.
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
