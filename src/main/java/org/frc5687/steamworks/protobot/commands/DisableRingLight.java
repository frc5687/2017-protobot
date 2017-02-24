package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Robot;
import org.frc5687.steamworks.protobot.subsystems.Lights;

public class DisableRingLight extends Command {

    Lights lights = Robot.lights;

    public DisableRingLight() {
        requires(lights);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        lights.turnRingLightOff();
    }

    @Override
    protected boolean isFinished() {
        return !lights.getRingLight();
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
