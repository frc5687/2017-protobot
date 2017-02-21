package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.commands.RunClimberManually;

/**
 * Created by Baxter on 1/28/2017.
 */
public class Climber extends Subsystem {

    private VictorSP climberMotor;
    private VictorSP climberMotor2;

    public Climber() {
        climberMotor = new VictorSP(RobotMap.Climber.CLIMBER_MOTOR);
        climberMotor2 = new VictorSP(RobotMap.Climber.CLIMBER_MOTOR_TWO);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new RunClimberManually());
    }

    public void setSpeed(double speed) {
        climberMotor.set(speed);
        climberMotor2.set(speed);
    }

    public void ascend() {
        setSpeed(-Constants.Climber.ASCEND_SPEED);
    }

    public void descend() {
        setSpeed(Constants.Climber.DESCEND_SPEED);
    }

    public void stop() {
        setSpeed(0);
    }

}
