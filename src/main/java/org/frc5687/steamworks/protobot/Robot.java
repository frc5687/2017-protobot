package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.subsystems.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;
import org.frc5687.steamworks.protobot.utils.PDP;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class Robot extends IterativeRobot {

    /**
     * Represents the operator interface / controls
     */
    public static OI oi;

    /**
     * Represents the robot's drivetrain
     */
    public static DriveTrain driveTrain;

    /**
     * Represents the robot's gear handler
     */
    public static GearHandler gearHandler;

    /**
     * Represents the pneumatics
     */
    public static Pneumatics pneumatics;

    public static Shifter shifter;

    /**
     * Represents the climbing mechanism
     */
    public static Climber climber;

    public static Lights lights;

    public static LEDStrip ledStrip;

    public static Robot robot;

    public static PDP pdp;

    public static Pincers pincers;

    public Robot() {
    }

    @Override
    public void startCompetition() {
        super.startCompetition();
    }

    public void robotInit() {
        robot = this;

        driveTrain = new DriveTrain();
        gearHandler = new GearHandler();
        pneumatics = new Pneumatics();
        shifter = new Shifter();
        climber = new Climber();
        lights = new Lights();
        ledStrip = new LEDStrip();
        pincers = new Pincers();

        pdp = new PDP(); // must be initialized after other subsystems
        oi = new OI(); // must be initialized after subsystems

        UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
        camera0.setResolution(640, 480);
        UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
        camera1.setResolution(640, 480);
    }

    @Override
    public void disabledInit() {
        ledStrip.setStripColor(LEDColors.DISABLED);
        super.disabledInit();
    }

    @Override
    public void autonomousInit() {
        ledStrip.setStripColor(LEDColors.AUTONOMOUS);
        super.autonomousInit();
    }

    @Override
    public void teleopInit() {
        ledStrip.setStripColor(LEDColors.TELEOP);
        super.teleopInit();
    }

    @Override
    public void testInit() {
        super.testInit();
    }

    @Override
    public void robotPeriodic() {
        super.robotPeriodic();
    }

    @Override
    public void disabledPeriodic() {
        super.disabledPeriodic();
        updateDashboard();
    }

        @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
        updateDashboard();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    @Override
    public void testPeriodic() {
        super.testPeriodic();
    }

    public void updateDashboard(){
        driveTrain.updateDashboard();
        gearHandler.updateDashboard();
        driveTrain.updateDashboard();
        shifter.updateDashboard();
        pincers.updateDashboard();
        ledStrip.updateDashboard();
        lights.updateDashboard();
        SmartDashboard.putNumber("Indicator", pdp.getIndicator());
    }

}
