package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.subsystems.Lights;

import static org.frc5687.steamworks.protobot.Robot.lights;

/**
 * Created by Admin on 2/11/2017.
 */
public class SetLight extends Command {
    public SetLight(){
        requires(lights);
    }

    @Override
    protected void execute() {
        lights.setRingLightColor((int) SmartDashboard.getNumber("DB/Slider 0", 0));
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
