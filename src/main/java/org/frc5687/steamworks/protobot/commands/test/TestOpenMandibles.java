package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.OpenMandibles;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 4/4/2017.
 */
public class TestOpenMandibles extends OpenMandibles {

    private double _maxAmps;

    @Override
    protected void initialize() {
        super.initialize();
        _maxAmps = 0;
    }

    @Override
    protected void execute() {
        super.execute();
        _maxAmps = Math.max(_maxAmps, pdp.getMandiblesAmps());
    }

    @Override
    protected void end() {
        super.end();
        boolean pass = true;
        if (_maxAmps > 2) {
            DriverStation.reportError("Mandible eject test amps reached (" + _maxAmps + " amps)", false);
        } else {
            DriverStation.reportError("Mandible eject test amps not reached (" + _maxAmps + " amps)", false);
            pass = false;
        }
        ledStrip.setStripColor(pass ? LEDColors.TEST_PASSED : LEDColors.TEST_FAILED);
        SmartDashboard.putBoolean("SelfTest/Mandibles/Eject/Passed", pass);
        SmartDashboard.putNumber("SelfTest/Mandibles/Eject/Amps", _maxAmps);
        mandibles.stop();
    }
}
