package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.pincers;

public class RunPincersManually extends Command {

    private enum State {
        CATCH,
        STANDBY,
        RELEASE;
    }

    private enum MotorState {
        UP,
        DOWN,
        RAISE,
        LOWER;
    }

    private enum PistonState {
        OPEN,
        CLOSED;
    }

    private State state;
    private MotorState motorState;
    private PistonState pistonState;
    private long endTime;

    public RunPincersManually() {
        requires(pincers);
    }

    @Override
    protected void initialize() {
        state = State.STANDBY;
        motorState = MotorState.RAISE;
        pistonState = PistonState.CLOSED;
    }

    @Override
    protected void execute() {
        switch (state) {
            case STANDBY:
                SmartDashboard.putString("State/State", "Standby");
                pistonState = PistonState.CLOSED;
                if (oi.isCatchGearPressed()) {
                    state = State.CATCH;
                    motorState = MotorState.LOWER;
                    endTime = System.currentTimeMillis() + 250;
                }
                if (oi.isReleaseGearPressed()) {
                    state = State.RELEASE;
                }
                break;
            case CATCH:
                SmartDashboard.putString("State/State", "Catch");
                if (motorState == MotorState.DOWN) {
                    pistonState = PistonState.OPEN;
                }
                if (!oi.isCatchGearPressed()) {
                    state = State.STANDBY;
                    motorState = MotorState.RAISE;
                    endTime = System.currentTimeMillis() + 250;
                }
                break;
            case RELEASE:
                SmartDashboard.putString("State/State", "Release");
                pistonState = PistonState.OPEN;
                if (!oi.isReleaseGearPressed()) {
                    state = State.STANDBY;
                }
                break;
        }

        switch (motorState) {
            case UP:
                SmartDashboard.putString("State/MotorState", "Up");
                pincers.setPincerSpeed(-0.1);
                break;
            case DOWN:
                SmartDashboard.putString("State/MotorState", "Down");
                pincers.setPincerSpeed(0.1);
                break;
            case RAISE:
                SmartDashboard.putString("State/MotorState", "Raise");
                pincers.setPincerSpeed(-0.4);
                if (System.currentTimeMillis() >= endTime) motorState = MotorState.UP;
                break;
            case LOWER:
                SmartDashboard.putString("State/MotorState", "Lower");
                pincers.setPincerSpeed(0.4);
                if (System.currentTimeMillis() >= endTime) motorState = MotorState.DOWN;
                break;
        }

        switch (pistonState) {
            case OPEN:
                SmartDashboard.putString("State/PistonState", "Open");
                if (pincers.isClosed()) pincers.open();
                break;
            case CLOSED:
                SmartDashboard.putString("State/PistonState", "Closed");
                if (pincers.isOpen()) pincers.close();
                break;
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        pincers.setPincerSpeed(0);
    }

}
