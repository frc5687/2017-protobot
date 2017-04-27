package org.frc5687.steamworks.protobot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.RobotMap;
import org.frc5687.steamworks.protobot.commands.actions.drive.DriveWith2Joysticks;
import org.frc5687.steamworks.protobot.utils.CompoundIRPIDSource;
import org.frc5687.steamworks.protobot.utils.IRPIDSource;
import org.frc5687.steamworks.protobot.utils.OffsetIRPIDSource;

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
    private IRPIDSource centerIR;
    //private CompoundIRPIDSource irSensors;

    public DriveTrain() {
        leftFrontMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_FRONT);
        leftRearMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_REAR);
        leftTopMotor = new VictorSP(RobotMap.Drive.LEFT_MOTOR_TOP);

        rightFrontMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_FRONT);
        rightRearMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_REAR);
        rightTopMotor = new VictorSP(RobotMap.Drive.RIGHT_MOTOR_TOP);

        rightEncoder = initializeEncoder(RobotMap.Drive.RIGHT_ENCODER_CHANNEL_A, RobotMap.Drive.RIGHT_ENCODER_CHANNEL_B, Constants.Encoders.RightDrive.REVERSED, Constants.pickConstant(Constants.Encoders.RightDrive.INCHES_PER_PULSE_TONY, Constants.Encoders.RightDrive.INCHES_PER_PULSE_RHODY));
        leftEncoder = initializeEncoder(RobotMap.Drive.LEFT_ENCODER_CHANNEL_A, RobotMap.Drive.LEFT_ENCODER_CHANNEL_B, Constants.Encoders.LeftDrive.REVERSED, Constants.pickConstant(Constants.Encoders.LeftDrive.INCHES_PER_PULSE_TONY, Constants.Encoders.LeftDrive.INCHES_PER_PULSE_RHODY));

        //leftIR = new OffsetIRPIDSource(RobotMap.Drive.LEFT_IR_SENSOR, Constants.DriveTrain.LEFT_IR_SENSOR_OFFSET);
        centerIR = new OffsetIRPIDSource(RobotMap.Drive.CENTER_IR_SENSOR, Constants.DriveTrain.CENTER_IR_SENSOR_OFFSET);
        //rightIR = new OffsetIRPIDSource(RobotMap.Drive.RIGHT_IR_SENSOR, Constants.DriveTrain.RIGHT_IR_SENSOR_OFFSET);

        //irSensors = new CompoundIRPIDSource(centerIR);
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

    public long getLeftTicks() {
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

    public long getRightTicks() {
        return rightEncoder.get();
    }

    public double getRightRate() {
        return rightEncoder.getRate();
    }

    public double getRightSpeed() {
        return rightFrontMotor.getSpeed() * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1);
    }

    public double getRate() {
        if (Math.abs(getRightTicks()) < 10) {
            return getLeftRate();
        }
        if (Math.abs(getLeftTicks()) < 10) {
            return getRightRate();
        }

        return (getLeftRate() + getRightRate()) * 0.5;
    }

    public boolean isDrivingStraight() {
        if (Math.abs(getLeftTicks())<10 || Math.abs(getRightTicks())<10) { return true; }
        return Math.abs(getLeftSpeed() - getRightSpeed()) / Math.abs((getLeftSpeed() + getRightSpeed())/2) < Constants.DriveTrain.STRAIGHT_TOLERANCE;
    }

    public void resetEncoders(){
        leftEncoder.reset();
        rightEncoder.reset();
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
        if (Math.abs(getRightTicks())<10) {
            return getLeftDistance();
        }
        if (Math.abs(getLeftTicks())<10) {
            return getRightDistance();
        }
        return (getLeftDistance() + getRightDistance()) / 2;
    }

    /**
     * Run drive motors at specified speeds
     *
     * @param leftSpeed  desired speed for left motors
     * @param rightSpeed desired speed for right motors
     */
    public void tankDrive(double leftSpeed, double rightSpeed) {
        tankDrive(leftSpeed, rightSpeed, true);
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

    public void runRightFrontMotor(double runSpeed){
        rightFrontMotor.set(runSpeed * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1));
    }
    public void runRightTopMotor(double runSpeed){
        rightTopMotor.set(runSpeed * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1));
    }
    public void runRightRearMotor(double runSpeed){
        rightRearMotor.set(runSpeed * (Constants.DriveTrain.RIGHT_MOTORS_INVERTED ? -1 : 1));
    }
    public void runLeftFrontMotor(double runSpeed){
        leftFrontMotor.set(runSpeed * (Constants.DriveTrain.LEFT_MOTORS_INVERTED ? -1 : 1));
    }
    public void runLeftTopMotor(double runSpeed){
        leftTopMotor.set(runSpeed * (Constants.DriveTrain.LEFT_MOTORS_INVERTED ? -1 : 1));
    }
    public void runLeftRearMotor(double runSpeed){
        leftRearMotor.set(runSpeed * (Constants.DriveTrain.LEFT_MOTORS_INVERTED ? -1 : 1));
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

        //SmartDashboard.putNumber("DriveTrain/IRDistance/Compound", irSensors.pidGet());
        //SmartDashboard.putNumber("DriveTrain/IRDistance/Left", leftIR.pidGet());
        SmartDashboard.putNumber("DriveTrain/IRDistance/Center", centerIR.pidGet());
        //SmartDashboard.putNumber("DriveTrain/IRDistance/Right", rightIR.pidGet());
    }

/*
    public CompoundIRPIDSource getIRSensors() {
        return irSensors;
    }
*/
    public IRPIDSource getIRSensor() {
        return centerIR;
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
