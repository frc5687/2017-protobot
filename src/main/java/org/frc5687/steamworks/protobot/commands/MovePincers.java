package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.pincers;

/**
 * Created by Caleb on 2/14/2017.
 */
public class MovePincers extends Command implements PIDOutput {

    public PIDController controller;
    private double setpoint;
    private double speed;

    public MovePincers(double setpoint, double speed) {
        requires(pincers);
        this.setpoint = setpoint;
        this.speed = speed;
    }

    protected void initialize() {
        controller = new PIDController(Constants.Pincers.PID.kP, Constants.Pincers.PID.kI, Constants.Pincers.PID.kD, pincers.getPotentiometer(), this);
        controller.setInputRange(Constants.Pincers.potentiometerLifted, Constants.Pincers.potentiometerLowered);
        controller.setOutputRange(Constants.Pincers.lowerSpeed, Constants.Pincers.raiseSpeed);
        controller.setAbsoluteTolerance(Constants.Pincers.PID.TOLERANCE);
        controller.setSetpoint(setpoint);
        controller.enable();
    }


    @Override
    protected boolean isFinished() {
        return false; // Run until interrupted controller.onTarget();
    }

    @Override
    protected void end() {
        pincers.setPincerSpeed(0);
        controller.disable();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            SmartDashboard.putNumber("Pincers/Speed", output);
            pincers.setPincerSpeed(output);
        }
    }
}