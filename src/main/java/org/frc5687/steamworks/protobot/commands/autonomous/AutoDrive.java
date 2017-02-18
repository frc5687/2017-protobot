package org.frc5687.steamworks.protobot.commands.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants.Auto.Drive;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.imu;

/**
 * Created by Baxter on 2/15/2017.
 */
public class AutoDrive extends Command implements PIDOutput {

    double finalDistance;
    private PIDController controller;
    private double distance;

    public AutoDrive(double distance) {
        this.distance = distance;
    }

    @Override
    protected void initialize() {
        this.finalDistance = distance + driveTrain.getRightDistance();
        controller = new PIDController(Drive.kP, Drive.kI, Drive.kD, imu, this);
        controller.setInputRange(-180, 180);
        controller.setOutputRange(-Drive.MAX_OUTPUT, Drive.MAX_OUTPUT);
        controller.setAbsoluteTolerance(Drive.TOLERANCE);
        controller.setContinuous();
        controller.setSetpoint(imu.getAngle());
        controller.enable();
        DriverStation.reportError("Auto Drive", false);
    }

    @Override
    protected boolean isFinished() {
        return driveTrain.getRightDistance() > finalDistance;
    }

    @Override
    protected void end() {
        controller.disable();
        driveTrain.tankDrive(0,0);
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            driveTrain.tankDrive(Drive.SPEED - output, Drive.SPEED + output); // positive output is counterclockwise
        }
    }

}
