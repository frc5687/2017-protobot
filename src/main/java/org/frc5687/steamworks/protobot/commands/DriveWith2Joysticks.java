package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.imu;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Ben Bernard on 1/13/2017.
 */
public class DriveWith2Joysticks extends Command implements PIDOutput {

    /*
     * Constructor
     */
    public DriveWith2Joysticks() {
        requires(driveTrain);
    }
    public static PIDController turnController;
    private static final double kP = 0.3;
    private static final double kI = 0.05;
    private static final double kD = 0.1;
    private static final double kF = 0.0;
    private static final double kToleranceDegrees = 2.0f;
    private static double targetAngle = 0;
    private double rotateToAngleRate = 0;
    private boolean isReversed;


    /*
     * Sets up the command
     * Called just before this Command runs the first time(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#initialize()
     */
    protected void initialize() {
        driveTrain.resetDriveEncoders();
        turnController = new PIDController(kP, kI, kD, kF, imu, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-0.1, 0.1);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        turnController.setSetpoint(targetAngle);
        turnController = new PIDController(kP, kI, kD, kF, imu, this);
    }

    /*
     * Executes the command
     * Called repeatedly when this Command is scheduled to run(non-Javadoc)
     * @see edu.wpi.first.wpilibj.command.Command#execute()
     */
    protected void execute() {
        if (oi.isLeftTriggerPressed()) {
            isReversed = false;
            turnController.enable();

        } else if (oi.isRightTriggerPressed()){
            isReversed = true;
            turnController.enable();
        } else {
            
            turnController.disable();
            driveTrain.tankDrive(oi.getLeftSpeed(), oi.getRightSpeed());
            targetAngle = imu.getAngle();

        }
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
            rotateToAngleRate = output;
            SmartDashboard.putNumber("PIDVal", output);

            if(isReversed) {
                driveTrain.tankDrive(rotateToAngleRate + Constants.Drive.FULL_BACKWARDS_SPEED, Constants.Drive.FULL_BACKWARDS_SPEED - rotateToAngleRate);
            }else{
                driveTrain.tankDrive(rotateToAngleRate + Constants.Drive.FULL_FORWARDS_SPEED, Constants.Drive.FULL_FORWARDS_SPEED - rotateToAngleRate);
            }
        }
    }
}