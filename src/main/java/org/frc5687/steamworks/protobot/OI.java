package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class OI {
    private Gamepad gamepad;
    boolean isReversed =Constants.Encoders.Defaults.REVERSED;


    public static final int REVERSE = Gamepad.Buttons.BACK.getNumber();

//Gear buttons
    public static final int GEAR_IN = 5;  // Green button
    public static final int GEAR_OUT = 6; // Yellow



    public static final int EXPAND_PISTON = 2;
    public static final int RETRACT_PISTON = 1;

    private JoystickButton gearInButton;
    private JoystickButton gearOutButton;
    private JoystickButton cancelButton;

    private JoystickLight capturedLight;
    private JoystickLight intakeInLight;
    private JoystickLight intakeOutLight;

    private JoystickButton leftAccel;
    private JoystickButton rightAccel;

    private JoystickButton expandPistonButton;
    private JoystickButton retractPistonButton;
    public OI() {
        gamepad = new Gamepad(0);

    }
    private double transformStickToSpeed(Gamepad.Axes stick) {
        double result = gamepad.getRawAxis(stick);
        result = Helpers.applyDeadband(result, Constants.Deadbands.DRIVE_STICK);
        result = Helpers.applySensitivityTransform(result);
        return result;
    }
    public double getLeftSpeed(){
        return transformStickToSpeed(Gamepad.Axes.LEFT_Y);

    }
    public double getRightSpeed(){
        return transformStickToSpeed(Gamepad.Axes.RIGHT_Y);
    }

    public boolean isGearInPressed() {
        return gearInButton.get();
    }

    public boolean isGearOutPressed() {
        return gearOutButton.get();
    }

}
