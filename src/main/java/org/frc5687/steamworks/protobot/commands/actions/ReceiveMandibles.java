package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.oi;
import static org.frc5687.steamworks.protobot.Robot.pdp;

/**
 * Default command for the mandibles system.  Starts by closing the the mandibles until the hard-stop is hit (based on amp draw).
 * Then brakes the motor using the Victor.
 */
public class ReceiveMandibles extends Command {

    private State state;
    private long endTime;
    private long switchTime;

    public ReceiveMandibles() {
        requires(mandibles);
    }

    @Override
    protected void initialize() {
        state = State.CLOSE;
        endTime = System.currentTimeMillis() + Constants.Mandibles.CLOSE_TIME;
    }

    @Override
    protected void execute() {
        switch (state) {
            case CLOSE:
                mandibles.close();
                if (System.currentTimeMillis() >= endTime || pdp.getMandiblesAmps() > Constants.Mandibles.THRESHOLD_AMPS) { state = State.CLAMP; }
                return;
            case CLAMP:
                mandibles.setSpeed(0);
                if (oi.isGearWigglePressed()) {
                    state = State.WIGGLE_OUT;
                    switchTime = System.currentTimeMillis() + Constants.Mandibles.WIGGLE_OUT_TIME;
                }
                return;
            case WIGGLE_OUT:
                mandibles.wiggleOut();
                if (!oi.isGearWigglePressed()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.Mandibles.WIGGLE_OUT_TIME;
                    state = State.WIGGLE_IN;
                }
                return;
            case WIGGLE_IN:
                mandibles.wiggleIn();
                if (!oi.isGearWigglePressed()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.Mandibles.WIGGLE_IN_TIME;
                    state = State.WIGGLE_OUT;
                }
                return;
        }
        mandibles.close();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        mandibles.stop();
    }

    @Override
    protected void interrupted() {
        end();
    }

    public enum State {
        CLOSE,
        CLAMP,
        WIGGLE_OUT,
        WIGGLE_IN;
    }

}
