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

        public void poll() {

        }
        public void updateDashboard() {
            SmartDashboard.putNumber("Dustpan/Lifter/Amperage", pdp.getDustpanLifterAmps());
        }
    }

    public class Roller extends Subsystem {

        private AnalogInput ir;
        private VictorSP motor;

        public Roller() {
            ir = new AnalogInput(RobotMap.Dustpan.IR);
            motor = new VictorSP(RobotMap.Dustpan.ROLLER_MOTOR);
        }

        public boolean hasGear() {
            return ir.getValue() >= Constants.Dustpan.IR_THRESHOLD;
        }

        public void set(double speed) {
            motor.set(speed);
        }

        @Override
        protected void initDefaultCommand() {
            setDefaultCommand(new HoldDust());
        }

        public void poll() {
            ledStrip.setGearInDustpan(hasGear());
        }

        public void updateDashboard() {
            SmartDashboard.putNumber("Dustpan/Roller/IR Value", ir.getValue());
            SmartDashboard.putNumber("Dustpan/Roller/Speed", roller.motor.getSpeed());
        }


    }

    public Dustpan() {
        roller = new Roller();
        lifter = new Lifter();
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
        lifter.poll();
        roller.poll();
    }

    public void updateDashboard() {
        lifter.updateDashboard();
        roller.updateDashboard();
    }


}
