package org.frc5687.steamworks.protobot.commands.actions.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.climber;

public class ReleaseFunnel extends Command {

    private long endTime;

    public ReleaseFunnel() {
        requires(climber);
    }

    @Override
    protected void initialize() {
        endTime = System.currentTimeMillis() + Constants.Climber.FUNNEL_RELEASE_TIME;
    }

    @Override
    protected void execute() {
        climber.setSpeed(Constants.Climber.FUNNEL_RELEASE_SPEED);
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > endTime;
    }

    @Override
    protected void end() {
        climber.stop();
    }

}