package org.frc5687.steamworks.protobot.commands.actions.mandibles;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.*;

/**
 * Default command for the mandibles system.  Starts by closing the the mandibles until the hard-stop is hit (based on amp draw).
 * Then brakes the motor using the Victor.
 */
public class ReceiveMandibles extends Command {

    protected State state;
    private long minMillis;
    private long endCloseMillis;
    private long switchTime;

    public ReceiveMandibles() {
        requires(mandibles);
    }

    @Override
    protected void initialize() {
        state = State.CLOSE;
        minMillis = System.currentTimeMillis() + Constants.Mandibles.MIN_CLOSE_TIME;
        endCloseMillis = System.currentTimeMillis() + Constants.Mandibles.MAX_CLOSE_TIME;
        ledStrip.setMandiblesOpen(false);
    }

    @Override
    protected void execute() {
        boolean gearPresent = mandibles.gearPresent();
        switch (state) {
            case CLOSE:
                mandibles.close();
                if (System.currentTimeMillis() >= endCloseMillis ||(System.currentTimeMillis() >= minMillis && pdp.getMandiblesAmps() > Constants.Mandibles.THRESHOLD_CLOSE_AMPS)) { state = State.CLAMP; }
                break;
            case CLAMP:
                mandibles.setSpeed(gearPresent ? Constants.pickConstant(Constants.Mandibles.CLAMP_SPEED_TONY, Constants.Mandibles.CLAMP_SPEED_RHODY) : Constants.pickConstant(Constants.Mandibles.RETAIN_SPEED_TONY, Constants.Mandibles.RETAIN_SPEED_RHODY));
                if (wiggle()) {
                    state = State.WIGGLE_OUT;
                    switchTime = System.currentTimeMillis() + Constants.Mandibles.WIGGLE_OUT_TIME;
                }
                break;
            case WIGGLE_OUT:
                mandibles.wiggleOut();
                if (!wiggle()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.Mandibles.WIGGLE_OUT_TIME;
                    state = State.WIGGLE_IN;
                }
                break;
            case WIGGLE_IN:
                mandibles.wiggleIn();
                if (!wiggle()) state = State.CLAMP;
                else if (System.currentTimeMillis() > switchTime) {
                    switchTime = System.currentTimeMillis() + Constants.Mandibles.WIGGLE_IN_TIME;
                    state = State.WIGGLE_OUT;
                }
                break;
            default:
                mandibles.setSpeed(gearPresent ? Constants.pickConstant(Constants.Mandibles.CLAMP_SPEED_TONY, Constants.Mandibles.CLAMP_SPEED_RHODY) : Constants.pickConstant(Constants.Mandibles.RETAIN_SPEED_TONY, Constants.Mandibles.RETAIN_SPEED_RHODY));
        }
        SmartDashboard.putString("Mandibles/ReceiveState", state.toString());
        SmartDashboard.putString("Mandibles/millis", Long.toString(System.currentTimeMillis()));
    }

    protected boolean wiggle() {
        return oi.isGearWigglePressed();
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
