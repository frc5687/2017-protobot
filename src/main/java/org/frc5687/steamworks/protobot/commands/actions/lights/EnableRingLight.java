package org.frc5687.steamworks.protobot.commands.actions.lights;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.lights;

public class EnableRingLight extends Command {

    public EnableRingLight() {
        requires(lights);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        lights.turnRingLightOn();
    }

    @Override
    protected boolean isFinished() {
        return lights.getRingLight();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
