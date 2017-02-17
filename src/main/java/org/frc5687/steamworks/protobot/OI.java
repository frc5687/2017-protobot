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


    public static final int OPEN_GEAR = 8;
    public static final int CLOSE_GEAR = 7;

    public static final int RAISE_PINCERS = 4;
    public static final int LOWER_PINCERS = 3;


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

    private JoystickButton closeGearButton;
    private JoystickButton openGearButton;

    private JoystickButton expandPistonButton;
    private JoystickButton retractPistonButton;

    private JoystickButton ascendClimber;
    private JoystickButton descendClimber;

    private JoystickButton shiftLow;
    private JoystickButton shiftHigh;

    private JoystickButton raisePincers;
    private JoystickButton lowerPincers;

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

        // Pneumatics Commands
        expandPistonButton.whenPressed(new ExpandPiston());
        retractPistonButton.whenPressed(new RetractPiston());

        shiftHigh.whenPressed(new Shift(DoubleSolenoid.Value.kForward));
        shiftLow.whenPressed(new Shift(DoubleSolenoid.Value.kReverse));

        closeGearButton = new JoystickButton(gamepad, CLOSE_GEAR);
        openGearButton = new JoystickButton(gamepad, OPEN_GEAR);

        closeGearButton.whenPressed(new CloseGearHandler());
        openGearButton.whenPressed(new OpenGearHandler());

        raisePincers.whenPressed(new RaisePincers());
        lowerPincers.whenPressed(new LowerPincers());
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
        return closeGearButton.get();
    }

    public boolean isGearOutPressed() {
        return openGearButton.get();
    }

    public boolean isAscendClimberPressed() {
        return ascendClimber.get();
    }

    public boolean isDescendClimberPressed() {
        return descendClimber.get();
    }

}
