package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.omg.CORBA.SystemException;

import static org.frc5687.steamworks.protobot.Robot.pincers;

public class ClosePincers extends Command {

    private long endMillis;
    public ClosePincers() {
        requires(pincers);
    }

    @Override
    protected void initialize() {
        endMillis = System.currentTimeMillis() + Constants.Pincers.CLOSE_DELAY;
        DriverStation.reportError("Closing Pincers", false);
    }

    @Override
    protected void execute() {
        pincers.close();
    }

    @Override
    protected boolean isFinished() {
        return pincers.isClosed() && System.currentTimeMillis() > endMillis;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
