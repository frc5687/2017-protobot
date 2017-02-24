package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.RobotMap;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;

/**
 * Created by Ben Bernard on 2/19/2017.
 */
public class FullSelfTest extends CommandGroup {
    public FullSelfTest() {
        addSequential(new TestDriveCIM("port front motor", driveTrain.leftFrontMotor, driveTrain.leftEncoder,  RobotMap.Drive.PDP_LEFT_MOTOR_FRONT, 1.0, 1000, 2, 4000));
    }
}
