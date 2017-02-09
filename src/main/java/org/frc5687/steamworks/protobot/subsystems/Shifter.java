package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Ben Bernard on 2/9/2017.
 */
public class Shifter extends Subsystem {

    private DoubleSolenoid shifterSolenoid;

    /**
     * Constructor
     */
    public Shifter() {
        // Sets the double solenoid to channels 0 and 1 of PCM
        shifterSolenoid = new DoubleSolenoid(RobotMap.Shifter.PISTON_EXTENDER, RobotMap.Shifter.PISTON_RETRACTOR);
    }


    public void shift(DoubleSolenoid.Value gear) {
        shifterSolenoid.set(gear);
    }
    /**
     * Expands the cylinder of double solenoid
     */
    public void shiftHigh() {
        DriverStation.reportError("Shifting to high gear", false);
        shifterSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * Retracts the cylinder of double solenoid
     */
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

    /**
     * Sets the default command for the Shifter subsystem
     */
    @Override
    protected void initDefaultCommand() {
    }

    public void updateDashboard() {
        SmartDashboard.putString("shifter/Gear", shifterSolenoid.get() == DoubleSolenoid.Value.kForward ? "High" : (shifterSolenoid.get() == DoubleSolenoid.Value.kReverse ? "Low" : "Unknown") );
    }}
