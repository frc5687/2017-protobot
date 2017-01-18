package org.frc5687.steamworks.protobot;

import edu.wpi.first.wpilibj.IterativeRobot;
import org.frc5687.steamworks.protobot.subsystems.DriveTrain;
import org.frc5687.steamworks.protobot.subsystems.GearHandler;

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

}
