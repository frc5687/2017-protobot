package org.frc5687.steamworks.protobot.commands.actions.lights;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.lights;

/**
 * Created by Ben Bernard on 3/4/2017.
 */
public class RunRingLight extends Command{

    public RunRingLight() {
        requires(lights);
    }

    @Override
    protected void initialize() {
        lights.turnRingLightOn();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        lights.turnRingLightOff();
    }
}
