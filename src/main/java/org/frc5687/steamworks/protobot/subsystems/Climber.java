package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.climber.StopClimber;

import static org.frc5687.steamworks.protobot.Robot.pdp;

public class Climber extends Subsystem {

    private VictorSP climberMotors;

    public Climber() {
        climberMotors = new VictorSP(RobotMap.Climber.CLIMBER_MOTORS);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new StopClimber());
    }

    public void setSpeed(double speed) {
        if (Constants.Climber.MOTOR_INVERTED) {
            speed = -speed;
        }
        climberMotors.set(speed);
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
        SmartDashboard.putNumber("Climber/Speed/Fore", climberMotors.get());
        SmartDashboard.putNumber("Climber/Amps/Fore", pdp.getClimberAAmps());
        SmartDashboard.putNumber("Climber/Amps/Aft", pdp.getClimberBAmps());
    }
}
