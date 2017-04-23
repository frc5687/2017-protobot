package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.mandibles.ReceiveMandibles;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.pdp;

public class Mandibles extends Subsystem {

    private VictorSP gearMotor;
    private AnalogInput ir;

    private boolean inverted = false;

    public Mandibles() {
        gearMotor = new VictorSP(RobotMap.Mandibles.MANDIBLES_MOTOR);
        inverted = Constants.pickConstant(Constants.Mandibles.TONY_MOTOR_INVERTED, Constants.Mandibles.RHODY_MOTOR_INVERTED);
        DriverStation.reportError("Mandibles inverted: " + inverted, false);
        ir = new AnalogInput(RobotMap.Mandibles.MANDIBLES_IR);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ReceiveMandibles());
    }

    public void setSpeed(double speed) {
        gearMotor.set((inverted?-1:1) * speed);
    }

    public void open() {
        setSpeed(Constants.pickConstant(Constants.Mandibles.OPEN_SPEED_TONY,Constants.Mandibles.OPEN_SPEED_RHODY));
    }

    public void close() {

        setSpeed(Constants.pickConstant(Constants.Mandibles.CLOSE_SPEED_TONY, Constants.Mandibles.CLOSE_SPEED_RHODY));
    }

    public void wiggleOut() {
        setSpeed(Constants.pickConstant(Constants.Mandibles.WIGGLE_SPEED_TONY, Constants.Mandibles.WIGGLE_SPEED_RHODY));
    }

    public void wiggleIn() {
        setSpeed(Constants.pickConstant(-Constants.Mandibles.WIGGLE_SPEED_TONY, -Constants.Mandibles.WIGGLE_SPEED_RHODY));
    }

    public void stop() {
        setSpeed(0);
    }

    public boolean gearPresent() {
        return ir.getValue() > Constants.Mandibles.IR_GEAR_DETECTED;
    }

    public void poll() {
        ledStrip.setGearInMandibles(gearPresent());
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Mandibles/MotorSpeed", gearMotor.getSpeed());
        SmartDashboard.putNumber("Mandibles/MotorAmperage", pdp.getMandiblesAmps());
        SmartDashboard.putNumber("Mandibles/IRValue", ir.getValue());
    }
}