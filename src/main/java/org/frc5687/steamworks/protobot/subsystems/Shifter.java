package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.RobotMap;

public class Shifter extends Subsystem {

    private DoubleSolenoid shifterSolenoid;

    public Shifter() {
        shifterSolenoid = new DoubleSolenoid(RobotMap.Shifter.PISTON_EXTENDER, RobotMap.Shifter.PISTON_RETRACTOR);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void shift(DoubleSolenoid.Value gear) {
        shifterSolenoid.set(gear);
    }

    public void shiftHigh() {
        DriverStation.reportError("Shifting to high gear", false);
        shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void shiftLow() {
        DriverStation.reportError("Shifting to low gear", false);
        shifterSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public DoubleSolenoid.Value getGear() {
        return shifterSolenoid.get();
    }

    /**
     * Disables the double solenoid
     */
    public void disablePiston() {
        DriverStation.reportError("Disabling shifter", false);
        shifterSolenoid.set(DoubleSolenoid.Value.kOff);
    }

    public boolean isInHighGear() {
        return shifterSolenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public boolean isInLowGear() {
        return shifterSolenoid.get() == DoubleSolenoid.Value.kReverse;
    }

    public void updateDashboard() {
        SmartDashboard.putString("shifter/Gear", shifterSolenoid.get() == DoubleSolenoid.Value.kForward ? "High" : (shifterSolenoid.get() == DoubleSolenoid.Value.kReverse ? "Low" : "Unknown") );
    }

}
