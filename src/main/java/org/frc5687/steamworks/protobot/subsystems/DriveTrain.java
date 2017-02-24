package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import org.frc5687.steamworks.protobot.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.DriveWith2Joysticks;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Created by Ben Bernard on 1/13/2017.
 */
public class DriveTrain extends Subsystem {

    private VictorSP leftFrontMotor;
    private VictorSP leftRearMotor;
    private VictorSP leftTopMotor;
    private VictorSP rightFrontMotor;
    private VictorSP rightRearMotor;
    private VictorSP rightTopMotor;
    private Encoder rightEncoder;
    private Encoder leftEncoder;
    private AnalogInput irSensor;

    public DriveTrain(){
        leftFrontMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_FRONT);
        leftRearMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_REAR);
        leftTopMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_TOP);

        rightFrontMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_FRONT);
        rightRearMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_REAR);
        rightTopMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_TOP);

        rightEncoder = initializeEncoder(RobotMap.Drive.RIGHT_ENCODER_CHANNEL_A, RobotMap.Drive.RIGHT_ENCODER_CHANNEL_B, Constants.Encoders.RightDrive.REVERSED, Constants.Encoders.RightDrive.INCHES_PER_PULSE);
        leftEncoder = initializeEncoder(RobotMap.Drive.LEFT_ENCODER_CHANNEL_A, RobotMap.Drive.LEFT_ENCODER_CHANNEL_B, Constants.Encoders.LeftDrive.REVERSED, Constants.Encoders.LeftDrive.INCHES_PER_PULSE);

        irSensor = new AnalogInput(RobotMap.Drive.IR_DRIVE_SENSOR);
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
        return leftFrontMotor.getSpeed() * (Constants.Drive.LEFT_MOTORS_INVERTED ? -1 : 1);
    }

    public double getLeftRPS() {
        return getLeftRate() / (Constants.Encoders.Defaults.PULSES_PER_ROTATION * Constants.Encoders.Defaults.INCHES_PER_PULSE);
    }

    public double getRightDistance() {
        return rightEncoder.getDistance();
    }

    public double getRightTicks() { return rightEncoder.get(); }

    public double getRightRate() {
        return rightEncoder.getRate();
    }

    public double getRightSpeed() {
        return rightFrontMotor.getSpeed() * (Constants.Drive.RIGHT_MOTORS_INVERTED ? -1 : 1);
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
        return (getLeftDistance()+getRightDistance())/2;
    }

    /**
     * Run drive motors at specified speeds
     * @param leftSpeed  desired speed for left motors
     * @param rightSpeed desired speed for right motors
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        tankDrive(leftSpeed, rightSpeed, false);
    }

    public void tankDrive(double leftSpeed, double rightSpeed, boolean overrideCaps) {
        if (!overrideCaps) {
            // Limit change in leftSpeed to +/- ACCELERATION_CAP
            leftSpeed = Math.min(leftSpeed, leftFrontMotor.get() + Constants.Limits.ACCELERATION_CAP);
            leftSpeed = Math.max(leftSpeed, leftFrontMotor.get() - Constants.Limits.ACCELERATION_CAP);

            // Limit change in rightSpeed to +/- ACCELERATION_CAP
            rightSpeed = Math.min(rightSpeed, rightFrontMotor.get() + Constants.Limits.ACCELERATION_CAP);
            rightSpeed = Math.max(rightSpeed, rightFrontMotor.get() - Constants.Limits.ACCELERATION_CAP);
        }
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    public void setLeftSpeed(double speed) {
        speed = speed * (Constants.Drive.LEFT_MOTORS_INVERTED ? -1 : 1);
        leftFrontMotor.setSpeed(speed);
        leftRearMotor.setSpeed(speed);
        leftTopMotor.setSpeed(speed);
    }

    public void setRightSpeed(double speed) {
        speed = speed * (Constants.Drive.RIGHT_MOTORS_INVERTED ? -1 : 1);
        rightFrontMotor.setSpeed(speed);
        rightRearMotor.setSpeed(speed);
        rightTopMotor.setSpeed(speed);
    }

    public void tankDrive(double speed) { tankDrive(speed, speed); }

    public void updateDashboard() {
        SmartDashboard.putNumber("drive/Right distance", getRightDistance());
        SmartDashboard.putNumber("drive/Left distance", getLeftDistance());

        SmartDashboard.putNumber("drive/Right ticks", getRightTicks());
        SmartDashboard.putNumber("drive/Left ticks", getLeftTicks());

        SmartDashboard.putNumber("drive/Right rate", getRightRate());
        SmartDashboard.putNumber("drive/Left rate", getLeftRate());

        SmartDashboard.putNumber("drive/Right speed", getRightSpeed());
        SmartDashboard.putNumber("drive/Left speed", getLeftSpeed());

        SmartDashboard.putNumber("drive/Right RPS" , getRightRPS());
        SmartDashboard.putNumber("drive/Left RPS" , getLeftRPS());

        SmartDashboard.putBoolean("drive/Right inverted", rightFrontMotor.getInverted());
        SmartDashboard.putBoolean("drive/Left inverted", leftFrontMotor.getInverted());

        SmartDashboard.putNumber("drive/Right Front Current", pdp.getRightFrontAmps());
        SmartDashboard.putNumber("drive/Right Top Current", pdp.getRightTopAmps());
        SmartDashboard.putNumber("drive/Right Rear Current", pdp.getRightRearAmps());
        SmartDashboard.putNumber("drive/Left Front Current", pdp.getLeftFrontAmps());
        SmartDashboard.putNumber("drive/Left Top Current", pdp.getLeftTopAmps());
        SmartDashboard.putNumber("drive/Left Rear Current", pdp.getLeftRearAmps());
        SmartDashboard.putNumber("drive/Average Current", pdp.getMeanDrivetrainAmps());

        SmartDashboard.putNumber("drive/IR Sensor", irSensor.getValue());
    }
}
