package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.CollectDust;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.LowerDustpan;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 4/4/2017.
 */
public class TestCollectDust extends CollectDust {

    private double _maxAmps;
    private long _endMillis;

    @Override
    protected void initialize() {
        _maxAmps = 0;
        _endMillis = System.currentTimeMillis() + 1000;
        super.initialize();
    }

    @Override
    protected void execute() {
        super.execute();
        _maxAmps = Math.max(_maxAmps, pdp.getDustpanRollerAmps());
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > _endMillis;
    }

    @Override
    protected void end() {
        super.end();
        boolean pass = true;
        if (!(_maxAmps > 2)) {
            DriverStation.reportError("Dustpan collect test failed to reach target amps (" + _maxAmps + ")", false);
        } else {
            DriverStation.reportError("Dustpan collect test reached target amps (" + _maxAmps + ")", false);
            pass = false;
        }
        ledStrip.setStripColor(pass ? LEDColors.TEST_PASSED : LEDColors.TEST_FAILED);
        SmartDashboard.putNumber("SelfTest/Dustpan/Intake/Amps", _maxAmps);
        SmartDashboard.putBoolean("SelfTest/Dustpan/Intake/Passed", pass);
    }
}
