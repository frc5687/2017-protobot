package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.frc5687.steamworks.protobot.commands.*;
import org.frc5687.steamworks.protobot.utils.Gamepad;
import org.frc5687.steamworks.protobot.utils.Helpers;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class OI {
    private Gamepad gamepad;
    private Joystick joystick;

    boolean isReversed = Constants.Encoders.Defaults.REVERSED;


    public static final int GP_OPEN_GEAR = 8;
    public static final int GP_CLOSE_GEAR = 7;

    public static final int JS_OPEN_GEAR = 8;
    public static final int JS_CLOSE_GEAR = 7;

    public static final int RAISE_PINCERS = 4;
    public static final int LOWER_PINCERS = 3;

    public static final int OPEN_PINCERS = 5;
    public static final int CLOSE_PINCERS = 6;

    public static final int RINGLIGHT_ON = 11;
    public static final int RINGLIGHT_OFF = 12;

    public static final int REVERSE = Gamepad.Buttons.BACK.getNumber();


    /**
     * Pneumatic buttons
     */
    public static final int EXPAND_PISTON = 2;
    public static final int RETRACT_PISTON = 1;

    /**
     * Shifter buttons
     */
    public static final int LOW_GEAR = 2;
    public static final int HIGH_GEAR = 1;

    private JoystickButton gpCloseGearButton;
    private JoystickButton gpOpenGearButton;

    private JoystickButton jsCloseGearButton;
    private JoystickButton jsOpenGearButton;

    private JoystickButton expandPistonButton;
    private JoystickButton retractPistonButton;

    private JoystickButton ascendClimber;
    private JoystickButton descendClimber;

    private JoystickButton shiftLow;
    private JoystickButton shiftHigh;

    private JoystickButton raisePincers;
    private JoystickButton lowerPincers;

    private JoystickButton openPincers;
    private JoystickButton closePincers;

    private JoystickButton ringLightOn;
    private JoystickButton ringLightOff;


    public OI() {
        gamepad = new Gamepad(0);
        joystick = new Joystick(1);

        // Joystick Buttons
        expandPistonButton = new JoystickButton(joystick, EXPAND_PISTON);
        retractPistonButton = new JoystickButton(joystick, RETRACT_PISTON);

        ascendClimber = new JoystickButton(gamepad, Gamepad.Buttons.Y.getNumber());
        descendClimber = new JoystickButton(gamepad, Gamepad.Buttons.X.getNumber());

        shiftLow = new JoystickButton(gamepad, Gamepad.Buttons.LEFT_BUMPER.getNumber());
        shiftHigh = new JoystickButton(gamepad, Gamepad.Buttons.RIGHT_BUMPER.getNumber());

        raisePincers = new JoystickButton(joystick, RAISE_PINCERS);
        lowerPincers = new JoystickButton(joystick, LOWER_PINCERS);

        openPincers = new JoystickButton(joystick, OPEN_PINCERS);
        closePincers = new JoystickButton(joystick, CLOSE_PINCERS);

        // Pneumatics Commands
        expandPistonButton.whenPressed(new ExpandPiston());
        retractPistonButton.whenPressed(new RetractPiston());

        shiftHigh.whenPressed(new Shift(DoubleSolenoid.Value.kForward));
        shiftLow.whenPressed(new Shift(DoubleSolenoid.Value.kReverse));

        gpCloseGearButton = new JoystickButton(gamepad, GP_CLOSE_GEAR);
        gpOpenGearButton = new JoystickButton(gamepad, GP_OPEN_GEAR);

        gpCloseGearButton.whenPressed(new CloseGearHandler());
        gpOpenGearButton.whenPressed(new OpenGearHandler());

        jsCloseGearButton = new JoystickButton(joystick, JS_CLOSE_GEAR);
        jsOpenGearButton = new JoystickButton(joystick, JS_OPEN_GEAR);

        jsCloseGearButton.whenPressed(new CloseGearHandler());
        jsOpenGearButton.whenPressed(new OpenGearHandler());

        raisePincers.whenPressed(new RaisePincers());
        lowerPincers.whenPressed(new LowerPincers());

        openPincers.whenPressed(new OpenPincers());
        closePincers.whenPressed(new ClosePincers());

        ringLightOn = new JoystickButton(joystick, RINGLIGHT_ON);
        ringLightOff = new JoystickButton(joystick, RINGLIGHT_OFF);

        ringLightOn.whenPressed(new EnableRingLight());
        ringLightOff.whenPressed(new DisableRingLight());

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

    public boolean isLeftTriggerPressed(){
        return(gamepad.getRawAxis(Gamepad.Axes.LEFT_TRIGGER) > Constants.OI.triggerThreshhold);
    }

    public boolean isRightTriggerPressed(){
        return(gamepad.getRawAxis(Gamepad.Axes.RIGHT_TRIGGER) > Constants.OI.triggerThreshhold);
    }

    public boolean isGearInPressed() {
        return gpCloseGearButton.get();
    }

    public boolean isGearOutPressed() {
        return gpOpenGearButton.get();
    }

    public boolean isAscendClimberPressed() {
        return ascendClimber.get();
    }

    public boolean isDescendClimberPressed() {
        return descendClimber.get();
    }

    public double getPincerSpeed() {
        double result = -joystick.getAxis(Joystick.AxisType.kY);
        return Helpers.applyDeadband(result, Constants.Deadbands.DRIVE_STICK);
    }
}
