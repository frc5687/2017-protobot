package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.RunClimberManually;
import org.frc5687.steamworks.protobot.commands.actions.StopClimber;

import static org.frc5687.steamworks.protobot.Robot.pdp;

public class Climber extends Subsystem {

    private VictorSP climberMotorFore;
    private VictorSP climberMotorAft;

    public Climber() {
        climberMotorFore = new VictorSP(RobotMap.Climber.CLIMBER_MOTOR_FORE);
        climberMotorAft = new VictorSP(RobotMap.Climber.CLIMBER_MOTOR_AFT);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopClimber());
    }

    public void setSpeed(double speed) {
        if (Constants.Climber.MOTOR_INVERTED) {
            speed = -speed;
        }
        climberMotorFore.set(speed);
        climberMotorAft.set(speed);
    }

    public double getAmps(){
        return pdp.getClimberAAmps();
    }

    public void ascend() {
        setSpeed(Constants.Climber.ASCEND_SPEED);
    }

    public void stop() {
        setSpeed(0);
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("Climber/Speed/Fore", climberMotorFore.get());
        SmartDashboard.putNumber("Climber/Speed/Aft", climberMotorAft.get());
        SmartDashboard.putNumber("Climber/Amps/Fore", pdp.getClimberAAmps());
        SmartDashboard.putNumber("Climber/Amps/Aft", pdp.getClimberBAmps());
    }
}
