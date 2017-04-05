package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.ReceiveMandibles;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 4/4/2017.
 */
public class TestFinishMandibles extends ReceiveMandibles {

    private long _endMillis;

    @Override
    protected void initialize() {
        _endMillis = System.currentTimeMillis() + 500;
        super.initialize();
    }

    @Override
    protected void execute() {
        super.execute();
    }

    @Override
    protected boolean isFinished() {
        return state==State.CLAMP || System.currentTimeMillis() > _endMillis;
    }

    @Override
    protected void end() {
        if (state==State.CLAMP) {
            if (!mandibles.gearPresent()) {
                DriverStation.reportError("Mandible eject test gear no longer present", false);
                SmartDashboard.putBoolean("SelfTest/Mandibles/Eject/GearEjected", true);
            } else {
                DriverStation.reportError("Mandible eject test gearstill present", false);
                SmartDashboard.putBoolean("SelfTest/Mandibles/Eject/GearEjected", false);
                SmartDashboard.putBoolean("SelfTest/Mandibles/Eject/Passed", false);
                ledStrip.setStripColor(LEDColors.TEST_FAILED);
            }
        } else {
            DriverStation.reportError("Mandible eject test failed - mandibles still open.", false);
            SmartDashboard.putBoolean("SelfTest/Mandibles/Eject/GearEjected", false);
            SmartDashboard.putBoolean("SelfTest/Mandibles/Eject/Passed", false);
            ledStrip.setStripColor(LEDColors.TEST_FAILED);
        }

        mandibles.stop();
    }
}
