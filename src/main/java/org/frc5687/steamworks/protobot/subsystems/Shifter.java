package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

import static org.frc5687.steamworks.protobot.Robot.oi;

public class Shifter extends Subsystem {

    private DoubleSolenoid shifterSolenoid;
    private Compressor compressor;
    private long waitPeriodEndTime = 0;
    private boolean autShiftEnabled = false;

    public Shifter() {
        shifterSolenoid = new DoubleSolenoid(RobotMap.Shifter.PISTON_EXTENDER, RobotMap.Shifter.PISTON_RETRACTOR);
        compressor = new Compressor(0);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void shift(Gear gear, boolean auto) {
        shifterSolenoid.set(gear.getSolenoidValue());
        waitPeriodEndTime = System.currentTimeMillis() + (auto ? Constants.Shifter.AUTO_WAIT_PERIOD : Constants.Shifter.MANUAL_WAIT_PERIOD);
        if (gear==Gear.HIGH) {oi.rumbleRight();}
        if (gear==Gear.LOW) {oi.rumbleLeft();}
    }

    public boolean waitPeriodElapsed() {
        return System.currentTimeMillis() > waitPeriodEndTime;
    }

    public Gear getGear() {
        DoubleSolenoid.Value current = shifterSolenoid.get();
        if (current==Gear.HIGH.getSolenoidValue()) {
            return Gear.HIGH;
        } else if (current== Gear.LOW.getSolenoidValue()) {
            return Gear.LOW;
        }
        return Gear.UNKNOWN;
    }

    public boolean isCompressorAtPressure() {
        return compressor.getPressureSwitchValue();
    }

    public boolean isCommpressorEnabled() {
        return compressor.enabled();
    }

    public double getCompressorCurrent() {
        return compressor.getCompressorCurrent();
    }

    public void updateDashboard() {
        SmartDashboard.putString("shifter/Gear", getGear()==Gear.HIGH ? "High" : (getGear() == Gear.LOW ? "Low" : "Unknown"));
    }

    public enum Gear {
        UNKNOWN(DoubleSolenoid.Value.kOff),
        HIGH(DoubleSolenoid.Value.kReverse),
        LOW(DoubleSolenoid.Value.kForward);

        private DoubleSolenoid.Value solenoidValue;

        Gear(DoubleSolenoid.Value solenoidValue) {
            this.solenoidValue = solenoidValue;
        }

        public DoubleSolenoid.Value getSolenoidValue() {
            return solenoidValue;
        }

    }

    public boolean isAutShiftEnabled() {
        return autShiftEnabled;
    }

    public void setAutShiftEnabled(boolean enabled) {
        autShiftEnabled = enabled;
    }

}
