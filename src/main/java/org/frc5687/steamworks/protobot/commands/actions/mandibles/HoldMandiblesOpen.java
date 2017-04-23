package org.frc5687.steamworks.protobot.commands.actions.mandibles;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;

import static org.frc5687.steamworks.protobot.Robot.mandibles;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Created by Baxter on 2/28/2017.
 */
public class HoldMandiblesOpen extends Command {

    private long millis;
    private long endTime;

    public HoldMandiblesOpen() {
        this(0);
    }

    public HoldMandiblesOpen(long millis) {

        requires(mandibles);
        this.millis = millis;

    }

    @Override
    protected void initialize() {
        endTime = System.currentTimeMillis() + millis;
    }

    @Override
    protected void execute() {
        mandibles.setSpeed(Constants.pickConstant(Constants.Mandibles.HOLD_OPEN_SPEED_TONY, Constants.Mandibles.HOLD_OPEN_SPEED_RHODY));
    }

    @Override
    protected void end() {
        DriverStation.reportError("HoldMandiblesOpen done (" + millis + ")", false);
    }

    @Override
    protected boolean isFinished() {
        return System.currentTimeMillis() > endTime && (DriverStation.getInstance().isAutonomous() || !oi.isEjectGearPressed());
    }
}
