package org.frc5687.steamworks.protobot.commands.actions.climber;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.*;
import static org.opencv.features2d.FeatureDetector.FAST;

/**
 * Created by Ben Bernard on 2/28/2017.
 */
public class AutoClimb extends Command {

    private State _state;
    private long _startupEndMillis;

    public AutoClimb() {
        requires(climber);
    }


    @Override
    protected void initialize() {
        _state = State.STARTUP;
        _startupEndMillis = System.currentTimeMillis() + Constants.Climber.STARTUP_MILLIS;
        DriverStation.reportError("Starting autoclimb.", false);
    }

    @Override
    protected void execute() {
        SmartDashboard.putString("Climber/Climb/State", _state.toString());
        switch (_state) {
            case STARTUP:
                climber.setSpeed(Constants.Climber.PICKUP_SPEED);
                if (System.currentTimeMillis() > _startupEndMillis) {
                    _state = State.PICKUP;
                }
                break;
            case PICKUP:
                climber.setSpeed(Constants.Climber.PICKUP_SPEED);
                ledStrip.setClimberRunning(true);
                if (pdp.getMeanClimberAmps() > Constants.Climber.HAVE_ROPE_AMPS) {
                    DriverStation.reportError("Saw amps at " + pdp.getMeanClimberAmps() + ". Moving to CLIMB.", false);
                    _state = State.CLIMB;
                }
                break;
            case CLIMB:
                climber.setSpeed(Constants.Climber.ASCEND_SPEED);
                ledStrip.setClimbing(true);
                if (pdp.getMeanClimberAmps() > Constants.Climber.REACHED_TOP_AMPS) {
                    DriverStation.reportError("Saw amps at " + pdp.getMeanClimberAmps() + ". Moving to REACHED_TOP.", false);
                    _state = State.REACHED_TOP;
                } else if (pdp.getMeanClimberAmps() < Constants.Climber.REACHED_TOP_AMPS) {
                    DriverStation.reportError("Saw amps at " + pdp.getMeanClimberAmps() + ". Moving to LOST_GRIP.", false);
                    _state = State.LOST_GRIP;
                }
                break;
            case REACHED_TOP:
                climber.setSpeed(Constants.Climber.ASCEND_SPEED);
                ledStrip.setAtTop(true);
                break;
            case LOST_GRIP:
                climber.setSpeed(Constants.Climber.ASCEND_SPEED);
                ledStrip.setClimberRunning(true);
                break;
        }
    }

    public enum State {
        STARTUP,
        PICKUP,
        CLIMB,
        LOST_GRIP,
        REACHED_TOP
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        climber.setSpeed(0);
        ledStrip.setClimberRunning(false);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
