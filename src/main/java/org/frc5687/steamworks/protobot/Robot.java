package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    /**
     * Represents the operator interface / controls
     */
    public static OI oi;

    /**
     * Provides static access to the singleton Robot instance
     */
    public static Robot robot;

    public void robotInit() {
        robot = this;
    }

    public void disabledInit() {
    }

    public void disabledPeriodic() {
    }

    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
    }

    public void teleopInit() {
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testInit() {
    }

    public void testPeriodic() {
    }

}
