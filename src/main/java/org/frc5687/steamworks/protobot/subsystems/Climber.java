package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.RunClimberManually;

public class Climber extends Subsystem {

    private VictorSP climberMotorFore;
    private VictorSP climberMotorAft;

    public Climber() {
        climberMotorFore = new VictorSP(RobotMap.Climber.CLIMBER_MOTOR_FORE);
        climberMotorAft = new VictorSP(RobotMap.Climber.CLIMBER_MOTOR_AFT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new RunClimberManually());
    }

    public void setSpeed(double speed) {
        if (Constants.Climber.MOTOR_INVERTED) {
            speed = -speed;
        }
        climberMotorFore.set(speed);
        climberMotorAft.set(speed);
    }

    public void ascend() {
        setSpeed(Constants.Climber.ASCEND_SPEED);
    }

    public void stop() {
        setSpeed(0);
    }

}
