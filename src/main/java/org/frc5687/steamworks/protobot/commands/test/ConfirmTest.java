package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

import java.sql.Driver;

import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command to prompt the operator to confirm a result using the gamepad buttons.
 */
public class ConfirmTest extends Command {

    private String _message;
    private String _success;
    private String _failure;
    private boolean _clear;

    public ConfirmTest(String message, String success, String failure) {
        _message = message;
        _success = success;
        _failure = failure;
        _clear = false;
    }

    @Override
    protected void initialize() {
        DriverStation.reportWarning(_message, false);
    }

    @Override
    protected void execute() {
        if (!_clear) {
            _clear = !(oi.isNoPressed() || oi.isYesPressed());
        }
    }

    @Override
    protected boolean isFinished() {
        if (_clear) {
            if (oi.isYesPressed()) {
                DriverStation.reportWarning(_success, false);
                return true;
            } else if (oi.isNoPressed()) {
                DriverStation.reportError(_failure, false);
                return true;
            }
        }
        return false;
    }
}
