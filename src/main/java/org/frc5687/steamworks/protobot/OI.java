package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.composite.DeployDustpan;
import org.frc5687.steamworks.protobot.commands.composite.EjectGear;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;
import org.frc5687.steamworks.protobot.commands.composite.EjectDustpan;
import org.frc5687.steamworks.protobot.utils.AxisButton;
import org.frc5687.steamworks.protobot.utils.Gamepad;
import org.frc5687.steamworks.protobot.utils.Helpers;

/**
 * The operator interface class handles communication with the driver station
 */
public class OI {


    public static final int GP_DEPLOY_DUSTPAN = Gamepad.Axes.LEFT_TRIGGER.getNumber();
    public static final int GP_EJECT_GEAR = Gamepad.Axes.RIGHT_TRIGGER.getNumber();

    public static final int GP_RECEIVE_MANDIBLES = Gamepad.Buttons.A.getNumber();
    public static final int GP_AUTO_CLIMB = Gamepad.Buttons.Y.getNumber();
    public static final int GP_SLOW_CLIMB = Gamepad.Buttons.X.getNumber();
    public static final int GP_FAST_CLIMB = Gamepad.Buttons.B.getNumber();

    public static final int GP_GIMME_LEFT = Gamepad.Buttons.LEFT_STICK.getNumber();
    public static final int GP_GIMME_RIGHT = Gamepad.Buttons.RIGHT_STICK.getNumber();


    public static final int OPEN_PINCERS = 5;
    public static final int CLOSE_PINCERS = 6;

    public static final int OC_DEPLOY_DUSTPAN = 3;
    public static final int OC_RECEIVE_MANDIBLES = 4;
    public static final int OC_RELEASE_PINCERS = 5;
    public static final int OC_EJECT_MANDIBLES = 6;

    public static final int OC_AUTO_CLIMB = 12;
    public static final int OC_SLOW_CLIMB = 11;
    public static final int OC_FAST_CLIMB = 10;
    public static final int OC_MANUAL_CLIMB = 9;

    public static final int OC_TOGGLE_RINGLIGHT = 8;


    private Gamepad gamepad;
    private Joystick operatorConsole;

    public AxisButton gpEjectGearButton;
    public JoystickButton gpReceiveMandiblesButton;
    public AxisButton gpDeployDustpan;
    public JoystickButton gpAutoClimb;
    public JoystickButton gpFastClimb;
    public JoystickButton gpSlowClimb;

    public JoystickButton ocToggleRinglight;

    private JoystickButton shiftLow;
    private JoystickButton shiftHigh;

    private JoystickButton gimmeGearLeft;
    private JoystickButton gimmeGearRight;


    public JoystickButton ocReceiveMandiblesButton;
    public JoystickButton ocEjectMandiblesButton;

    public JoystickButton ocDeployDustpan;

    public JoystickButton ocEjectDustpan;
    public JoystickButton ocAutoClimb;
    public JoystickButton ocSlowClimb;
    public JoystickButton ocFastClimb;
    public JoystickButton ocManualClimb;


    private JoystickButton gearWiggle;




