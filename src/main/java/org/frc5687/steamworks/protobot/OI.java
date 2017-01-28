package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.frc5687.steamworks.protobot.utils.Gamepad;
import org.frc5687.steamworks.protobot.utils.Helpers;
import org.frc5687.steamworks.protobot.commands.ExpandPiston;
import org.frc5687.steamworks.protobot.commands.RetractPiston;
import org.frc5687.steamworks.protobot.commands.OpenGearHandler;
import org.frc5687.steamworks.protobot.commands.CloseGearHandler;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class OI {
    private Gamepad gamepad;
    private Joystick joystick;

    boolean isReversed =Constants.Encoders.Defaults.REVERSED;
    public static final int OPEN_GEAR = 1;  // Green button
    public static final int CLOSE_GEAR = 2; // Yellow

    public static final int REVERSE = Gamepad.Buttons.BACK.getNumber();

    /**
     * Gear buttons
     */
    public static final int GEAR_IN = 5;  // Green button
    public static final int GEAR_OUT = 6; // Yellow

    /**
     * Pneumatic buttons
     */
    public static final int EXPAND_PISTON = 2;
    public static final int RETRACT_PISTON = 1;

    /**
     * Climber buttons
     */
    public static final int ASCEND_CLIMBER = 0;
    public static final int DESCEND_CLIMBER = 0;
    public static final int STOP_CLIMBER = 0;

    private JoystickButton gearInButton;
    private JoystickButton gearOutButton;

    private JoystickButton expandPistonButton;
    private JoystickButton retractPistonButton;

    private JoystickButton ascendClimber;
    private JoystickButton descendClimber;

    public OI() {
        gamepad = new Gamepad(0);
        joystick = new Joystick(1);

        // Joystick Buttons
        gearInButton = new JoystickButton(joystick, GEAR_IN);
        gearOutButton = new JoystickButton(joystick, GEAR_OUT);

        expandPistonButton = new JoystickButton(joystick, EXPAND_PISTON);
        retractPistonButton = new JoystickButton(joystick, RETRACT_PISTON);

        ascendClimber = new JoystickButton(joystick, ASCEND_CLIMBER);
        descendClimber = new JoystickButton(joystick, DESCEND_CLIMBER);

        // Pneumatics Commands
        expandPistonButton.whenPressed(new ExpandPiston());
        retractPistonButton.whenPressed(new RetractPiston());

        gearInButton = new JoystickButton(joystick, CLOSE_GEAR);
        gearOutButton = new JoystickButton(joystick,OPEN_GEAR);

        gearInButton.whenPressed(new CloseGearHandler());
        gearOutButton.whenPressed(new OpenGearHandler());
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

    public boolean isAscendClimberPressed() {
        return ascendClimber.get();
    }

    public boolean isDescendClimberPressed() {
        return descendClimber.get();
    }

}
