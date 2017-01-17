package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.RunGearHandlerManually;

/**
 * Created by Ben Bernard on 1/16/2017.
 */
public class GearHandler extends Subsystem {

    private VictorSP gearMotor;
    private DigitalInput homeSensor;
    private DigitalInput extensionSensor;

    public GearHandler() {
        gearMotor = new VictorSP(RobotMap.GearHandler.MOTOR_PORT);
        homeSensor = new DigitalInput(RobotMap.GearHandler.HOME_SENSOR_PORT);
        extensionSensor = new DigitalInput(RobotMap.GearHandler.EXTENSION_SENSOR_PORT);
    }

    public void open() {
        gearMotor.set(Constants.GearHandler.openSpeed);
    }

    public void close() {
        gearMotor.set(Constants.GearHandler.closeSpeed);
    }

    public void stop() {
        gearMotor.set(0);
    }

    public boolean isOpen() {
        return false;
    }

    public boolean isClosed() {
        return false;
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunGearHandlerManually());
    }
}

