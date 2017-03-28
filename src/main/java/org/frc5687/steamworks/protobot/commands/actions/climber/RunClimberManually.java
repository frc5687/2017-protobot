package org.frc5687.steamworks.protobot.commands.actions.climber;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.frc5687.steamworks.protobot.Robot.climber;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class RunClimberManually extends Command {

    public RunClimberManually() {
        requires(climber);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        double speed = oi.getClimberSpeed();
        SmartDashboard.putNumber("Climber/ManualSpeed", speed);
        climber.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        DriverStation.reportError("Stopping climber.", false);
        climber.setSpeed(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
