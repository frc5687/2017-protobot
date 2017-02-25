package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 2/23/2017.
 */
public class TestDriveTrain extends Command {
    private static double kTOLERANCE = 0.1;
    private double _runSpeed;
    private int _runMillis;
    private double _targetAmps;
    private int _targetTicks;

    private State _state = State.FRONTRIGHT;
    private long _endMillis;

    private double _maxAmps = 0;
//1.0, 1000, 2, 4000
    public TestDriveTrain(double runSpeed) {
        _runSpeed = 1.0;
        _runMillis = 1000;
        _targetAmps = 2;
        _targetTicks = 4000;
    }

    @Override
    protected void initialize() {
        _state = State.FRONTRIGHT;
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
    }

    @Override
    protected void execute() {

        switch (_state) {
            case FRONTRIGHT:
                _maxAmps = Math.max(_maxAmps, pdp.getRightFrontAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.FRONTLEFT;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case FRONTLEFT:
                _maxAmps = Math.max(_maxAmps, pdp.getLeftFrontAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.TOPRIGHT;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case TOPRIGHT:
                _maxAmps = Math.max(_maxAmps, pdp.getRightTopAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.TOPLEFT;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case TOPLEFT:
                _maxAmps = Math.max(_maxAmps, pdp.getLeftTopAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.TOPLEFT;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
        }

    }

    @Override
    protected void end() {
        boolean pass=true;
        if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
            pass = false;
            DriverStation.reportError("Target amperage not reached on " + _description + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
        }
        if (Math.abs((_targetTicks - _encoder.get()) / _targetTicks) > kTOLERANCE) {
            pass = false;
            DriverStation.reportError("Target ticks not reached on " + _description + ".  Expected " + _targetTicks + " but measured " + _encoder.get() + ".", false);
        }
    }

    @Override
    protected boolean isFinished() {
        return _state == State.DONE;
    }


    private enum State {
        FRONTRIGHT,
        FRONTLEFT,
        TOPRIGHT,
        TOPLEFT,
        REARRIGHT,
        REARLEFT,
        DONE;
    }

}
