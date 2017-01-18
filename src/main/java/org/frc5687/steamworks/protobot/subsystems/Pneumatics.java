package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Subsystem for pneumatics
 */
public class Pneumatics extends Subsystem {

    private DoubleSolenoid doubleSolenoid;

    /**
     * Constructor
     */
    public Pneumatics() {
        // Sets the double solenoid to channels 0 and 1 of PCM
        doubleSolenoid = new DoubleSolenoid(RobotMap.Pneumatics.expandPort, RobotMap.Pneumatics.retractPort);
    }

    /**
     * Expands the cylinder of double solenoid
     */
    public void expandPiston() {
        DriverStation.reportError("Extending piston", false);
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * Retracts the cylinder of double solenoid
     */
    public void retractPiston() {
        DriverStation.reportError("Retracting piston", false);
        doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * Disables the double solenoid
     */
    public void disablePiston() {
        DriverStation.reportError("Disabling piston", false);
        doubleSolenoid.set(DoubleSolenoid.Value.kOff);
    }


    public boolean isExpanded() {
        return doubleSolenoid.get() == DoubleSolenoid.Value.kForward;
    }

    public boolean isRetracted() {
        return doubleSolenoid.get() == DoubleSolenoid.Value.kReverse;
    }

    /**
     * Sets the default command for the pneumatics subsystem
     */
    @Override
    protected void initDefaultCommand() {
    }

    public void updateDashboard() {
        SmartDashboard.putBoolean("pneumatics/Forward", doubleSolenoid.get() == DoubleSolenoid.Value.kForward );
        SmartDashboard.putBoolean("pneumatics/Reverse", doubleSolenoid.get() == DoubleSolenoid.Value.kReverse );
   }
}