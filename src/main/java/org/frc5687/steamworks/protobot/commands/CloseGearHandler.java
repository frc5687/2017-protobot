package org.frc5687.steamworks.protobot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.gearHandler;

/**
 * Created by Caleb on 1/21/2017.
 */
public class CloseGearHandler extends Command {

    public enum State {
        CLOSE,
        CLAMP;
    }

    private State state;
    private long endTime;

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
