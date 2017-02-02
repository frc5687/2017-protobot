package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.GearHandler.PID.Hold;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Ben Bernard on 1/16/2017.
 */
public class RunGearHandlerManually extends Command implements PIDOutput {

    private PIDController controller;
    private double setpoint;
    private double pidOutput;

    public RunGearHandlerManually() {
        requires(gearHandler);
    }

    @Override
    protected void initialize() {
        setpoint = gearHandler.potentiometerValue();
        controller = new PIDController(Hold.kP, Hold.kI, Hold.kD, gearHandler.getPotentiometer(), this);
        controller.setInputRange(Constants.GearHandler.PID.MIN_INPUT, Constants.GearHandler.PID.MAX_INPUT);
        controller.setOutputRange(-Hold.MAX_SPEED, Hold.MAX_SPEED);
        controller.setAbsoluteTolerance(Hold.TOLERANCE);
        controller.setSetpoint(setpoint);
        controller.enable();
    }

    protected void execute() {
        if (oi.isGearInPressed()) {
            gearHandler.close();
            setpoint = gearHandler.potentiometerValue();
            if(controller.isEnabled()) controller.disable();
        } else if (oi.isGearOutPressed()) {
            gearHandler.open();
            setpoint = gearHandler.potentiometerValue();
            if(controller.isEnabled()) controller.disable();
        } else {
            gearHandler.setSpeed(pidOutput);
            if(!controller.isEnabled()) controller.enable();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            pidOutput = output;
        }
    }
}