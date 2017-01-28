package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.climber;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Baxter on 1/28/2017.
 */
public class RunClimberManually extends Command{

    public  RunClimberManually() {
        requires(climber);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    protected void execute() {
        if (oi.isAscendClimberPressed()) { climber.ascend(); }
        else if (oi.isDescendClimberPressed()) { climber.descend(); }
        else { climber.stop(); }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}