    public OI() {
        gamepad = new Gamepad(0);
        operatorConsole = new Joystick(1);

        /*
         * X Box Gamepad Buttons
         */


        shiftLow = new JoystickButton(gamepad, Gamepad.Buttons.LEFT_BUMPER.getNumber());
        shiftHigh = new JoystickButton(gamepad, Gamepad.Buttons.RIGHT_BUMPER.getNumber());

        gpEjectGearButton = new AxisButton(gamepad, GP_EJECT_GEAR, Constants.OI.AXIS_BUTTON_THRESHHOLD);
        gpReceiveMandiblesButton = new JoystickButton(gamepad, GP_RECEIVE_MANDIBLES);

        gpDeployDustpan = new AxisButton(gamepad, GP_DEPLOY_DUSTPAN, Constants.OI.AXIS_BUTTON_THRESHHOLD);

        shiftHigh.whenPressed(new Shift(DoubleSolenoid.Value.kForward));
        shiftLow.whenPressed(new Shift(DoubleSolenoid.Value.kReverse));

        gpReceiveMandiblesButton.whenPressed(new ReceiveMandibles());
        gpEjectGearButton.whenPressed(new EjectGear());

        gpDeployDustpan.whenPressed(new DeployDustpan());


        /*
         * Operator Console Buttons
         */

        ocReceiveMandiblesButton = new JoystickButton(operatorConsole, OC_RECEIVE_MANDIBLES);
        ocEjectMandiblesButton = new JoystickButton(operatorConsole, OC_EJECT_MANDIBLES);

        ocDeployDustpan = new JoystickButton(operatorConsole, OC_DEPLOY_DUSTPAN);
        ocEjectDustpan = new JoystickButton(operatorConsole, OC_RELEASE_PINCERS);


        ocToggleRinglight = new JoystickButton(operatorConsole, OC_TOGGLE_RINGLIGHT);
        /*
         * Button Functions
         */


        ocReceiveMandiblesButton.whenPressed(new ReceiveMandibles());
        ocEjectMandiblesButton.whenPressed(new EjectMandibles());

        ocDeployDustpan.whenPressed(new DeployDustpan());

        ocEjectDustpan.whenPressed(new EjectDustpan());
        //gpReleasePincers.whenPressed(new ReleasePincers());
        gimmeGearLeft = new JoystickButton(gamepad, GP_GIMME_LEFT);
        gimmeGearRight = new JoystickButton(gamepad, GP_GIMME_RIGHT);

        gimmeGearLeft.whileHeld(new GimmeGear());
        gimmeGearRight.whileHeld(new GimmeGear());

        gpAutoClimb = new JoystickButton(gamepad, GP_AUTO_CLIMB);
        gpSlowClimb = new JoystickButton(gamepad, GP_SLOW_CLIMB);
        gpFastClimb = new JoystickButton(gamepad, GP_FAST_CLIMB);

        ocAutoClimb = new JoystickButton(operatorConsole, OC_AUTO_CLIMB);
        ocSlowClimb = new JoystickButton(operatorConsole, OC_SLOW_CLIMB);
        ocFastClimb = new JoystickButton(operatorConsole, OC_FAST_CLIMB);
        ocManualClimb = new JoystickButton(operatorConsole, OC_MANUAL_CLIMB);

        gpAutoClimb.toggleWhenPressed(new AutoClimb());
        gpSlowClimb.toggleWhenPressed(new Climb(Constants.Climber.PICKUP_SPEED));
        gpFastClimb.toggleWhenPressed(new Climb(Constants.Climber.ASCEND_SPEED));

        ocAutoClimb.toggleWhenPressed(new AutoClimb());
        ocSlowClimb.toggleWhenPressed(new Climb(Constants.Climber.PICKUP_SPEED));
        ocFastClimb.toggleWhenPressed(new Climb(Constants.Climber.ASCEND_SPEED));
        ocManualClimb.toggleWhenPressed(new RunClimberManually());

        ocToggleRinglight.toggleWhenPressed(new RunRingLight());
    }

    private double transformStickToSpeed(Gamepad.Axes stick) {
        double result = gamepad.getRawAxis(stick) * -1;
        result = Helpers.applyDeadband(result, Constants.Deadbands.DRIVE_STICK);
        result = Helpers.applySensitivityTransform(result);
        return result;
    }

    public double getLeftSpeed() {
        double result = transformStickToSpeed(Gamepad.Axes.LEFT_Y);
        SmartDashboard.putNumber("OI/LeftSpeed", result);
        return result;
    }

    public double getRightSpeed() {
        double result = transformStickToSpeed(Gamepad.Axes.RIGHT_Y);
        SmartDashboard.putNumber("OI/RIghtSpeed", result);
        return result;
    }

    public boolean isGearInPressed() {
        return gpReceiveMandiblesButton.get();
    }

    public boolean isGearOutPressed() {
        return gpEjectGearButton.get();
    }

    public boolean isAscendClimberPressed() {
        return false;
    }

    public boolean isDescendClimberPressed() {
        return false;
    }

    public boolean isGearWigglePressed() {
        return ocReceiveMandiblesButton.get() || gpReceiveMandiblesButton.get();
    }

    public boolean isDeployPincersPressed() {
        return ocDeployDustpan.get() || gpDeployDustpan.get();
    }

    public boolean isEjectGearPressed() {
        return ocEjectMandiblesButton.get() || gpEjectGearButton.get();
    }

    public double getPincerSpeed() {
        double result = -operatorConsole.getAxis(Joystick.AxisType.kY);
        result = Helpers.applyDeadband(result, Constants.Deadbands.DRIVE_STICK);
        result *= 0.5;
        return result;
    }

    public boolean isGimmeGearPressed() {
        return gimmeGearLeft.get() || gimmeGearRight.get();
    }

    public boolean isgpButtonPressed(int gpButton) {
        return gamepad.getRawButton(gpButton);
    }

    public boolean isocButtonPressed(int ocButton) {
        return operatorConsole.getRawButton(ocButton);
    }

    public boolean isAutoClimbPressed() {
        return ocAutoClimb.get() || gpAutoClimb.get();
    }

    public boolean isReleasePincersPressed() {
        return ocEjectDustpan.get() || gpEjectGearButton.get();
    }

    public double getClimberSpeed() {
        return (1 - operatorConsole.getAxis(Joystick.AxisType.kThrottle))/2;
    }
}
