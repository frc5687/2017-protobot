package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import org.frc5687.steamworks.protobot.RobotMap;

/**
 * Created by Baxter on 1/28/2017.
 */
public class Climber extends Subsystem {

    private VictorSP climberMotor;

    public Climber() {
        climberMotor = new VictorSP(RobotMap.Climber.CLIMBER_MOTOR);
    }

    public void initDefaultCommand() {
    }

    public void setSpeed(double speed) {
        climberMotor.set(speed);
    }

    public void stop() {
        setSpeed(0);
    }

}
