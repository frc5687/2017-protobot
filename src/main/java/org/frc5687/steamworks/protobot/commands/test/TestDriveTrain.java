package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 2/23/2017.
 */
public class TestDriveTrain extends Command {
    private static double kTOLERANCE = 0.25;
    private double _runSpeed;
    private int _runMillis;
    private double _targetAmps;
    private int _targetTicks;

    private State _state = State.RIGHTFRONT;
    private long _endMillis;

    private double _maxAmps = 0;

    public TestDriveTrain() {
        _runSpeed = 1.0;
        _runMillis = 1000;
        _targetAmps = 10;
        _targetTicks = 1400;
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Starting drivetrain test", false);
        _state = State.RIGHTFRONT;
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
        driveTrain.resetDriveEncoders();
    }
    boolean pass=true;

    @Override
    protected void execute() {

        switch (_state) {
            case RIGHTFRONT:
                driveTrain.runRightFrontMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getRightFrontAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.RIGHTFRONTDONE;
                }
                break;
            case RIGHTFRONTDONE:
                driveTrain.runRightFrontMotor(0);
                report("right front", driveTrain.getRightTicks());
                _state = State.RIGHTTOP;
                break;

            case RIGHTTOP:
                driveTrain.runRightTopMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getRightTopAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.RIGHTTOPDONE;
                }
                break;
            case RIGHTTOPDONE:
                driveTrain.runRightFrontMotor(0);
                report("right top", driveTrain.getRightTicks());
                _state = State.RIGHTREAR;
                break;

            case RIGHTREAR:
                driveTrain.runRightRearMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getRightRearAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.RIGHTREARDONE;
                }
                break;
            case RIGHTREARDONE:
                driveTrain.runRightRearMotor(0);
                report("right rear", driveTrain.getRightTicks());
                _state = State.LEFTFRONT;
                break;

            case LEFTFRONT:
                driveTrain.runLeftFrontMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getLeftFrontAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.LEFTFRONTDONE;
                }
                break;
            case LEFTFRONTDONE:
                driveTrain.runLeftFrontMotor(0);
                report("left front", driveTrain.getLeftTicks());
                _state = State.LEFTTOP;
                break;

            case LEFTTOP:
                driveTrain.runLeftTopMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getLeftTopAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.LEFTTOPDONE;
                }
                break;
            case LEFTTOPDONE:
                driveTrain.runLeftFrontMotor(0);
                report("left top", driveTrain.getLeftTicks());
                _state = State.LEFTREAR;
                break;

            case LEFTREAR:
                driveTrain.runLeftRearMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getLeftRearAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.LEFTREARDONE;
                }
                break;
            case LEFTREARDONE:
                driveTrain.runLeftRearMotor(0);
                report("left rear", driveTrain.getLeftTicks());
                _state = State.DONE;
                break;
        }

    }
    private void report(String side, long ticks) {
        if (_maxAmps < _targetAmps) {
            pass = false;
            DriverStation.reportError("Target amperage not reached on " + side  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
        }
        if (Math.abs((_targetTicks - ticks) / _targetTicks) > kTOLERANCE) {
            pass = false;
            DriverStation.reportError("Target ticks not reached on " + side + ".  Expected " + _targetTicks + " but measured " + ticks + ".", false);
        }
        driveTrain.resetDriveEncoders();
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
    }
    @Override
    protected void end() {
    }

    @Override
    protected boolean isFinished() {
        return _state == State.DONE;
    }


    private enum State {
        RIGHTFRONT,
        RIGHTFRONTDONE,
        RIGHTTOP,
        RIGHTTOPDONE,
        RIGHTREAR,
        RIGHTREARDONE,
        LEFTFRONT,
        LEFTFRONTDONE,
        LEFTTOP,
        LEFTTOPDONE,
        LEFTREAR,
        LEFTREARDONE,
        DONE
    }

}
