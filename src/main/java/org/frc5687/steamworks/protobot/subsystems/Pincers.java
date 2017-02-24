package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.RunPincersManually;

import static org.frc5687.steamworks.protobot.Robot.pincers;

public class Pincers extends Subsystem implements PIDOutput {

    public PIDController controller;
    private VictorSP pincerMotor;
    private DoubleSolenoid piston;
    private AnalogPotentiometer potentiometer;
    private double rest;

    public Pincers(){
        pincerMotor = new VictorSP(RobotMap.Pincers.PINCER_MOTOR);
        potentiometer = new AnalogPotentiometer(RobotMap.Pincers.POTENTIOMETER);
        piston = new DoubleSolenoid(RobotMap.Pincers.PISTON_EXTENDER, RobotMap.Pincers.PISTON_RETRACTOR);
        rest = Constants.pickConstant(Constants.Pincers.POTENTIOMETER_LIFTED_TONY, Constants.Pincers.POTENTIOMETER_LIFTED_RHODY);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunPincersManually());
    }

    protected void createController() {
        if (controller!=null) { return; }
        controller = new PIDController(Constants.Pincers.PID.kP, Constants.Pincers.PID.kI, Constants.Pincers.PID.kD, pincers.getPotentiometer(), this);
        controller.setInputRange(Constants.Pincers.PID.MIN_INPUT, Constants.Pincers.PID.MAX_INPUT);
        controller.setOutputRange(-Constants.Pincers.MAX_SPEED, Constants.Pincers.MAX_SPEED);
        controller.setAbsoluteTolerance(Constants.Pincers.PID.TOLERANCE);
    }

    public void setPincerSpeed(double speed){
        pincerMotor.set(speed);
    }

    public void raise() {
        double setPoint = Constants.pickConstant(Constants.Pincers.POTENTIOMETER_LIFTED_TONY, Constants.Pincers.POTENTIOMETER_LIFTED_RHODY);
        createController();
        controller.setSetpoint(setPoint);
        controller.enable();
        DriverStation.reportError("Setting setpoint to " + setPoint + " in Pincers.raise()", false);
    }

    public void lower() {
        double setPoint = Constants.pickConstant(Constants.Pincers.POTENTIOMETER_LOWERED_TONY, Constants.Pincers.POTENTIOMETER_LOWERED_RHODY);
        createController();
        controller.setSetpoint(setPoint);
        controller.enable();
        DriverStation.reportError("Setting setpoint to " + setPoint + " in Pincers.lower()", false);
    }

    public void open(){
        piston.set(DoubleSolenoid.Value.kForward);
    }

    public void rest(){
        createController();
        controller.setSetpoint(rest);
//        controller.enable();
        DriverStation.reportError("Setting setpoint to " + rest + " in Pincers.rest()", false);
    }

    public void close(){
        piston.set(DoubleSolenoid.Value.kReverse);
    }

    public double getAngle(){
        return potentiometer.get();
    }

    public boolean isLifted(){
        return potentiometer.get() == Constants.pickConstant(Constants.Pincers.POTENTIOMETER_LIFTED_TONY, Constants.Pincers.POTENTIOMETER_LIFTED_RHODY);
    }

    public boolean isOpen() {
        return piston.get() == DoubleSolenoid.Value.kReverse;
    }

    public boolean isClosed() {return piston.get() == DoubleSolenoid.Value.kForward;}


    public boolean isLowered(){
        return potentiometer.get() == Constants.pickConstant(Constants.Pincers.POTENTIOMETER_LOWERED_TONY, Constants.Pincers.POTENTIOMETER_LOWERED_RHODY);
    }

    public AnalogPotentiometer getPotentiometer(){
        return potentiometer;
    }

    public void updateDashboard(){
        SmartDashboard.putNumber("Pincer/PotentiometerValue",potentiometer.get());
        SmartDashboard.putNumber("Pincer/SetPoint",controller==null?0:controller.getSetpoint());
    }

    @Override
    public void pidWrite(double v) {
        setPincerSpeed(v);
    }

}
