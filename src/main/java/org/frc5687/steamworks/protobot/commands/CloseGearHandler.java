package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Caleb on 1/21/2017.
 */
public class CloseGearHandler extends Command {

    public enum State {
        CLOSE,
        CLAMP,
        WIGGLE_OUT,
        WIGGLE_IN;
    }

    private State state;
    private long endTime;
    private long switchTime;

    public  CloseGearHandler() {
        requires(gearHandler);
    }

    protected void initialize(){
        state = State.CLOSE;
        endTime = System.currentTimeMillis() + Constants.GearHandler.CLOSE_TIME;
    }
    protected void execute() {
        switch (state) {
            case CLOSE:
                gearHandler.close();
                if(System.currentTimeMillis() >= endTime) state = State.CLAMP;
                return;
            case CLAMP:
                gearHandler.clamp();
                if(oi.isGearWigglePressed()) {
                    state = State.WIGGLE_OUT;
                    switchTime = System.currentTimeMillis() + Constants.GearHandler.wiggleOutTime;
                }
                return;
            case WIGGLE_OUT:
                gearHandler.wiggleOut();
                if (!oi.isGearWigglePressed()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.GearHandler.wiggleOutTime;
                    state = State.WIGGLE_IN;
                }
                return;
            case WIGGLE_IN:
                gearHandler.wiggleIn();
                if (!oi.isGearWigglePressed()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.GearHandler.wiggleInTime;
                    state = State.WIGGLE_OUT;
                }
                return;
        }
        gearHandler.close();
    }

    protected boolean isFinished(){
        return false;
    }

    protected void end() {
        gearHandler.stop();
    }

    protected void interrupted() {
        end();
    }
}
