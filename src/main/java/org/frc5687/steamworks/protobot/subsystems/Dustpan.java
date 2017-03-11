package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.composite.StowDustpan;

import static org.frc5687.steamworks.protobot.Robot.*;

public class Dustpan extends Subsystem {

    private VictorSP lifterMotor;
    private VictorSP rollerMotor;
    private AnalogInput ir;

    public Dustpan() {
        lifterMotor = new VictorSP(RobotMap.Dustpan.LIFTER_MOTOR);
        rollerMotor = new VictorSP(RobotMap.Dustpan.ROLLER_MOTOR);
        ir = new AnalogInput(RobotMap.Dustpan.IR);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StowDustpan());
    }

    public void setLifterSpeed(double speed) {
        lifterMotor.set(speed);
    }

    public void setRollerSpeed(double speed) {
        rollerMotor.set(speed);
    }

    public void hold() {
        rollerMotor.set(Constants.Dustpan.ROLLER_HOLD_SPEED);

    }

    public void eject() {
        rollerMotor.set(Constants.Dustpan.EJECT_SPEED);

    }

    public void collect() {
        rollerMotor.set(Constants.Dustpan.COLLECT_SPEED);
    }

    public void poll() {
        if (hasGear()) { ledStrip.setStripColor(LEDColors.GEAR_IN_DUSTPAN); }
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Pincers/IR Value", ir.getValue());
        SmartDashboard.putNumber("Pincers/Amperage", pdp.getDustpanLifterAmps());
        SmartDashboard.putNumber("Pincers/Speed", lifterMotor.getSpeed());
    }

    public boolean hasGear() {
        return ir.getValue() >= Constants.Dustpan.IR_THRESHOLD;
    }

}
