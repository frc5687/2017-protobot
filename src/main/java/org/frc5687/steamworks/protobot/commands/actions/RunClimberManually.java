package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.climber;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class RunClimberManually extends Command {

    public RunClimberManually() {
        requires(climber);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        if (oi.isAscendClimberPressed()) climber.ascend();
        else climber.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
