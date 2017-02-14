package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.Constants.Drive;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.imu;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Ben Bernard on 1/13/2017.
 */
public class DriveWith2Joysticks extends Command implements PIDOutput, PIDSource {

    /*
     * Constructor
     */
    public DriveWith2Joysticks() {
        requires(driveTrain);
    }
    public static PIDController turnController;

    private static double targetAngle = 0;
    private boolean isReversed;


    /*
     * Sets up the command
     * Called just before this Command runs the first time(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#initialize()
     */
    protected void initialize() {
        driveTrain.resetDriveEncoders();
        turnController = new PIDController(Drive.kP, Drive.kI, Drive.kD, imu, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-Drive.MAX_SPEED_DIFFERENCE, Drive.MAX_SPEED_DIFFERENCE);
        turnController.setAbsoluteTolerance(Drive.kToleranceDegrees);
        turnController.setContinuous(true);
        turnController.setSetpoint(0);
    }

    /*
     * Executes the command
     * Called repeatedly when this Command is scheduled to run(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#execute()
     */
    protected void execute() {
        if (oi.isLeftTriggerPressed()) {
            isReversed = false;
            if(!turnController.isEnabled())
                turnController.enable();

        } else if (oi.isRightTriggerPressed()){
            isReversed = true;
            if(!turnController.isEnabled())
                turnController.enable();
        } else {

            if(turnController.isEnabled()) {
                turnController.disable();
                turnController.reset();
            }
            driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
            targetAngle = imu.getAngle();

        }
        SmartDashboard.putNumber("PID/proportional",turnController.getP());
        SmartDashboard.putNumber("PID/integral", turnController.getI());
        SmartDashboard.putNumber("PID/derivative", turnController.getD());
        SmartDashboard.putNumber("PID/setpoint", turnController.getSetpoint());

    }

    /*
     * Check if this command is finished running
     * Make this return true when this Command no longer needs to run execute()(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#isFinished()
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /*
     * Command execution clean-up
     * Called once after isFinished returns true(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#end()
     */
    protected void end() {
    }

    /*
     * Handler for when command is interrupted
     * Called when another command which requires one or more of the same(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#interrupted()
     */
    protected void interrupted() {
    }

    @Override
    public void pidWrite(double output) {
        synchronized (this) {
            SmartDashboard.putNumber("PID/output", output);

            if(isReversed) {
                driveTrain.tankDrive(output + Constants.Drive.FULL_BACKWARDS_SPEED, Constants.Drive.FULL_BACKWARDS_SPEED - output);
            }else{
                driveTrain.tankDrive(output + Constants.Drive.FULL_FORWARDS_SPEED, Constants.Drive.FULL_FORWARDS_SPEED - output);
            }
        }
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return null;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public double pidGet() {
        return imu.getAngle() - targetAngle - 180;
    }
}