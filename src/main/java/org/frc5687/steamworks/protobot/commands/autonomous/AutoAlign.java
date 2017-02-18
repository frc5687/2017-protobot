package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.Auto.Align;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.imu;

/**
 * Created by Baxter on 2/15/2017.
 */
public class AutoAlign extends Command implements PIDOutput {

    private PIDController controller;
    private double endTime;
    private double angle;

    public AutoAlign(double angle) {
        requires(driveTrain);
        this.angle = angle;
    }

    @Override
    protected void initialize() {
        controller = new PIDController(Align.kP, Align.kI, Align.kD, imu, this);
        controller.setInputRange(Constants.Auto.MAX_IMU_ANGLE, Constants.Auto.MAX_IMU_ANGLE);
        controller.setOutputRange(-Align.MAX_OUTPUT, Align.MAX_OUTPUT);
        controller.setAbsoluteTolerance(Align.TOLERANCE);
        controller.setContinuous();
        controller.setSetpoint(angle);
        controller.enable();
    }

    @Override
    protected void execute() {
        if(!controller.onTarget()) endTime = System.currentTimeMillis() + Align.STEADY_TIME;
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() >= endTime;
    }

    @Override
    protected void end() {
        controller.disable();
        driveTrain.tankDrive(0,0);
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            driveTrain.tankDrive(-output, output); // positive output is counterclockwise
        }
    }
}
