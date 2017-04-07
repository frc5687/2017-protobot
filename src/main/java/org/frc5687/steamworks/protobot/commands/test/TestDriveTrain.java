package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.LEDColors;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.ledStrip;
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
        requires(driveTrain);
        _runSpeed = 1.0;
        _runMillis = 2000;
        _targetAmps = 3;
        _targetTicks = 50;
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
                ledStrip.setStripColor(LEDColors.TEST_RUNNING);
                driveTrain.runRightFrontMotor(_runSpeed);
                _maxAmps = Math.max(_maxAmps, pdp.getRightFrontAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    driveTrain.runRightFrontMotor(0);
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
                    driveTrain.runRightTopMotor(0);
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
                    driveTrain.runRightRearMotor(_runSpeed);
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
                    driveTrain.runLeftFrontMotor(0);
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
                    driveTrain.runLeftTopMotor(0);
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
                    driveTrain.runLeftRearMotor(0);
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
            SmartDashboard.putBoolean("SelfTest/Drivetrain/" + side + "/Amps/Passed", false);
            DriverStation.reportError("Target amperage not reached on " + side  + ".  Expected " + _targetAmps + " but measured " + _maxAmps + ".", false);
        } else {
            SmartDashboard.putBoolean("SelfTest/Drivetrain/" + side + "/Amps/Passed", true);
            DriverStation.reportError("Amp draw passed on " + side  + ".  Expected " + _targetAmps + " and measured  " + _maxAmps + ".", false);
        }
        SmartDashboard.putNumber("SelfTest/Drivetrain/" + side + "/Amps/Measured", _maxAmps);
        if (ticks < _targetTicks) {
            pass = false;
            SmartDashboard.putBoolean("SelfTest/Drivetrain/" + side + "/Ticks/Passed", false);
            DriverStation.reportError("Target ticks not reached on " + side + ".  Expected " + _targetTicks + " but measured " + ticks + ".", false);
        } else {
            SmartDashboard.putBoolean("SelfTest/Drivetrain/" + side + "/Ticks/Passed", true);
            DriverStation.reportError("Target ticks reached on " + side + ".  Expected " + _targetTicks + " and measured " + ticks + ".", false);
        }
        ledStrip.setStripColor(pass ? LEDColors.TEST_PASSED : LEDColors.TEST_FAILED);

        SmartDashboard.putNumber("SelfTest/Drivetrain/" + side + "/Ticks/Measured", ticks);
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
