package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.subsystems.GearHandler;

import java.util.Date;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;

/**
 * Created by Caleb on 1/21/2017.
 */
public class OpenGearHandler extends Command {

    private long endTime;

    public  OpenGearHandler() {
        requires(gearHandler);
    }

    protected void initialize(){
        DriverStation.reportError("Ejecting gear", false);
        endTime = new Date().getTime() + Constants.GearHandler.OPEN_TIME;
    }

    protected void execute() {
        gearHandler.open();
    }

    protected boolean isFinished(){
        return new Date().getTime() > endTime;
    }

    protected void end() {
        gearHandler.stop();
    }

    protected void interrupted() {
        end();
    }
}
