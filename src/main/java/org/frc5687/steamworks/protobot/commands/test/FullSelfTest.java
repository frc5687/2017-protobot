package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.*;
import org.frc5687.steamworks.protobot.commands.actions.lights.SetLEDStrip;
import org.frc5687.steamworks.protobot.utils.Color;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;

public class FullSelfTest extends CommandGroup {
    public FullSelfTest() {
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please ensure the robot is drydocked and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestDriveTrain());
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please ensure all hands are clear of the climber and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestClimber());
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please ensure all hands are clear of the mandibles and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestCloseMandibles());
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please place a gear into the funnel and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestWiggleMandibles());
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please ensure the space in front of the mandibles is clear press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestOpenMandibles());
        addSequential(new TestFinishMandibles());
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please ensure that the area below the dustpan is clear and safe and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestLowerDustpan());
        addSequential(new TestCollectDust());
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please insert a gear into the dustpan and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new TestRaiseDustpan());
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Please place the vision test target in position and press Start to continue.", "Test started.", "Test aborted."));
        addSequential(new VisionTest());
        addSequential(new SetLEDStrip(Color.RED));
        addSequential(new ConfirmTest("Is the LED strip solid red? Press Start for yes, Back for no.", "SelfTest/LEDStrip/Red"));
        addSequential(new SetLEDStrip(Color.GREEN));
        addSequential(new ConfirmTest("Is the LED strip solid green? Press Start for yes, Back for no.", "SelfTest/LEDStrip/Green"));
        addSequential(new SetLEDStrip(Color.BLUE));
        addSequential(new ConfirmTest("Is the LED strip solid blue? Press Start for yes, Back for no.", "SelfTest/LEDStrip/Blue"));

    }
}
