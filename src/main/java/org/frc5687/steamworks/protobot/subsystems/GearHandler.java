package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.RunGearHandlerManually;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 1/16/2017.
 */
public class GearHandler extends Subsystem {

    private VictorSP gearMotor;
    private AnalogPotentiometer limitPotentiometer;

    public GearHandler() {
        gearMotor = new VictorSP(RobotMap.GearHandler.GEAR_MOTOR);
        limitPotentiometer = new AnalogPotentiometer(RobotMap.GearHandler.GEAR_POTENTIOMETER); 
        SmartDashboard.putBoolean("MaxHall", false);
        SmartDashboard.putBoolean("MinHall", false);
    }

    public void setSpeed(double speed) {
        gearMotor.set(speed);
    }

    public void open() {
        gearMotor.set(Constants.GearHandler.openSpeed);
    }

    public void close() {
        gearMotor.set(Constants.GearHandler.closeSpeed);
    }

    public void clamp() {
        gearMotor.set(Constants.GearHandler.clampSpeed);
    }

    public void wiggleOut() {
        gearMotor.set(Constants.GearHandler.wiggleSpeed);
    }

    public void wiggleIn() {
        gearMotor.set(-Constants.GearHandler.wiggleSpeed);
    }

    public void stop() {
        gearMotor.set(0);
    }

    public double potentiometerValue(){
        return limitPotentiometer.get();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunGearHandlerManually());
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Mandibles/MotorSpeed", gearMotor.getSpeed());
        SmartDashboard.putNumber("Mandibles/PotentiometerValue", potentiometerValue());
        SmartDashboard.putNumber("Mandibles/MotorAmperage", pdp.getGearHandlerAmps());
    }
}