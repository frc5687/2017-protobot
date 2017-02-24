package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.utils.LEDSwitch;

/**
 * Subsystem to control lights for vision tracking and shooter aid
 *
 * @author wil
 */
public class Lights extends Subsystem {

    private LEDSwitch ringLight;

    public Lights() {
        ringLight = new LEDSwitch(RobotMap.Lights.RINGLIGHT);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public boolean getRingLight() {
        return ringLight.get();
    }

    public void turnRingLightOn() {
        ringLight.set(true);
    }

    public void turnRingLightOff() {
        ringLight.set(false);
    }

    public void updateDashboard() {
        SmartDashboard.putBoolean("lights/ringlight", ringLight.get());
    }

}