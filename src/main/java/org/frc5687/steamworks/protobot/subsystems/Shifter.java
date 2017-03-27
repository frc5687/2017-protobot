package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

public class Shifter extends Subsystem {

    private DoubleSolenoid shifterSolenoid;
    private long waitPeriodEndTime = 0;

    public Shifter() {
        shifterSolenoid = new DoubleSolenoid(RobotMap.Shifter.PISTON_EXTENDER, RobotMap.Shifter.PISTON_RETRACTOR);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void shift(Gear gear, boolean auto) {
        shifterSolenoid.set(gear.getSolenoidValue());
        waitPeriodEndTime = System.currentTimeMillis() + (auto ? Constants.Shifter.AUTO_WAIT_PERIOD : Constants.Shifter.MANUAL_WAIT_PERIOD);
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

    public void updateDashboard() {
        SmartDashboard.putString("shifter/Gear", getGear()==Gear.HIGH ? "High" : (getGear() == Gear.LOW ? "Low" : "Unknown"));
    }

    public enum Gear {
        UNKNOWN(DoubleSolenoid.Value.kOff),
        HIGH(DoubleSolenoid.Value.kForward),
        LOW(DoubleSolenoid.Value.kReverse);

        private DoubleSolenoid.Value solenoidValue;

        Gear(DoubleSolenoid.Value solenoidValue) {
            this.solenoidValue = solenoidValue;
        }

        public DoubleSolenoid.Value getSolenoidValue() {
            return solenoidValue;
        }

    }


}
