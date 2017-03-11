package org.frc5687.steamworks.protobot.commands.actions.dustpan;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import static org.frc5687.steamworks.protobot.Robot.dustpan;

/**
 * Created by Baxter on 3/11/2017.
 */
public class TimeoutDustpanRollers extends Command {


    private final long time;
    private long endTime;

    public TimeoutDustpanRollers(long time) {
        requires(dustpan.roller);
        this.time = time;
    }

    @Override
    protected void initialize() {
        endTime = System.currentTimeMillis() + time;

        DriverStation.reportError("TimeoutDustpanRollers", false);
    }

    @Override
    protected void execute() {
        dustpan.roller.set(0);
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() >= endTime;
    }

}
