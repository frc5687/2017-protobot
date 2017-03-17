package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.DriveWith2Joysticks;
import org.frc5687.steamworks.protobot.utils.IRPIDSource;

import static org.frc5687.steamworks.protobot.Robot.pdp;

public class DriveTrain extends Subsystem implements PIDSource {

    private VictorSP leftFrontMotor;
    private VictorSP leftRearMotor;
    private VictorSP leftTopMotor;
    private VictorSP rightFrontMotor;
    private VictorSP rightRearMotor;
    private VictorSP rightTopMotor;
    private Encoder rightEncoder;
    private Encoder leftEncoder;
    private IRPIDSource irSensor;

    public DriveTrain() {
        leftFrontMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_FRONT);
        leftRearMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_REAR);
        leftTopMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_TOP);

        rightFrontMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_FRONT);
        rightRearMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_REAR);
        rightTopMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_TOP);

        rightEncoder = initializeEncoder(RobotMap.Drive.RIGHT_ENCODER_CHANNEL_A, RobotMap.Drive.RIGHT_ENCODER_CHANNEL_B, Constants.Encoders.RightDrive.REVERSED, Constants.Encoders.RightDrive.INCHES_PER_PULSE);
        leftEncoder = initializeEncoder(RobotMap.Drive.LEFT_ENCODER_CHANNEL_A, RobotMap.Drive.LEFT_ENCODER_CHANNEL_B, Constants.Encoders.LeftDrive.REVERSED, Constants.Encoders.LeftDrive.INCHES_PER_PULSE);

        irSensor = new IRPIDSource(RobotMap.Drive.IR_DRIVE_SENSOR);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveWith2Joysticks());
    }

    public void resetDriveEncoders() {
        rightEncoder.reset();
        leftEncoder.reset();
    }

    public double getLeftDistance() {
        return leftEncoder.getDistance();
    }

    public double getLeftTicks() {
        return leftEncoder.get();
    }

    public double getLeftRate() {
        return leftEncoder.getRate();
    }

    public double getLeftSpeed() {
        return leftFrontMotor.getSpeed() * (Constants.DriveTrain.LEFT_MOTORS_INVERTED ? -1 : 1);
    }

    public void setLeftSpeed(double speed) {
        speed = speed * (Constants.DriveTrain.LEFT_MOTORS_INVERTED ? -1 : 1);
        leftFrontMotor.setSpeed(speed);
        leftRearMotor.setSpeed(speed);
        leftTopMotor.setSpeed(speed);
    }

    public double getLeftRPS() {
        return getLeftRate() / (Constants.Encoders.Defaults.PULSES_PER_ROTATION * Constants.Encoders.Defaults.INCHES_PER_PULSE);
    }

    public double getRightDistance() {
        return rightEncoder.getDistance();
    }

    public double getRightTicks() {
        return rightEncoder.get();
    }

    public double getRightRate() {
        return rightEncoder.getRate();
    }

    public double getRightSpeed() {
        return rightFrontMotor.getSpeed() * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1);
    }

    public void setRightSpeed(double speed) {
        speed = speed * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1);
        rightFrontMotor.setSpeed(speed);
        rightRearMotor.setSpeed(speed);
        rightTopMotor.setSpeed(speed);
    }

    public double getRightRPS() {
        return getRightRate() / (Constants.Encoders.Defaults.PULSES_PER_ROTATION * Constants.Encoders.Defaults.INCHES_PER_PULSE);
    }

    private Encoder initializeEncoder(int channelA, int channelB, boolean reversed, double distancePerPulse) {
        Encoder encoder = new Encoder(channelA, channelB, reversed, Encoder.EncodingType.k4X);
        encoder.setDistancePerPulse(distancePerPulse);
        encoder.setMaxPeriod(Constants.Encoders.Defaults.MAX_PERIOD);
        encoder.setSamplesToAverage(Constants.Encoders.Defaults.SAMPLES_TO_AVERAGE);
        encoder.reset();
        return encoder;
    }

    /**
     * @return average of leftDistance and rightDistance
     */
    public double getDistance() {
        return (getLeftDistance() + getRightDistance()) / 2;
    }

    /**
     * Run drive motors at specified speeds
     *
     * @param leftSpeed  desired speed for left motors
     * @param rightSpeed desired speed for right motors
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        tankDrive(leftSpeed, rightSpeed, false);
    }

    public void tankDrive(double leftSpeed, double rightSpeed, boolean overrideCaps) {
        if (!overrideCaps) {
            // Limit change in leftSpeed to +/- ACCELERATION_CAP
            leftSpeed = Math.min(leftSpeed, getLeftSpeed() + Constants.Limits.ACCELERATION_CAP);
            leftSpeed = Math.max(leftSpeed, getLeftSpeed() - Constants.Limits.ACCELERATION_CAP);

            // Limit change in rightSpeed to +/- ACCELERATION_CAP
            rightSpeed = Math.min(rightSpeed, getRightSpeed() + Constants.Limits.ACCELERATION_CAP);
            rightSpeed = Math.max(rightSpeed, getRightSpeed() - Constants.Limits.ACCELERATION_CAP);
        }
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    public void tankDrive(double speed) {
        tankDrive(speed, speed);
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("DriveTrain/Distance/Right", getRightDistance());
        SmartDashboard.putNumber("DriveTrain/Distance/Left", getLeftDistance());

        SmartDashboard.putNumber("DriveTrain/Ticks/Right", getRightTicks());
        SmartDashboard.putNumber("DriveTrain/Ticks/Left", getLeftTicks());

        SmartDashboard.putNumber("DriveTrain/Rate/Right", getRightRate());
        SmartDashboard.putNumber("DriveTrain/Rate/Left", getLeftRate());

        SmartDashboard.putNumber("DriveTrain/Speed/Right", getRightSpeed());
        SmartDashboard.putNumber("DriveTrain/Speed/Left", getLeftSpeed());

        SmartDashboard.putNumber("DriveTrain/RPS/Right", getRightRPS());
        SmartDashboard.putNumber("DriveTrain/RPS/Left", getLeftRPS());

        SmartDashboard.putBoolean("DriveTrain/Inverted/Right", Constants.DriveTrain.RIGHT_MOTORS_INVERTED);
        SmartDashboard.putBoolean("DriveTrain/Inverted/Left", Constants.DriveTrain.LEFT_MOTORS_INVERTED);

        SmartDashboard.putNumber("DriveTrain/Amps/Right/Front", pdp.getRightFrontAmps());
        SmartDashboard.putNumber("DriveTrain/Amps/Right/Top", pdp.getRightTopAmps());
        SmartDashboard.putNumber("DriveTrain/Amps/Right/Rear", pdp.getRightRearAmps());
        SmartDashboard.putNumber("DriveTrain/Amps/Left/Front", pdp.getLeftFrontAmps());
        SmartDashboard.putNumber("DriveTrain/Amps/Left/Top", pdp.getLeftTopAmps());
        SmartDashboard.putNumber("DriveTrain/Amps/Left/Rear", pdp.getLeftRearAmps());
        SmartDashboard.putNumber("DriveTrain/Amps/Average", pdp.getMeanDrivetrainAmps());

        SmartDashboard.putNumber("DriveTrain/IR Sensor Distance", irSensor.pidGet());
        SmartDashboard.putNumber("DriveTrain/IR Sensor Raw", irSensor.getRaw());
    }

    public IRPIDSource getIrSensor() {
        return irSensor;
    }

    @Override
    public double pidGet() {
        return getDistance();
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

}
