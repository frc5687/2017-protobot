package org.frc5687.steamworks.protobot;

import org.frc5687.steamworks.protobot.Utils.Gamepad;
import org.frc5687.steamworks.protobot.Utils.Helpers;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class OI {
    private Gamepad gamepad;

    public static final int REVERSE = Gamepad.Buttons.BACK.getNumber();

    public OI() {
        gamepad = new Gamepad(0);

    }
    private double transformStickToSpeed(Gamepad.Axes stick) {
        double result = gamepad.getRawAxis(stick);
        result = Helpers.applyDeadband(result, Constants.Deadbands.DRIVE_STICK);
        result = Helpers.applySensitivityTransform(result);
        return result;
}
