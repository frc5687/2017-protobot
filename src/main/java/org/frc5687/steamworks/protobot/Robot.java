package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.frc5687.steamworks.protobot.subsystems.DriveTrain;
import org.frc5687.steamworks.protobot.subsystems.GearHandler;
import org.frc5687.steamworks.protobot.subsystems.Pneumatics;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;

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

    public static Robot robot;

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

        oi = new OI(); // must be initialized after subsystems

        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(640, 480);
    }

    @Override
    public void disabledInit() {
        super.disabledInit();
    }

    @Override
    public void autonomousInit() {
        super.autonomousInit();
    }

    @Override
    public void teleopInit() {
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
    }

    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
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
    }

}
