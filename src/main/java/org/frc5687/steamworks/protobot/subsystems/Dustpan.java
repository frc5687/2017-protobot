package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.HoldDust;
import org.frc5687.steamworks.protobot.commands.actions.dustpan.RaiseDustpan;
import org.frc5687.steamworks.protobot.commands.composite.StowDustpan;

import static org.frc5687.steamworks.protobot.Robot.*;

public class Dustpan {

    private AnalogInput ir;
    public Lifter lifter;
    public Roller roller;

    public class Lifter extends Subsystem {

        private VictorSP motor;

        public Lifter() {
            motor = new VictorSP(RobotMap.Dustpan.LIFTER_MOTOR);
        }

        public void set(double speed) {
            motor.set(speed);
        }

        @Override
        protected void initDefaultCommand() {
            setDefaultCommand(new StowDustpan());
        }

    }

    public class Roller extends Subsystem {

        private VictorSP motor;

        public Roller() {
            motor = new VictorSP(RobotMap.Dustpan.ROLLER_MOTOR);
        }

        public void set(double speed) {
            motor.set(speed);
        }

        @Override
        protected void initDefaultCommand() {
            setDefaultCommand(new HoldDust());
        }

    }

    public Dustpan() {
        roller = new Roller();
        lifter = new Lifter();
        ir = new AnalogInput(RobotMap.Dustpan.IR);
    }

    public void hold() {
        roller.set(Constants.Dustpan.ROLLER_HOLD_SPEED);

    }

    public void eject() {
        roller.set(Constants.Dustpan.EJECT_SPEED);

    }

    public void collect() {
        roller.set(Constants.Dustpan.COLLECT_SPEED);
    }

    public void poll() {
        if (hasGear()) { ledStrip.setStripColor(LEDColors.GEAR_IN_DUSTPAN); }
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Pincers/IR Value", ir.getValue());
        SmartDashboard.putNumber("Pincers/Amperage", pdp.getDustpanLifterAmps());
        SmartDashboard.putNumber("Pincers/Speed", roller.motor.getSpeed());
    }

    public boolean hasGear() {
        return ir.getValue() >= Constants.Dustpan.IR_THRESHOLD;
    }

}
