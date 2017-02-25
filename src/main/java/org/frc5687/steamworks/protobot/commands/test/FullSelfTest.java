package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.RobotMap;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;

public class FullSelfTest extends CommandGroup {
    public FullSelfTest() {
        addSequential(new TestDriveTrain("port front motor", driveTrain.leftFrontMotor, driveTrain.leftEncoder,  RobotMap.Drive.PDP_LEFT_MOTOR_FRONT, 1.0, 1000, 2, 4000));
    }
}
