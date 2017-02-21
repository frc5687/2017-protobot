package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.LowerPincers;
import org.frc5687.steamworks.protobot.commands.RestPincers;

import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Created by Caleb on 2/14/2017.
 */

public class Pincers extends Subsystem implements PIDOutput {

    public PIDController controller;

    private VictorSP pincerMotor;
    private DoubleSolenoid piston;
    private AnalogPotentiometer potentiometer;
    private double rest = Constants.pickConstant(Constants.Pincers.potentiometerLiftedTony, Constants.Pincers.potentiometerLiftedRhody);

    public Pincers(){
        pincerMotor = new VictorSP(RobotMap.Pincers.PINCER_MOTOR);
        potentiometer = new AnalogPotentiometer(RobotMap.Pincers.POTENTIOMETER);
        piston = new DoubleSolenoid(RobotMap.Pincers.PISTON_EXTENDER, RobotMap.Pincers.PISTON_RETRACTOR);


    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RestPincers());
    }

    protected void createController() {
        if (controller!=null) { return; }
        controller = new PIDController(Constants.Pincers.PID.kP, Constants.Pincers.PID.kI, Constants.Pincers.PID.kD, pincers.getPotentiometer(), this);
        controller.setInputRange(0, 0.3);
        controller.setOutputRange(-Constants.Pincers.maxSpeed, Constants.Pincers.maxSpeed);
        controller.setAbsoluteTolerance(Constants.Pincers.PID.TOLERANCE);
    }

    public void setPincerSpeed(double speed){
        pincerMotor.set(speed);
    }

    public void raise() {
        rest = Constants.pickConstant(Constants.Pincers.potentiometerLiftedTony, Constants.Pincers.potentiometerLiftedRhody);
        createController();
        controller.setSetpoint(rest);
        controller.enable();
    }

    public void lower() {
        rest = Constants.pickConstant(Constants.Pincers.potentiometerLoweredTony, Constants.Pincers.potentiometerLoweredRhody);
        createController();
        controller.setSetpoint(rest);
        controller.enable();
    }

    public void open(){
        piston.set(DoubleSolenoid.Value.kForward);
    }

    public void rest(){
        createController();
        controller.setSetpoint(rest);
        controller.enable();
    }

    public void close(){
        piston.set(DoubleSolenoid.Value.kReverse);
    }

    public double getAngle(){
        return potentiometer.get();
    }

    public boolean isLifted(){
        return potentiometer.get() == Constants.pickConstant(Constants.Pincers.potentiometerLiftedTony, Constants.Pincers.potentiometerLiftedRhody);
    }

    public boolean isOpen() {
        return piston.get() == DoubleSolenoid.Value.kReverse;
    }

    public boolean isClosed() {return piston.get() == DoubleSolenoid.Value.kForward;}


    public boolean isLowered(){
        return potentiometer.get() == Constants.pickConstant(Constants.Pincers.potentiometerLoweredTony, Constants.Pincers.potentiometerLoweredRhody);
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
