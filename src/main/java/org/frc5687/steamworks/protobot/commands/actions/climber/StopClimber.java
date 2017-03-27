package org.frc5687.steamworks.protobot.commands.actions.climber;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.climber;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class StopClimber extends Command {


    public StopClimber() {
        requires(climber);
    }


    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        climber.setSpeed(0);
    }
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
        end();
    }
}
