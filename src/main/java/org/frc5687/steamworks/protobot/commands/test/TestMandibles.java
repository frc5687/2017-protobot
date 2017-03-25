package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.subsystems.Mandibles;


import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Caleb on 3/8/2017.
 */

public class TestMandibles extends Command {
    private double _runSpeed;
    private int _runMillis = 1000;
    private double _targetAmps = 1;
    private double kTolerance = 0.5;
    private double _maxAmps;
    public long _endMillis =System.currentTimeMillis() + _runMillis;
    public double _minAmps;


    public TestMandibles(double runSpeed) {
        _runSpeed = runSpeed;
        _runMillis = 1000;
        _targetAmps = 6;

    }

    @Override
    protected void initialize() {
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
        mandibles.setSpeed(_runSpeed);
    }

    @Override
    protected void execute() {
        _maxAmps = Math.max(_maxAmps, pdp.getMandiblesAmps());
    }

    @Override
    protected boolean isFinished() {
        return (System.currentTimeMillis() > _endMillis);
    }

    @Override
    protected void end() {
        if(pdp.getMandiblesAmps()<1){
            DriverStation.reportError("Mandibles not responding", false);
        }
        if(_maxAmps>_targetAmps+_targetAmps*kTolerance){
            DriverStation.reportError("Mandibles high amperage", false);
        }

    }
    public void interrupted() {
        end();
    }
}