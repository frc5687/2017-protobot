package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.ReceiveMandibles;

import java.sql.Driver;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;

public class Mandibles extends Subsystem {

    private VictorSP gearMotor;
    private AnalogPotentiometer limitPotentiometer;
    private AnalogInput ir;

    public Mandibles() {
        gearMotor = new VictorSP(RobotMap.Mandibles.MANDIBLES_MOTOR);
        limitPotentiometer = new AnalogPotentiometer(RobotMap.Mandibles.POTENTIOMETER);
        ir = new AnalogInput(RobotMap.Mandibles.MANDIBLES_IR);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ReceiveMandibles());
    }

    public void setSpeed(double speed) {
        gearMotor.set(speed);
    }

    public void open() {
        setSpeed(Constants.Mandibles.OPEN_SPEED);
    }

    public void close() {
        setSpeed(Constants.Mandibles.CLOSE_SPEED);
    }

    public void wiggleOut() {
        setSpeed(Constants.Mandibles.WIGGLE_SPEED);
    }

    public void wiggleIn() {
        setSpeed(-Constants.Mandibles.WIGGLE_SPEED);
    }

    public void stop() {
        setSpeed(0);
    }

    public boolean gearPresent() {
        return ir.getValue() > Constants.Mandibles.IR_GEAR_DETECTED;
    }

    public double potentiometerValue() {
        return limitPotentiometer.get();
    }

    public void poll() {
        ledStrip.setGearInMandibles(gearPresent());
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Mandibles/MotorSpeed", gearMotor.getSpeed());
        SmartDashboard.putNumber("Mandibles/PotentiometerValue", potentiometerValue());
        SmartDashboard.putNumber("Mandibles/MotorAmperage", pdp.getMandiblesAmps());
        SmartDashboard.putNumber("Mandibles/IRValue", ir.getValue());
    }
}