package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.LEDColors;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.composite.StowDustpan;

import static org.frc5687.steamworks.protobot.Robot.*;

public class Dustpan extends Subsystem implements PIDOutput {

    public PIDController controller;
    private VictorSP lifterMotor;
    private VictorSP rollerMotor;
    private AnalogPotentiometer potentiometer;
    private AnalogInput ir;
    private double rest;

    public Dustpan() {
        lifterMotor = new VictorSP(RobotMap.Dustpan.LIFTER_MOTOR);
        rollerMotor = new VictorSP(RobotMap.Dustpan.ROLLER_MOTOR);
        potentiometer = new AnalogPotentiometer(RobotMap.Dustpan.POTENTIOMETER);
        ir = new AnalogInput(RobotMap.Dustpan.IR);
        rest = Constants.pickConstant(Constants.Dustpan.POTENTIOMETER_LIFTED_TONY, Constants.Dustpan.POTENTIOMETER_LIFTED_RHODY);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StowDustpan());
    }

    protected void createController() {
        if (controller != null) {
            return;
        }
        controller = new PIDController(Constants.Dustpan.PID.kP, Constants.Dustpan.PID.kI, Constants.Dustpan.PID.kD, dustpan.getPotentiometer(), this);
        controller.setInputRange(Constants.Dustpan.PID.MIN_INPUT, Constants.Dustpan.PID.MAX_INPUT);
        controller.setOutputRange(-Constants.Dustpan.RAISE_SPEED, Constants.Dustpan.RAISE_SPEED);
        controller.setAbsoluteTolerance(Constants.Dustpan.PID.TOLERANCE);
    }

    public void setLifterSpeed(double speed) {
        lifterMotor.set(speed);
    }

    public void setRollerSpeed(double speed) {
        rollerMotor.set(speed);
    }

    public void raise() {
        double setPoint = Constants.pickConstant(Constants.Dustpan.POTENTIOMETER_LIFTED_TONY, Constants.Dustpan.POTENTIOMETER_LIFTED_RHODY);
        createController();
        controller.setSetpoint(setPoint);
        controller.enable();
    }

    public void lower() {
        double setPoint = Constants.pickConstant(Constants.Dustpan.POTENTIOMETER_LOWERED_TONY, Constants.Dustpan.POTENTIOMETER_LOWERED_RHODY);
        createController();
        controller.setSetpoint(setPoint);
        controller.enable();
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


    public void relax() {


    }

    public void rest() {
        createController();
        controller.setSetpoint(rest);
        controller.enable();
    }

    public void close() {
    }

    public double getAngle() {
        return potentiometer.get();
    }

    public boolean isLifted() {
        return potentiometer.get() == Constants.pickConstant(Constants.Dustpan.POTENTIOMETER_LIFTED_TONY, Constants.Dustpan.POTENTIOMETER_LIFTED_RHODY);
    }

    public boolean onTarget() {
        return controller.onTarget();
    }

    public boolean isLowered() {
        return potentiometer.get() == Constants.pickConstant(Constants.Dustpan.POTENTIOMETER_LOWERED_TONY, Constants.Dustpan.POTENTIOMETER_LOWERED_RHODY);
    }

    public AnalogPotentiometer getPotentiometer() {
        return potentiometer;
    }

    public void poll() {
        if (hasGear()) { ledStrip.setStripColor(LEDColors.GEAR_IN_DUSTPAN); }
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Pincers/PotentiometerValue", potentiometer.get());
        SmartDashboard.putNumber("Pincers/IR Value", ir.getValue());
        SmartDashboard.putNumber("Pincers/Amperage", pdp.getDustpanLifterAmps());
        SmartDashboard.putNumber("Pincers/PID/SetPoint", controller == null ? 0 : controller.getSetpoint());
        SmartDashboard.putBoolean("Pincers/PID/On Target", controller == null ? false : controller.onTarget());
        SmartDashboard.putNumber("Pincers/Speed", lifterMotor.getSpeed());
    }

    @Override
    public void pidWrite(double v) {
//        setLifterSpeed(v);
    }

    public boolean hasGear() {
        return ir.getValue() >= Constants.Dustpan.IR_THRESHOLD;
    }

}
