package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.frc5687.steamworks.protobot.subsystems.DriveTrain;
import org.frc5687.steamworks.protobot.subsystems.GearHandler;
import org.frc5687.steamworks.protobot.subsystems.Pneumatics;

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

    public void robotInit() {
        robot = this;
        // initialize subsystems
        driveTrain = new DriveTrain();
        oi = new OI();
    }
}
