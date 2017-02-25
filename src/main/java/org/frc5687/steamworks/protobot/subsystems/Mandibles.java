package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.RunMandiblesManually;

import static org.frc5687.steamworks.protobot.Robot.pdp;

public class Mandibles extends Subsystem {

    private VictorSP gearMotor;
    private AnalogPotentiometer limitPotentiometer;

    public Mandibles() {
        gearMotor = new VictorSP(RobotMap.GearHandler.GEAR_MOTOR);
        limitPotentiometer = new AnalogPotentiometer(RobotMap.GearHandler.GEAR_POTENTIOMETER);
        SmartDashboard.putBoolean("MaxHall", false);
        SmartDashboard.putBoolean("MinHall", false);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunMandiblesManually());
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

    public double potentiometerValue() {
        return limitPotentiometer.get();
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Mandibles/MotorSpeed", gearMotor.getSpeed());
        SmartDashboard.putNumber("Mandibles/PotentiometerValue", potentiometerValue());
        SmartDashboard.putNumber("Mandibles/MotorAmperage", pdp.getGearHandlerAmps());
    }
}