package org.frc5687.steamworks.protobot.commands.actions;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5687.steamworks.protobot.Constants;
import org.frc5687.steamworks.protobot.subsystems.Shifter;

import static org.frc5687.steamworks.protobot.Robot.driveTrain;
import static org.frc5687.steamworks.protobot.Robot.shifter;

/**
 * Created by Baxter on 3/26/2017.
 */
public class RunShifterAutomatically extends Command {

    private long waitPeriodEndTime;

    public RunShifterAutomatically() {

    }

    @Override
    protected void execute() {
        if (System.currentTimeMillis() > waitPeriodEndTime) {
            switch (shifter.getGear()) {
                case kForward:
                    break;
                case kReverse:
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    private void shift(Shifter.Gear gear) {
        waitPeriodEndTime = System.currentTimeMillis() + Constants.Shifter.WAIT_PERIOD;
        Command shift = new Shift(gear);
        shift.start();
    }

}
