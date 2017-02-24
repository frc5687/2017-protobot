package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.oi;

public class CloseMandibles extends Command {

    public enum State {
        CLOSE,
        CLAMP,
        WIGGLE_OUT,
        WIGGLE_IN;
    }

    private State state;
    private long endTime;
    private long switchTime;

    public CloseMandibles() {
        requires(mandibles);
    }

    protected void initialize(){
        state = State.CLOSE;
        endTime = System.currentTimeMillis() + Constants.GearHandler.CLOSE_TIME;
    }
    protected void execute() {
        switch (state) {
            case CLOSE:
                mandibles.close();
                if(System.currentTimeMillis() >= endTime) state = State.CLAMP;
                return;
            case CLAMP:
                mandibles.clamp();
                if(oi.isGearWigglePressed()) {
                    state = State.WIGGLE_OUT;
                    switchTime = System.currentTimeMillis() + Constants.GearHandler.WIGGLE_OUT_TIME;
                }
                return;
            case WIGGLE_OUT:
                mandibles.wiggleOut();
                if (!oi.isGearWigglePressed()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.GearHandler.WIGGLE_OUT_TIME;
                    state = State.WIGGLE_IN;
                }
                return;
            case WIGGLE_IN:
                mandibles.wiggleIn();
                if (!oi.isGearWigglePressed()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.GearHandler.WIGGLE_IN_TIME;
                    state = State.WIGGLE_OUT;
                }
                return;
        }
        mandibles.close();
    }

    protected boolean isFinished(){
        return false;
    }

    protected void end() {
        mandibles.stop();
    }

    protected void interrupted() {
        end();
    }

}
