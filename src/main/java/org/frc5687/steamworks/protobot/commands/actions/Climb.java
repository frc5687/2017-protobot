package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.frc5687.steamworks.protobot.Robot.climber;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class Climb extends Command {
    private double _speed;

    public Climb(double speed) {
        requires(climber);
        _speed = speed;
    }


    @Override
    protected void initialize() {
        DriverStation.reportError("Starting climber at speed " + _speed, false);
    }

    @Override
    protected void execute() {
        climber.setSpeed(_speed);
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
