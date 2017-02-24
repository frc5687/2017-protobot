package org.frc5687.steamworks.protobot.commands;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.shifter;

public class Shift extends Command{

    private DoubleSolenoid.Value gear = DoubleSolenoid.Value.kOff;
    private double initialLeftSpeed, initialRightSpeed;
    private long endTime;
    private State state = State.STOP_MOTOR;

    public enum State {
        STOP_MOTOR,
        WAIT_FOR_MOTOR,
        SHIFT,
        WAIT_FOR_SHIFT,
        START_MOTOR,
        DONE;
    }

    public Shift(DoubleSolenoid.Value gear) {
        requires(driveTrain);
        requires(shifter);
        this.gear = gear;
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Starting shift command", false);
        state = State.STOP_MOTOR;
    }

    @Override
    protected void execute() {
        switch(state) {
            case STOP_MOTOR:
                DriverStation.reportError("Shift state STOP_MOTOR", false);
                initialLeftSpeed = driveTrain.getLeftSpeed();
                initialRightSpeed = driveTrain.getRightSpeed();
                driveTrain.tankDrive(0,0,true);
                endTime = System.currentTimeMillis() + Constants.Shifter.STOP_MOTOR_TIME;
                state = State.WAIT_FOR_MOTOR;
                break;
            case WAIT_FOR_MOTOR:
                DriverStation.reportError("Shift state WAIT_FOR_MOTOR", false);
                if(System.currentTimeMillis() >= endTime) state = State.SHIFT;
                break;
            case SHIFT:
                DriverStation.reportError("Shift state SHIFT", false);
                shifter.shift(gear);
                endTime = System.currentTimeMillis() + Constants.Shifter.SHIFT_TIME;
                state = State.WAIT_FOR_SHIFT;
            case WAIT_FOR_SHIFT:
                DriverStation.reportError("Shift state WAIT_FOR_SHIFT", false);
                if(System.currentTimeMillis() >= endTime) state = State.START_MOTOR;
                break;
            case START_MOTOR:
                DriverStation.reportError("Shift state START_MOTOR", false);
                driveTrain.tankDrive(initialLeftSpeed, initialRightSpeed, true);
                state = State.DONE;
                break;
        }
    }

    @Override
    protected boolean isFinished() {
        return state == State.DONE;
    }

    @Override
    protected void interrupted() {
    }

}
