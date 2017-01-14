package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.frc5687.steamworks.protobot.subsystems.DriveTrain;

/**
 * Created by Ben Bernard on 1/12/2017.
 */
public class Robot extends IterativeRobot {

    /**
     * Represents the drivetrain
     */
    public static DriveTrain driveTrain;

    public static OI oi;

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
