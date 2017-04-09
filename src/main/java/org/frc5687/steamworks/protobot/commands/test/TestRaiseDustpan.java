package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.LowerDustpan;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.RaiseDustpan;

import static org.frc5687.steamworks.protobot.Robot.dustpan;
import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 4/4/2017.
 */
public class TestRaiseDustpan extends RaiseDustpan {

    private double _maxAmps;

    public TestRaiseDustpan() {
        super();
    }

    @Override
    protected void initialize() {
        _maxAmps = 0;
        super.initialize();
        SmartDashboard.putBoolean("SelfTest/Dustpan/Intake/GearDetected", dustpan.roller.hasGear());
    }

    @Override
    protected void execute() {
        super.execute();
        _maxAmps = Math.max(_maxAmps, pdp.getDustpanLifterAmps());
    }

    @Override
    protected void end() {
        super.end();
        boolean pass = true;
        if (!(_maxAmps > 2)) {
            DriverStation.reportError("Dustpan raise test failed to reach target amps (" + _maxAmps + ")", false);
        } else {
            DriverStation.reportError("Dustpan raise test reached target amps (" + _maxAmps + ")", false);
            pass = false;
        }
        dustpan.roller.set(0);
        ledStrip.setStripColor(pass ? LEDColors.TEST_PASSED : LEDColors.TEST_FAILED);
        SmartDashboard.putNumber("SelfTest/Dustpan/Raise/Amps", _maxAmps);
        SmartDashboard.putBoolean("SelfTest/Dustpan/Raise/Passed", pass);
    }
}
