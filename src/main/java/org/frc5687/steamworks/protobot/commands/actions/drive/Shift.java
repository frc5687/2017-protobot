package org.frc5687.steamworks.protobot.commands.actions.drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.subsystems.Shifter;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.shifter;

public class Shift extends Command {

    private Shifter.Gear gear;
    private double initialLeftSpeed, initialRightSpeed;
    private long endTime;
    private State state = State.STOP_MOTOR;
    private boolean auto;

    public Shift(Shifter.Gear gear, boolean auto) {
        requires(driveTrain);
        requires(shifter);
        this.gear = gear;
        this.auto = auto;
    }

    @Override
    protected void initialize() {
        DriverStation.reportError("Shifting to " + gear, false);
        state = State.STOP_MOTOR;
    }

    @Override
    protected void execute() {
        switch (state) {
            case STOP_MOTOR:
                initialLeftSpeed = driveTrain.getLeftSpeed();
                initialRightSpeed = driveTrain.getRightSpeed();
                driveTrain.tankDrive(0, 0, true);
                endTime = System.currentTimeMillis() + Constants.Shifter.STOP_MOTOR_TIME;
                state = State.WAIT_FOR_MOTOR;
                break;
            case WAIT_FOR_MOTOR:
                if (System.currentTimeMillis() >= endTime) state = State.SHIFT;
                break;
            case SHIFT:
                shifter.shift(gear, auto);
                endTime = System.currentTimeMillis() + Constants.Shifter.SHIFT_TIME;
                state = State.WAIT_FOR_SHIFT;
                break;
            case WAIT_FOR_SHIFT:
                if (System.currentTimeMillis() >= endTime) state = State.START_MOTOR;
                break;
            case START_MOTOR:
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

    public enum State {
        STOP_MOTOR,
        WAIT_FOR_MOTOR,
        SHIFT,
        WAIT_FOR_SHIFT,
        START_MOTOR,
        DONE;
    }

}
