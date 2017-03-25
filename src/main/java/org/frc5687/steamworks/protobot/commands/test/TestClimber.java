package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.subsystems.Climber;

import static org.frc5687.steamworks.protobot.Robot.climber;

/**
 * Created by Caleb on 3/8/2017.
 */

public class TestClimber extends Command {
    private double _runSpeed;
    private int _runMillis;
    private double _targetAmps;
    private double kTolerance = 0.5;
    private double _maxAmps;
    public long _endMillis;
    public double _minAmps;


    public TestClimber(double runSpeed) {
        _runSpeed = runSpeed;
        _runMillis = 1000;
        _targetAmps = 6;

    }

    @Override
    protected void initialize() {
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
        climber.setSpeed(_runSpeed);
    }

    @Override
    protected void execute() {
    _maxAmps = Math.max(_maxAmps,climber.getAmps());
    }

    @Override
    protected boolean isFinished() {
        return (System.currentTimeMillis() > _endMillis);
    }

    @Override
    protected void end() {
        if(climber.getAmps()<2){
            DriverStation.reportError("Climber not responding", false);
        }
        if(_maxAmps>_targetAmps+_targetAmps*kTolerance){
            DriverStation.reportError("Climber high amperage", false);
        }

    }
    public void interrupted() {
        end();
    }
}