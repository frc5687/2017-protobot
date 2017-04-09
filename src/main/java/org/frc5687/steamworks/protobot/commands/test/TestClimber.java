package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.subsystems.Climber;

import static org.frc5687.steamworks.protobot.Robot.climber;
import static org.frc5687.steamworks.protobot.Robot.ledStrip;

/**
 * Created by Caleb on 3/8/2017.
 */

public class TestClimber extends Command {
    private int _runMillis = 2000;
    private double _slowAmps;
    private double _fastAmps;
    private double _slowAmpsTarget=2.0;
    private double _fastAmpsTarget=3.0;

    private State _state;
    public long _endMillis;


    public TestClimber() {
        requires(climber);
    }

    @Override
    protected void initialize() {
        _slowAmps = 0;
        _fastAmps = 0;
        _endMillis = System.currentTimeMillis() + _runMillis;
        _state = State.SLOW_RUN;

    }

    @Override
    protected void execute() {
        switch (_state) {
            case SLOW_RUN:
                climber.setSpeed(Constants.Climber.PICKUP_SPEED);
                _slowAmps = Math.max(_slowAmps, climber.getAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _endMillis = System.currentTimeMillis() + _runMillis;
                    _state = State.FAST_RUN;
                }
                break;
            case FAST_RUN:
                climber.setSpeed(Constants.Climber.ASCEND_SPEED);
                _fastAmps = Math.max(_fastAmps, climber.getAmps());
                if (System.currentTimeMillis() > _endMillis) {
                    _state = State.DONE;
                }
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return _state == State.DONE;
    }

    @Override
    protected void end() {
        boolean pass = true;
        climber.setSpeed(0);
        if (_slowAmps >= _slowAmpsTarget) {
            DriverStation.reportError("Climber pickup reached target amps (" + _slowAmps + ")", false);
            SmartDashboard.putBoolean("SelfTest/Climber/Pickup/Passed", true);
        } else {
            DriverStation.reportError("Climber pickup failed to reach target amps (" + _slowAmps + ")", false);
            SmartDashboard.putBoolean("SelfTest/Climber/Pickup/Passed", false);
            pass = false;
        }
        SmartDashboard.putNumber("SelfTest/Climber/Pickup/Amps", _slowAmps);
        if (_fastAmps >= _fastAmpsTarget) {
            DriverStation.reportError("Climber ascend reached target amps (" + _fastAmps + ")", false);
            SmartDashboard.putBoolean("SelfTest/Climber/Ascend/Passed", true);
        } else {
            DriverStation.reportError("Climber ascend failed to reach target amps (" + _fastAmps + ")", false);
            SmartDashboard.putBoolean("SelfTest/Climber/Ascend/Passed", false);
            pass = false;
        }
        SmartDashboard.putNumber("SelfTest/Climber/Ascend/Amps", _slowAmps);
        ledStrip.setStripColor(pass ? LEDColors.TEST_PASSED : LEDColors.TEST_FAILED);
    }
    public void interrupted() {
        end();
    }

    private enum State {
        SLOW_RUN,
        FAST_RUN,
        DONE
    }

}