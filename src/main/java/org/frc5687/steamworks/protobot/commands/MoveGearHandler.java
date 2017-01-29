package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.GearHandler.PID;
import static org.frc5687.steamworks.protobot.Robot.gearHandler;

/**
 * Created by Baxter on 1/28/2017.
 */
public class MoveGearHandler extends Command implements PIDOutput {

    private PIDController controller;
    private double setpoint;

    public MoveGearHandler(double target) {
        requires(gearHandler);
        setpoint = target;
    }

    @Override
    protected void initialize() {
        controller = new PIDController(PID.kP, PID.kI, PID.kD, gearHandler.getPotentiometer(), this);
        controller.setInputRange(PID.MIN_INPUT, PID.MAX_INPUT);
        controller.setOutputRange(Constants.GearHandler.closeSpeed, Constants.GearHandler.openSpeed);
        controller.setAbsoluteTolerance(PID.kTOLERANCE);
        controller.setSetpoint(setpoint);

        controller.enable();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return controller.onTarget();
    }

    @Override
    protected void end() {
        gearHandler.stop();
        controller.disable();
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            gearHandler.setSpeed(output);
        }
    }

}