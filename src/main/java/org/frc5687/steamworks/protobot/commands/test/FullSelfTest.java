package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.RobotMap;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;

public class FullSelfTest extends CommandGroup {
    public FullSelfTest() {
        addSequential(new ConfirmTest("Please ensure the robot is drydocked and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestDriveTrain(1));
        addSequential(new TestClimber(0.5));
        addSequential(new TestMandibles(0.5));

    }
}
