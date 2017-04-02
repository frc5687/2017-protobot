package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.RobotMap;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;

public class FullSelfTest extends CommandGroup {
    public FullSelfTest() {
        addSequential(new ConfirmTest("Please ensure the robot is drydocked and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestDriveTrain());
        addSequential(new ConfirmTest("Please ensure all hands are clear of the climber and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestClimber());
        // addSequential(new TestCloseMandibles(0.5));
        addSequential(new ConfirmTest("Please place a gear into the funnel and press Start to continue.", "Test started.", "Test aborted."));
        // addSequential(new TestWiggleMandibles(0.5));
        addSequential(new ConfirmTest("Please ensure that the area below the dustpan is clear and safe and press Start to continue.", "Test started.", "Test aborted."));
        // addSequential(new TestDustpanDeploy());
        addSequential(new ConfirmTest("Please insert a gear into the dustpan and press Start to continue.", "Test started.", "Test aborted."));
        // addSequential(new TestDustpanRecover());
        addSequential(new ConfirmTest("Please place the vision test target in position and Start to continue.", "Test started.", "Test aborted."));
        addSequential(new VisionTest());

    }
}
