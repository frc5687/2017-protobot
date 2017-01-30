package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        controller = new PIDController(PID.Move.kP, PID.Move.kI, PID.Move.kD, gearHandler.getPotentiometer(), this);
        controller.setInputRange(PID.MIN_INPUT, PID.MAX_INPUT);
        controller.setOutputRange(Constants.GearHandler.closeSpeed, Constants.GearHandler.openSpeed);
        controller.setAbsoluteTolerance(PID.Move.TOLERANCE);
        controller.setSetpoint(setpoint);
        controller.enable();
    }

    @Override
    protected void execute() {
        SmartDashboard.putBoolean("PID Enabled", controller.isEnabled());
    }

    @Override
    protected boolean isFinished() {
        SmartDashboard.putBoolean("onTarget", controller.onTarget());
        return controller.onTarget();
    }

    @Override
    protected void end() {
        gearHandler.stop();
        controller.disable();
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            gearHandler.setSpeed(output);
            SmartDashboard.putNumber("PID Output", output);
        }
    }

}
