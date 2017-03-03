package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.commands.actions.*;
import org.frc5687.steamworks.protobot.commands.composite.DeployPincers;
import org.frc5687.steamworks.protobot.commands.composite.EjectGear;
import org.frc5687.steamworks.protobot.commands.composite.EjectMandibles;
import org.frc5687.steamworks.protobot.commands.composite.ReleasePincers;
import org.frc5687.steamworks.protobot.utils.AxisButton;
import org.frc5687.steamworks.protobot.utils.Gamepad;
import org.frc5687.steamworks.protobot.utils.Helpers;

/**
 * The operator interface class handles communication with the driver station
 */
public class OI {


    public static final int GP_DEPLOY_PINCERS = Gamepad.Axes.LEFT_TRIGGER.getNumber();
    public static final int GP_EJECT_GEAR = Gamepad.Axes.RIGHT_TRIGGER.getNumber();

    public static final int GP_RECEIVE_MANDIBLES = Gamepad.Buttons.A.getNumber();
    public static final int GP_AUTO_CLIMB = Gamepad.Buttons.Y.getNumber();
    public static final int GP_SLOW_CLIMB = Gamepad.Buttons.X.getNumber();
    public static final int GP_FAST_CLIMB = Gamepad.Buttons.B.getNumber();

    public static final int GP_GIMME_LEFT = Gamepad.Buttons.LEFT_STICK.getNumber();
    public static final int GP_GIMME_RIGHT = Gamepad.Buttons.RIGHT_STICK.getNumber();


    public static final int OPEN_PINCERS = 5;
    public static final int CLOSE_PINCERS = 6;

    public static final int OC_DEPLOY_PINCERS = 3;
    public static final int OC_RECEIVE_MANDIBLES = 4;
    public static final int OC_RELEASE_PINCERS = 5;
    public static final int OC_EJECT_MANDIBLES = 6;

    public static final int OC_AUTO_CLIMB = 12;



    private Gamepad gamepad;
    private Joystick operatorConsole;

    public AxisButton gpEjectGearButton;
    public JoystickButton gpReceiveMandiblesButton;

    public JoystickButton ocReceiveMandiblesButton;
    public JoystickButton ocEjectGearButton;

    public JoystickButton ocDeployPincers;
    public AxisButton gpDeployPincers;

    public JoystickButton ocReleasePincers;
    //public JoystickButton gpReleasePincers;
    public JoystickButton ocAutoClimb;
    public JoystickButton gpAutoClimb;
    public JoystickButton gpFastClimb;
    public JoystickButton gpSlowClimb;

    private JoystickButton ascendClimber;
    private JoystickButton descendClimber;

    private JoystickButton shiftLow;
    private JoystickButton shiftHigh;

    private JoystickButton openPincers;
    private JoystickButton closePincers;

    private JoystickButton gearWiggle;

    private JoystickButton gimmeGearLeft;
    private JoystickButton gimmeGearRight;



    public OI() {
        gamepad = new Gamepad(0);
        operatorConsole = new Joystick(1);

        /*
         * X Box Gamepad Buttons
         */

        //ascendClimber = new JoystickButton(gamepad, Gamepad.Buttons.Y.getNumber());
        //descendClimber = new JoystickButton(gamepad, Gamepad.Buttons.X.getNumber());

        shiftLow = new JoystickButton(gamepad, Gamepad.Buttons.LEFT_BUMPER.getNumber());
        shiftHigh = new JoystickButton(gamepad, Gamepad.Buttons.RIGHT_BUMPER.getNumber());

        gpEjectGearButton = new AxisButton(gamepad, GP_EJECT_GEAR, .2);
        gpReceiveMandiblesButton = new JoystickButton(gamepad, GP_RECEIVE_MANDIBLES);

        gpDeployPincers = new AxisButton(gamepad, GP_DEPLOY_PINCERS, 0.2);
        // gpReleasePincers = new JoystickButton(gamepad, GP_RELEASE_PINCERS);

        /*
         * Operator Console Buttons
         */

        openPincers = new JoystickButton(operatorConsole, OPEN_PINCERS);
        closePincers = new JoystickButton(operatorConsole, CLOSE_PINCERS);

        ocReceiveMandiblesButton = new JoystickButton(operatorConsole, OC_RECEIVE_MANDIBLES);
        ocEjectGearButton = new JoystickButton(operatorConsole, OC_EJECT_MANDIBLES);

        ocDeployPincers = new JoystickButton(operatorConsole, OC_DEPLOY_PINCERS);
        ocReleasePincers = new JoystickButton(operatorConsole, OC_RELEASE_PINCERS);


        /*
         * Button Functions
         */

        shiftHigh.whenPressed(new Shift(DoubleSolenoid.Value.kForward));
        shiftLow.whenPressed(new Shift(DoubleSolenoid.Value.kReverse));

        gpReceiveMandiblesButton.whenPressed(new ReceiveMandibles());
        gpEjectGearButton.whenPressed(new EjectGear());

        ocReceiveMandiblesButton.whenPressed(new ReceiveMandibles());
        ocEjectGearButton.whenPressed(new EjectMandibles());

//        openPincers.whenPressed(new ClosePincers());
//        closePincers.whenPressed(new OpenPincers());

        ocDeployPincers.whenPressed(new DeployPincers());
        gpDeployPincers.whenPressed(new DeployPincers());

        ocReleasePincers.whenPressed(new ReleasePincers());
        //gpReleasePincers.whenPressed(new ReleasePincers());
        gimmeGearLeft = new JoystickButton(gamepad, GP_GIMME_LEFT);
        gimmeGearRight = new JoystickButton(gamepad, GP_GIMME_RIGHT);

        gimmeGearLeft.whileHeld(new GimmeGear());
        gimmeGearRight.whileHeld(new GimmeGear());

        gpAutoClimb = new JoystickButton(gamepad, GP_AUTO_CLIMB);
        gpSlowClimb = new JoystickButton(gamepad, GP_SLOW_CLIMB);
        gpFastClimb = new JoystickButton(gamepad, GP_FAST_CLIMB);
        ocAutoClimb = new JoystickButton(operatorConsole, OC_AUTO_CLIMB);

        gpAutoClimb.toggleWhenPressed(new AutoClimb());
        ocAutoClimb.toggleWhenPressed(new AutoClimb());
        gpSlowClimb.toggleWhenPressed(new Climb(Constants.Climber.PICKUP_SPEED));
        gpFastClimb.toggleWhenPressed(new Climb(Constants.Climber.ASCEND_SPEED));

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
        return ocDeployPincers.get() || gpDeployPincers.get();
    }

    public boolean isEjectGearPressed() {
        return ocEjectGearButton.get() || gpEjectGearButton.get();
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
}
