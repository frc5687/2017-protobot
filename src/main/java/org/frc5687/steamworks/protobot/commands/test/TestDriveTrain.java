package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
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

    public TestDriveTrain(double runSpeed) {
        _runSpeed = runSpeed;
        _runMillis = 1000;
        _targetAmps = 2;
        _targetTicks = 4000;
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Starting drivetrain test", false);
        _state = State.FRONTRIGHT;
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
    }
    boolean pass=true;

    @Override
    protected void execute() {

        switch (_state) {
            case FRONTRIGHT:
                driveTrain.runFrontRightMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getRightFrontAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.FRONTRIGHTDONE;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case FRONTRIGHTDONE:
                if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target amperage not reached on "  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
                }
                if (Math.abs((_targetTicks - driveTrain.getRightTicks()) / _targetTicks) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target ticks not reached on front right.  Expected " + _targetTicks + " but measured "+driveTrain.getRightTicks() + ".", false);
                }
                _state = State.FRONTLEFT;
                driveTrain.runFrontRightMotor(0);
                _maxAmps = 0;
                break;
            case FRONTLEFT:
                driveTrain.runFrontLeftMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getLeftFrontAmps());
                if (System.currentTimeMillis() > _endMillis){
                    _state = State.FRONTLEFTDONE;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case FRONTLEFTDONE:

                if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target amperage not reached on "  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
                }
                if (Math.abs((_targetTicks - driveTrain.getRightTicks()) / _targetTicks) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target ticks not reached on front right.  Expected " + _targetTicks + " but measured "+driveTrain.getRightTicks() + ".", false);
                }
                driveTrain.runTopLeftMotor(0);
                _state = State.TOPRIGHT;
                _maxAmps = 0;

                break;
            case TOPRIGHT:
                driveTrain.runTopRightMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getRightTopAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.TOPRIGHTDONE;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case TOPRIGHTDONE:
                if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target amperage not reached on "  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
                }
                if (Math.abs((_targetTicks - driveTrain.getRightTicks()) / _targetTicks) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target ticks not reached on front right.  Expected " + _targetTicks + " but measured "+ driveTrain.getRightTicks() + ".", false);
                }
                driveTrain.runTopRightMotor(0);
                _state = State.TOPLEFT;
                _maxAmps = 0;
                break;
            case TOPLEFT:
                driveTrain.runTopLeftMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getLeftTopAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.TOPLEFTDONE;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case TOPLEFTDONE:
                if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target amperage not reached on "  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
                }
                if (Math.abs((_targetTicks - driveTrain.getRightTicks()) / _targetTicks) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target ticks not reached on front right.  Expected " + _targetTicks + " but measured "+ driveTrain.getRightTicks() + ".", false);
                }
                driveTrain.runTopLeftMotor(0);
                _state = State.REARLEFT;
                _maxAmps = 0;
                break;


            case REARLEFT:
                driveTrain.runRearLeftMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getLeftRearAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.REARLEFTDONE;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case REARLEFTDONE:
                if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target amperage not reached on "  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
                }
                if (Math.abs((_targetTicks - driveTrain.getRightTicks()) / _targetTicks) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target ticks not reached on front right.  Expected " + _targetTicks + " but measured "+ driveTrain.getRightTicks() + ".", false);
                }
                _maxAmps = 0;
                driveTrain.runTopLeftMotor(0);
                _state = State.REARRIGHT;
                break;
            case REARRIGHT:
                driveTrain.runRearRightMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getLeftRearAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.REARRIGHTDONE;
                    _endMillis =+ System.currentTimeMillis();
                }
                break;
            case REARRIGHTDONE:
                if (Math.abs((_targetAmps - _maxAmps) / _targetAmps) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target amperage not reached on "  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
                }
                if (Math.abs((_targetTicks - driveTrain.getRightTicks()) / _targetTicks) > kTOLERANCE) {
                    pass = false;
                    DriverStation.reportError("Target ticks not reached on front right.  Expected " + _targetTicks + " but measured " + driveTrain.getRightTicks() + ".", false);
                }
                _maxAmps = 0;
                break;
        }

    }

    @Override
    protected void end() {
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
        FRONTRIGHTDONE,
        FRONTLEFTDONE,
        TOPRIGHTDONE,
        TOPLEFTDONE,
        REARRIGHTDONE,
        REARLEFTDONE,
        DONE;
    }

}
