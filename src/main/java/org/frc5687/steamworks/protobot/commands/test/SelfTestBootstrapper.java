package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import static org.frc5687.steamworks.protobot.Robot.autoRotorChooser;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Ben Bernard on 3/18/2017.
 */
public class SelfTestBootstrapper extends Command {
    boolean done = false;

    public SelfTestBootstrapper() {

    }

    @Override
    protected void execute() {
        if (!done && oi.isYesPressed() && oi.isNoPressed() && !DriverStation.getInstance().isFMSAttached()) {
            Scheduler.getInstance().add(new FullSelfTest());
            done=true;
        }
    }

    @Override
    protected boolean isFinished() {
        return done;
    }
}
