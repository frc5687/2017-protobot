package org.frc5687.steamworks.protobot.commands.actions.climber;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class PickupRope extends Command {


    public PickupRope() {
        requires(climber);
    }


    @Override
    protected void initialize() {
        DriverStation.reportError("Starting pickup.", false);
    }

    @Override
    protected void execute() {
        SmartDashboard.putString("Climber/Climb/State", "Pickup");
        climber.setSpeed(Constants.Climber.PICKUP_SPEED);
    }
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        climber.setSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
