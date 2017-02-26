package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.CloseMandibles;
import org.frc5687.steamworks.protobot.commands.RunMandiblesManually;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.lights;
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
        setDefaultCommand(new CloseMandibles());
    }

    public void setSpeed(double speed) {
        gearMotor.set(speed);
    }

    public void open() {
        gearMotor.set(Constants.GearHandler.OPEN_SPEED);
    }

    public void close() {
        gearMotor.set(Constants.GearHandler.CLOSE_SPEED);
    }

    public void clamp() {
        gearMotor.set(Constants.GearHandler.CLAMP_SPEED);
    }

    public void wiggleOut() {
        gearMotor.set(Constants.GearHandler.WIGGLE_SPEED);
    }

    public void wiggleIn() {
        gearMotor.set(-Constants.GearHandler.WIGGLE_SPEED);
    }

    public void stop() {
        gearMotor.set(0);
    }

    public boolean gearPresent() {
        return ir.getValue() > Constants.GearHandler.IR_GEAR_DETECTED;
    }

    public double potentiometerValue() {
        return limitPotentiometer.get();
    }

    public void updateDashboard() {
        if (gearPresent()) { ledStrip.setStripColor(LEDColors.CAPTURED);}
        SmartDashboard.putNumber("Mandibles/MotorSpeed", gearMotor.getSpeed());
        SmartDashboard.putNumber("Mandibles/PotentiometerValue", potentiometerValue());
        SmartDashboard.putNumber("Mandibles/MotorAmperage", pdp.getGearHandlerAmps());
        SmartDashboard.putNumber("Mandibles/IRValue", ir.getValue());
    }
}