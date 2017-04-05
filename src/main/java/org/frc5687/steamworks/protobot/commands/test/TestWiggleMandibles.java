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
public class TestWiggleMandibles extends ReceiveMandibles {

    private long _wiggleEndMillis;
    private long _endMillis;
    private double _maxAmps;


    public TestWiggleMandibles() {
        super();
    }

    @Override
    protected void initialize() {
        SmartDashboard.putBoolean("SelfTest/Mandibles/Wiggle/GearPresent", true);
        _wiggleEndMillis = System.currentTimeMillis() + 5000;
        _endMillis = System.currentTimeMillis() + 6000;
        super.initialize();
    }

    @Override
    protected void execute() {
        super.execute();
        _maxAmps = Math.max(_maxAmps, pdp.getMandiblesAmps());
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > _endMillis;
    }

    @Override
    protected boolean wiggle() {
        return System.currentTimeMillis() <= _wiggleEndMillis;
    }

    @Override
    protected void end() {
        boolean pass = true;
        if (_maxAmps > 2) {
            DriverStation.reportError("Mandible wiggle test amps reached (" + _maxAmps + " amps)", false);
        } else {
            DriverStation.reportError("Mandible wiggle test amps not reached (" + _maxAmps + " amps)", false);
            pass = false;
        }
        if (mandibles.gearPresent()) {
            DriverStation.reportError("Mandible wiggle test gear still present", false);
            SmartDashboard.putBoolean("SelfTest/Mandibles/Wiggle/GearRetained", true);
        } else {
            DriverStation.reportError("Mandible wiggle test gear absent", false);
            SmartDashboard.putBoolean("SelfTest/Mandibles/Wiggle/GearRetained", false);
            pass = false;
        }

        ledStrip.setStripColor(pass ? LEDColors.TEST_PASSED : LEDColors.TEST_FAILED);
        SmartDashboard.putBoolean("SelfTest/Mandibles/Wiggle/Passed", pass);
        SmartDashboard.putNumber("SelfTest/Mandibles/Wiggle/Amps", _maxAmps);
        mandibles.stop();
    }
}
