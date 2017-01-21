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
    private DigitalInput minExtensionSensor;
    private DigitalInput maxExtensionSensor;

    public GearHandler() {
        gearMotor = new VictorSP(RobotMap.GearHandler.GEAR_MOTOR);
        minExtensionSensor = new DigitalInput(RobotMap.GearHandler.MIN_EXTENSION__HALL);
        maxExtensionSensor = new DigitalInput(RobotMap.GearHandler.MAX__ETENSION_HALL);
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

    public boolean isAtMaxExtension() {
        return !maxExtensionSensor.get();

    }

    public boolean isAtMinExtension() {
        return !isAtMinExtension();
    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new RunGearHandlerManually());
    }
}

