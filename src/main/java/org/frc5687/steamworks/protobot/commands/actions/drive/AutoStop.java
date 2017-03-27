package org.frc5687.steamworks.protobot.commands.actions.drive;

import edu.wpi.first.wpilibj.command.Command;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;

/**
 * Created by Ben Bernard on 2/25/2017.
 */
public class AutoStop extends Command {

    public AutoStop() {
        requires(driveTrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        driveTrain.tankDrive(0);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }
}
