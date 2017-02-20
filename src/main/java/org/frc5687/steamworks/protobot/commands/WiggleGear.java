package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Caleb on 2/16/2017.
 */
public class WiggleGear extends Command {
    public WiggleGear(){
        requires(gearHandler);
    }

    private int direction;
    private double switchTime;

    @Override
    protected void initialize() {
        direction = 1;
        switchTime = System.currentTimeMillis() + Constants.GearHandler.wiggleTime;
    }

    @Override
    protected void execute() {
        gearHandler.setSpeed(Constants.GearHandler.wiggleSpeed * direction);
        if (System.currentTimeMillis() > switchTime) {
            switchTime = System.currentTimeMillis() + Constants.GearHandler.wiggleTime;
            direction = direction * -1;
        }
    }

    @Override
    protected boolean isFinished() {
        return  System.currentTimeMillis() > Constants.GearHandler.wiggleTotalTime;
    }

    protected void end(){
        gearHandler.stop();
    }

    protected void interrupted(){
        end();
    }
}